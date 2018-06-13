package edu.upb.atencionssu_lp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.List;

import edu.upb.atencionssu_lp.Adapters.BeneficiarioAdapter;
import edu.upb.atencionssu_lp.Controladores.NavigatorDAO;
import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.Modelos.Credenciales;

public class BeneficiariosActivity extends AppCompatActivity {

    private RecyclerView beneficiariosTitularRecyclerView;
    private BeneficiarioAdapter beneficiarioTitularAdapter;
    private RecyclerView beneficiariosSecundariosRecyclerView;
    private BeneficiarioAdapter beneficiarioSecundariosAdapter;
    private Context context;

    private boolean titular = false;

    // Progress Dialog
    private ProgressDialog pDialog;

    private List<Beneficiario> beneficiarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiarios);

        context = getApplicationContext();

        NavigatorDAO.setActivity(context, "beneficiarios");
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_beneficiarios);
        navigation.setOnNavigationItemSelectedListener(NavigatorDAO.mOnNavigationItemSelectedListener);

        if (Integer.parseInt(Credenciales.Titular.getID()) == 0) {
            goLogInScreen();
        } else {

            beneficiariosTitularRecyclerView = findViewById(R.id.beneficiarioTitularRecyclerView);
            beneficiariosTitularRecyclerView.setHasFixedSize(true);
            beneficiariosTitularRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            beneficiarioTitularAdapter = new BeneficiarioAdapter(this);
            beneficiariosTitularRecyclerView.setAdapter(beneficiarioTitularAdapter);
            beneficiarios = new LinkedList<>();
            beneficiarios.add(Credenciales.Titular);
            beneficiarioTitularAdapter.colocarDatos(beneficiarios);


            beneficiariosSecundariosRecyclerView = findViewById(R.id.beneficiarioTitularRecyclerView);
            beneficiariosSecundariosRecyclerView.setHasFixedSize(true);
            beneficiariosSecundariosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            beneficiarioSecundariosAdapter = new BeneficiarioAdapter(this);
            beneficiariosSecundariosRecyclerView.setAdapter(beneficiarioTitularAdapter);

        }

    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.desvincular:
                Credenciales.desvincular();
                goInicioScreen();

              return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goInicioScreen() {
        Intent intent = new Intent(this, InicioActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
