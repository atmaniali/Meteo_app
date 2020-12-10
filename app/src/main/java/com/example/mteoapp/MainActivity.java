package com.example.mteoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    TextView t_date,t_city,t_c,t_temp,t_climat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t_date = findViewById(R.id.date);
        t_city = findViewById(R.id.city);
        t_c = findViewById(R.id.c_f);
        t_temp = findViewById(R.id.tempu);
        t_climat = findViewById(R.id.climat);

        find_weather();
    }

    public void find_weather(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "api.openweathermap.org/data/2.5/weather?q=London&appid=1c6b29660ec159bab8903aeb280f3884&unis=tempirial";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {Log.e(TAG+ "onResponse: (Jsnobject)",response.toString() );
                        try {
                                JSONObject jsonObject = response.getJSONObject("main");
                                JSONArray jsonArray = response.getJSONArray("weather");
                                JSONObject object = jsonArray.getJSONObject(0);
                                String temp = String.valueOf(jsonObject.get("temp"));
                                String description = object.getString("description");
                                String city = response.getString("name");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e(TAG+"onErrorResponse: ", error.toString() );
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }
}
