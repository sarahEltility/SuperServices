package com.example.sarah.superservices.mMySQL;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sarah.superservices.mDataObject.Spacraft;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    Spinner spinner1;
    String jsonData;
    ProgressDialog pd;
    ArrayList<String> spacecrafts = new ArrayList<>();


    public DataParser(Context c, Spinner spinner1, String jsonData) {
        this.c = c;
        this.spinner1 = spinner1;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        pd.dismiss();
        if (result == 0){
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(c,"Parsed Successfully",Toast.LENGTH_SHORT).show();
            // BIND
            ArrayAdapter adapter = new ArrayAdapter(c,android.R.layout.simple_list_item_1,spacecrafts);
            spinner1.setAdapter(adapter);

        }


    }
    private int parseData()
    {
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo = null;

            spacecrafts.clear();
            Spacraft s = null;


            for (int i = 0 ; i<ja.length(); i++)
            {
                jo = ja.getJSONObject(i);
                int parnet_id = jo.getInt("parnet_id");
                String service_name = jo.getString("service_name");

                s = new Spacraft();
                s.setId(parnet_id);
                s.setName(service_name);

                spacecrafts.add(service_name);

            }
            return 1;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  0;


    }
}
