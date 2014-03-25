package es.rchavarria.einarcounts;

public class CounterController {

	private int counter = 0;
	
	public String next() {
		counter++;
		
		if(counter > 10) {
			counter = 0;
			return "#";
		}
		
		return Integer.valueOf(counter).toString();
	}

}
