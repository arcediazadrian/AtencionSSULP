package edu.upb.atencionssu_lp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import edu.upb.atencionssu_lp.Adapters.BeneficiarioAdapter;

public class BeneficiariosActivity extends AppCompatActivity {

    private RecyclerView beneficiariosRecyclerView;
    private BeneficiarioAdapter beneficiarioAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiarios);

        beneficiariosRecyclerView = findViewById(R.id.beneficiariosSecundariosRecyclerView);
        beneficiariosRecyclerView.setHasFixedSize(true);
        beneficiariosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        beneficiarioAdapter = new BeneficiarioAdapter(this);
        beneficiariosRecyclerView.setAdapter(beneficiarioAdapter);

        loadData();
    }


    private void loadData() {

    }
}
