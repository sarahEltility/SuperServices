package com.example.sarah.superservices.profile;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.sarah.superservices.MapsActivity;
import com.example.sarah.superservices.R;

import java.text.DateFormat;
import java.util.Calendar;

public class profileActivity extends AppCompatActivity {


    Button btnDate;
    TextView tvDateSelected;
    Calendar dateTime = Calendar.getInstance();
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    TextView sendlocation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        btnDate = (Button)findViewById(R.id.btnPick);
        tvDateSelected = (TextView)findViewById(R.id.tvDateSelect);
         updateTextLabel();
        btnDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View v){
                updateDate();
                updateTime();


            }

        });

        sendlocation = (TextView)findViewById(R.id.tvsendlocation);
        sendlocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent sendLocationIntent = new Intent(profileActivity.this,MapsActivity.class);
                profileActivity.this.startActivity(sendLocationIntent);

            }
        });


    }

    private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void updateTime(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){


        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            dateTime.set(Calendar.YEAR, i);
            dateTime.set(Calendar.MONTH, i1);
            dateTime.set(Calendar.DAY_OF_MONTH, i2);
            updateTextLabel();
        }
    };
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            dateTime.set(Calendar.HOUR_OF_DAY, i);
            dateTime.set(Calendar.MINUTE, i1);
            updateTextLabel();

        }
    };

    private  void updateTextLabel(){
        tvDateSelected.setText(formatDateTime.format(dateTime.getTime()));

    }

}

