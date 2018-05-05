package edu.upb.atencionssu_lp.Modelos;

/**
 * Created by Adrian on 4/27/2018.
 */

public class Beneficiario {

    private String nombre;
    private int id;

    public Beneficiario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
