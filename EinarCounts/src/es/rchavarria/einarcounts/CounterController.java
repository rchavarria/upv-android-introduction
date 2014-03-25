package es.rchavarria.einarcounts;

public class CounterController {

	public String next(String previousCounter) {
		int number = 0;
		try {
			number = Integer.parseInt(previousCounter);
		} catch(Exception e) {}
		
		int next = number + 1;
		
		return (next <= 10) ? Integer.valueOf(next).toString() : "#";
	}

}
