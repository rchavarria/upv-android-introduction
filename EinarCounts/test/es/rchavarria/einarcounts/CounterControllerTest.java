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
	public void incrementsFromNothingTo1() {
		assertEquals("1", controller.next("#"));
	}
	
	@Test
	public void incrementsFrom1To2() {
		assertEquals("2", controller.next("1"));
	}
	
	@Test
	public void incrementsFrom2To3() {
		assertEquals("3", controller.next("2"));
	}
	
	@Test
	public void incrementsFrom9To10() {
		assertEquals("10", controller.next("9"));
	}
	
	@Test
	public void incrementsFrom10ToAString() {
		assertEquals("#", controller.next("10"));
	}

}
