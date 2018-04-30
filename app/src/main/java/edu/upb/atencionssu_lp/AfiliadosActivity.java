package edu.upb.atencionssu_lp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import edu.upb.atencionssu_lp.Adapters.AfiliadosAdapter;

public class AfiliadosActivity extends AppCompatActivity {

    private RecyclerView afiliadosRecyclerView;
    private AfiliadosAdapter afiliadosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afiliados);

        afiliadosRecyclerView = findViewById(R.id.afiliadosRecyclerView);
        afiliadosRecyclerView.setHasFixedSize(true);
        afiliadosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        afiliadosAdapter = new AfiliadosAdapter(this);
        afiliadosRecyclerView.setAdapter(afiliadosAdapter);

        loadData();
    }


    private void loadData() {

    }
}
