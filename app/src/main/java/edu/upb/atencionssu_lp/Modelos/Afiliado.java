package edu.upb.atencionssu_lp.Modelos;

/**
 * Created by Adrian on 4/27/2018.
 */

public class Afiliado {

    private String nombre;
    private int id;
    private String imagenPerfilURL;

    public Afiliado(String nombre, int id, String imagenPerfilURL) {
        this.nombre = nombre;
        this.id = id;
        this.imagenPerfilURL = imagenPerfilURL;
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

    public String getImagenPerfilURL() {
        return imagenPerfilURL;
    }

    public void setImagenPerfilURL(String imagenPerfilURL) {
        this.imagenPerfilURL = imagenPerfilURL;
    }
}
