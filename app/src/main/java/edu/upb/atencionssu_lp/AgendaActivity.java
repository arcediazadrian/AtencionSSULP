package edu.upb.atencionssu_lp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import edu.upb.atencionssu_lp.Adapters.AgendaAdapter;
import edu.upb.atencionssu_lp.Controladores.AgendaDAO;
import edu.upb.atencionssu_lp.Controladores.NavigatorDAO;
import edu.upb.atencionssu_lp.Modelos.Agenda;
import edu.upb.atencionssu_lp.Modelos.Credenciales;
import edu.upb.atencionssu_lp.Volley.ServerCallback;

public class AgendaActivity extends AppCompatActivity {

    private RecyclerView agendaRecyclerView;
    private FloatingActionButton agendarButton;
    private AgendaAdapter agendaAdapter;
    private Context context;
    private List<Agenda> agenda;
    private String fecha_filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        context = getApplicationContext();

        NavigatorDAO.setActivity(context, "agendar");
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_agendar);
        navigation.setOnNavigationItemSelectedListener(NavigatorDAO.mOnNavigationItemSelectedListener);

        agendaRecyclerView = findViewById(R.id.agendaRecyclerView);
        agendarButton = findViewById(R.id.agendarButton);

        agendaRecyclerView.setHasFixedSize(true);
        agendaRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        agendaAdapter = new AgendaAdapter(this);
        agendaRecyclerView.setAdapter(agendaAdapter);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fecha_filtro = simpleDateFormat.format(calendar.getTime());

        new LoadAgenda().execute();

        agendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MedicosActivity.class);
                startActivity(intent);
            }
        });

    }

    class LoadAgenda extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

        }

        protected Void doInBackground(Void... params) {
            AgendaDAO.getAgendaByIdBeneficiario(Credenciales.Titular.getID(), fecha_filtro, context, new ServerCallback() {
                @Override
                public void onSucces() {
                    agendaAdapter.colocarDatos(AgendaDAO.agenda);
                }

                @Override
                public void onFailure(){

                }
            });
            return null;
        }

        protected void onPostExecute(Void param) {
            AgendaDAO.clearAgenda();
        }

    }


}
