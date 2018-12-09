package com.pnytrainings.myapplication.ui.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pnytrainings.myapplication.R;
import com.pnytrainings.myapplication.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonActivity extends AppCompatActivity {

    String myJson ;

    ArrayList<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        myJson = loadJSONFromAsset();

        parseJson(myJson);
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("contact.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



    public void parseJson(String json){

       try {
           JSONObject jsonObject = new JSONObject(json);
           JSONArray jsonArray = jsonObject.getJSONArray("contacts");

           for (int i=0 ; i<jsonArray.length();i++){

               JSONObject user = jsonArray.getJSONObject(i);

               User userData= new User();

               userData.setId(user.optString("id",""));
               userData.setGender(user.optString("gender",""));
               userData.setAddress(user.optString("address",""));
               userData.setName(user.optString("name",""));

               JSONObject phone = user.getJSONObject("phone");

               userData.setMobile(phone.optString("mobile",""));
               userData.setHome(phone.optString("home",""));
               userData.setOffice(phone.optString("office",""));

               users.add(userData);

           }


       } catch (JSONException e) {
           e.printStackTrace();
       }


   }
}
