package com.example.sarah.superservices.model;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sarah.superservices.AdminProfile;
import com.example.sarah.superservices.Cleaners.AdapterUsers_cleaners;
import com.example.sarah.superservices.Cleaners.CleanerActivity;
import com.example.sarah.superservices.Cleaners.CleanersViewOps;
import com.example.sarah.superservices.Laundary.AdapterUsers_laundary;
import com.example.sarah.superservices.Laundary.LaundaryActivity;
import com.example.sarah.superservices.Login.LoginActivity;
import com.example.sarah.superservices.Login.LoginViewOps;
import com.example.sarah.superservices.LoginAdmin.LoginAdminViewOps;
import com.example.sarah.superservices.LoginServiceProvider.LoginServiceViewOps;
import com.example.sarah.superservices.MainActivity;

import com.example.sarah.superservices.R;
import com.example.sarah.superservices.RegisterActivityies.RegisterActivity;
import com.example.sarah.superservices.RegisterActivityies.RegisterViewOps;
import com.example.sarah.superservices.RegisterServiceProvider.RegisterServiceViewOps;
import com.example.sarah.superservices.Requests.AdapterUsers_SentRequests;
import com.example.sarah.superservices.Requests.AdapterUsers_receiveRequest;
import com.example.sarah.superservices.Requests.RequestsActivity;
import com.example.sarah.superservices.UserProfile.UserProfileActivity;
import com.example.sarah.superservices.profile.profileActivity;
import com.google.android.gms.maps.GoogleMap;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mah on 2/4/2017.
 */

public class Connector {
    private  String name;
private AdapterUsers_laundary adapterUsers_laundary;
    private CleanerActivity cleanerActivity;
    private UserProfileActivity userProfileActivity;
    private profileActivity profileActivity;
private AdapterUsers_SentRequests adapterUsers_sentRequests;
    private AdapterUsers_receiveRequest adapterUsers_receiveRequest;
    //to acce
    // ss the activity view
    private RegisterViewOps registerViewOps;
    //login
    private LoginViewOps loginViewOps;
    private LoginServiceViewOps loginServiceViewOps;
    private RegisterServiceViewOps registerserviceviewops;
    private LoginAdminViewOps loginadminviewops;
private LaundaryActivity laundaryActivity;
private RequestsActivity requestsActivity;
    //follower
    private CleanersViewOps followersViewOps;

    private RecyclerView recyclerView;

    //profile
    TextView profile_name,number_follower_following,phone,city,email,dateBirth;
    ProgressBar progressBarProfile;
    de.hdodenhof.circleimageview.CircleImageView profile_img;
    //friend on map
    GoogleMap mMap;

    TextView textView;
    TextView textView2;

    Context context;

    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    ArrayList<Users> arr;

    private AdapterUsers_cleaners adapterUsers_followers;

    public Connector(Context context,RegisterViewOps registerViewOps){
        this.context = context;
        this.registerViewOps = registerViewOps;

    }
    public Connector(Context context,RequestsActivity requestsActivity){

        this.context = requestsActivity.getApplicationContext();
        this.requestsActivity = requestsActivity;
    }
    public Connector(RequestsActivity  requestsActivity, RecyclerView recyclerView){
        this.requestsActivity =requestsActivity;
        this.recyclerView = recyclerView;
        this.context = cleanerActivity.getApplicationContext();

    }
    public Connector(UserProfileActivity userProfileActivity,de.hdodenhof.circleimageview.CircleImageView profile_img , TextView profile_name,TextView phone,TextView email){
      this.userProfileActivity = userProfileActivity;
        this.profile_name = profile_name;
        this.phone = phone;
        this.email = email;
        this.profile_img = profile_img;
        this.context = userProfileActivity.getApplicationContext();

    }
    public Connector(Context context, CleanersViewOps followersViewOps){
        this.context = context;
        this.followersViewOps = followersViewOps;
    }
    public Connector(profileActivity profileActivity, ProgressBar progressBar, de.hdodenhof.circleimageview.CircleImageView profile_img , TextView profile_name, TextView number_follower_following, TextView phone, TextView city, TextView email, TextView dateBirth){
        this.profileActivity = profileActivity;
        this.profile_name = profile_name;
        this.context = profileActivity.getApplicationContext();
        this.number_follower_following = number_follower_following;
        this.phone = phone;
        this.city = city;
        this.email = email;
        this.dateBirth = dateBirth;
        this.progressBarProfile = progressBar;
        this.profile_img = profile_img;

    }

    public Connector(CleanerActivity  cleanerActivity, RecyclerView recyclerView){
        this.cleanerActivity =cleanerActivity;
        this.recyclerView = recyclerView;
        this.context = cleanerActivity.getApplicationContext();

    }

    public Connector(Context context, LoginServiceViewOps loginViewOps) {
        this.context = context;
        this.loginServiceViewOps = loginViewOps;
    }

    public Connector(Context context, LoginViewOps loginViewOps) {
        this.context=context;
        this.loginViewOps=loginViewOps;
    }

    public Connector(Context context, RegisterServiceViewOps registerViewOps)
    {
        this.context=context;
        this.registerserviceviewops=registerViewOps;

    }

    public Connector(Context context, LoginAdminViewOps loginViewOps) {
        this.context=context;
        this.loginadminviewops=loginViewOps;

    }

    public Connector(LaundaryActivity laundaryActivity, RecyclerView recyclerView) {
        this.laundaryActivity =laundaryActivity;
        this.recyclerView = recyclerView;
        this.context = laundaryActivity.getApplicationContext();


    }

    public void Register(final Users user) {

        final String URL="http://superservices.000webhostapp.com/api/public/customers/add";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = preferences.edit();

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Registration Service", "Response : "+ response);

                //parsing
                Parser parser = new Parser();
                String data = parser.ParseRegisterResponse(response);
                if(data.length()!=0){
                    if(parser.isRegisterResonseValid()){
                        prefEditor.putString("TOKEN",data).apply();
                        prefEditor.putString("email",user.getEmail()).apply();
                        prefEditor.apply();
                        prefEditor.putString("password",user.getPassword()).apply();
                        prefEditor.apply();
                        ///call send to home
                        // SEND_NAME(user.getUsername());
                        registerViewOps.hideProgress();
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                    else {
                        registerViewOps.hideProgress();
                        Toast.makeText(context , data , Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context,RegisterActivity.class);
                        context.startActivity(intent);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Registration Service", "Error :Send Token Failed");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    registerViewOps.hideProgress();
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    Toast.makeText(context, "auth error", Toast.LENGTH_LONG).show();
                    registerViewOps.hideProgress();
                }
                else {
                    registerViewOps.hideProgress();
                    Register(user);
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", user.getName());
                params.put("password", user.getPassword());
                params.put("email",user.getEmail() );
                params.put("phone",user.getPhone());

                return params;

            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    public void RegisterService(final Users user) {

        final String URL="http://superservices.000webhostapp.com/api/public/providers/add";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = preferences.edit();

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Registration Service", "Response : "+ response);

                //parsing
                Parser parser = new Parser();
                String data = parser.ParseRegisterResponse(response);
                if(data.length()!=0){
                    if(parser.isRegisterResonseValid()){
                        prefEditor.putString("TOKEN",data).apply();
                        prefEditor.putString("email",user.getEmail()).apply();
                        prefEditor.apply();
                        prefEditor.putString("password",user.getPassword()).apply();
                        prefEditor.apply();
                        ///call send to home
                        // SEND_NAME(user.getUsername());
                        registerViewOps.hideProgress();
                        Intent intent = new Intent(context, profileActivity.class);
                        context.startActivity(intent);
                    }
                    else {
                        registerViewOps.hideProgress();
                        Toast.makeText(context , data , Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context,RegisterActivity.class);
                        context.startActivity(intent);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Registration Service", "Error :Send Token Failed");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    registerViewOps.hideProgress();
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    Toast.makeText(context, "auth error", Toast.LENGTH_LONG).show();
                    //registerViewOps.hideProgress();
                }
                else {
                    //registerViewOps.hideProgress();
                    Register(user);
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", user.getName());
                params.put("password", user.getPassword());
                params.put("email",user.getEmail() );
                params.put("phone",user.getPhone());
                params.put("age",user.getAge());
                params.put("service",user.getService());

                return params;

            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    /////////////////////////////////////////////////////////Log In
    public void Login(final Users user) {

        final String URL="http://superservices.000webhostapp.com/api/public/customers/login";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = preferences.edit();
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Login Service", "Response : "+ response);

                //parsing
                Parser parser = new Parser();
                String data = parser.ParseRegisterResponse(response);
                if(data.length()!=0){
                    if(parser.isRegisterResonseValid()){
                        if(loginViewOps!=null){ loginViewOps.hideProgress();}
                        prefEditor.putString("TOKEN",data).apply();
                        prefEditor.apply();
                        prefEditor.putString("email",user.getEmail()).apply();
                        prefEditor.apply();
                        prefEditor.putString("password",user.getPassword()).apply();
                        prefEditor.apply();
                        ///call send to home
                        //////SEND_NAME(user.getUsername());
                        ///
                        preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        String token = preferences.getString("TOKEN","");
                        Log.i("tokkkken","hhhhhh     "+token);
                        Intent intent = new Intent(context, UserProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         context.startActivity(intent);
                    }
                    else {
                        loginViewOps.hideProgress();
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
                    if(loginViewOps!=null){loginViewOps.hideProgress();}
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
                     if(loginViewOps!=null){loginViewOps.hideProgress();}
                     Toast.makeText(context, "Wrong Username or password!", Toast.LENGTH_LONG).show();
                     Intent back=new Intent(context,MainActivity.class);
                     //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     context. startActivity(back);
                 }
                else if(error instanceof TimeoutError){
                    Login(user);
            }
                else {


                    loginViewOps.hideProgress();
                    Toast.makeText(context , "try again" , Toast.LENGTH_LONG).show();
                    Login(user);

                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", user.getEmail());
                params.put("password", user.getPassword());

                return params;

            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    /////////////////////////////////////////////////////////////////////////
    public void LoginAdmin(final Users user) {

        final String URL="http://superservices.000webhostapp.com/api/public/provider/login";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = preferences.edit();
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Login Service", "Response : "+ response);

                //parsing
                Parser parser = new Parser();
                String data = parser.ParseRegisterResponse(response);
                if(data.length()!=0){
                    if(parser.isRegisterResonseValid()){
                        if(loginViewOps!=null){ loginViewOps.hideProgress();}
                        prefEditor.putString("TOKEN",data).apply();
                        prefEditor.apply();
                        prefEditor.putString("email",user.getEmail()).apply();
                        prefEditor.apply();
                        prefEditor.putString("password",user.getPassword()).apply();
                        prefEditor.apply();
                        ///call send to home
                        //////SEND_NAME(user.getUsername());
                        ///
                        preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        String token = preferences.getString("TOKEN","");
                        Log.i("tokkkken","hhhhhh     "+token);
                        Intent intent = new Intent(context, AdminProfile.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    else {
                        loginViewOps.hideProgress();
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
                    if(loginViewOps!=null){loginViewOps.hideProgress();}
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
                    if(loginViewOps!=null){loginViewOps.hideProgress();}
                    Toast.makeText(context, "Wrong Username or password!", Toast.LENGTH_LONG).show();
                    Intent back=new Intent(context,MainActivity.class);
                    //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context. startActivity(back);
                }
                else if(error instanceof TimeoutError){
                    Login(user);
                }
                else {


                    loginViewOps.hideProgress();
                    Toast.makeText(context , "try again" , Toast.LENGTH_LONG).show();
                    Login(user);

                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", user.getEmail());
                params.put("password", user.getPassword());

                return params;

            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    public void LoginServicePr(final Users user) {

        final String URL="http://superservices.000webhostapp.com/api/public/admin/login";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = preferences.edit();
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Login Service", "Response : "+ response);

                //parsing
                Parser parser = new Parser();
                String data = parser.ParseRegisterResponse(response);
                if(data.length()!=0){
                    if(parser.isRegisterResonseValid()){
                        if(loginViewOps!=null){ loginViewOps.hideProgress();}
                        prefEditor.putString("TOKEN",data).apply();
                        prefEditor.apply();
                        prefEditor.putString("email",user.getEmail()).apply();
                        prefEditor.apply();
                        prefEditor.putString("password",user.getPassword()).apply();
                        prefEditor.apply();
                        ///call send to home
                        //////SEND_NAME(user.getUsername());
                        ///
                        preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        String token = preferences.getString("TOKEN","");
                        Log.i("tokkkken","hhhhhh     "+token);
                        Intent intent = new Intent(context, profileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    else {
                        loginViewOps.hideProgress();
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
                    if(loginViewOps!=null){loginViewOps.hideProgress();}
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
                    if(loginViewOps!=null){loginViewOps.hideProgress();}
                    Toast.makeText(context, "Wrong Username or password!", Toast.LENGTH_LONG).show();
                    Intent back=new Intent(context,MainActivity.class);
                    //back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context. startActivity(back);
                }
                else if(error instanceof TimeoutError){
                    Login(user);
                }
                else {


                    loginViewOps.hideProgress();
                    Toast.makeText(context , "try again" , Toast.LENGTH_LONG).show();
                    Login(user);

                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", user.getEmail());
                params.put("password", user.getPassword());

                return params;

            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    //followers-------------------------------------------------------------------------------------
    public void GetCleaners() {

        final String URL="http://superservices.000webhostapp.com/api/public/";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Followers Service", "Response : "+ response.toString());


                //parsing the json array to arraylist

                Parser parser = new Parser();

                arr = parser.ParseFollowingsResponse(response);
                Log.e("followers Service", "arr size : "+ arr.size());
                if (arr.size()!=0){


                    adapterUsers_followers = new AdapterUsers_cleaners(arr, cleanerActivity.getContext() , cleanerActivity);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cleanerActivity.getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterUsers_followers);
                    adapterUsers_followers.notifyDataSetChanged();


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("followers Service", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                  ///  context.startActivity(new Intent(context,LoginActivity.class));
                }
                else {
                    Log.e("followers Service", "Error :------ "+error.toString());
                    // GetFollowings();

                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","Bearer  "+getToken(cleanerActivity.getContext()));
                //params.put("Authorization","Bearer "+"Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYyLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9yZWdpc3RlciIsImlhdCI6MTQ4NzU5OTcwMiwiZXhwIjoxNDg3Njg2MTAyLCJuYmYiOjE0ODc1OTk3MDIsImp0aSI6ImYyZmZiMDA1NzNkYzBjYzg3ODlkMDJhYWMyN2M4ZTM2In0.gqXdZ81YBQ-_n_7jNhNODExCN3b2rh-A_zAg4cNVvCA");
                return params;
            }
        };

        Volley.newRequestQueue(cleanerActivity.getContext()).add(request);

    }
    //Search result---------------------------------------------------------------------------------
     //get laundaries
    public void GetLandary() {

        final String URL="http://superservices.000webhostapp.com/api/public/";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Followers Service", "Response : "+ response.toString());


                //parsing the json array to arraylist

                Parser parser = new Parser();

                arr = parser.ParseFollowingsResponse(response);
                Log.e("followers Service", "arr size : "+ arr.size());
                if (arr.size()!=0){


                    adapterUsers_laundary = new AdapterUsers_laundary(arr, laundaryActivity.getContext() , laundaryActivity);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cleanerActivity.getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterUsers_followers);
                    adapterUsers_followers.notifyDataSetChanged();


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("followers Service", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    ///  context.startActivity(new Intent(context,LoginActivity.class));
                }
                else {
                    Log.e("followers Service", "Error :------ "+error.toString());
                    // GetFollowings();

                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","Bearer  "+getToken(laundaryActivity.getContext()));
                //params.put("Authorization","Bearer "+"Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYyLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9yZWdpc3RlciIsImlhdCI6MTQ4NzU5OTcwMiwiZXhwIjoxNDg3Njg2MTAyLCJuYmYiOjE0ODc1OTk3MDIsImp0aSI6ImYyZmZiMDA1NzNkYzBjYzg3ODlkMDJhYWMyN2M4ZTM2In0.gqXdZ81YBQ-_n_7jNhNODExCN3b2rh-A_zAg4cNVvCA");
                return params;
            }
        };

        Volley.newRequestQueue(laundaryActivity.getContext()).add(request);

    }

    //get Sent Requests
    public void GetMySentRequestes() {

        final String URL="https://tugcce.herokuapp.com/api/getMyRequests";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Sent request Service", "Response : "+ response.toString());


                //parsing the json array to arraylist

                Parser parser = new Parser();

                arr = parser.ParseSentRequestResponse(response);
                Log.e("sent request Service", "arr size : "+ arr.size());
                if (arr.size()!=0){


                    adapterUsers_sentRequests = new AdapterUsers_SentRequests(arr,context,requestsActivity);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterUsers_sentRequests);
                    adapterUsers_sentRequests.notifyDataSetChanged();


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("sent request Service", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    /// context.startActivity(new Intent(context,LoginActivity.class));
                    Toast.makeText(context, "auth ", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("sent request Service", "Error :------ "+error.toString());


                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","Bearer  "+getToken(context));
               /// params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYxLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ4NzYyMzQ3NCwiZXhwIjoxNDg3NzA5ODc0LCJuYmYiOjE0ODc2MjM0NzQsImp0aSI6ImZlYWFhNDUwNTllYmIwNDUwZGFmZDIyOTViMTBlNGJlIn0.sY_vHlIlXs6dWkm82nDVGxsOYY4DZdIGff8r9ZIrsVM");

                return params;
            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    //cancel request
    public void CancelMySentRequestes(final String id) {

        final String URL="https://tugcce.herokuapp.com/api/cancelRequest";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("cancel request Service", "Response : "+ response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("sent request Service", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    /// context.startActivity(new Intent(context,LoginActivity.class));
                    Toast.makeText(context, "auth ", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("sent request Service", "Error :------ "+error.toString());


                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","Bearer  "+getToken(context));
                //params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYxLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ4NzYyMzQ3NCwiZXhwIjoxNDg3NzA5ODc0LCJuYmYiOjE0ODc2MjM0NzQsImp0aSI6ImZlYWFhNDUwNTllYmIwNDUwZGFmZDIyOTViMTBlNGJlIn0.sY_vHlIlXs6dWkm82nDVGxsOYY4DZdIGff8r9ZIrsVM");

                return params;
            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    //get Coming Requests
    public void GetComingRequestes() {

        final String URL="https://tugcce.herokuapp.com/api/getRequests";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("come request Service", "Response : "+ response.toString());


                //parsing the json array to arraylist

                Parser parser = new Parser();

                arr = parser.ParseSentRequestResponse(response);
                Log.e("come request Service", "arr size : "+ arr.size());
               /// if (arr.size()!=0){


                    adapterUsers_receiveRequest = new AdapterUsers_receiveRequest(arr,context,requestsActivity);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterUsers_receiveRequest);
                    adapterUsers_receiveRequest.notifyDataSetChanged();


                ///}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("come request Service", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    /// context.startActivity(new Intent(context,LoginActivity.class));
                    Toast.makeText(context, "auth ", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("come request Service", "Error :------ "+error.toString());


                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","Bearer  "+getToken(context));
               // params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYxLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ4NzYyMzQ3NCwiZXhwIjoxNDg3NzA5ODc0LCJuYmYiOjE0ODc2MjM0NzQsImp0aSI6ImZlYWFhNDUwNTllYmIwNDUwZGFmZDIyOTViMTBlNGJlIn0.sY_vHlIlXs6dWkm82nDVGxsOYY4DZdIGff8r9ZIrsVM");

                return params;
            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    //Confirm coming request
    public void ConfirmRequestes(final String id) {

        final String URL="https://tugcce.herokuapp.com/api/acceptRequest";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("accept request Service", "Response : "+ response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("accept request Service", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    /// context.startActivity(new Intent(context,LoginActivity.class));
                    Toast.makeText(context, "auth ", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("sent request Service", "Error :------ "+error.toString());


                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","Bearer "+getToken(context));
               /// params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYxLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ4NzYyMzQ3NCwiZXhwIjoxNDg3NzA5ODc0LCJuYmYiOjE0ODc2MjM0NzQsImp0aSI6ImZlYWFhNDUwNTllYmIwNDUwZGFmZDIyOTViMTBlNGJlIn0.sY_vHlIlXs6dWkm82nDVGxsOYY4DZdIGff8r9ZIrsVM");

                return params;
            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    //reject request
    public void RejectRequestes(final String id) {

        final String URL="https://tugcce.herokuapp.com/api/rejectRequest";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("reject request Service", "Response : "+ response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("reject request Service", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    /// context.startActivity(new Intent(context,LoginActivity.class));
                    Toast.makeText(context, "auth ", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("reject request Service", "Error :------ "+error.toString());


                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","Bearer "+getToken(context));
                ///params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYxLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ4NzYyMzQ3NCwiZXhwIjoxNDg3NzA5ODc0LCJuYmYiOjE0ODc2MjM0NzQsImp0aSI6ImZlYWFhNDUwNTllYmIwNDUwZGFmZDIyOTViMTBlNGJlIn0.sY_vHlIlXs6dWkm82nDVGxsOYY4DZdIGff8r9ZIrsVM");

                return params;
            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    //Send Request
    public void SendRequest(final String id) {

        final String URL="https://tugcce.herokuapp.com/api/sendRequest";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("send request Service", "Response : "+ response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("send request Service", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    /// context.startActivity(new Intent(context,LoginActivity.class));
                    Toast.makeText(context, "auth ", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("sent request Service", "Error :------ "+error.toString());


                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","Bearer  "+getToken(context));
                /// params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYxLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ4NzYyMzQ3NCwiZXhwIjoxNDg3NzA5ODc0LCJuYmYiOjE0ODc2MjM0NzQsImp0aSI6ImZlYWFhNDUwNTllYmIwNDUwZGFmZDIyOTViMTBlNGJlIn0.sY_vHlIlXs6dWkm82nDVGxsOYY4DZdIGff8r9ZIrsVM");

                return params;
            }
        };

        Volley.newRequestQueue(context).add(request);

    }
    //Set Location

    //get info
    public void GetInfo(){
        final String URL="https://tugcce.herokuapp.com/api/getInfo";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("get info request", "Response : "+ response.toString());
                progressBarProfile.setVisibility(View.GONE);
                Parser parser = new Parser();
              Users users = parser.FillProfile(response);
                profile_name.setText(users.getName());
                email.setText(users.getEmail());
                phone.setText(users.getPhone());
                number_follower_following.setText(users.getNumber_of_followers()+" Followers "+"| "+users.getNumber_of_followings()+" Followings ");
                city.setText(users.getCity());
                dateBirth.setText(users.getDate_of_birth());
               profile_img.setImageResource(0);



                    Picasso.with(profileActivity.getApplicationContext())
                            .load(users.getImage())
                            .placeholder(R.drawable.jar_loading)
                            .error(R.drawable.e)
                            .into(profile_img);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("get info request", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    /// context.startActivity(new Intent(context,LoginActivity.class));
                    Toast.makeText(context, "auth ", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("get info request", "Error :------ "+error.toString());


                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                preferences = PreferenceManager.getDefaultSharedPreferences(profileActivity.getApplicationContext());
                params.put("Authorization","Bearer  "+ preferences.getString("TOKEN",""));
               /// params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYxLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ4Nzc3NTQ3NiwiZXhwIjoxNDg3ODYxODc2LCJuYmYiOjE0ODc3NzU0NzYsImp0aSI6ImE2MjMzMTYwNjE5MmVkMjM0MTViYWVjNzllNzUxYmQwIn0.PESsA-rVi-jMmkzD8n-6NmOuDbj8BhqKILzTF5JCjI0");

                return params;
            }
        };

        Volley.newRequestQueue(profileActivity.getApplicationContext()).add(request);
    }
    //get info user profile
    public void GetInfoUserProfile(final String id){
        final String URL="https://tugcce.herokuapp.com/api/getUserInfo";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("info user_profile", "Response in user info  : "+ response.toString());
                progressBarProfile.setVisibility(View.GONE);
                Parser parser = new Parser();
                Users users = parser.FillUserProfile(response);
                profile_name.setText(users.getName());
                email.setText(users.getEmail());
                phone.setText(users.getPhone());
               /// number_follower_following.setText(users.getNumber_of_followers()+" Followers "+"| "+users.getNumber_of_followings()+" Followings ");
                city.setText(users.getCity());
                dateBirth.setText(users.getDate_of_birth());
                profile_img.setImageResource(0);



                Picasso.with(context)
                        .load(users.getImage())
                        .placeholder(R.drawable.jar_loading)
                        .error(R.drawable.e)
                        .into(profile_img);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("get info request", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    /// context.startActivity(new Intent(context,LoginActivity.class));
                    Toast.makeText(context, "auth ", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("get info request", "Error :------ "+error.toString());


                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                preferences = PreferenceManager.getDefaultSharedPreferences(context);
                params.put("Authorization","Bearer  "+ preferences.getString("TOKEN",""));
                /// params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYxLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ4Nzc3NTQ3NiwiZXhwIjoxNDg3ODYxODc2LCJuYmYiOjE0ODc3NzU0NzYsImp0aSI6ImE2MjMzMTYwNjE5MmVkMjM0MTViYWVjNzllNzUxYmQwIn0.PESsA-rVi-jMmkzD8n-6NmOuDbj8BhqKILzTF5JCjI0");

                return params;
            }
        };

        Volley.newRequestQueue(context).add(request);
    }
    //log out
    public void LogOut() {

        final String URL="https://tugcce.herokuapp.com/api/logout";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Log Out Service", "Response : "+ response.toString());
               ///
                preferences = PreferenceManager.getDefaultSharedPreferences(context);
                prefEditor = preferences.edit();
                prefEditor.putString("TOKEN","").apply();
                prefEditor.apply();
                preferences = PreferenceManager.getDefaultSharedPreferences(context);
                prefEditor = preferences.edit();
                prefEditor.putString("MessageOnMap","");
                prefEditor.apply();
                preferences = PreferenceManager.getDefaultSharedPreferences(context);
                prefEditor.putString("OnOffButtonStatus","");
                 prefEditor.apply();
                prefEditor.putString("email","").apply();
                prefEditor.apply();
                prefEditor.putString("password","").apply();
                prefEditor.apply();
/*
                preferences = PreferenceManager.getDefaultSharedPreferences(context);
                prefEditor = preferences.edit();
                prefEditor.putBoolean("token_sent", false).apply();
*/

                Intent back=new Intent(context,LoginActivity.class);
                back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
              context. startActivity(back);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Log out Service", "Error :------");
                if ( error instanceof NoConnectionError || error instanceof NetworkError) {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_LONG).show();

                }
                else if(error instanceof AuthFailureError)
                {
                    /// context.startActivity(new Intent(context,LoginActivity.class));
                    Toast.makeText(context, "auth ", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("Log out Service", "Error :------ "+error.toString());


                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;

            }
            /////////
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization","Bearer  "+getToken(context));
               // params.put("Authorization","Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjYxLCJpc3MiOiJodHRwOlwvXC90dWdjY2UuaGVyb2t1YXBwLmNvbVwvYXBpXC9sb2dpbiIsImlhdCI6MTQ4NzYyMzQ3NCwiZXhwIjoxNDg3NzA5ODc0LCJuYmYiOjE0ODc2MjM0NzQsImp0aSI6ImZlYWFhNDUwNTllYmIwNDUwZGFmZDIyOTViMTBlNGJlIn0.sY_vHlIlXs6dWkm82nDVGxsOYY4DZdIGff8r9ZIrsVM");

                return params;
            }
        };

        Volley.newRequestQueue( context).add(request);

    }
    //Edit info

    //get the token from shared preferences TO used in GetFollowings
    private String getToken(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String token = preferences.getString("TOKEN","");
        return token;
    }
    //get Location
 }
