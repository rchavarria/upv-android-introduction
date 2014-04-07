package com.example.holamundo;

import org.example.mislugares.GeoPunto;
import org.example.mislugares.Lugar;
import org.example.mislugares.Lugares;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends FragmentActivity implements OnInfoWindowClickListener {

	private GoogleMap mapa;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		
		Log.d(Lugares.TAG, "searching for id : " + R.id.mapa);
		mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMap();
		Log.d(Lugares.TAG, "id found : " + R.id.mapa);
		mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mapa.setMyLocationEnabled(true);
		mapa.getUiSettings().setZoomControlsEnabled(true);
		mapa.getUiSettings().setCompassEnabled(true);
		
		if (Lugares.vectorLugares.size() > 0) {
			GeoPunto p = Lugares.vectorLugares.get(0).getPosicion();
			mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(
					new LatLng(p.getLatitud(), p.getLongitud()), 12));
		}

		for (Lugar lugar : Lugares.vectorLugares) {
			GeoPunto p = lugar.getPosicion();
			if (p != null && p.getLatitud() != 0) {
				BitmapDrawable iconoDrawable = (BitmapDrawable) getResources().getDrawable(lugar.getTipo().getRecurso());
				Bitmap iGrande = iconoDrawable.getBitmap();
				Bitmap icono = Bitmap.createScaledBitmap(iGrande,
						iGrande.getWidth() / 7, iGrande.getHeight() / 7, false);
				mapa.addMarker(new MarkerOptions()
						.position(new LatLng(p.getLatitud(), p.getLongitud()))
						.title(lugar.getNombre()).snippet(lugar.getDireccion())
						.icon(BitmapDescriptorFactory.fromBitmap(icono)));
			}

		}

	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

}
