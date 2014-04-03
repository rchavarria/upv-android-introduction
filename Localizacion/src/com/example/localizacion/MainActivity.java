package com.example.localizacion;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener {
	
   private static final long TIEMPO_MIN = 10 * 1000 ; // 10 segundos
   private static final long DISTANCIA_MIN = 5 ; // 5 metros
   private static final String[] A = { "n/d", "preciso", "impreciso" };
   private static final String[] P = { "n/d", "bajo", "medio","alto" };
   private static final String[] E = { "fuera de servicio", "temporalmente no disponible ","disponible" };

   private LocationManager manejador;
   private String proveedor;
   private TextView salida;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      salida = (TextView) findViewById(R.id.salida);

      manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
      log("Proveedores de localización: \n ");
      muestraProveedores();

      Criteria criterio = new Criteria();
      Criterio.setCostAllowed(false);
      Criterio.setAltitudeRequired(false);
      Criterio.setAccuracy(Criteria.ACCURACY_FINE);

      proveedor = manejador.getBestProvider(criterio, true);

      log("Mejor proveedor: " + proveedor + "\n");
      log("Comenzamos con la última localización conocida:");

      Location localizacion = manejador.getLastKnownLocation(proveedor);
      muestraLocaliz(localizacion);
   }

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
	}
}
