package com.example.holamundo;

import org.example.mislugares.Lugares;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	private BaseAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adapter = new ArrayAdapter<String>(
				this, 
				android.R.layout.simple_list_item_1,
				Lugares.nameList());
		setListAdapter(adapter);
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
		case R.id.search:
			showPreferences(null);
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

	public void showPreferences(View v) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String s = "notificaciones: " + pref.getBoolean("notificaciones", true)
				+ ", distancia m�nima: " + pref.getString("distancia", "?");
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
}
