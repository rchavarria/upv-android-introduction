package org.example.mislugares;

public class GeoPunto {
	private double longitud, latitud; // En grados sexagesimales

	public GeoPunto(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	/** Transcribe el punto a String.
	 * @return un string con la parte entera y la imaginaria
	 */
	public String toString() {
		return new String("lat:"+latitud+" long:"+longitud);
	}

	/** Calcula la distancia de este punto a otro punto
	 * @param punto el punto del que calculamos su distancia a éste.
	 * @return la distancia en metros.
	 */
	public double distancia(GeoPunto punto) {
		final double RADIO_TIERRA = 6378000; // en metros
		double dLat = Math.toRadians(punto.latitud - latitud); /* en radianes, para usar en sin y cos */
		double dLong = Math.toRadians(punto.longitud - longitud);
		double a = Math.sin(dLat/2) *
				   Math.sin(dLat/2) + 
				   Math.sin(dLong/2) *
				   Math.sin(dLong/2) *
				   Math.cos(Math.toRadians(punto.latitud)) *
				   Math.cos(Math.toRadians(latitud));
		double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		return RADIO_TIERRA * c;
	}
}