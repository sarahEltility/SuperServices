package com.example.sarah.superservices;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.sarah.superservices.Cleaners.CleanerActivity;
import com.example.sarah.superservices.R;


public class Home_Maintenance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__maintenance);
        final Button btnClean = (Button) findViewById(R.id.btnClean);
        final Button btnLaundry = (Button) findViewById(R.id.btnLaundry);
        final Button btnElectrician = (Button) findViewById(R.id.btnElectrician);
        final Button btnPestCont = (Button) findViewById(R.id.btnPesCont);
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), CleanerActivity.class);
                startActivity(i);

            }
        });

    }

}

