package edu.upb.atencionssu_lp.Modelos;

import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Adrian on 5/6/2018.
 */

public class Datos_Personales {

    //atributos de clse

    private String ciudad;
    private String direccion;
    private Date fechaNacimiento;
    private int edad;
    private char genero;
    private int telefonoCelular;
    private int telefonFijo;
    private int carnetDeIdentidad;
    private String ciudadEmision;

    //constructores

    public Datos_Personales(String ciudad, String dir, Date fecha, char gen) {
        this.ciudad = ciudad;
        this.direccion = dir;
        this.fechaNacimiento = fecha;
        this.genero = gen;
        //TODO calcular edad
        //this.edad = calcularEdad();
    }

    public Datos_Personales(String ciudad, String dir, Date fechaN, char gen, int celular, int telFijo, int CI, String emisionCI) {
        this.ciudad = ciudad;
        this.direccion = dir;
        this.fechaNacimiento = fechaN;
        this.genero = gen;
        this.telefonoCelular = celular;
        this.telefonFijo = telFijo;
        this.carnetDeIdentidad = CI;
        this.ciudadEmision = emisionCI;

    }


    // getters y setters

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String dir) {
        this.direccion = dir;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date nuevaFecha) {
        this.fechaNacimiento = nuevaFecha;
    }

    public String getGenero() {
        if (genero == 'M') return "Masculino";
        else return "Femenino";
    }

    public void setGenero(char gen) {
        this.genero = gen;
    }

    //funciones complementarias
    /*
    private int calcularEdad() {
        Calendar fecha_actual = getCalendar(new Date());
        Calendar fecha_nacimiento = getCalendar(fechaNacimiento);
        int fechaNacimiento = fecha_nacimiento.get(1);
        this.edad = fecha_actual.get(1) - fechaNacimiento;
        System.out.println("Classes.Datos_Personales.calcularEdad()" + fecha_actual.get(1) + "  fechaNacimiento: " + fechaNacimiento);
        if (fecha_actual.get(2) > fecha_nacimiento.get(2) || (fecha_actual.get(2) == fecha_nacimiento.get(2) && fecha_actual.get(3) > fecha_nacimiento.get(3))) {
            edad--;
        }
        return edad;
    }
    */

    public Calendar getCalendar(Date date) {
        Calendar fecha = Calendar.getInstance(Locale.ITALY);
        fecha.setTime(date);
        return fecha;
    }


}
