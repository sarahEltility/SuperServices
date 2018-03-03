package com.example.sarah.superservices.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mah on 2/4/2017.
 */
public class Parser extends AppCompatActivity {
    String data;
    boolean f=true;

    ArrayList<Users> Users_info;

    public String ParseRegisterResponse(String jsonFile) {
            try {
                f=true;
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

    public boolean isRegisterResonseValid(){
            return f;
        }
 
    //followings Activity
    public ArrayList<Users> ParseFollowingsResponse(String jsonFile) {

       Users_info = new ArrayList<>();

        Users user;

        try {
            JSONArray ja = new JSONArray(jsonFile);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);

                //Wanted Data
                String ID = String.valueOf(jo.getInt("id"));
///                String username=jo.getString("username");
                String name =jo.getString("name");

                user = new Users();
              ////  user.setUsername(username);
                user.setName(name);
                user.setId(ID);

                Users_info.add(user);
            }

         }catch (JSONException e) {
            e.printStackTrace();
        }

        return Users_info;
    }
    //My Sent Request
    public ArrayList<Users> ParseSentRequestResponse(String jsonFile) {

    Users_info = new ArrayList<>();

    Users user;

    try {
        JSONArray ja = new JSONArray(jsonFile);
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);

            //Wanted Data
            int ID = jo.getInt("id");
            String username=jo.getString("username");
            String name =jo.getString("name");

            user = new Users();
            user.setUsername(username);
            user.setName(name);
            user.setId(String.valueOf(ID));

            Users_info.add(user);
        }

    }catch (JSONException e) {
        e.printStackTrace();
    }

    return Users_info;
}
     //profile
     public Users FillProfile(String json) {
    Users u1 = new Users();
    JSONObject jo = null;

    try {

        jo = new JSONObject(json);
        String name=jo.getString("name");
        String email=jo.getString("email");
        String phone=jo.getString("phone");
        String image=jo.getString("img_path");
        String number_of_followers = jo.getString("followers");
        String number_of_followings= jo.getString("followings");
        String city = jo.getString("city");
        String date_of_birth = jo.getString("date_of_birth");

        if(name!="null")
          u1.setName(name);
        else u1.setName("No Name");
        if(email!="null")
            u1.setEmail(email);
        else u1.setEmail("No Email");
        if(phone!="null")
            u1.setPhone(phone);
        else u1.setPhone("No Phone");
        if(image!="null")
            u1.setImage(image);
        else
            u1.setImage("http://www.planetcreation.co.uk/createpic/my-avatar.JPG");

        if(number_of_followers!="null")
            u1.setNumber_of_followers(number_of_followers);
        else
            u1.setNumber_of_followers(" 0 ");

        if(number_of_followings!="null")
            u1.setNumber_of_followings(number_of_followings);
        else
            u1.setNumber_of_followings(" 0 ");

        if(city!="null")
            u1.setCity(city);
        else
            u1.setCity("No City");

        if(date_of_birth!="null")
            u1.setDate_of_birth(date_of_birth);
        else
            u1.setDate_of_birth("No Date of birth");






    }
    catch (JSONException e) {
        e.printStackTrace();
    }
    return u1;
}
    //user profile
    public Users FillUserProfile(String json) {
        Users u1 = new Users();
        JSONObject jo = null;
        JSONObject joUser = null;

        try {

            joUser = new JSONObject(json);
            jo = joUser.getJSONObject("user");
            String name=jo.getString("name");
            String email=jo.getString("email");
            String phone=jo.getString("phone");
            String image=jo.getString("img_path");
            String msg=jo.getString("message");
            String battery=jo.getString("battery");
//            String number_of_followers = jo.getString("followers");
  //          String number_of_followings= jo.getString("followings");
            String city = jo.getString("city");
            String date_of_birth = jo.getString("date_of_birth");

            if(name!="null")
                u1.setName(name);
            else u1.setName("No Name");
            if(email!="null")
                u1.setEmail(email);
            else u1.setEmail("No Email");
            if(phone!="null")
                u1.setPhone(phone);
            else u1.setPhone("No Phone");
            if(image!="null")
                u1.setImage(image);
            else
                u1.setImage("http://www.planetcreation.co.uk/createpic/my-avatar.JPG");
            if(msg!="null"&&msg!=null)
                u1.setMessage(msg);
            else u1.setMessage("No Messages");
            if(battery!="null"&&battery!=null)
                u1.setBattery(battery);
            else u1.setBattery("Not detected");


           // if(number_of_followers!="null")
              //  u1.setNumber_of_followers(number_of_followers);
         ///   else
              //  u1.setNumber_of_followers(" 0 ");

         /*   if(number_of_followings!="null")
                u1.setNumber_of_followings(number_of_followings);
            else
                u1.setNumber_of_followings(" 0 ");
*/
            if(city!="null")
                u1.setCity(city);
            else
                u1.setCity("No City");

            if(date_of_birth!="null")
                u1.setDate_of_birth(date_of_birth);
            else
                u1.setDate_of_birth("No Date of birth");






        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return u1;
    }
    //notifications
    }


