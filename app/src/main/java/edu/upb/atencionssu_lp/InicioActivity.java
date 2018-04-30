package edu.upb.atencionssu_lp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {

    private Button afiliadosButton;
    private Button agendaButton;
    private Button centrosDeAtencionButton;
    private Button emergenciaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        final Context context = getApplicationContext();

        afiliadosButton = findViewById(R.id.afiliadosButton);
        agendaButton = findViewById(R.id.agendaButton);
        centrosDeAtencionButton = findViewById(R.id.centrosDeAtencionButton);
        emergenciaButton = findViewById(R.id.emergenciaButton);

        afiliadosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BeneficiariosActivity.class);
                startActivity(intent);
            }
        });

        agendaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AgendaActivity.class);
                startActivity(intent);
            }
        });

        centrosDeAtencionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CentrosDeAtencionActivity.class);
                startActivity(intent);
            }
        });

        emergenciaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EmergenciaActivity.class);
                startActivity(intent);
            }
        });

    }
}
