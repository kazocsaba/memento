package hu.kazocsaba.memento;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MementoStoreTest {
	private void testMementoStreamStore(Memento memento) throws IOException, MementoFormatException {
		ByteArrayOutputStream ba=new ByteArrayOutputStream();
		MementoStore.mementoToXmlStream(memento, ba);
		assertEquals(memento, MementoStore.xmlStreamToMemento(new ByteArrayInputStream(ba.toByteArray())));
		ba.reset();
		MementoStore.mementoToBinary(memento,ba);
		assertEquals(memento,MementoStore.binaryToMemento(new ByteArrayInputStream(ba.toByteArray())));
	}
	
	@Test
	public void testSingleMemento() throws Exception {
		testMementoStreamStore(new Memento());
		Memento compound=new Memento();
		compound.putString("key", "value");
		compound.putFloat("float key", 3.1415f);
		compound.putInt("integer key", 1555000);
		compound.putBoolean("boolTrue",true);
		compound.putBoolean("boolFalse",false);
		compound.putChar("char",'×');
		compound.putString("empty","");
		compound.putDouble("double key", 4.66d);
		compound.putLong("my long", -81111777733636L);

		testMementoStreamStore(compound);
	}
	@Test
	public void testTrickyStrings() throws Exception {
		Memento tricky=new Memento();
		tricky.putString("allspace", "   ");
		tricky.putString("spaceprefix", "  bla");
		tricky.putString("spacesuffix", "bla ");
		tricky.putString("markup", "<xml version=\"1.0 & 2.0\"/>");
		tricky.putString("key\ncontaining newline","value\ncontaining newline");
		tricky.createChild("type\ncontaining newline");
		tricky.createChild("");
		
		testMementoStreamStore(tricky);
	}
	@Test
	public void testHierarchy() throws Exception {
		Memento root=new Memento();
		root.putString("id","1");
		Memento child=root.createChild();
		child.putString("child","First");
		child=root.createChild("second type");
		assertEquals("second type",child.getType());
		child.putString("child", "Second");
		child=child.createChild("grandson type");
		child.putString("grandson", "yes");
		root.createChild().putString("child", "Third");
		
		testMementoStreamStore(root);
	}
	@Test
	public void testArrayProperties() throws Exception {
		Memento root=new Memento();
		root.putStringArray("empty",new String[0]);
		root.putStringArray("singleEmpty",new String[]{""});
		root.putStringArray("single",new String[]{"vagyok"});
		root.putStringArray("multi",new String[]{"Egy","","Két","Három"});
		root.putIntArray("emptyInt",new int[0]);
		root.putIntArray("singleInt",new int[]{-36});
		root.putIntArray("multiInt",new int[]{0,4,Integer.MAX_VALUE,-99});
		root.putByteArray("emptyByte",new byte[0]);
		root.putByteArray("singleByte",new byte[]{0});
		root.putByteArray("singleByte2",new byte[]{90});
		root.putByteArray("multiByte",new byte[]{0,1,2,3,5,7,9,10,44,66,89,120,-60,-20,-6,-1});
		root.putDoubleArray("doubles",new double[]{3.14,0});
		root.putDoubleArray("emptyDoubleArray",new double[]{});

		testMementoStreamStore(root);
	}

}
