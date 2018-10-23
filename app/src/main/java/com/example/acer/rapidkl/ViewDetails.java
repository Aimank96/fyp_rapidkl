package com.example.acer.rapidkl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ViewDetails extends AppCompatActivity {

    //declare
    private TextView tvUsers;
    private TextView tvId;
    private TextView tvBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        //find id
        tvUsers=(TextView)findViewById(R.id.tvUsers);
        tvId=(TextView)findViewById(R.id.tvId);
        tvBalance=(TextView)findViewById(R.id.tvBalance);


        viewIdentity();

    }
    String URL= Constans.URL+"viewDetails.php";
    private void viewIdentity() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //letak somthing untuk tau berjay
                        SetTargetText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewDetails.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){

            @Override

            protected Map<String, String> getParams() throws AuthFailureError //ni kita pass parameter dekat html
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("ID",SplashScreen.userID);


                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void SetTargetText(String response) {
        String cDetail[];
        String delimeter="!!";
        cDetail=response.split(delimeter);

        tvId.setText(""+cDetail[0]);
        tvUsers.setText(""+cDetail[1]);
        tvBalance.setText(""+cDetail[2]);
    }

    ;

}
