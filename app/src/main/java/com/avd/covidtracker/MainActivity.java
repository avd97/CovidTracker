package com.avd.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,StateCases.class));
                finish();
            }
        },SPLASH_OUT);

        checkConnection();
    }

    public void checkConnection() {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(null != networkInfo){
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
//                Toast.makeText(this, "Wifi Enabled", Toast.LENGTH_SHORT).show();
            }
            else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
//                Toast.makeText(this, "Mobile Data Enabled", Toast.LENGTH_SHORT).show();
            }
        }
        else {
//            Toast.makeText(this, "No Network found !", Toast.LENGTH_SHORT).show();
        }
    }
}