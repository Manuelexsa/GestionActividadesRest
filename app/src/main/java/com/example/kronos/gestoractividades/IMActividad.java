package com.example.kronos.gestoractividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class IMActividad extends Activity {
    TextView tvProf, tvGrup, tvDesc, tvTipo;
    String index="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_imactividad);
        this.getActionBar(). setDisplayHomeAsUpEnabled ( true );
        tvDesc = (TextView)findViewById(R.id.tvdetdesc);
        Bundle b = getIntent().getExtras();
        if(b !=null ){
            Actividad a = (Actividad)b.getSerializable("actividad");
            index = a.getId();
            tvDesc.setText(a.getDescripcion().toString());
        }


    }

    public void aceptar(View v){
        String id,idprofesor,tipo,fechai,fechaf,lugari,lugarf,descripcion,alumno;
        descripcion=tvDesc.getText().toString();

        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("descripcion", descripcion);
        bundle.putString("index",index);
        i.putExtras(bundle);
        setResult(Activity.RESULT_OK, i);
        finish();
    }
    public void cancelar(View v){
        Intent i = new Intent();
        setResult(Activity.RESULT_CANCELED, i);
        finish();
    }
}