package edu.upb.atencionssu_lp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import edu.upb.atencionssu_lp.Adapters.BeneficiarioAdapter;
import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.Modelos.Credenciales;

public class BeneficiariosActivity extends AppCompatActivity {

    private RecyclerView beneficiariosTitularRecyclerView;
    private BeneficiarioAdapter beneficiarioTitularAdapter;
    private RecyclerView beneficiariosSecundariosRecyclerView;
    private BeneficiarioAdapter beneficiarioSecundariosAdapter;

    // Progress Dialog
    private ProgressDialog pDialog;

    private List<Beneficiario> beneficiarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiarios);


        if (Credenciales.Titular.getId() == 0) {
            goLogInScreen();
        }else{
            beneficiariosTitularRecyclerView = findViewById(R.id.beneficiarioTitularRecyclerView);
            beneficiariosTitularRecyclerView.setHasFixedSize(true);
            beneficiariosTitularRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            beneficiarioTitularAdapter = new BeneficiarioAdapter(this);
            beneficiariosTitularRecyclerView.setAdapter(beneficiarioTitularAdapter);
            beneficiarios = new LinkedList<>();
            beneficiarios.add(Credenciales.Titular);
            beneficiarioTitularAdapter.setBeneficiarios(beneficiarios);


            beneficiariosSecundariosRecyclerView = findViewById(R.id.beneficiarioTitularRecyclerView);
            beneficiariosSecundariosRecyclerView.setHasFixedSize(true);
            beneficiariosSecundariosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            beneficiarioSecundariosAdapter = new BeneficiarioAdapter(this);
            beneficiariosSecundariosRecyclerView.setAdapter(beneficiarioTitularAdapter);

        }


        //new LoadBeneficiarios().execute();

    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     */
    class LoadBeneficiarios extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
