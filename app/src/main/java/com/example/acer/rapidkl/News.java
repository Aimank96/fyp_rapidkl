package com.example.acer.rapidkl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class News extends AppCompatActivity {

    //declare
    TextView tvNews1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //find id
        tvNews1=(TextView) findViewById(R.id.tvNews1);
        populateNews();
    }

    private void populateNews() {
        final String URL=Constans.URL + "News.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //letak somthing untuk tau berjaya
                        tvNews1.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvNews1.setText(error.toString());
                    }
                }){

            @Override

            protected Map<String, String> getParams() throws AuthFailureError //ni kita pass parameter dekat html
            {
                Map<String,String> map = new HashMap<String,String>();
              return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    };

}
