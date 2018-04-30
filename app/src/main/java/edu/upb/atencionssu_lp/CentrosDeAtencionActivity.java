package edu.upb.atencionssu_lp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import edu.upb.atencionssu_lp.Adapters.CentrosDeAtencionAdapter;

public class CentrosDeAtencionActivity extends AppCompatActivity {

    private RecyclerView centrosDeAtencionRecyclerView;
    private CentrosDeAtencionAdapter centrosDeAtencionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centros_de_atencion);

        centrosDeAtencionRecyclerView = findViewById(R.id.centrosDeAtencionRecycleView);
        centrosDeAtencionRecyclerView.setHasFixedSize(true);
        centrosDeAtencionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        centrosDeAtencionAdapter = new CentrosDeAtencionAdapter(this);
        centrosDeAtencionRecyclerView.setAdapter(centrosDeAtencionAdapter);

        loadData();
    }


    private void loadData() {

    }
}
