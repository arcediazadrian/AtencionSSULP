package edu.upb.atencionssu_lp.Controladores;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.R;
import edu.upb.atencionssu_lp.Volley.CustomRequest;

/**
 * Created by Adrian on 5/5/2018.
 */

public class LogInDAO {
    // url to get all beneficiarios_json list
    private static String url_get_beneficiario_titular = "get_beneficiario_titular.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_BENEFICIARIOS = "beneficiarios";
    private static final String TAG_ID = "id_beneficiario";
    private static final String TAG_NOMBRE = "primer_nombre";

    public static Beneficiario beneficiario= new Beneficiario("NONAME", 0);;

    public static Beneficiario getBeneficiarioById(String id, final Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = context.getString(R.string.ip) + url_get_beneficiario_titular;

        Map<String, String> params = new HashMap<String, String>();
        params.put(TAG_ID, id);

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
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray(TAG_BENEFICIARIOS);


                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                // Get current json object
                                JSONObject beneficiarioJson = array.getJSONObject(i);

                                // Get the current beneficiario (json object) data

                                String id = beneficiarioJson.getString(TAG_ID);
                                String primer_nombre = beneficiarioJson.getString(TAG_NOMBRE);

                                Log.d("Beneficiario", id + " " + primer_nombre);

                                beneficiario.setId(Integer.parseInt(id));
                                beneficiario.setNombre(primer_nombre);

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Toast.makeText(context, "Ocurrio un error al acceder a la base de datos", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
        Log.d("Beneficiario",beneficiario.getId() + " " + beneficiario.getNombre());
        return beneficiario;
    }


}
