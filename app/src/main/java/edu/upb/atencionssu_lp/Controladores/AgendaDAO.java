package edu.upb.atencionssu_lp.Controladores;

import android.content.Context;
import android.content.Intent;
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
 * Created by Adrian on 5/6/2018.
 */

public class AgendaDAO {
    // url to get all beneficiarios_json list
    private static String url_get_agenda = "get_agenda.php";
    private static String url_get_medicos = "get_medicos.php";
    private static String url_get_horarios = "get_horarios.php";
    private static String url_insert_agenda = "insert_agenda.php";


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AGENDA = "agenda";
    private static final String TAG_MEDICOS = "medicos";
    private static final String TAG_HORARIOS = "horarios";
    private static final String TAG_AGENDA_ID = "id_agenda";
    private static final String TAG_ID_BENEFICIARIO = "id_beneficiario";
    private static final String TAG_AGENDA_FECHA_AGENDADA = "fecha_agendada";
    private static final String TAG_AGENDA_NUMERO_CONSULTA = "numero_consulta";
    private static final String TAG_ID_MEDICO = "id_medico";
    private static final String TAG_AGENDA_TURNO = "turno";
    private static final String TAG_BENEFICIARIO_PRIMER_APELLIDO = "beneficiario_primer_apellido";
    private static final String TAG_BENEFICIARIO_PRIMER_NOMBRE = "beneficiario_primer_nombre";
    private static final String TAG_BENEFICIARIO_SEGUNDO_APELLIDO = "beneficiario_segundo_apellido";
    private static final String TAG_BENEFICIARIO_SEGUNDO_NOMBRE = "beneficiario_segundo_nombre";
    private static final String TAG_BENEFICIARIO_FECHA_NACIMIENTO = "beneficiario_fecha_nacimiento";
    private static final String TAG_CONSULTORIO_ID = "consultorio_id";
    private static final String TAG_CONSULTORIO_CODIGO = "consultorio_codigo";
    private static final String TAG_CONSULTORIO_NOMBRE = "consultorio_nombre";
    private static final String TAG_CONSULTORIO_PISO = "consultorio_piso";
    private static final String TAG_MEDICO_PRIMER_APELLIDO = "medico_primer_apellido";
    private static final String TAG_MEDICO_PRIMER_NOMBRE = "medico_primer_nombre";
    private static final String TAG_MEDICO_SEGUNDO_APELLIDO = "medico_segundo_apellido";
    private static final String TAG_MEDICO_SEGUNDO_NOMBRE = "medico_segundo_nombre";
    private static final String TAG_MEDICO_TELEFONO_OFICINA = "medico_telefono_oficina";
    private static final String TAG_MEDICO_TELEFONO_MOVIL = "medico_telefono_movil";
    private static final String TAG_MEDICO_CORREO_INSTITUCIONAL = "medico_correo_institucional";
    private static final String TAG_MEDICO_CORREO_DIARIO = "medico_correo_diario";
    private static final String TAG_MEDICO_ESPECIALIDAD = "medico_especialidad";
    private static final String TAG_MEDICO_HORARIO_INICIO = "medico_horario_inicio";
    private static final String TAG_MEDICO_HORARIO_SALIDA = "medico_horario_salida";

    public static List<Agenda> agenda = new ArrayList<>();
    public static List<Medico> medicos = new ArrayList<>();
    public static List<Integer> horario = new ArrayList<>();

    public static void getAgendaByIdBeneficiario(String id, String fecha_filtro, final Context context, final ServerCallback callback) {
        String url = context.getString(R.string.ip) + url_get_agenda;

        Map<String, String> params = new HashMap<String, String>();
        params.put(TAG_ID_BENEFICIARIO, id);
        params.put(TAG_AGENDA_FECHA_AGENDADA, fecha_filtro);

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
                        try {
                            // Get the JSON array
                            JSONArray array = response.getJSONArray(TAG_AGENDA);


                            // Loop through the array elements
                            for (int i = 0; i < array.length(); i++) {
                                // Get current json object
                                JSONObject agendaJson = array.getJSONObject(i);
                                // Get the current agenda (json object) data

                                String id = agendaJson.getString(TAG_AGENDA_ID);
                                String id_beneficiario = agendaJson.getString(TAG_ID_BENEFICIARIO);
                                String fecha_agendada = agendaJson.getString(TAG_AGENDA_FECHA_AGENDADA);
                                int consulta = Integer.parseInt(agendaJson.getString(TAG_AGENDA_NUMERO_CONSULTA));
                                String id_medico = agendaJson.getString(TAG_ID_MEDICO);
                                int turno = Integer.parseInt(agendaJson.getString(TAG_AGENDA_TURNO));

                                String beneficiario_primer_apellido = agendaJson.getString(TAG_BENEFICIARIO_PRIMER_APELLIDO);
                                String beneficiario_primer_nombre = agendaJson.getString(TAG_BENEFICIARIO_PRIMER_NOMBRE);
                                String beneficiario_segundo_apellido = agendaJson.getString(TAG_BENEFICIARIO_SEGUNDO_APELLIDO);
                                String beneficiario_segundo_nombre = agendaJson.getString(TAG_BENEFICIARIO_SEGUNDO_NOMBRE);
                                String beneficiario_fecha_nacimietno = agendaJson.getString(TAG_BENEFICIARIO_FECHA_NACIMIENTO);

                                String consultorio_id = agendaJson.getString(TAG_CONSULTORIO_ID);
                                String consultorio_codigo = agendaJson.getString(TAG_CONSULTORIO_CODIGO);
                                String consultorio_nombre = agendaJson.getString(TAG_CONSULTORIO_NOMBRE);
                                int consultorio_piso = Integer.parseInt(agendaJson.getString(TAG_CONSULTORIO_PISO));

                                String medico_primer_apellido = agendaJson.getString(TAG_MEDICO_PRIMER_APELLIDO);
                                String medico_primer_nombre = agendaJson.getString(TAG_MEDICO_PRIMER_NOMBRE);
                                String medico_segundo_apellido = agendaJson.getString(TAG_MEDICO_SEGUNDO_APELLIDO);
                                String medico_segundo_nombre = agendaJson.getString(TAG_MEDICO_SEGUNDO_NOMBRE);
                                String medico_telefono_oficina = agendaJson.getString(TAG_MEDICO_TELEFONO_OFICINA);
                                String medico_telefono_movil = agendaJson.getString(TAG_MEDICO_TELEFONO_MOVIL);
                                String medico_correo_institucional = agendaJson.getString(TAG_MEDICO_CORREO_INSTITUCIONAL);
                                String medico_correo_diario = agendaJson.getString(TAG_MEDICO_CORREO_DIARIO);
                                String medico_especialidad = agendaJson.getString(TAG_MEDICO_ESPECIALIDAD);

                                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                                Date fa = null;
                                Date fn = null;

                                try {
                                    fa = ft.parse(fecha_agendada);
                                    fn = ft.parse(beneficiario_fecha_nacimietno);
                                } catch (ParseException e) {
                                    Toast.makeText(context, "Error al persear fechas", Toast.LENGTH_SHORT);
                                    Log.d("Error Parser", "Error al parsear fechas");
                                }

                                Agenda a = new Agenda(id, new Beneficiario(id_beneficiario, beneficiario_primer_apellido, beneficiario_primer_nombre, beneficiario_segundo_apellido, beneficiario_segundo_nombre, fn), fa, consulta, new Medico(id_medico, medico_primer_apellido, medico_segundo_apellido, medico_primer_nombre, medico_segundo_nombre, medico_correo_institucional, medico_correo_diario, medico_telefono_oficina, medico_telefono_movil, medico_especialidad, new Consultorio(consultorio_id, consultorio_nombre, consultorio_codigo, consultorio_piso)), turno);
                                agenda.add(a);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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

    public static void getMedicos(final Context context, final ServerCallback callback) {
        String url = context.getString(R.string.ip) + url_get_medicos;

        Map<String, String> params = new HashMap<String, String>();

        // Initialize a new JsonObjectRequest instance
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.GET,
                url,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        Log.d("Response: ", response.toString());

                        // Process the JSON
                        try {
                            // Get the JSON array
                            JSONArray array = response.getJSONArray(TAG_MEDICOS);


                            // Loop through the array elements
                            for (int i = 0; i < array.length(); i++) {
                                // Get current json object
                                JSONObject medicoJson = array.getJSONObject(i);
                                // Get the current agenda (json object) data

                                String id_medico = medicoJson.getString(TAG_ID_MEDICO);

                                String consultorio_id = medicoJson.getString(TAG_CONSULTORIO_ID);
                                String consultorio_codigo = medicoJson.getString(TAG_CONSULTORIO_CODIGO);
                                String consultorio_nombre = medicoJson.getString(TAG_CONSULTORIO_NOMBRE);
                                int consultorio_piso = Integer.parseInt(medicoJson.getString(TAG_CONSULTORIO_PISO));

                                String medico_primer_apellido = medicoJson.getString(TAG_MEDICO_PRIMER_APELLIDO);
                                String medico_primer_nombre = medicoJson.getString(TAG_MEDICO_PRIMER_NOMBRE);
                                String medico_segundo_apellido = medicoJson.getString(TAG_MEDICO_SEGUNDO_APELLIDO);
                                String medico_segundo_nombre = medicoJson.getString(TAG_MEDICO_SEGUNDO_NOMBRE);
                                String medico_telefono_oficina = medicoJson.getString(TAG_MEDICO_TELEFONO_OFICINA);
                                String medico_telefono_movil = medicoJson.getString(TAG_MEDICO_TELEFONO_MOVIL);
                                String medico_correo_institucional = medicoJson.getString(TAG_MEDICO_CORREO_INSTITUCIONAL);
                                String medico_correo_diario = medicoJson.getString(TAG_MEDICO_CORREO_DIARIO);
                                String medico_especialidad = medicoJson.getString(TAG_MEDICO_ESPECIALIDAD);
                                String medico_horario_inicio = medicoJson.getString(TAG_MEDICO_HORARIO_INICIO);
                                String medico_horario_salida = medicoJson.getString(TAG_MEDICO_HORARIO_SALIDA);

                                Medico m = new Medico(id_medico, medico_primer_apellido, medico_segundo_apellido, medico_primer_nombre, medico_segundo_nombre, medico_correo_institucional, medico_correo_diario, medico_telefono_oficina, medico_telefono_movil, medico_especialidad, medico_horario_inicio, medico_horario_salida,new Consultorio(consultorio_id, consultorio_nombre, consultorio_codigo, consultorio_piso));
                                medicos.add(m);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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

    public static void getHorarioByMedicoFecha(String id_medico, String fecha_agendada, final Context context, final ServerCallback callback) {
        String url = context.getString(R.string.ip) + url_get_horarios;

        Map<String, String> params = new HashMap<String, String>();
        params.put(TAG_ID_MEDICO, id_medico);
        params.put(TAG_AGENDA_FECHA_AGENDADA, fecha_agendada);

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
                        try {
                            // Get the JSON array
                            JSONArray array = response.getJSONArray(TAG_HORARIOS);


                            // Loop through the array elements
                            for (int i = 0; i < array.length(); i++) {
                                // Get current json object
                                JSONObject horarioJson = array.getJSONObject(i);
                                // Get the current agenda (json object) data

                                int turno = Integer.parseInt(horarioJson.getString(TAG_AGENDA_TURNO));

                                horario.add(turno);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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

    public static void insertAgenda(String id_agenda, String id_beneficiario, String id_medico, String fecha_agendada, int turno, final Context context, final ServerCallback callback) {
        String url = context.getString(R.string.ip) + url_insert_agenda;

        Map<String, String> params = new HashMap<String, String>();
        params.put(TAG_AGENDA_ID, id_agenda);
        params.put(TAG_ID_BENEFICIARIO, id_beneficiario);
        params.put(TAG_ID_MEDICO, id_medico);
        params.put(TAG_AGENDA_FECHA_AGENDADA, fecha_agendada);
        params.put(TAG_AGENDA_TURNO, turno+"");


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


    public static void clearAgenda() {
        agenda.clear();
    }

    public static void clearMedicos() {
        medicos.clear();
    }

    public static void clearHorario(){horario.clear();}
}
