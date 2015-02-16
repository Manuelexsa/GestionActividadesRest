package com.example.kronos.gestoractividades;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class DetalleActividad extends Activity {
    Actividad a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_detalle_actividad);
        a = (Actividad)getIntent().getExtras().getSerializable("actividad");
        FragmentodetAct.mostrarActividad(a);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.global, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.andiracti) {

            //borrar(a.getId());
            return true;
        }else if (id == R.id.anadirprof) {
            //borrar(a.getId());
            return true;
        }else if (id == R.id.anadirgru) {
            //borrar(a.getId());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void borrar(String id){
        Toast.makeText(this, a.toString() + "-----------" + a.getId(), Toast.LENGTH_LONG).show();
        DeleteRestFul dr = new DeleteRestFul();
        dr.execute(id);
    }

    private class DeleteRestFul extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... s) {
            String r = ClienteRestFul.delete(FragmentoListAct.URLBASE+"actividad/"+s[0]);
            return r;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                //FragmentoLista.notificar();
            }catch(Exception ex){}

        }
    }
}