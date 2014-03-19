package com.example.holamundo;

import java.text.DateFormat;
import java.util.Date;

import org.example.mislugares.Lugar;
import org.example.mislugares.Lugares;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class VistaLugar extends Activity {
	private long id;
	private Lugar lugar;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
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
			return true;
		case R.id.accion_llegar:
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
	
	private void deleteAction(final int id) {
//		final EditText entrada = new EditText(this);
//        entrada.setText("0");
        new AlertDialog.Builder(this)
           .setTitle("Borrado de lugar")
           .setMessage("�Desea usted borrar este lugar?")
//           .setView(entrada)
           .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int whichButton) {
                   Lugares.borrar(id);
                   finish();
               }})
           .setNegativeButton("Cancelar", null)
           .show();
	}
}