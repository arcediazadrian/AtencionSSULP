package edu.upb.atencionssu_lp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.upb.atencionssu_lp.Controladores.BeneficiariosDAO;
import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.Modelos.Credenciales;
import edu.upb.atencionssu_lp.Volley.VolleyRequestQueue;

public class LogInActivity extends AppCompatActivity {

    private EditText logInEditText;
    private Button logInButton;
    private Context context;
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
                String id = logInEditText.getText().toString();
                Beneficiario titular = BeneficiariosDAO.getBeneficiarioById(id, getApplicationContext());
                Credenciales.Titular = titular;
                Toast.makeText(context, "Sign in as " + titular.getNombreCompleto(), Toast.LENGTH_LONG);
                goBeneficariosScreen();
            }
        });
    }

    private void goBeneficariosScreen() {
        Intent intent = new Intent(this, InicioActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
