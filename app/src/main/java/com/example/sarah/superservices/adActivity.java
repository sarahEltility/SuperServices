package com.example.sarah.superservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adActivity extends AppCompatActivity {
    Context context;
    String email,pass;
    String data="";
    boolean f=true;
SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        final EditText etdEmail = (EditText) findViewById(R.id.email);
        final EditText etdPassword = (EditText) findViewById(R.id.password);
        final Button btnLogin = (Button) findViewById(R.id.loginButton);
context=this;


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etdEmail.getText().toString();
                pass = etdPassword.getText().toString();
                Login(email,pass);

            }
        });
    }

    public void Login(final String email, final String pass) {

        final String URL="http://superservices.000webhostapp.com/api/public/admin/login";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = preferences.edit();
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Login Service", "Response : "+ response);

                //parsing

                String data = ParseRegisterResponse(response);
                if(data.length()!=0){
                    if(isRegisterValid()){
                        prefEditor.putString("TOKEN",data).apply();
                        prefEditor.apply();
                        prefEditor.putString("email",email).apply();
                        prefEditor.apply();
                        prefEditor.putString("password",pass).apply();
                        prefEditor.apply();
                        ///call send to home
                        //////SEND_NAME(user.getUsername());
                        ///
                        preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        String token = preferences.getString("TOKEN","");
                        Log.i("tokkkken","hhhhhh     "+token);
                        Intent intent = new Intent(context,
                                AdminProfile.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    else {

                        Toast.makeText(context , data , Toast.LENGTH_LONG).show();
                        /// Intent intent = new Intent(context, LogInActivity.class);
                        /// context.startActivity(intent);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Login Service", "Error :Send Token Failed");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {

                    preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    // String token = preferences.getString("TOKEN","");
                    String email = preferences.getString("email","");
                    String password = preferences.getString("password","");
                    if(email.length()>0&&password.length()>0){
                        Intent back=new Intent(context,MainActivity.class);
                        //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context. startActivity(back);
                    }
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();
                }
                else if(error instanceof AuthFailureError)
                {

                    Toast.makeText(context, "Wrong Username or password!", Toast.LENGTH_LONG).show();
                    Intent back=new Intent(context,MainActivity.class);
                    //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context. startActivity(back);
                }
                else if(error instanceof TimeoutError){
                    Login(email,pass);
                }
                else {
                    Toast.makeText(context , "try again" , Toast.LENGTH_LONG).show();
                    Login(email,pass);

                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", email);
                params.put("password", pass);

                return params;

            }
        };

        Volley.newRequestQueue(context).add(request);

    }

    public String ParseRegisterResponse(String jsonFile) {

        try {

            JSONObject jsonObject = new JSONObject(jsonFile);
            if(!jsonObject.isNull("TOKEN")) {
                data = jsonObject.getString("TOKEN");

            }else if(!jsonObject.isNull("error")) {
                data = jsonObject.getString("error");
                f=false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
    public boolean isRegisterValid(){
        return f;
    }


}

