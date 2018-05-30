package edu.upb.atencionssu_lp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.upb.atencionssu_lp.Adapters.HorariosAdapter;
import edu.upb.atencionssu_lp.Adapters.MedicosAdapter;
import edu.upb.atencionssu_lp.Adapters.OnHorarioClickListener;
import edu.upb.atencionssu_lp.Controladores.AgendaDAO;
import edu.upb.atencionssu_lp.Controladores.NavigatorDAO;
import edu.upb.atencionssu_lp.Modelos.Credenciales;
import edu.upb.atencionssu_lp.Modelos.Horario;
import edu.upb.atencionssu_lp.Volley.ServerCallback;

public class HorarioPickerActivity extends AppCompatActivity {

    private Context context;
    private HorariosAdapter horariosAdapter;
    private RecyclerView horariosRecyclerView;
    private String id_medico;
    private String fecha_agendada;
    private int turno_seleccionado;
    private String timestamp_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_picker);

        context = getApplicationContext();

        NavigatorDAO.setActivity(context, "agendar");
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_agendar);
        navigation.setOnNavigationItemSelectedListener(NavigatorDAO.mOnNavigationItemSelectedListener);

        id_medico = getIntent().getStringExtra("id_medico");
        fecha_agendada = getIntent().getStringExtra("date");

        horariosRecyclerView = findViewById(R.id.horariosRecyclerView);
        horariosRecyclerView.setHasFixedSize(true);
        horariosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        horariosAdapter = new HorariosAdapter(this);
        horariosRecyclerView.setAdapter(horariosAdapter);

        Long tsLong = System.currentTimeMillis()/1000;
        timestamp_id = tsLong.toString();
        Log.d("timestamp", timestamp_id);

        new LoadHorario().execute();

        AlertDialog.Builder builder = new AlertDialog.Builder(HorarioPickerActivity.this);
        builder.setMessage("Seguro que desea agendar a esta hora?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new AddAgenda().execute();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();


        horariosAdapter.setOnHorarioClickListener(new OnHorarioClickListener() {
            @Override
            public void onHorarioClick(Horario horario) {
                turno_seleccionado = horario.turno;
                alert.show();
                //new AddAgenda().execute();
            }
        });


    }


    class LoadHorario extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

        }

        protected Void doInBackground(Void... params) {
            AgendaDAO.getHorarioByMedicoFecha(id_medico, fecha_agendada, context, new ServerCallback() {
                @Override
                public void onSucces() {
                    List<Integer> turnos = discardTurnos(AgendaDAO.horario);
                    Log.d("turnos size", turnos.size()+"");
                    List<Horario> horario = generateHorario(turnos);
                    horariosAdapter.colocarDatos(horario);
                }

                @Override
                public void onFailure(){

                }
            });
            return null;
        }

        protected void onPostExecute(Void param) {
            AgendaDAO.clearMedicos();
        }

    }

    class AddAgenda extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

        }

        protected Void doInBackground(Void... params) {
            AgendaDAO.insertAgenda(timestamp_id, Credenciales.Titular.getID(), id_medico, fecha_agendada, turno_seleccionado, context, new ServerCallback() {
                @Override
                public void onSucces() {
                    Intent intent = new Intent(context, AgendaActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(){

                }
            });
            return null;
        }

        protected void onPostExecute(Void param) {
            AgendaDAO.clearMedicos();
        }

    }

    private List<Integer> discardTurnos(List<Integer> horario) {
        List<Integer> turnos = new ArrayList<>();
        for(int i = 0; i <= 48; i++){
            turnos.add(i);
        }

        turnos.removeAll(horario);
        return turnos;
    }

    private List<Horario> generateHorario(List<Integer> turnos){
        List<Horario> horario = new ArrayList<>();
        for (int i = 0; i < turnos.size(); i++){
            horario.add(new Horario(turnos.get(i)));
        }

        return horario;
    }

}
