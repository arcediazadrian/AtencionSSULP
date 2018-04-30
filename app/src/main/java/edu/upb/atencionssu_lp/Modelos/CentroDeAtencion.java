package edu.upb.atencionssu_lp.Modelos;

/**
 * Created by Adrian on 4/28/2018.
 */

public class CentroDeAtencion {
    String nombre;
    String direccion;
    String imagenCentroAtencionURL;

    public CentroDeAtencion(String nombre, String direccion, String imagenCentroAtencionURL) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagenCentroAtencionURL = imagenCentroAtencionURL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagenCentroAtencionURL() {
        return imagenCentroAtencionURL;
    }

    public void setImagenCentroAtencionURL(String imagenCentroAtencionURL) {
        this.imagenCentroAtencionURL = imagenCentroAtencionURL;
    }
}
