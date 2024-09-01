package com.example.gpskhushi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity implements LocationListener {

    Button btArea, btCity, btState, btCountry, btPin,btShow;
    boolean isNet, isGPS;
    double latitude, longitude;
    LocationManager man;
    Location loc;
    Geocoder geo;
    Address add;
    List<Address> list;
    String area,city,state,country,pin;
    MySqlHelper ms;
    Calendar cd;
    String date,time;
    int sr=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ms = new MySqlHelper(getApplicationContext());
        ms.checkDatabase();
        btArea = findViewById(R.id.btArea);
        btCity = findViewById(R.id.btCity);
        btState = findViewById(R.id.btState);
        btCountry = findViewById(R.id.btCountry);
        btPin = findViewById(R.id.btPin);
        btShow = findViewById(R.id.btShow);

        man = (LocationManager) getSystemService(LOCATION_SERVICE);
        isNet = man.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        isGPS = man.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isNet || isGPS) {
            if (isNet) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                man.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 6000, 10, this);
                if(man!=null)
                {
                    loc = man.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(loc!=null)
                    {
                        latitude = loc.getLatitude();
                        longitude = loc.getLongitude();
                    }
                }
            }
            if(isGPS)
            {
                man.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,10,this);
                if(man!=null)
                {
                    loc = man.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(loc!=null)
                    {
                        latitude = loc.getLatitude();
                        longitude = loc.getLongitude();
                    }
                }
            }
            Toast.makeText(getApplicationContext(),"Latitude : "+latitude+"\nLongitude : "+longitude,Toast.LENGTH_LONG).show();

            try
            {
                geo = new Geocoder(this, Locale.getDefault());
                list = geo.getFromLocation(latitude,longitude,1);
                add = list.remove(0);

                area = add.getAddressLine(0);
                city = add.getLocality();
                state = add.getAdminArea();
                country = add.getCountryName();
                pin = add.getPostalCode();

                btArea.setText(area);
                btCity.setText(city);
                btState.setText(state);
                btCountry.setText(country);
                btPin.setText(pin);

            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }

        }

        btArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),area,Toast.LENGTH_SHORT).show();
            }
        });

        btCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),city,Toast.LENGTH_SHORT).show();
            }
        });

        btState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),state,Toast.LENGTH_SHORT).show();
            }
        });

        btCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_SHORT).show();
            }
        });

        btPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),pin,Toast.LENGTH_SHORT).show();
                ms.deleteValues();
            }
        });

        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(),ShowActivity.class);
                startActivity(ii);
            }
        });



    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

        if (isNet) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            man.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 6000, 10, this);
            if(man!=null)
            {
                loc = man.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if(loc!=null)
                {
                    latitude = loc.getLatitude();
                    longitude = loc.getLongitude();
                }
            }
        }
        if(isGPS)
        {
            man.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,10,this);
            if(man!=null)
            {
                loc = man.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(loc!=null)
                {
                    latitude = loc.getLatitude();
                    longitude = loc.getLongitude();
                }
            }
        }
        Toast.makeText(getApplicationContext(),"Latitude : "+latitude+"\nLongitude : "+longitude,Toast.LENGTH_LONG).show();

        try
        {
            geo = new Geocoder(this,Locale.getDefault());
            list = geo.getFromLocation(latitude,longitude,1);
            add = list.remove(0);

            btArea.setText(add.getAddressLine(0));
            btCity.setText(add.getLocality());
            btState.setText(add.getAdminArea());
            btCountry.setText(add.getCountryName());
            btPin.setText(add.getPostalCode());
            sr++;
            cd = Calendar.getInstance();
            date = ""+cd.get(Calendar.DATE);
            time = "" + cd.get(Calendar.HOUR) + ":" + String.format("%02d", cd.get(Calendar.MINUTE)) + ":" + String.format("%02d", cd.get(Calendar.SECOND));
            String area = add.getAddressLine(0);
            city = add.getLocality();
            pin = add.getPostalCode();

            ms.insertValues(""+sr,date,time,area,city,pin);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}