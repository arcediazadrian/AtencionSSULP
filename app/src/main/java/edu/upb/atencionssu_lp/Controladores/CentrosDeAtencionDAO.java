package edu.upb.atencionssu_lp.Controladores;

import java.util.ArrayList;
import java.util.List;

import edu.upb.atencionssu_lp.Modelos.CentroDeAtencion;

/**
 * Created by Adrian on 5/14/2018.
 */

public class CentrosDeAtencionDAO {
    public static List<CentroDeAtencion> centroDeAtencion = new ArrayList<>();

    public static void loadCentrosDeAtencion(){
        centroDeAtencion.add(new CentroDeAtencion("Hospital Obrero", "La Paz", ""));
    }
}
