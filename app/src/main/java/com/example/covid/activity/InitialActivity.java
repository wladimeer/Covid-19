package com.example.covid.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.example.covid.R;

public class InitialActivity extends AppCompatActivity {

    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(checkConnection()) {
                    Intent intent = new Intent(
                            InitialActivity.this ,MainActivity.class
                    );

                    startActivity(intent);
                }

                finish();
            }
        }, 2000);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean checkConnection() {
        boolean state = false;

        ConnectivityManager manager;
        manager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        Network[] networks = manager.getAllNetworks();

        for(Network network : networks) {
            NetworkInfo info = manager.getNetworkInfo(network);

            if(info != null) {
                state = true;
            }
        }

        return state;
    }

}