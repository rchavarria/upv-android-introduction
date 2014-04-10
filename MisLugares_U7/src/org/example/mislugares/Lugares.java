package org.example.mislugares;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Lugares {

	public static final String TAG = "TagLugares";
	public static List<Lugar> vectorLugares = ejemploLugares();
	public static GeoPunto posicionActual = new GeoPunto(0, 0);

	private static LugaresBD lugaresBD;

	public static void indicializaBD(Context contexto) {
		lugaresBD = new LugaresBD(contexto);
	}

	public static Cursor listado() {
		SQLiteDatabase bd = lugaresBD.getReadableDatabase();
		return bd.rawQuery("SELECT * FROM lugares", null);
	}

	public Lugares() {
		vectorLugares = ejemploLugares();
	}

	public static Lugar elemento(int id) {
	    Lugar lugar = null;
	    SQLiteDatabase bd = lugaresBD.getReadableDatabase();
	    Cursor cursor = bd.rawQuery("SELECT * FROM lugares WHERE _id = " + id, null);
	    if (cursor.moveToNext()){
	        lugar = new Lugar();
	        lugar.setNombre(cursor.getString(1));
	        lugar.setDireccion(cursor.getString(2));
	        lugar.setPosicion(new GeoPunto(cursor.getDouble(3), cursor.getDouble(4)));
	        lugar.setTipo(TipoLugar.values()[cursor.getInt(5)]);
	        lugar.setFoto(cursor.getString(6));
	        lugar.setTelefono(cursor.getInt(7));
	        lugar.setUrl(cursor.getString(8));
	        lugar.setComentario(cursor.getString(9));
	        lugar.setFecha(cursor.getLong(10));
	        lugar.setValoracion(cursor.getFloat(11));
	    }
	    cursor.close();
	    bd.close();
	    return lugar;
	}
	public static void anyade(Lugar lugar) {
		vectorLugares.add(lugar);
	}

	public static int nuevo() {
		Lugar lugar = new Lugar();
		vectorLugares.add(lugar);
		return vectorLugares.size() - 1;
	}

	public static void borrar(int id) {
		vectorLugares.remove(id);
	}

	public static int size() {
		return vectorLugares.size();
	}

	public static ArrayList<Lugar> ejemploLugares() {
		ArrayList<Lugar> lugares = new ArrayList<Lugar>();
		lugares.add(new Lugar("Escuela Polit�cnica Superior de Gand�a",
				"C/ Paranimf, 1 46730 Gandia (SPAIN)", -0.166093, 38.995656,
				TipoLugar.EDUCACION, 962849300, "http://www.epsg.upv.es",
				"Uno de los mejores lugares para formarse.", 3));

		lugares.add(new Lugar("Al de siempre",
				"P.Industrial Junto Mol� Nou - 46722, Benifla (Valencia)",
				-0.190642, 38.925857, TipoLugar.BAR, 636472405, "",
				"No te pierdas el arroz en calabaza.", 3));

		lugares.add(new Lugar("androidcurso.com", "ciberespacio", 0.0, 0.0,
				TipoLugar.EDUCACION, 962849300, "http://androidcurso.com",
				"Amplia tus conocimientos sobre Android.", 5));

		lugares.add(new Lugar(
				"Barranco del Infierno",
				"V�a Verde del r�o Serpis. Villalonga (Valencia)",
				-0.295058,
				38.867180,
				TipoLugar.NATURALEZA,

				0,
				"http://sosegaos.blogspot.com.es/2009/02/lorcha-villalonga-via-verde-del-rio.html",
				"Espectacular ruta para bici o andar", 4));

		lugares.add(new Lugar("La Vital",
				"Avda. de La Vital, 0 46701 Gand�a (Valencia)", -0.1720092,
				38.9705949, TipoLugar.COMPRAS, 962881070,
				"http://www.lavital.es/", "El t�pico centro comercial", 2));

		return lugares;

	}

	public static List<String> nameList() {
		List<Lugar> places = ejemploLugares();
		List<String> names = new ArrayList<String>(places.size());

		for (Lugar place : places) {
			names.add(place.getNombre());
		}

		return names;
	}
	
	public static void actualizaLugar(int id, Lugar lugar){
		lugar = Lugares.elemento((int) id);
		
	    SQLiteDatabase bd = lugaresBD.getWritableDatabase();
	    bd.execSQL("UPDATE lugares SET nombre = '"+ lugar.getNombre() +
	        "', direccion = '" + lugar.getDireccion() +
	        "', longitud = " + lugar.getPosicion().getLongitud()  +
	        " , latitud = " + lugar.getPosicion().getLatitud()  +
	        " , tipo = " + lugar.getTipo().ordinal() +
	        " , foto = '" + lugar.getFoto()  +
	        "', telefono = " + lugar.getTelefono()  +
	        " , url = '" + lugar.getUrl()  +
	        "', comentario = '" + lugar.getComentario()  +
	        "', fecha = " + lugar.getFecha()  +
	        " , valoracion = " + lugar.getValoracion()  +
	        " WHERE _id = "+ id);
	        bd.close();
	}
}