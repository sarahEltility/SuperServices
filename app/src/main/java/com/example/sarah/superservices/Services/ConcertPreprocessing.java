package com.example.sarah.superservices.Services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.sarah.superservices.R;


public class ConcertPreprocessing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_preprocessing);
        final Button btnPhotographer = (Button)findViewById(R.id.btnPhotography);
        final Button btnCoordinated_Concert = (Button)findViewById(R.id.btnCoordinated);
        final Button btnDesignerWed = (Button)findViewById(R.id.btnDesigner);

    }

}
