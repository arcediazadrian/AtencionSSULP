package edu.upb.atencionssu_lp.Controladores;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upb.atencionssu_lp.Modelos.Agenda;
import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.Modelos.Consultorio;
import edu.upb.atencionssu_lp.Modelos.Medico;
import edu.upb.atencionssu_lp.R;
import edu.upb.atencionssu_lp.Volley.CustomRequest;
import edu.upb.atencionssu_lp.Volley.ServerCallback;
import edu.upb.atencionssu_lp.Volley.VolleyRequestQueue;

/**
 * Created by Adrian on 5/28/2018.
 */

public class EmergenciaDAO {
    private static String url_insert_emergencia = "insert_emergencia.php";


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID_BENEFICIARIO = "id_beneficiario";
    private static final String TAG_LOCALIZACION = "localizacion";
    private static final String TAG_FECHA_EMERGENCIA = "fecha_emergencia";

    public static void insertEmergencia(String id_beneficiario, String localizacion, String fecha_emergencia, final Context context, final ServerCallback callback) {
        String url = context.getString(R.string.ip) + url_insert_emergencia;

        Map<String, String> params = new HashMap<String, String>();
        params.put(TAG_ID_BENEFICIARIO, id_beneficiario);
        params.put(TAG_LOCALIZACION, localizacion);
        params.put(TAG_FECHA_EMERGENCIA, fecha_emergencia);

        // Initialize a new JsonObjectRequest instance
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        Log.d("Response: ", response.toString());

                        // Process the JSON
                        callback.onSucces();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        Toast.makeText(context, "Ocurrio un error al acceder a la base de datos: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Log.d("Volley Error", error.getLocalizedMessage() + "/n" + error.getMessage());

                        callback.onFailure();
                    }
                }
        );


        VolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
