package hu.kazocsaba.memento;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MementoTest {
	@Test
	public void testGetPropertyType() {
		Memento m = new Memento();
		m.putBoolean("key", true);
		assertEquals(Boolean.class, m.getPropertyType("key"));
		m.putChar("key", 'l');
		assertEquals(Character.class, m.getPropertyType("key"));
		m.putFloat("key", 4.6f);
		assertEquals(Float.class, m.getPropertyType("key"));
		m.putInt("key", 5);
		assertEquals(Integer.class, m.getPropertyType("key"));
		m.putLong("key", 58888888882L);
		assertEquals(Long.class, m.getPropertyType("key"));
		m.putString("key", "blah");
		assertEquals(String.class, m.getPropertyType("key"));
		m.putStringArray("key", new String[0]);
		assertEquals(String[].class, m.getPropertyType("key"));
		m.putIntArray("key", new int[0]);
		assertEquals(Integer[].class, m.getPropertyType("key"));
		m.putByteArray("key", new byte[0]);
		assertEquals(Byte[].class, m.getPropertyType("key"));
		m.putDouble("key", 5.7d);
		assertEquals(Double.class, m.getPropertyType("key"));
		m.putDoubleArray("key", new double[0]);
		assertEquals(Double[].class, m.getPropertyType("key"));
	}
}
