package com.pnytrainings.myapplication.ui.volley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pnytrainings.myapplication.R;
import com.pnytrainings.myapplication.model.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VolleyActivity extends AppCompatActivity {

    String url = "http://epay.cybussolutions.com/Api_Service/getAllUsers";
    String sendUrl = "http://epay.cybussolutions.com/Api_Service/signupUser";
    RecyclerView userRv;

    ArrayList<Customer> customers = new ArrayList<>();
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        userRv= (RecyclerView) findViewById(R.id.userRV);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        userRv.setLayoutManager(mLayoutManager);

        signUp();
    }

    public void getData(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            //    Toast.makeText(VolleyActivity.this, response, Toast.LENGTH_SHORT).show();
                parseJson(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(VolleyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);
        requestQueue.add(stringRequest);

    }

    public void parseJson(String responce){

        try {
            JSONArray data =  new JSONArray(responce);

            for(int i=0 ; i<data.length();i++){

                JSONObject customer = data.getJSONObject(i);

                Customer myCustomer = new Customer();

                myCustomer.setFname(customer.optString("fname",""));

                myCustomer.setLname(customer.optString("lname",""));

                myCustomer.setCustomer_id(customer.optString("customer_id",""));

                myCustomer.setProfile_pic(customer.optString("profile_pic",""));

                customers.add(myCustomer);
            }

            UserAdapter userAdapter = new UserAdapter(this,customers);
            userRv.setAdapter(userAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void signUp() {


        loading = ProgressDialog.show(VolleyActivity.this, "Please wait...", "Getting Data From Server ...", false, false);

        loading.show();

        StringRequest request = new StringRequest(Request.Method.POST, sendUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                loading.dismiss();
                getData();
                Toast.makeText(VolleyActivity.this, response, Toast.LENGTH_SHORT).show();
            }

        }
                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }


        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("email", "duster@yahoo.com");
                map.put("first_name", "black");
                map.put("last_name", "duster");
                map.put("password", "1234");
                map.put("phone_number", "00000");
                map.put("address", "house 10 street 100");
                map.put("gender", "2");
                map.put("cardtype", "0");

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);
        requestQueue.add(request);

    }
}
