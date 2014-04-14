package com.example.holamundo;

import org.example.mislugares.Lugares;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SelectorFragment extends Fragment implements OnItemClickListener {

	@Override
	public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle savedInstanceState) {
		View vista = inflador.inflate(
				R.layout.fragment_selector, contenedor, false);
		return vista;
	}

	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		BaseAdapter adaptador = new AdaptadorCursorLugares(getActivity(),
				Lugares.listado());
		ListView listView = (ListView) getView().findViewById(R.id.listView);
		listView.setAdapter(adaptador);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View vista, int posicion, long id) {
		Intent intent = new Intent(getActivity(), VistaLugar.class);
		intent.putExtra("id", id);
		startActivity(intent);
	}
}
