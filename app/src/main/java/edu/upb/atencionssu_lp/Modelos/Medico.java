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
    private int turnoEntrada;
    private int turnoSalida;

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
        //this.turno = calcularTurno(horaIn);
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
        this.turnoEntrada = calcularTurnoEntrada(horarioInicio);
        this.turnoSalida = calcularTurnoSalida(this.turnoEntrada);
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

    public int getTurnoEntrada() {
        return turnoEntrada;
    }

    public void setTurnoEntrada(int turnoEntrada) {
        this.turnoEntrada = turnoEntrada;
    }

    public int getTurnoSalida() {
        return turnoSalida;
    }

    public void setTurnoSalida(int turnoSalida) {
        this.turnoSalida = turnoSalida;
    }

    // public String getTurno() {
    //    return this.turno;
    //

   // public void setTurno(String turn) {
      //  this.turno = turn;
   // }
    //funciones

    private int calcularTurnoEntrada(String hInicio){
        int turno = 0;
        int hora = Integer.parseInt(hInicio.substring(0, hInicio.indexOf(':')));
        int min = Integer.parseInt(hInicio.substring(hInicio.indexOf(':')+1, hInicio.length()));

        turno = (hora-8) + ((min == 0)?0:(min == 15)?1:(min == 30)?2:(min == 45)?3:0);
        return turno;
    }

    public String getNombreCompleto() {
        return apellidoPaterno + " " + apellidoMaterno + " " + primerNombre + " " + segundoNombre;
    }

    public String getNombre() {
        return primerNombre + " " + segundoNombre;
    }

    public String getApellido() {
        return apellidoPaterno+ " " + apellidoMaterno;
    }

    public String getNombreSimple(){
        return apellidoPaterno + " " + primerNombre;
    }

    private int calcularTurnoSalida(int turnoEntrada){
        int turno = turnoEntrada + (4*8);
        return turno;
    }


}