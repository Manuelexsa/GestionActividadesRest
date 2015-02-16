package com.example.kronos.gestoractividades;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by kronos on 14/02/2015.
 */
public class Actividad implements Serializable, Comparable<Actividad>{

    private String id,idprofesor,tipo,fechai,fechaf,lugari,lugarf,descripcion,alumno;

    public Actividad(JSONObject object) throws JSONException {
        this.id = object.getString("id");
        this.idprofesor = object.getString("idprofesor");
        this.tipo = object.getString("tipo");
        this.fechai = object.getString("fechai");
        this.fechaf = object.getString("fechaf");
        this.lugari = object.getString("lugari");
        this.lugarf = object.getString("lugarf");
        this.descripcion = object.getString("descripcion");
        this.alumno = object.getString("alumno");

    }

    public JSONObject getJson(){
        JSONObject objetoJSON=new JSONObject();
        try{
            objetoJSON.put("id",this.id);
            objetoJSON.put("idprofesor",this.idprofesor);
            objetoJSON.put("tipo",this.tipo);
            objetoJSON.put("fechai",this.fechai);
            objetoJSON.put("fechaf",this.fechaf);
            objetoJSON.put("lugarI",this.lugari);
            objetoJSON.put("lugarF",this.lugarf);
            objetoJSON.put("descripcion",this.descripcion);
            objetoJSON.put("alumno",this.alumno);
            return objetoJSON;


        }catch(Exception e){
            return null;
        }
    }

    public Actividad(String id, String idprofesor, String tipo, String fechai, String fechaf, String lugari, String lugarf, String descripcion, String alumno) {
        this.id = id;
        this.idprofesor = idprofesor;
        this.tipo = tipo;
        this.fechai = fechai;
        this.fechaf = fechaf;
        this.lugari = lugari;
        this.lugarf = lugarf;
        this.descripcion = descripcion;
        this.alumno = alumno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(String idprofesor) {
        this.idprofesor = idprofesor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public String getFechaf() {
        return fechaf;
    }

    public void setFechaf(String fechaf) {
        this.fechaf = fechaf;
    }

    public String getLugari() {
        return lugari;
    }

    public void setLugari(String lugari) {
        this.lugari = lugari;
    }

    public String getLugarf() {
        return lugarf;
    }

    public void setLugarf(String lugarf) {
        this.lugarf = lugarf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id='" + id + '\'' +
                ", idprofesor='" + idprofesor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechai='" + fechai + '\'' +
                ", fechaf='" + fechaf + '\'' +
                ", lugari='" + lugari + '\'' +
                ", lugarf='" + lugarf + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public int compareTo(Actividad another) {
        return 0;
    }
}
