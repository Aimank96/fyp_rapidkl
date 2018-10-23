package com.example.acer.rapidkl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class CustomerService extends AppCompatActivity implements View.OnClickListener {

    ///declare
    private TextView tvServ1;
    private TextView tvServ2;
    private TextView tvServ3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);

        //find id etFullName=(EditText)findViewById(R.id.etFullName);
        tvServ1=(TextView)findViewById(R.id.tvServ1);
        tvServ2=(TextView)findViewById(R.id.tvServ2);
        tvServ3=(TextView)findViewById(R.id.tvServ3);

    }
    final String URL=Constans.URL + "News.php";
    private void popilate() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //letak somthing untuk tau berjaya
                        tvServ1.setText(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CustomerService.this,error.toString(),Toast.LENGTH_LONG ).show();
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
    }


    @Override
    public void onClick(View v) {

    }
}
