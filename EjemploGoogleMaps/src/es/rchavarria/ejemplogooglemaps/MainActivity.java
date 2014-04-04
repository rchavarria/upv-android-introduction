package es.rchavarria.ejemplogooglemaps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapClickListener {

	private final LatLng UPV = new LatLng(39.481106, -0.340987);
	private GoogleMap mapa;
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		initMap();
	}

	private void initMap() {
		android.app.Fragment smf = getFragmentManager().findFragmentById(R.id.map);
		if (smf == null) {
			return;
		}
		
//		mapa = smf.getMap();
//		mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//		mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UPV, 15));
//		mapa.setMyLocationEnabled(true);
//		mapa.getUiSettings().setZoomControlsEnabled(false);
//		mapa.getUiSettings().setCompassEnabled(true);
//		mapa.addMarker(new MarkerOptions()
//			.position(UPV)
//			.title("UPV")
//			.snippet("Universidad Politécnica de Valencia")
//			.icon(BitmapDescriptorFactory
//			.fromResource(R.drawable.ic_launcher))
//			.anchor(0.5f, 0.5f));
//		mapa.setOnMapClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			
			return rootView;
		}
	}

	public void moveCamera(View view) {
		mapa.moveCamera(CameraUpdateFactory.newLatLng(UPV));
	}

	public void animateCamera(View view) {
		if (mapa.getMyLocation() != null)
			mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					mapa.getMyLocation().getLatitude(), mapa.getMyLocation()
							.getLongitude()), 15));
	}

	public void addMarker(View view) {
		mapa.addMarker(new MarkerOptions().position(
		new LatLng(mapa.getCameraPosition().target.latitude,
		mapa.getCameraPosition().target.longitude)));
	}

	@Override
	public void onMapClick(LatLng puntoPulsado) {
		if(mapa == null) {
			initMap();
		}
		
		if(mapa == null) {
			return;
		}
		
		mapa.addMarker(new MarkerOptions()
			.position(puntoPulsado)
			.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
	}
}
