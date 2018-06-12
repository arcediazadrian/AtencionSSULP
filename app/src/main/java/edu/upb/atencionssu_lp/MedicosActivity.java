package edu.upb.atencionssu_lp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import edu.upb.atencionssu_lp.Adapters.BeneficiarioAdapter;
import edu.upb.atencionssu_lp.Adapters.MedicosAdapter;
import edu.upb.atencionssu_lp.Adapters.OnBeneficiarioClickListener;
import edu.upb.atencionssu_lp.Adapters.OnMedicoClickListener;
import edu.upb.atencionssu_lp.Controladores.AgendaDAO;
import edu.upb.atencionssu_lp.Controladores.NavigatorDAO;
import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.Modelos.Credenciales;
import edu.upb.atencionssu_lp.Modelos.Medico;
import edu.upb.atencionssu_lp.Volley.ServerCallback;

public class MedicosActivity extends AppCompatActivity{

    private RecyclerView medicosRecyclerView;
    private MedicosAdapter medicosAdapter;
    private Context context;
    private String id_medico_escogido;
    private int turno_entrada_medico, turno_salida_medico;
    private String fecha_agendada;
    private String beneficiarioEscogido;

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

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fecha_agendada = simpleDateFormat.format(calendar.getTime());

        Log.d("Fecha a agendar",fecha_agendada);

        medicosAdapter.setOnMedicoClickListener(new OnMedicoClickListener() {
            @Override
            public void onMedicoClick(Medico medico) {
                id_medico_escogido = medico.getID();
                turno_entrada_medico = medico.getTurnoEntrada();
                turno_salida_medico = medico.getTurnoSalida();
                goHorarioPickerScreen();
            }
        });

        escogerBeneficiarioDialog();
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


    private void goHorarioPickerScreen() {
        Intent intent = new Intent(context, HorarioPickerActivity.class);
        intent.putExtra("id_medico", id_medico_escogido);
        intent.putExtra("turno_entrada", turno_entrada_medico);
        intent.putExtra("turno_salida", turno_salida_medico);
        intent.putExtra("date", fecha_agendada);
        intent.putExtra("beneficiairo", beneficiarioEscogido);
        startActivity(intent);
    }

    public void escogerBeneficiarioDialog(){
        final Dialog escogerBeneficiarioDialog = new Dialog(MedicosActivity.this);
        escogerBeneficiarioDialog.setContentView(R.layout.dialog_escoger_beneficiario);
        escogerBeneficiarioDialog.setTitle("Escoger beneficiario");
        //escogerBeneficiarioDialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //escogerBeneficiarioDialog.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        LinearLayout escogerTitular = (LinearLayout) escogerBeneficiarioDialog.findViewById(R.id.escogerBeneficiarioLayout);
        TextView nombreTitular = (TextView) escogerBeneficiarioDialog.findViewById(R.id.escogerNombreBeneficiarioTextView);
        TextView idTitular = (TextView) escogerBeneficiarioDialog.findViewById(R.id.escogerIdBeneficiarioTextView);

        nombreTitular.setText(Credenciales.Titular.getNombre() + "\n" + Credenciales.Titular.getApellido());
        idTitular.setText(Credenciales.Titular.getMatricula());

        escogerTitular.setEnabled(true);

        escogerTitular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beneficiarioEscogido = Credenciales.Titular.getID();
                escogerBeneficiarioDialog.cancel();
            }
        });

        if(Credenciales.Secuandarios.size() > 0) {


            RecyclerView beneficiariosSecundarios = (RecyclerView) escogerBeneficiarioDialog.findViewById(R.id.beneficiarioTitularRecyclerView);
            beneficiariosSecundarios.setHasFixedSize(true);
            beneficiariosSecundarios.setLayoutManager(new LinearLayoutManager(this));

            BeneficiarioAdapter adapter = new BeneficiarioAdapter(MedicosActivity.this);
            beneficiariosSecundarios.setAdapter(adapter);
            List<Beneficiario> beneficiarios = new LinkedList<>();
            beneficiarios.addAll(Credenciales.Secuandarios);
            adapter.colocarDatos(beneficiarios);

            beneficiariosSecundarios.setEnabled(true);

            adapter.setOnBeneficiarioClickListener(new OnBeneficiarioClickListener() {
                @Override
                public void onBeneficiarioClick(Beneficiario beneficiario) {
                    beneficiarioEscogido = beneficiario.getID();
                    escogerBeneficiarioDialog.cancel();
                }
            });

        }

        escogerBeneficiarioDialog.show();
    }
}
