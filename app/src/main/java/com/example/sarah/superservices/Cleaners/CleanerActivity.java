package com.example.sarah.superservices.Cleaners;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sarah.superservices.MainActivity;
import com.example.sarah.superservices.R;
import com.example.sarah.superservices.model.Connector;

public class CleanerActivity extends AppCompatActivity implements CleanersViewOps,View.OnClickListener {

    //find views
    private ProgressBar progressBar;
Connector c;
    //recycler
    private RecyclerView recyclerView;
    private AdapterUsers_cleaners adapter;

        //object to connect with presenter
    private CleanerPresenterProviderOps presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follower_nav_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //init views
        //progressBar = (ProgressBar)findViewById(R.id.progress_bar_following_activity);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_followers);

        presenter =  new CleanerPresenterImpl(this);

        c = new Connector(this,recyclerView);
        c.GetCleaners();

    }
    private void LogoutAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("All Followers can see your location")
                .setCancelable(false)
                //add event to left button
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // onOffButton.setText("ON");
                                c.LogOut();
                            }
                        });
        //add event to right button
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    @Override
    public void onClick(View view) {

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
        // super.onBackPressed();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void  setRecyclerAdapter(AdapterUsers_cleaners adapter) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter.notifyDataSetChanged();
    }

}
