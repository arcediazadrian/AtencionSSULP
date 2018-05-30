package edu.upb.atencionssu_lp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.upb.atencionssu_lp.Adapters.MedicosAdapter;
import edu.upb.atencionssu_lp.Adapters.OnMedicoClickListener;
import edu.upb.atencionssu_lp.Controladores.AgendaDAO;
import edu.upb.atencionssu_lp.Controladores.NavigatorDAO;
import edu.upb.atencionssu_lp.DatePicker.DatePickerFragment;
import edu.upb.atencionssu_lp.Modelos.Medico;
import edu.upb.atencionssu_lp.Volley.ServerCallback;

public class MedicosActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private RecyclerView medicosRecyclerView;
    private MedicosAdapter medicosAdapter;
    private Context context;
    private String id_medico_escogido;
    private String fecha_agendada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos);

        context = getApplicationContext();

        NavigatorDAO.setActivity(context, "agendar");
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_agendar);
        navigation.setOnNavigationItemSelectedListener(NavigatorDAO.mOnNavigationItemSelectedListener);

        medicosRecyclerView = findViewById(R.id.medicosRecyclerView);
        medicosRecyclerView.setHasFixedSize(true);
        medicosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicosAdapter = new MedicosAdapter(this);
        medicosRecyclerView.setAdapter(medicosAdapter);

        new LoadMedicos().execute();

        medicosAdapter.setOnMedicoClickListener(new OnMedicoClickListener() {
            @Override
            public void onMedicoClick(Medico medico) {
                id_medico_escogido = medico.getID();
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    class LoadMedicos extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

        }

        protected Void doInBackground(Void... params) {
           AgendaDAO.getMedicos(context, new ServerCallback() {
               @Override
               public void onSucces() {
                   medicosAdapter.colocarDatos(AgendaDAO.medicos);
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fecha_agendada = simpleDateFormat.format(calendar.getTime());
        goHorarioPickerScreen();
    }

    private void goHorarioPickerScreen() {
        Intent intent = new Intent(context, HorarioPickerActivity.class);
        intent.putExtra("id_medico", id_medico_escogido);
        intent.putExtra("date", fecha_agendada);
        startActivity(intent);
    }
}
