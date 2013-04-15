Memento library
===============

This library provides support for storing typed properties. Unlike
`java.util.Properties`, it stores type information (it supportes
most of the primitive types and arrays), and allows arranging
the data in a hierarchy to express complex data structures. Thus
it is more powerful than string-string mappings and ini files,
but not so heavy-weight as databases.

Using
-----

The library resides in the central Maven repository with
group ID `hu.kazocsaba` and artifact ID `memento`. If
you use a project management system which can fetch dependencies
from there, you can just add the library as a dependency. E.g.
in Maven:

    <dependency>
        <groupId>hu.kazocsaba</groupId>
        <artifactId>memento</artifactId>
        <version>1.0.0</version>
    </dependency>

You can also browse the [online javadoc](http://kazocsaba.github.com/memento/apidocs/index.html).

Examples
--------

Suppose we want to store instances of the following class:

    class Person {
        String name;
        int age;
        double iq;
    }

Persistence can be implemented so:

    void savePerson(Person person, Memento memento) {
        memento.putString("name", person.name);
        memento.putInt("age", person.age);
        memento.putDouble("intelligence quotient", person.iq);
    }
    
    Person loadPerson(Memento memento) throws MementoFormatException {
        Person person=new Person();
        person.name=memento.getString("name");
        person.age=memento.getInt("age");
        person.iq=memento.getDouble("intelligence quotient");
    }

### Handling library evolution

If we update the data structure with an additional method, we can still load
mementos saved in the previous version if we handle the missing property:

    class Person {
        String name;
        int age;
        double iq;
        String language;
    }
    
    void savePerson(Person person, Memento memento) {
        memento.putString("name", person.name);
        memento.putInt("age", person.age);
        memento.putDouble("intelligence quotient", person.iq);
    }
    
    Person loadPerson(Memento memento) throws MementoFormatException {
        Person person=new Person();
        person.name=memento.getString("name");
        person.age=memento.getInt("age");
        person.iq=memento.getDouble("intelligence quotient");
        
        // if we can supply a default value:
        person.language=memento.getString("language", "English");
        
        // otherwise check if the property is missing and handle it
        if (memento.hasProperty("language")) {
            person.language=memento.getString("language");
        } else {
            // handle missing property
        }
    }

### Complex data structures

Now suppose that we want to represent children of people too:

    class Person {
        String name;
        int age;
        double iq;
        List<Person> children;
    }
    
    void savePerson(Person person, Memento memento) {
        memento.putString("name", person.name);
        memento.putInt("age", person.age);
        memento.putDouble("intelligence quotient", person.iq);
        
        for (Person child: person.children)
            savePerson(child, memento.createChild());
    }
    
    Person loadPerson(Memento memento) throws MementoFormatException {
        Person person=new Person();
        person.name=memento.getString("name");
        person.age=memento.getInt("age");
        person.iq=memento.getDouble("intelligence quotient");
        
        for (Memento childMemento: memento) {
            Person child=loadPerson(childMemento);
            person.children.add(child);
        }
    }

If a memento needs to have multiple child mementos of different kinds, a string
can be used to identify the different memento types:

    void savePerson(Person person, Memento memento) {
        // ...
        
        for (Person child: person.children)
            savePerson(child, memento.createChild("child"));
         for (Account account: person.accounts)
            savePerson(child, memento.createChild("account"));
    }
    
    Person loadPerson(Memento memento) throws MementoFormatException {
        // ...
        
        for (Memento childMemento: memento) {
            if ("child".equals(childMemento.getType())) {
                // read and add child
            } else if ("account".equals(childMemento.getType())) {
                // read and add account details
            }
        }
    }