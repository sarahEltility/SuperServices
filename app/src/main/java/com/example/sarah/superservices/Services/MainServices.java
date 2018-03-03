package com.example.sarah.superservices.Services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sarah.superservices.Home_Maintenance;
import com.example.sarah.superservices.R;

public class MainServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khalis__services);
        final Button btnHomeMaintenance = (Button) findViewById(R.id.btnHomeMaintenance);
        final Button btnConcertsPreprocessing = (Button) findViewById(R.id.btnConcertsPreprocessing);
        final Button btnCosmetology = (Button) findViewById(R.id.btnCosmetology);
        btnHomeMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HomaeMaintenanceIntent = new Intent(MainServices.this, Home_Maintenance.class);
                MainServices.this.startActivity(HomaeMaintenanceIntent);

            }
        });
        btnConcertsPreprocessing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ConcertsPreprocessingIntent = new Intent(MainServices.this, ConcertPreprocessing.class);
                MainServices.this.startActivity(ConcertsPreprocessingIntent);

            }
        });
        btnCosmetology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CosmetologyIntent = new Intent(MainServices.this, Cosmetology.class);
                MainServices.this.startActivity(CosmetologyIntent);

            }
        });


    }
}