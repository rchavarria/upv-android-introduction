package es.rchavarria.einarcounts;

public class CounterController {

	public String incrementCounter(String previousCounter) {
		int number = Integer.parseInt(previousCounter);
		int next = number + 1;
		if(next > 10) {
			next = 1;
		}
		
		return Integer.valueOf(next).toString();
	}

}
