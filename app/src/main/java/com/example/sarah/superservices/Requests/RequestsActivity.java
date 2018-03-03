package com.example.sarah.superservices.Requests;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sarah.superservices.R;
import com.example.sarah.superservices.model.Connector;

public class RequestsActivity extends AppCompatActivity implements RequestViewOps,View.OnClickListener {

    //find views
    private ProgressBar progressBar;

    //recycler
    private RecyclerView recyclerView;
    private AdapterUsers_SentRequests adapter;


    Connector c;

    //object to connect with presenter
    private RequestPresenterProviderOps presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        progressBar = (ProgressBar)findViewById(R.id.progres_sent_request);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_RequestActivity);

        presenter =  new RequestPresenterImp(this);

        c = new Connector(RequestsActivity.this,recyclerView);
       c.GetMySentRequestes();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_requests);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                if(tab.getText().equals("Sent requests")) {
                    c = new Connector(RequestsActivity.this,recyclerView);
                    c.GetMySentRequestes();
                }
                else if(tab.getText().equals("Received requests")) {
                    c = new Connector(RequestsActivity.this,recyclerView);
                    c.GetComingRequestes();
            }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });


    }


    @Override
    public void onClick(View view) {

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

}
