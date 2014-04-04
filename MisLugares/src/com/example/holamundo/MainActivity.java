package com.example.holamundo;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
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
	private LocationManager manejador;
	private Location mejorLocaliz;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adapter = new PlacesAdapter(this);
		setListAdapter(adapter);
		
		manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			actualizaMejorLocaliz(manejador.getLastKnownLocation(LocationManager.GPS_PROVIDER));
		}
		if (manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			actualizaMejorLocaliz(manejador.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
		}
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

	@Override
	protected void onResume() {
		super.onResume();
		activarProveedores();
	}

	private void activarProveedores() {
		if (manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			manejador.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20 * 1000, 5, this);
		}

		if (manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10 * 1000, 10, this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		manejador.removeUpdates(this);
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
}
