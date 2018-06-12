package edu.upb.atencionssu_lp.Modelos;

import java.util.Date;

/**
 * Created by Adrian on 4/27/2018.
 */

public class Beneficiario extends Datos_Personales {


    private String estadoCivil;
    private Date fechaAfiliacion;
    private String ID;
    private String matricula;
    private String Ocupacion;
    private String primerApellido;
    private String primerNombre;
    private String segundoApellido;
    private String segundoNombre;
    private String tercerNombre;
    private String tipoDeSangre;
    private String tipoDeSeguro;
    private String tipoBeneficiario;


    public Beneficiario(String ciudad, String dir, Date fecha, char gen) {
        super(ciudad, dir, fecha, gen);
    }

    public Beneficiario(String id, String primerApellido, String primerNombre, String segundoApellido, String segundoNombre, Date fechaNacimietno){
        super(null, null, fechaNacimietno,' ');
        this.ID = id;
        this.primerApellido = primerApellido;
        this.primerNombre = primerNombre;
        this.segundoApellido = segundoApellido;
        this.segundoNombre = segundoNombre;
        this.matricula = generarMatricula();
    }

    public Beneficiario(String ciudad, String dir, Date fecha, char gen, String primerNombre, String primerApellido, String segundoNombre, String segundoApellido, Date afiliacion, String tipoSeguro) {
        super(ciudad, dir, fecha, gen);

        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.segundoNombre = segundoNombre;
        this.segundoApellido = segundoApellido;
        this.tipoDeSeguro = tipoSeguro;
        this.fechaAfiliacion = afiliacion;
        this.Ocupacion = null;
        this.matricula = generarMatricula();
    }

    public Beneficiario(String ciudad, String dir, Date fecha, char gen, String primerNombre, String primerApellido, String segundoNombre, String segundoApellido, Date afiliacion, String tipoSeguro, String id) {
        super(ciudad, dir, fecha, gen);
        this.ID = id;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.segundoNombre = segundoNombre;
        this.segundoApellido = segundoApellido;
        this.tipoDeSeguro = tipoSeguro;
        this.fechaAfiliacion = afiliacion;
        this.Ocupacion = null;
        this.matricula = generarMatricula();
    }

    //getters y setters

    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEstadoCivil(String estado) {
        this.estadoCivil = estado;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getOcupacion() {
        return this.Ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.Ocupacion = ocupacion;
    }

    public String getPrimerApellido() {
        return this.primerApellido;
    }

    public void setPrimerApellido(String apellido) {
        this.primerApellido = apellido;
    }

    public String getPrimerNombre() {
        return this.primerNombre;
    }

    public void setPrimerNombre(String nombre) {
        this.primerNombre = nombre;
    }

    public String getSegundoApellido() {
        return this.segundoApellido;
    }

    public void setSegundoApellido(String apellido) {
        this.segundoApellido = apellido;
    }

    public String getSegundoNombre() {
        return this.segundoNombre;
    }

    public void setSegundoNombre(String nombre) {
        this.segundoNombre = nombre;
    }

    public String getTipoDeSangre() {
        return this.tipoDeSangre;
    }

    public void setTipoDeSangre(String tipo) {
        this.tipoDeSangre = tipo;
    }

    public String getTipoDeSeguro() {
        return this.tipoDeSeguro;
    }

    public void setTipoDeSeguro(String tipo) {
        this.tipoDeSeguro = tipo;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public String getTercerNombre() {
        return tercerNombre;
    }

    public void setTercerNombre(String tercerNombre) {
        this.tercerNombre = tercerNombre;
    }

    public String getTipoBeneficiario() {
        return tipoBeneficiario;
    }

    public void setTipoBeneficiario(String tipoBeneficiario) {
        this.tipoBeneficiario = tipoBeneficiario;
    }
    //funciones especiales

    public String generarMatricula() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFechaNacimiento().getYear());
        sb.substring(2);

        int mes = getFechaNacimiento().getMonth();
        if (getGenero().compareTo("Femenino") == 0)
            mes += 50;
        sb.append(mes);
        int dia = getFechaNacimiento().getDay();
        if (dia < 10) sb.append("0");
        sb.append(getFechaNacimiento().getDay());
        sb.append(getPrimerApellido().charAt(0));
        sb.append(getSegundoApellido().charAt(0));
        sb.append(getPrimerNombre().charAt(0));

        return sb.toString();
    }

    public String getNombreCompleto() {
        return primerApellido + " " + segundoApellido + " " + primerNombre + " " + segundoNombre;
    }

    public String getNombre() {
        return primerNombre + " " + segundoNombre;
    }

    public String getApellido() {
        return primerApellido + " " + segundoApellido;
    }

    public String getNombreSimple(){
        return primerApellido + " " + primerNombre;
    }


}