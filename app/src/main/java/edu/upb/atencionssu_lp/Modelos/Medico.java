package edu.upb.atencionssu_lp.Modelos;

import java.util.Date;

/**
 * Created by Adrian on 4/28/2018.
 */

public class Medico extends Datos_Personales {

    private String ID;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Consultorio consultorio;
    private String correoDiario;
    private String correoInstitucional;
    private String telefonoOficina;
    private String telefonoMovil;
    private String especialidad;
    private String horarioInicio;
    private String horarioSalida;
    private String primerNombre;
    private String segundoNombre;
    private String turno;

    public Medico(String ciudad, String dir, Date fechaNac, char gen, String primerNombre, String segundoNombre, String app, String apm, Consultorio consultorio, String correo, String esp, String horaIn, String horaSal) {
        super(ciudad, dir, fechaNac, gen);
        this.primerNombre = primerNombre;
        this.apellidoPaterno = app;
        this.apellidoMaterno = apm;
        this.correoDiario = correo;
        this.especialidad = esp;
        this.horarioInicio = horaIn;
        this.horarioSalida = horaSal;
        this.segundoNombre = segundoNombre;
        this.consultorio = consultorio;
        this.turno = calcularTurno(horaIn);
    }

    public Medico(String id, String apellidoPaterno, String apellidoMaterno, String primerNombre, String segundoNombre, String correoInstitucional, String correoDiario, String telefonoOficina, String telefonoMovil, String especialidad, Consultorio consultorio) {
        super(null, null, null, ' ');
        this.ID = id;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.correoInstitucional = correoInstitucional;
        this.correoDiario = correoDiario;
        this.telefonoOficina = telefonoOficina;
        this.telefonoMovil = telefonoMovil;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
    }

    public Medico(String id, String apellidoPaterno, String apellidoMaterno, String primerNombre, String segundoNombre, String correoInstitucional, String correoDiario, String telefonoOficina, String telefonoMovil, String especialidad, String horarioInicio, String horarioSalida, Consultorio consultorio) {
        super(null, null, null, ' ');
        this.ID = id;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.correoInstitucional = correoInstitucional;
        this.correoDiario = correoDiario;
        this.telefonoOficina = telefonoOficina;
        this.telefonoMovil = telefonoMovil;
        this.especialidad = especialidad;
        this.horarioInicio = horarioInicio;
        this.horarioSalida = horarioSalida;
        this.consultorio = consultorio;
    }

    //getters y setters


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno(String apellido) {
        this.apellidoMaterno = apellido;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public void setApellidoMaterno(String apellido) {
        this.apellidoMaterno = apellido;
    }

    public Consultorio getConsultorio() {
        return this.consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public String getCorreoDiario() {
        return this.correoDiario;
    }

    public void setCorreoDiario(String correoDiario) {
        this.correoDiario = correoDiario;
    }

    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String esp) {
        this.especialidad = esp;
    }

    public String getHorarioInicio() {
        return this.horarioInicio;
    }

    public void setHorarioInicio(String inicio) {
        this.horarioInicio = inicio;
    }

    public String getHorarioSalida() {
        return this.horarioSalida;
    }

    public void setHorarioSalida(String salida) {
        this.horarioSalida = salida;
    }

    public String primerNombre() {
        return this.primerNombre;
    }

    public void setPrimerNombre(String nombre) {
        this.primerNombre = nombre;
    }

    public String getSegundoNombre() {
        return this.segundoNombre;
    }

    public void setSegundoNombre(String nombre) {
        this.segundoNombre = nombre;
    }

    public String getTurno() {
        return this.turno;
    }

    public void setTurno(String turn) {
        this.turno = turn;
    }
    //funciones

    private String calcularTurno(String hora) {
        String turn = null;
        System.out.println("Classes.Medico.calcularTurno() =" + hora.compareTo("12:00"));

        if (hora.compareTo("12:00") >= 0) {
            turn = "TT";
        } else {
            turn = "TM";
        }


        return turn;
    }

    public String getNombreCompleto() {
        return apellidoPaterno + " " + apellidoMaterno + " " + primerNombre + " " + segundoNombre;
    }


}