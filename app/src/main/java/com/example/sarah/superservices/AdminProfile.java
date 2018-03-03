package com.example.sarah.superservices;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class AdminProfile extends AppCompatActivity {
    private LinearLayout linearLayout;
    private LinearLayout parentLinearLayout;
    private TextView services;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        linearLayout = (LinearLayout)findViewById(R.id.linear_layout);
        services =(TextView)findViewById(R.id.txtservices);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }

        public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }
}




