package com.example.holamundo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
			launchAbout(null);
			break;
		case R.id.config:
			launchPreferences(null);
			break;
		}
		return true;
	}
	
	public void launchAbout(View view) {
		Intent i = new Intent(this, AboutActivity.class);
		startActivity(i);
	}
	
	public void launchPreferences(View view) {
		Intent i = new Intent(this, Preferences.class);
		startActivity(i);
	}
}
