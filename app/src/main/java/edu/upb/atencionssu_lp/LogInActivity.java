package edu.upb.atencionssu_lp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.upb.atencionssu_lp.Controladores.LogInDAO;
import edu.upb.atencionssu_lp.Modelos.Beneficiario;
import edu.upb.atencionssu_lp.Modelos.Credenciales;

public class LogInActivity extends AppCompatActivity {

    private EditText logInEditText;
    private Button logInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInEditText = findViewById(R.id.logInEditText);
        logInButton = findViewById(R.id.logInButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = logInEditText.getText().toString();
                Beneficiario titular = LogInDAO.getBeneficiarioById(id, getApplicationContext());
                Credenciales.Titular = titular;
                Log.d("Titular Log in", titular.getNombre() + " " + titular.getId());
                goBeneficariosScreen();
            }
        });
    }

    private void goBeneficariosScreen() {
        Intent intent = new Intent(this, BeneficiariosActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
