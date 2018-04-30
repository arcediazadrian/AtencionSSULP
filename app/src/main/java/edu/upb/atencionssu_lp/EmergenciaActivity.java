package edu.upb.atencionssu_lp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmergenciaActivity extends AppCompatActivity {

    private FloatingActionButton emergenciaFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);

        emergenciaFloatingActionButton = findViewById(R.id.emergenciaFloatingActionButton);

        emergenciaFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencia();
            }
        });

    }

    private void emergencia() {
    }
}