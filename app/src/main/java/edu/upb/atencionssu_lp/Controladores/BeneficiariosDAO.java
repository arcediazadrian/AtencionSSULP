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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.R;
import edu.upb.atencionssu_lp.Volley.CustomRequest;
import edu.upb.atencionssu_lp.Volley.VolleyRequestQueue;

/**
 * Created by Adrian on 5/5/2018.
 */

public class BeneficiariosDAO {
    // url to get all beneficiarios_json list
    private static String url_get_beneficiario_titular = "get_beneficiario_titular.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_BENEFICIARIOS = "beneficiario";
    private static final String TAG_ID = "id_beneficiario";
    private static final String TAG_FECHA_AFILIACION =  "fecha_afiliacion";
    private static final String TAG_FECHA_NACIMIENTO = "fecha_nacimiento";
    private static final String TAG_PRIMER_APELLIDO = "primer_apellido";
    private static final String TAG_PRIMER_NOMBRE = "primer_nombre";
    private static final String TAG_SEGUNDO_APELLIDO = "segundo_apellido";
    private static final String TAG_SEGUNDO_NOMBRE = "segundo_nombre";
    private static final String TAG_TERCER_NOMBRE = "tercer_nombre";
    private static final String TAG_TIPO_BENEFICIARIO = "tipo_beneficiario";
    private static final String TAG_TIPO_DE_SANGRE = "tipo_de_sangre";
    private static final String TAG_GENERO = "genero";
    private static final String TAG_DIRECCION = "direccion";
    private static final String TAG_CIUDAD = "ciudad";


    public static Beneficiario beneficiario = new Beneficiario("La Paz","Los Pinos", new Date(), 'M' , "primer nombre", "primer apellido","segundo nombre", "segundo apellido",new Date(), "tipo seguro", "0");

    public static Beneficiario getBeneficiarioById(String id, final Context context){
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
                                String feha_afiliacion = beneficiarioJson.getString(TAG_FECHA_AFILIACION);
                                String fecha_nacimiento = beneficiarioJson.getString(TAG_FECHA_NACIMIENTO);
                                String primer_apellido = beneficiarioJson.getString(TAG_PRIMER_APELLIDO);
                                String primer_nombre = beneficiarioJson.getString(TAG_PRIMER_NOMBRE);
                                String segundo_apellido = beneficiarioJson.getString(TAG_SEGUNDO_APELLIDO);
                                String segundo_nombre = beneficiarioJson.getString(TAG_SEGUNDO_NOMBRE);
                                String tercer_nombre = beneficiarioJson.getString(TAG_TERCER_NOMBRE);
                                String tipo_beneficiario = beneficiarioJson.getString(TAG_TIPO_BENEFICIARIO);
                                String tipo_de_sangre = beneficiarioJson.getString(TAG_TIPO_DE_SANGRE);
                                String ciudad = beneficiarioJson.getString(TAG_CIUDAD);
                                String genero = beneficiarioJson.getString(TAG_GENERO);
                                String direccion = beneficiarioJson.getString(TAG_DIRECCION);

                                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                                Date fa = null;
                                Date fn = null;

                                try {
                                    fa = ft.parse(feha_afiliacion);
                                    fn = ft.parse(fecha_nacimiento);
                                } catch (ParseException e) {
                                    Toast.makeText(context, "Error al persear fechas", Toast.LENGTH_SHORT);
                                    Log.d("Error Parser", "Error al parsear fechas");
                                }

                                beneficiario.setID(id);
                                beneficiario.setFechaAfiliacion(fa);
                                beneficiario.setFechaNacimiento(fn);
                                beneficiario.setPrimerApellido(primer_apellido);
                                beneficiario.setPrimerNombre(primer_nombre);
                                beneficiario.setSegundoApellido(segundo_apellido);
                                beneficiario.setSegundoNombre(segundo_nombre);
                                beneficiario.setTercerNombre(tercer_nombre);
                                beneficiario.setTipoBeneficiario(tipo_beneficiario);
                                beneficiario.setTipoDeSangre(tipo_de_sangre);
                                beneficiario.setCiudad(ciudad);
                                beneficiario.setDireccion(direccion);
                                beneficiario.setGenero(genero.toCharArray()[0]);

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
                        Toast.makeText(context, "Ocurrio un error al acceder a la base de datos: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Log.d("Volley Error", error.getLocalizedMessage()+ "/n" + error.getMessage());
                    }
                }
        );

        VolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);

        Log.d("Beneficiario",beneficiario.getID() + " " + beneficiario.getNombreCompleto());
        return beneficiario;
    }
}
