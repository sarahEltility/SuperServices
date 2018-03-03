package com.example.sarah.superservices;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sarah.superservices.Login.LoginActivity;
import com.example.sarah.superservices.Services.MainServices;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnCustomer = (Button)findViewById(R.id.btncustomer);
        final Button btnServiceProvider = (Button)findViewById(R.id.btnServiceProvider);
        final TextView tvAdminstration = (TextView)findViewById(R.id.tvAdministration);
        final TextView textview = (TextView)findViewById(R.id.textView2) ;

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ServicesIntent = new Intent(MainActivity.this,MainServices.class);
                MainActivity.this.startActivity(ServicesIntent);
            }
        });

        tvAdminstration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adminIntent = new Intent(MainActivity.this, adActivity.class);
                MainActivity.this.startActivity(adminIntent);
            }
        });



        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CustomerIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(CustomerIntent);

            }
        });


        btnServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SuperProviderIntent = new Intent(MainActivity.this, com.example.sarah.superservices.LoginServiceProvider.LoginActivity.class);
                MainActivity.this.startActivity(SuperProviderIntent);

            }
        });


    }
}


