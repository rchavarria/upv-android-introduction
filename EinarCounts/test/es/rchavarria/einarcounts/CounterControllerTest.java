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
	public void incrementsUpTo1() {
		checkIncrement("1");
	}
	
	@Test
	public void incrementsUpTo2() {
		checkIncrement("2");
	}
	
	@Test
	public void incrementsUpTo3() {
		checkIncrement("3");
	}
	
	@Test
	public void incrementsUpTo4() {
		checkIncrement("4");
	}
	
	@Test
	public void incrementsUpTo5() {
		checkIncrement("5");
	}
	
	@Test
	public void incrementsUpTo6() {
		checkIncrement("6");
	}
	
	@Test
	public void incrementsUpTo7() {
		checkIncrement("7");
	}
	
	@Test
	public void incrementsUpTo8() {
		checkIncrement("8");
	}
	
	@Test
	public void incrementsUpTo9() {
		checkIncrement("9");
	}
	
	@Test
	public void incrementsUpTo10() {
		checkIncrement("10");
	}
	
	@Test
	public void resetsWhenItReaches10() {
		checkIncrement("10");
		
		assertEquals("#", controller.incrementCount());
	}
	
	private void checkIncrement(String upTo) {
		int countUpTo = Integer.parseInt(upTo) - 1;
		for(int i = 0; i < countUpTo; i++) {
			controller.incrementCount();
		}
		
		assertEquals(upTo, controller.incrementCount());
	}

}
