package edu.upb.atencionssu_lp.Modelos;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adrian on 4/28/2018.
 */

public class Agenda implements Serializable{
    private Beneficiario paciente;
    private String id;
    private Date fechaDeAtencion;
    private int numeroConsulta;
    private Medico medico;
    private int turno;

    public Agenda(String id, Beneficiario paciente, Date fechaDeAtencion, int numeroConsulta, Medico medico, int turno) {
        this.id = id;
        this.paciente = paciente;
        this.fechaDeAtencion = fechaDeAtencion;
        this.numeroConsulta = numeroConsulta;
        this.medico = medico;
        this.turno = turno;
    }

    public Agenda(Beneficiario ben, Date fecha, int consulta, Medico medico) {
        this.paciente = ben;
        this.fechaDeAtencion = fecha;
        this.numeroConsulta = consulta;
        this.medico = medico;
    }

    public Agenda(Beneficiario ben, int consulta, Date fecha) {
        this.paciente = ben;
        this.numeroConsulta = consulta;
        this.fechaDeAtencion = fecha;
    }

    public Agenda(Beneficiario ben, int consulta, Date fecha, int turno) {
        this.paciente = ben;
        this.numeroConsulta = consulta;
        this.fechaDeAtencion = fecha;
        this.turno = turno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Beneficiario getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Beneficiario ben) {
        this.paciente = ben;
    }

    public Date getFechaDeAtencion() {
        return this.fechaDeAtencion;
    }

    public void setFechaDeAtencion(Date fecha) {
        this.fechaDeAtencion = fecha;
    }

    public int getNumeroConsulta() {
        return this.numeroConsulta;
    }

    public void setNumeroDeConsulta(int consulta) {
        this.numeroConsulta = consulta;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }


    public String getHoraConsulta() {
        String hora = "";

        hora += ((turno / 4) + 8) + ":";
        int modulo = turno % 4;

        if (modulo == 0) hora += "00";
        else if (modulo == 1) hora += "15";
        else if (modulo == 2) hora += "30";
        else hora += "45";


        return hora;
    }

    public String getNombrePaciente() {
        return paciente.getNombreCompleto();
    }


}
