package es.rchavarria.einarcounts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CounterActivity extends Activity {

	private CounterController controller = new CounterController();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter);
	}
	
	public void counterTouched(View v) {
		TextView txt = (TextView) findViewById(R.id.txtCounter);
		
		txt.setText(controller.incrementCount());
		txt.invalidate();
	}
}
