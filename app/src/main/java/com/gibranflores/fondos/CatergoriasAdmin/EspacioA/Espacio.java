package com.gibranflores.fondos.CatergoriasAdmin.EspacioA;

public class Espacio {

    private String imagen;
    private String nombre;
    private int vista;

    public Espacio(String imagen, String nombre, int vista) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.vista = vista;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVista() {
        return vista;
    }

    public void setVista(int vista) {
        this.vista = vista;
    }
}
