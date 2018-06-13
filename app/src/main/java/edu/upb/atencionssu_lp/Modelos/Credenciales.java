package edu.upb.atencionssu_lp.Modelos;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adrian on 5/5/2018.
 */

public class Credenciales {
    public static Beneficiario Titular = new Beneficiario("La Paz","Los Pinos", new Date(), 'M' , "primer nombre", "primer apellido","segundo nombre", "segundo apellido",new Date(), "tipo seguro", "0");
    public static List<Beneficiario> Secuandarios = new LinkedList<>();

    public static void desvincular(){
        Titular = new Beneficiario("La Paz","Los Pinos", new Date(), 'M' , "primer nombre", "primer apellido","segundo nombre", "segundo apellido",new Date(), "tipo seguro", "0");
        Secuandarios = new LinkedList<>();
    }
}
