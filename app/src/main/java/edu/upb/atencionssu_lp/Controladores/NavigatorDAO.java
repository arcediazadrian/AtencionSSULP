package edu.upb.atencionssu_lp.Controladores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import edu.upb.atencionssu_lp.AgendaActivity;
import edu.upb.atencionssu_lp.BeneficiariosActivity;
import edu.upb.atencionssu_lp.CentrosDeAtencionActivity;
import edu.upb.atencionssu_lp.EmergenciaActivity;
import edu.upb.atencionssu_lp.InicioActivity;
import edu.upb.atencionssu_lp.R;

/**
 * Created by Adrian on 5/28/2018.
 */

public class NavigatorDAO {

    public static Context context;

    public static String activityClass;

    public static BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    if(!activityClass.equals("inicio")){
                    navigationInicio();
                    }
                    return true;
                case R.id.navigation_beneficiarios:
                    if(!activityClass.equals("beneficiarios")){
                    navigationBeneficiarios();
                    }
                    return true;
                case R.id.navigation_agendar:
                    if(!activityClass.equals("agendar")){
                    navigationAgenda();
                    }
                    return true;
                case R.id.navigation_centros_de_atencion:
                    if(!activityClass.equals("centros_de_atencion")){
                    navigationCentrosDeAtencion();
                    }
                    return true;
                case R.id.navigation_emergencia:
                    if(!activityClass.equals("emergencia")){
                    navigationEmergencia();
                    }
                    return true;
            }
            return false;
        }
    };

    public static void setActivity(Context pContext, String pActivity){
        context = pContext;
        activityClass = pActivity;

    }

    private static void navigationInicio(){
        Intent intent = new Intent(context, InicioActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static void navigationBeneficiarios(){
        Intent intent = new Intent(context, BeneficiariosActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static void navigationAgenda(){
        Intent intent = new Intent(context, AgendaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static void navigationCentrosDeAtencion(){
        Intent intent = new Intent(context, CentrosDeAtencionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static void navigationEmergencia(){
        Intent intent = new Intent(context, EmergenciaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
