package com.quochung.sudungthuoc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Logo extends AppCompatActivity {
    private LottieAnimationView haveconnection;
    private LottieAnimationView noconnection;
    private ImageView logo;
    private boolean loaded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        haveconnection = findViewById(R.id.haveconnectionani);
        noconnection = findViewById(R.id.noconnectionani);
        logo = findViewById(R.id.logo);
        noconnection.setVisibility(View.GONE);
        haveconnection.setVisibility(View.GONE);
        loaded = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                Intent intentvaoungdung = new Intent(Logo.this,MainActivity.class);
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                startActivity(intentvaoungdung);
                finish();
            }
            else {
                Handler handler = new Handler();
                logo.setVisibility(View.GONE);
                noconnection.setVisibility(View.VISIBLE);
                Runnable r = new Runnable() {
                    public void run() {


                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            if (loaded == false) {
                                loaded = true;
                                noconnection.setVisibility(View.GONE);

                                haveconnection.setVisibility(View.VISIBLE);
                                Intent intentvaoungdung = new Intent(Logo.this,MainActivity.class);
                                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                                startActivity(intentvaoungdung);


                            }
                        }
                        handler.postDelayed(this, 3500);
                    }
                };

                handler.postDelayed(r, 3500);

            }
            }

        }, 650);

    }
}