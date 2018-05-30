package edu.upb.atencionssu_lp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.upb.atencionssu_lp.Controladores.AgendaDAO;
import edu.upb.atencionssu_lp.Controladores.BeneficiariosDAO;
import edu.upb.atencionssu_lp.Controladores.NavigatorDAO;
import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.Modelos.Credenciales;
import edu.upb.atencionssu_lp.Volley.ServerCallback;

public class LogInActivity extends AppCompatActivity {

    private EditText logInEditText;
    private Button logInButton;
    private Context context;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        context = getApplicationContext();

        logInEditText = findViewById(R.id.logInEditText);
        logInButton = findViewById(R.id.logInButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = logInEditText.getText().toString();

            }
        });
    }

    private void goInicioScreen() {
        Intent intent = new Intent(this, InicioActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    class LoadBeneficiarios extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

        }

        protected Void doInBackground(Void... params) {
            BeneficiariosDAO.getBeneficiarioById(id, context, new ServerCallback() {
                @Override
                public void onSucces() {
                    Credenciales.Titular = BeneficiariosDAO.beneficiario;
                    Toast.makeText(context, "Sign in as " + BeneficiariosDAO.beneficiario.getNombreCompleto(), Toast.LENGTH_LONG);
                    goInicioScreen();
                }

                @Override
                public void onFailure(){

                }
            });
            return null;
        }

        protected void onPostExecute(Void param) {
            BeneficiariosDAO.clearBeneficiarios();
        }

    }


}
