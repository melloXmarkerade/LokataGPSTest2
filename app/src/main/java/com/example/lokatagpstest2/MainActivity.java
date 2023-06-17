package com.example.lokatagpstest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button locate, cctolocation, lapulapulocation, mandauelocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locate = findViewById(R.id.Locate);
        cctolocation = findViewById(R.id.CebuCCTOButton);
        lapulapulocation = findViewById(R.id.LapuLapuBut);
        mandauelocation = findViewById(R.id.TEAMButton);

        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locatIntent = new Intent(getApplicationContext(), GPSActivity.class);
                startActivity(locatIntent);
            }
        });

        cctolocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cctoIntent = new Intent(getApplicationContext(), CCTOImpoundment.class);
                startActivity(cctoIntent);
            }
        });

        lapulapulocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lapulapuIntent = new Intent(getApplicationContext(), LapuLapuImpoundment.class);
                startActivity(lapulapuIntent);
            }
        });
        mandauelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MandaueIntent = new Intent(getApplicationContext(), MadaueTEAMLocation.class);
                startActivity(MandaueIntent);
            }
        });



    }
}