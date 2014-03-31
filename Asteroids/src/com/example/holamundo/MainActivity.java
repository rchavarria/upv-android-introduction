package com.example.holamundo;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private BaseAdapter adapter;
	private MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adapter = new PlacesAdapter(this);
		setListAdapter(adapter);
		
		 Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
		 
		 // MediaPlayer to play sounds
		 mp = MediaPlayer.create(this, R.raw.audio);
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

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);

		Intent intent = new Intent(this, VistaLugar.class);
		intent.putExtra("id", id);
		startActivity(intent);
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
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		String s = "notificaciones: " + pref.getBoolean("notificaciones", true)
				+ ", distancia mínima: " + pref.getString("distancia", "?");
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
		
		mp.start();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPause() {
		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();

		Toast.makeText(this, "pausing sounds", Toast.LENGTH_SHORT).show();
		mp.pause();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		if(mp != null) {
			int position = mp.getCurrentPosition();
			outState.putInt("position", position);
		}
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		
		if(state != null && mp != null) {
			int position = state.getInt("position");
			mp.seekTo(position);
		}
	}
}
