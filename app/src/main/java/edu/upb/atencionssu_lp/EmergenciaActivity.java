package edu.upb.atencionssu_lp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.upb.atencionssu_lp.Controladores.EmergenciaDAO;
import edu.upb.atencionssu_lp.Controladores.NavigatorDAO;
import edu.upb.atencionssu_lp.Modelos.Credenciales;
import edu.upb.atencionssu_lp.Volley.ServerCallback;

public class EmergenciaActivity extends AppCompatActivity implements LocationListener {
    protected LocationManager locationManager;
    protected Context context;
    private String latlng;

    private TextView emergenciaTextView;

    private FloatingActionButton emergenciaFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);

        context = getApplicationContext();

        NavigatorDAO.setActivity(context, "emergencia");
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_emergencia);
        navigation.setOnNavigationItemSelectedListener(NavigatorDAO.mOnNavigationItemSelectedListener);

        emergenciaTextView = findViewById(R.id.emergenciaTextView);
        emergenciaTextView.setText("Por favor espere mientras hallamos su localizacion...");

        emergenciaFloatingActionButton = findViewById(R.id.emergenciaFloatingActionButton);
        emergenciaFloatingActionButton.setEnabled(false);
        emergenciaFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        emergenciaFloatingActionButton.setAlpha(0.25f);

        emergenciaFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencia();
            }
        });


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    private void emergencia() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha_emergencia = simpleDateFormat.format(calendar.getTime());

        EmergenciaDAO.insertEmergencia(Credenciales.Titular.getID(), latlng, fecha_emergencia, context, new ServerCallback() {
            @Override
            public void onSucces() {
                makeCall();
            }

            @Override
            public void onFailure() {
                makeCall();
            }
        });

    }

    public void makeCall(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:70198194"));

        if (ActivityCompat.checkSelfPermission(EmergenciaActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        latlng = lat + " , " + lng;


        emergenciaTextView.setText("Localizacion Encontrada!");
        emergenciaFloatingActionButton.setEnabled(true);
        emergenciaFloatingActionButton.setAlpha(1f);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

}