package com.example.sarah.superservices.UserProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sarah.superservices.R;
import com.example.sarah.superservices.Services.MainServices;
import com.example.sarah.superservices.model.Connector;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    String id, isFollowing, name;

    Connector connector;

    //views
    TextView user_profile_name, user_number_follower_following, user_phone, user_city, user_email, user_dateBirth;
    ProgressBar progressBar;
    de.hdodenhof.circleimageview.CircleImageView user_profile_img;
    Button user_profile_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user_profile_btn = (Button) findViewById(R.id.user_profile_btn);

        id = getIntent().getStringExtra("id");
        isFollowing = getIntent().getStringExtra("isFollowing");
        name = getIntent().getStringExtra("name");
        //    if(getIntent().getStringExtra("btn")!=null&&getIntent().getStringExtra("btn").equals("unfollow")){
        //     user_profile_btn.setText("Unfollow");
        //   user_profile_btn.setTextColor(Color.parseColor("#f73e66"));
        // user_profile_btn.setBackgroundResource(R.drawable.unfollow_rounded);
        //}
        user_profile_btn.setOnClickListener(this);
        user_profile_name = (TextView) findViewById(R.id.userprofile_name);
        user_phone = (TextView) findViewById(R.id.usertextView_phone);
        user_profile_img = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.userprofile_image);
        connector = new Connector(this, user_profile_img, user_profile_name, user_phone, user_email);
       /*
        if (isFollowing != null) {
            if (isFollowing.equals("1")) {
                user_profile_btn.setText("UnFollow");
                connector.GetInfoUserProfile(id);
            } else {
                user_profile_name.setText(name);
                user_number_follower_following.setText("follow him to see more info");
                progressBar.setVisibility(View.GONE);
            }
        } else {
            ///
            user_profile_name.setText(name);
            user_number_follower_following.setText(" ");
            progressBar.setVisibility(View.GONE);


        }
*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplication(), MainServices.class);
        startActivity(i);
    }
}
