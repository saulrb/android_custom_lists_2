package mx.itesm.m6_srb_labo_listaspersonalizadas2;

import java.io.Serializable;

/**
 * Created by saul on 18/2/2017.
 */

public class Jugador implements Serializable {

    String nombre;
    int posicion;
    String nacionalidad;
    int idImagenPosicion;
    byte[] byteArrayFoto;

    public Jugador(byte[] byteArrayFoto, int idImagenPosicion, String nacionalidad, String nombre, int posicion) {
        this.byteArrayFoto = byteArrayFoto;
        this.idImagenPosicion = idImagenPosicion;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public byte[] getByteArrayFoto() {
        return byteArrayFoto;
    }

    public void setByteArrayFoto(byte[] byteArrayFoto) {
        this.byteArrayFoto = byteArrayFoto;
    }

    public int getIdImagenPosicion() {
        return idImagenPosicion;
    }

    public void setIdImagenPosicion(int idImagenPosicion) {
        this.idImagenPosicion = idImagenPosicion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
