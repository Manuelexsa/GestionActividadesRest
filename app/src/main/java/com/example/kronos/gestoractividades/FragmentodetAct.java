package com.example.kronos.gestoractividades;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentodetAct extends Fragment {
    private static TextView idprofesor, tipo, fechai, fechaf, lugari, lugarf, descripcion, alumno;

    public FragmentodetAct() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_det_act, container, false);

        tipo = (TextView) v.findViewById(R.id.tvdestip);
        fechai = (TextView) v.findViewById(R.id.tvdethi);
        fechaf = (TextView) v.findViewById(R.id.tvdethf);
        lugari = (TextView)v.findViewById(R.id.tvdetli);
        lugarf = (TextView)v.findViewById(R.id.tvdetlf);
        descripcion = (TextView)v.findViewById(R.id.tvdetdesc);
        alumno = (TextView)v.findViewById(R.id.tvdestalum);
        return v;
    }

    public static void mostrarActividad(Actividad a){
        Log.v("acti",a.toString());
        tipo.setText(a.getTipo());
        fechai.setText(a.getFechai());
        fechaf.setText(a.getFechaf());
        lugari.setText(a.getLugari());
        lugarf.setText(a.getLugarf());
        descripcion.setText(a.getDescripcion());
        alumno.setText(a.getAlumno());
    }



}
