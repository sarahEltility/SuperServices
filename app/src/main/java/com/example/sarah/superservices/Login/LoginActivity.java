package com.example.sarah.superservices.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.sarah.superservices.MainActivity;
import com.example.sarah.superservices.R;
import com.example.sarah.superservices.RegisterActivityies.RegisterActivity;

import org.json.JSONObject;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by 3zzoz on 17/02/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginViewOps, View.OnClickListener {

    //find views
    private ProgressBar progressBar;
    private EditText email;
    private EditText password;
    private TextView info,regist;

    private ImageView mprofile;
    //object to connect with presenter
    private LoginPresenterProviderOps presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);

        //init
        regist=(TextView)findViewById(R.id.regist);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_login_activity);
        email = (EditText) findViewById(R.id.email_edit_text_yourmail);
        password = (EditText) findViewById(R.id.password_edit_text_yourpass);
        info = (TextView) findViewById(R.id.info);

        mprofile = (ImageView) findViewById(R.id.imageView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ///
        findViewById(R.id.login_btn).setOnClickListener(this);
        presenter = new LoginPresenterImpl(this);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        //close the app
        //if (this.drawer.isDrawerOpen(GravityCompat.START)) {
        //  this.drawer.closeDrawer(GravityCompat.START);
        //}
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //intent.addCategory(Intent.CATEGORY_HOME);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onClick(View view) {
        if (email.getText().toString().length() == 0) {
            email.setError(getString(R.string.email_error));
        }
        if (password.getText().toString().length() == 0) {
            password.setError(getString(R.string.password_error));
        } else {
            presenter.validateCredentials(email.getText().toString(), password.getText().toString());
        }
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
    public void setUsernameError(String s) {
        email.setError(getString(R.string.email_error));
    }

    @Override
    public void setPasswordError(String s) {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void setEmailError(String s) {

    }

    @Override
    public void setPhoneError(String s) {

    }

    @Override
    public void setNameError(String s) {

    }

}
