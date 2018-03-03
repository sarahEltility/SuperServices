package com.example.sarah.superservices.mMySQL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sarah on 5/11/17
 */

public class Connecter {
    public static HttpURLConnection connect (String urlAddress){

        try {
            URL url = new URL(urlAddress);
            HttpURLConnection con =(HttpURLConnection) url.openConnection();
            //SET PROPERTIES
            con.setRequestMethod("GET");
            con.setConnectTimeout(2000);
            con.setReadTimeout(2000);
            con.setDoInput(true);



        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;


    }

}
