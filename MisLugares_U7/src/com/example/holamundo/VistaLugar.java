package com.example.holamundo;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.example.mislugares.Lugar;
import org.example.mislugares.Lugares;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class VistaLugar extends Activity {
	final static int RESULTADO_EDITAR= 1;
	final static int RESULTADO_GALERIA= 2;
	final static int RESULTADO_FOTO= 3;
	
	private long id;
	private Lugar lugar;
	private Uri uriFoto;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
                
        actualizarVistas();
    }
	
	private void actualizarVistas() {
		lugar = Lugares.elemento((int) id);
		
        TextView nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView tipo = (TextView) findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());
        TextView direccion = (TextView) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        TextView telefono = (TextView) findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        TextView url = (TextView) findViewById(R.id.url);
        url.setText(lugar.getUrl());
        TextView comentario = (TextView) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        TextView fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                           new Date(lugar.getFecha())));
        TextView hora = (TextView) findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                           new Date(lugar.getFecha())));
        RatingBar valoracion = (RatingBar) findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
            new OnRatingBarChangeListener() {
                @Override public void onRatingChanged(RatingBar ratingBar,
                                                float valor, boolean fromUser) {
                    lugar.setValoracion(valor);
                }
        });
        
        ImageView image = (ImageView) findViewById(R.id.foto);
        ponerFoto(image, lugar.getFoto());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.vista_lugar, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.accion_compartir:
			Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, lugar.getNombre() + " - "+ lugar.getUrl());
            startActivity(intent);
			return true;
		case R.id.accion_llegar:
			verMapa(null);
			return true;
		case R.id.accion_editar:
			return true;
		case R.id.accion_borrar:
			deleteAction((int) id);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		ImageView image = (ImageView) findViewById(R.id.foto);
		
		if(requestCode == RESULTADO_GALERIA && resultCode == Activity.RESULT_OK) {
			lugar.setFoto(data.getDataString());
		    ponerFoto(image, lugar.getFoto());
		    Lugares.actualizaLugar((int) id, lugar);
		
		} else if(requestCode == RESULTADO_FOTO && resultCode == Activity.RESULT_OK && lugar!=null && uriFoto!=null) {
	       lugar.setFoto(uriFoto.toString());
	       Lugares.actualizaLugar((int) id, lugar);
	       ponerFoto(image, lugar.getFoto());
		}
	}
	
	private void deleteAction(final int id) {
//		final EditText entrada = new EditText(this);
//        entrada.setText("0");
        new AlertDialog.Builder(this)
           .setTitle("Borrado de lugar")
           .setMessage("¿Desea usted borrar este lugar?")
//           .setView(entrada)
           .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int whichButton) {
                   Lugares.borrar(id);
                   finish();
               }})
           .setNegativeButton("Cancelar", null)
           .show();
	}
	
	private void verMapa(View v) {
		Uri uri;
        double lat = lugar.getPosicion().getLatitud();
        double lon = lugar.getPosicion().getLongitud();
        if (lat != 0 || lon != 0) {
               uri = Uri.parse("geo:" + lat + "," + lon);
        } else {
               uri = Uri.parse("geo:0,0?q=" + lugar.getDireccion());
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
	}
	
	public void llamadaTelefono(View view) {
		startActivity(new Intent(Intent.ACTION_DIAL, 
				Uri.parse("tel:" + lugar.getTelefono())));
	}

	public void pgWeb(View view) {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(lugar.getUrl())));
	}

	public void galeria(View view) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");

		startActivityForResult(intent, RESULTADO_GALERIA);
	}
	
	protected void ponerFoto(ImageView imageView, String uri) {
		if (uri != null) {
			imageView.setImageURI(Uri.parse(uri));
		} else {
			imageView.setImageBitmap(null);
		}
	}
	
	public void tomarFoto(View view) {
	    String fileUri = Environment.getExternalStorageDirectory() + 
	    		File.separator +
	    		"img_" + 
	    		(System.currentTimeMillis() / 1000) + 
	    		".jpg";
	    uriFoto = Uri.fromFile(new File(fileUri));
	    
	    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
	    startActivityForResult(intent, RESULTADO_FOTO);
	}
	
	public void eliminarFoto(View v) {
		lugar.setFoto(null);
		ImageView image = (ImageView) findViewById(R.id.foto);
		ponerFoto(image, null);
	}
}