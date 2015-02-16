package com.example.kronos.gestoractividades;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collections;


public class FragmentoListAct extends Fragment {

    private ListView lvActividades;
    private static ArrayList<Actividad> alActividades;
    private static Adaptador ad;
    View v;

    private FragmentodetAct fd;
    private FragmentoListAct fl;
    public static final String URLBASE = "http://ieszv.x10.bz/restful/api/";
    private final int DETALLE = 0;

    public FragmentoListAct() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case DETALLE:
                    Actividad a =(Actividad) data.getSerializableExtra("actividad");
                    FragmentodetAct.mostrarActividad(a);
                    break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragmento_list_act, container, false);

        return v;
    }


}
