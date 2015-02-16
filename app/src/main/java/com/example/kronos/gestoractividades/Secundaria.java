package com.example.kronos.gestoractividades;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Secundaria extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final boolean PORTRAIT =true ;
    private static final int AÑADIR = 1;
    public static boolean Tablet = false;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private final static String URLBASE="http://ieszv.x10.bz/restful/api/";
    private ListView lv;
    private Adaptador ad;
    private FragmentodetAct fd;
    private FragmentoListAct fl;
    private ArrayList<Actividad> actividades;
    private static final int CREAR=0;
    private static final int MODIFICAR=1;
    private static final int BORRAR = 2;
    private static final int DETALLE = 3;
    private  View detalle,lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_secundaria);
        fd=(FragmentodetAct)getFragmentManager().findFragmentById(R.id.fragmento_det_act);
        fl=(FragmentoListAct)getFragmentManager().findFragmentById(R.id.fragmento_list_act);
         detalle = findViewById(R.id.fragmento_det_act);
        detalle.setVisibility(View.GONE);
         lista = findViewById(R.id.fragmento_list_act);
        lista.setVisibility(View.VISIBLE);
        Tablet = detalle != null && detalle.getVisibility() == View.VISIBLE;

        actividades = new ArrayList <Actividad>();
        lv= (ListView)findViewById(R.id.lvActividades);
        ad = new Adaptador(this,R.layout.detalle,actividades);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Actividad ac = actividades.get(pos);
                Log.v("ac",ac.toString());
                if(Tablet){

                    FragmentodetAct.mostrarActividad(ac);
                }else if(PORTRAIT){
                    detalle.setVisibility(View.VISIBLE);
                    lista.setVisibility(View.GONE);
                    FragmentodetAct.mostrarActividad(ac);
                }

            }

        });
        registerForContextMenu(lv);
        cargarActividades();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){
            String id,idprofesor,tipo,fechai,fechaf,lugari,lugarf,descripcion,alumno;
            long index=0;
            Actividad a;
            switch (requestCode){
                case CREAR:
                    id = data.getStringExtra("id");
                    idprofesor = data.getStringExtra("idprofesor");
                    tipo = data.getStringExtra("tipo");
                    fechai = data.getStringExtra("fechai");
                    fechaf = data.getStringExtra("fechaf");
                    lugari = data.getStringExtra("lugari");
                    lugarf = data.getStringExtra("lugarf");
                    descripcion = data.getStringExtra("descripcion");
                    alumno = data.getStringExtra("alumno");
                    tostada(tipo+"");
                    a = new Actividad(id, idprofesor, tipo, fechai, fechaf, lugari, lugarf, descripcion, alumno);
                    anadirActividad(a);
                    break;
                case MODIFICAR:
                    id = data.getStringExtra("id");
                    idprofesor = data.getStringExtra("idprofesor");
                    tipo = data.getStringExtra("tipo");
                    fechai = data.getStringExtra("fechai");
                    fechaf = data.getStringExtra("fechaf");
                    lugari = data.getStringExtra("lugari");
                    lugarf = data.getStringExtra("lugarf");
                    descripcion = data.getStringExtra("descripcion");
                    alumno = data.getStringExtra("alumno");
                    tostada(tipo+"");
                    a = new Actividad(id, idprofesor, tipo, fechai, fechaf, lugari, lugarf, descripcion, alumno);

                    break;

            }
        }else {
            switch (requestCode){
                case CREAR:
                    tostada("Cancelado.");
                    break;
                case MODIFICAR:

                    tostada("Cancelado");
                    break;


            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavigationDrawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp( R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.global, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id == R.id.andiracti) {
            Intent i = new Intent(this,IMActividad.class);
            startActivityForResult(i, CREAR);
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

    public boolean onContextItemSelected(MenuItem item) {
        int id=item.getItemId();
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index = info.position;

        Log.v("aaa", String.valueOf(index));
        Actividad a =actividades.get(index) ;
        if(id==R.id.action_Borrar){

        }else if(id==R.id.action_Modificar){
            Intent intent = new Intent(this,IMActividad.class);
            Bundle b = new Bundle();
            b.putSerializable("actividad", a);
            b.putInt("index", id);
            intent.putExtras(b);
            startActivityForResult(intent, MODIFICAR);
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                tostada("hola que ase");
                break;
            case 2:
                mTitle = getString(R.string.title_section2);

                break;
            case 3:
                mTitle = getString(R.string.title_section3);

                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragmento_list_act, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Secundaria) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    private String anadirActividad(Actividad acs) {
        //new Actividad("1","0","complementaria","2015-01-29","2015-01-29","Alhambra","Alhambra","Vamos a la Alhambra","Juanito")
        Actividad ac=acs;
        PostRESTFul post=new PostRESTFul();
        // post.execute(new Actividad[]{ac});
        ParametrosPost pp=new ParametrosPost();
        pp.URL=URLBASE+"actividad";
        pp.Objson=ac.getJson();
        post.execute(pp);
        return "1";

    }

    private void cargarActividades(){
        String [] peticiones = new String[3];
        peticiones[0] = "actividad/juanito";
        peticiones[1] = "profesor";
        peticiones[2] = "grupo";
        GetRestful get =new GetRestful();
        get.execute(peticiones);
    }

    private class GetRestful extends AsyncTask< String, Void, String[] > {

        @Override
        protected String[] doInBackground(String... params) {
            String [] r = new String [params.length];
            int contador =0;
            for(String s:params){
                r[contador] = ClienteRestFul.get(URLBASE + s);
                contador++;
            }
            return r;
        }

        @Override
        protected void onPostExecute(String[] r) {
            super.onPostExecute(r);
            JSONTokener token = new JSONTokener(r[0]);
            try {
                // JSONObject objeto = new JSONObject(token);
                JSONArray array = new JSONArray(token);
                for (int i = 0; i < array.length(); i++){
                    JSONObject objeto = array.getJSONObject(i);
                    Actividad a = new Actividad(objeto);
                    actividades.add(a);
                }
                ad.notifyDataSetChanged();
            } catch (JSONException e) {

            }
        }

    }

    private class ParametrosPost{
        String URL;
        JSONObject Objson;

    }

    private class PostRESTFul extends AsyncTask<ParametrosPost, Void, String>{

        //Llegan varias urls de consultas, y devuelve un array de respuestas.
        @Override
        protected String doInBackground(ParametrosPost[] params) {
            String r=ClienteRestFul.post(params[0].URL,params[0].Objson) ;
            return r;
        }

        @Override
        protected void onPostExecute(String r) {
            super.onPostExecute(r);
            //Toast.makeText(Principal.this, r, Toast.LENGTH_SHORT).show();
            JSONTokener tokener = new JSONTokener(r);
            try {
                //JSONObject raiz = new JSONObject(tokener); Para respuestas cortas JSON (r:1)
                JSONObject raiz = new JSONObject(tokener);
                ad.notifyDataSetChanged();
            } catch (JSONException e) {

            }
        }


    }

    private class DeleteRestfull extends AsyncTask<String, Void, String> {

        private int index;

        public DeleteRestfull(int index){
            this.index = index;
        }

        @Override
        protected String doInBackground(String... params) {
            String r = ClienteRestFul.delete(URLBASE + params[0]);
            return r;
        }

        @Override
        protected void onPostExecute(String r) {
            super.onPostExecute(r);
           // listaActividades.remove(index);
            //actualizarLista();
        }
    }

    private void agregarActividad(Actividad actividad){
        ParametrosPost pp = new ParametrosPost();
        pp.URL = URLBASE + "actividad";
        pp.Objson = actividad.getJson();
        if(pp.Objson != null) {
            PostRESTFul post = new PostRESTFul();
            post.execute(pp);
        }
    }

   /* public void borrarActividad(int index){
        String[] peticiones = new String[3];
        int id = actividades.get(index).getId();
        peticiones[0] = "actividad/" + id;
        peticiones[1] = "actividadprofesor/" + id;
        peticiones[2] = "actividadgrupo/" + id;

        DeleteRestfull del = new DeleteRestfull(index);
        del.execute(peticiones);
    }*/



    private void tostada(String s){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, s, duration);
        toast.show();
    }

}
