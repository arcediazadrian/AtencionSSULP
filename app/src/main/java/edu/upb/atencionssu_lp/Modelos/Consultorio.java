package edu.upb.atencionssu_lp.Modelos;

/**
 * Created by Adrian on 5/8/2018.
 */

public class Consultorio {
    private String ID;
    private String nombre;
    private String codigo;
    private int piso;

    public Consultorio(String ID, String nombre, String codigo, int piso) {
        this.ID = ID;
        this.nombre = nombre;
        this.codigo = codigo;
        this.piso = piso;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
