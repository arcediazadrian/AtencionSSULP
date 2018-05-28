package edu.upb.atencionssu_lp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.upb.atencionssu_lp.Adapters.CentrosDeAtencionAdapter;
import edu.upb.atencionssu_lp.Adapters.OnCentroDeAtencionClickListener;
import edu.upb.atencionssu_lp.Controladores.CentrosDeAtencionDAO;
import edu.upb.atencionssu_lp.Modelos.CentroDeAtencion;

public class CentrosDeAtencionActivity extends AppCompatActivity {

    private RecyclerView centrosDeAtencionRecyclerView;
    private CentrosDeAtencionAdapter centrosDeAtencionAdapter;
    //private Dialog mapDialog;
    //private Button dialogMapButton;
    //private Button dialogAtrasButton;
    //private TextView dialogDireccionTextView;


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

        centrosDeAtencionAdapter.setOnCentroDeAtencionClickListener(new OnCentroDeAtencionClickListener() {
            @Override
            public void onCentroDeAtencionClick(CentroDeAtencion centroDeAtencion) {
               /* AlertDialog.Builder builder = new AlertDialog.Builder(CentrosDeAtencionActivity.this);
                builder.setMessage("Info centro de atencion " + centroDeAtencion.getNombre())
                        .setCancelable(false)
                        .setPositiveButton("Mostrar Mapa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(CentrosDeAtencionActivity.this, CentroDeAtencionMapsActivity.class);
                            }
                        })
                        .setNegativeButton("Atras", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                */
               mapDialog(centroDeAtencion);
            }
        });
    }


    private void loadData() {
        CentrosDeAtencionDAO.loadCentrosDeAtencion();
        centrosDeAtencionAdapter.colocarDatos((ArrayList<CentroDeAtencion>) CentrosDeAtencionDAO.centroDeAtencion);
    }

    public void mapDialog(CentroDeAtencion centroDeAtencion){
        final Dialog mapDialog = new Dialog(CentrosDeAtencionActivity.this);
        mapDialog.setContentView(R.layout.dialog_centro_de_atencion);


        Button dialogMapButton = (Button)mapDialog.findViewById(R.id.cDialogMapa);
        Button dialogAtrasButton = (Button)mapDialog.findViewById(R.id.cDialogAtras);
        TextView dialogDireccionTextView = (TextView)mapDialog.findViewById(R.id.cDialogDireccion);

        dialogDireccionTextView.setText(centroDeAtencion.getDireccion());

        dialogMapButton.setEnabled(true);
        dialogAtrasButton.setEnabled(true);

        dialogMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CentrosDeAtencionActivity.this, CentroDeAtencionMapsActivity.class);
                startActivity(intent);
            }
        });

        dialogAtrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mapDialog.cancel();
            }
        });

        mapDialog.show();
    }

}
