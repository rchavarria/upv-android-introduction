package es.rchavarria.activitiescommunication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VerificationActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verification);

		Bundle extras = getIntent().getExtras();
		String name = extras.getString("name");
		TextView txt = (TextView) findViewById(R.id.txtQuestion);
		txt.setText("Hola " + name + "! ¿Acepta usted las condiciones?");
	}

	public void accept(View v) {
		Intent i = new Intent();
		i.putExtra("result", true);
		setResult(RESULT_OK, i);
		finish();
	}
	
	public void reject(View v) {
		Intent i = new Intent();
		i.putExtra("result", false);
		setResult(RESULT_OK, i);
		finish();
	}
}
