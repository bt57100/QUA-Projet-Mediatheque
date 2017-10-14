package util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConsole {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadLineNull() {
		String prompt = "Cause IOException";
		Console.printPrompt(prompt);
		assertNull("NOK readLine", Console.readLine());
	}

	@Test
	public void testReadLineString() {
		String prompt = "Enter prompt";
		assertTrue("NOK lineString", Console.readLine(prompt).equals("prompt"));
	}

	@Test
	public void testReadInt() {
		int value = 0;
		String prompt = "Enter 0";
		assertEquals("NOK readInt", value, Console.readInt(prompt));
	}

	@Test
	public void testReadDouble() {
		double value = 0.1;
		String prompt = "Enter 0.1";
		assertEquals("NOK readInt", value, Console.readDouble(prompt), 0.001);
	}

}
