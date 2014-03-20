package es.rchavarria.einarcounts;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CounterControllerTest {

	private CounterController controller;

	@Before
	public void setUp() {
		controller = new CounterController();
	}
	
	@Test
	public void incrementsFrom1To2() {
		assertEquals("2", controller.incrementCounter("1"));
	}
	
	@Test
	public void incrementsFrom2To3() {
		assertEquals("3", controller.incrementCounter("2"));
	}
	
	@Test
	public void incrementsFrom9To10() {
		assertEquals("10", controller.incrementCounter("9"));
	}
	
	@Test
	public void incrementsFrom10To1() {
		assertEquals("1", controller.incrementCounter("10"));
	}

}
