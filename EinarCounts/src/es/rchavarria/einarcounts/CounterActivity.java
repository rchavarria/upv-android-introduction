package es.rchavarria.einarcounts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CounterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter);
	}
	
	public void incrementCounter(View v) {
		TextView txt = (TextView) findViewById(R.id.txtCounter);
		
		String numberString = txt.getText().toString();
		int number = Integer.parseInt(numberString);
		
		String nextNumber = Integer.valueOf(number + 1).toString();
		txt.setText(nextNumber);
		txt.invalidate();
	}
}
