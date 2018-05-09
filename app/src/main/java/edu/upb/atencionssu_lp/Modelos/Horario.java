package edu.upb.atencionssu_lp.Modelos;

/**
 * Created by Adrian on 5/9/2018.
 */

public class Horario {
    public String horarioEntrada;
    public String horarioSalida;
    public int turno;

    public Horario(int turno){
        this.turno = turno;
        horarioEntrada = turnoToHora(turno);
        horarioSalida = turnoToHora(turno+1);
    }

    private String turnoToHora(int turno){
        String hora = "";

        hora += ((turno / 4) + 8) + ":";
        int modulo = turno % 4;

        if (modulo == 0) hora += "00";
        else if (modulo == 1) hora += "15";
        else if (modulo == 2) hora += "30";
        else hora += "45";

        return hora;
    }

    @Override
    public String toString(){
        return horarioEntrada + "   -   " + horarioSalida;
    }
}
