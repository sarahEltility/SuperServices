package com.example.sarah.superservices.RegisterActivityies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sarah.superservices.MainActivity;
import com.example.sarah.superservices.R;

public class RegisterActivity extends AppCompatActivity implements RegisterViewOps,View.OnClickListener {

//find views
private ProgressBar progressBar;
private Button Submit;
private EditText name,email,phone,password;
    private TextView hint;
    Context mcontext;


//object to connect with presenter
private RegisterPresenterProvidedOps presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressBar = (ProgressBar) findViewById(R.id.progres);
        Submit=(Button)findViewById(R.id.Register_button_register_activity);
        name=(EditText)findViewById(R.id.name_edit_text_yourname);
        email=(EditText)findViewById(R.id.email_edit_text_yourmail);
        password = (EditText) findViewById(R.id.password_edit_text_yourpass);
        hint=(TextView)findViewById(R.id.hint);
        phone=(EditText)findViewById(R.id.phone_edit_text_yourphone);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ///
        Submit.setOnClickListener(this);
        presenter =  new RegisterPresenterImpl(this);
        TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        //String mPhoneNumber = tMgr.getLine1Number();
        //phone.setText(mPhoneNumber);
    }

    @Override
    public void onClick(View view) {
        showProgress();
        if(name.getText().toString().length()==0){
            name.setError(getString(R.string.name_error));
            hint.setText("Name cannot be empty");
            hideProgress();
        }
        else if(email.getText().toString().length()==0){
            email.setError(getString(R.string.email_error));
            hint.setText("Email cannot be empty");
            hideProgress();
        }
        else if(!isEmailValid(email.getText().toString()))
        {
            email.setError("Invalid email");
            hint.setText("Invalid email");
            hideProgress();
        }
        else if(password.getText().toString().length()==0){
            password.setError(getString(R.string.password_error));
            hint.setText("Password cannot be empty");
            hideProgress();
        }

        else if(!isPasswordValid(password.getText().toString()))
        {
            password.setError("Invalid pass");
            hint.setText("Invalid password");
            hideProgress();
        }
        else {

            presenter.validateCredentials(name.getText().toString(),phone.getText().toString(),email.getText().toString(),password.getText().toString());
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
    public void setPasswordError(String s) {

    }

    @Override
    public void setEmailError(String s) {

    }


    @Override
    public void setNameError(String s) {

    }

    @Override
    public  boolean isPasswordValid(String password){
        return password.matches("(?=.*[a-z-A-Z]).{8,}");
    }

    @Override
    public boolean isEmailValid(CharSequence email) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }


    @Override
    public void navigateToHome() {

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
}
