package com.quochung.sudungthuoc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class NoConnection extends AppCompatActivity {
    private LottieAnimationView haveconnection;
    private LottieAnimationView noconnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);
        haveconnection = findViewById(R.id.haveconnectionani);
        noconnection = findViewById(R.id.noconnectionani);
        noconnection.setVisibility(View.VISIBLE);
        haveconnection.setVisibility(View.GONE);

        Handler handler = new Handler();
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        Runnable r = new Runnable() {
            public void run() {
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    noconnection.setVisibility(View.GONE);
                    haveconnection.setVisibility(View.VISIBLE);
                    Intent intentvaoungdung = new Intent(NoConnection.this,MainActivity.class);
                    overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                    startActivity(intentvaoungdung);
                    finish();


                }
                handler.postDelayed(this, 1500);
            }
        };

        handler.postDelayed(r, 1500);

    }



}