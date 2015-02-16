package com.example.kronos.gestoractividades;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kronos on 14/02/2015.
 */
public class Adaptador extends ArrayAdapter<Actividad> {

    private Context contexto;
    private ArrayList<Actividad> lista;
    private int recurso;
    private static LayoutInflater i;

    static class ViewHolder{
        public TextView idprofesor, tipo, fechai, fechaf, lugari, lugarf, descripcion, alumno;
        public int posicion;
    }

    public Adaptador(Context context, int resource, ArrayList <Actividad> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.lista = objects;
        this.recurso = resource;
        this.i = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            convertView = i.inflate(recurso, null);
            vh = new ViewHolder();
            vh.tipo = (TextView) convertView.findViewById(R.id.tvActividad);
            vh.fechai = (TextView) convertView.findViewById(R.id.tvdethi);
            convertView.setTag(vh);
        }
        else{
            vh=(ViewHolder) convertView.getTag();
        }
        vh.posicion=position;
        Log.v("tipo",lista.get(position).getTipo());
        vh.tipo.setText(lista.get(position).getTipo().toString());
        return convertView;
    }
}
