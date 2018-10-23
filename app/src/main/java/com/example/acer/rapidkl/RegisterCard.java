package com.example.acer.rapidkl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class RegisterCard extends AppCompatActivity implements View.OnClickListener{

    private EditText etCardNumber;
    private EditText etExpiry;
    private EditText etCVV;
    private Button btnSaveCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_card);

        etCardNumber=(EditText)findViewById(R.id.etCardNumber);
        etExpiry=(EditText)findViewById(R.id.etExpiry);
        etCVV=(EditText)findViewById(R.id.etCVV);
        btnSaveCard=(Button)findViewById(R.id.btnSaveCard);

        btnSaveCard.setOnClickListener(this);
        }



    @Override
    public void onClick(View v) {
        String CardNumb=etCardNumber.getText().toString();
        String Expiry=etExpiry.getText().toString();
        String CVV=etCVV.getText().toString();
        if(v==btnSaveCard){
            RegistersCard(CardNumb,Expiry,CVV);
        }

    }
    final String URL=Constans.URL+"RegisterCreditCard.php";
    private void RegistersCard(final String CardNumber, final String Expiry, final String CVV) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Intent intent=new Intent(RegisterCard.this,UserSpace.class);
                        intent.putExtra("KEY_ID",SplashScreen.userID);
                        startActivity(intent);
                        Toast.makeText(RegisterCard.this,response.toString(),Toast.LENGTH_LONG).show();
                        //letak somthing untuk tau berjaya

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterCard.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){

            @Override

            protected Map<String, String> getParams() throws AuthFailureError //ni kita pass parameter dekat html
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("CardNumb",CardNumber);
                map.put("Expiry",Expiry);
                map.put("CVV",CVV);
                map.put("Identity",SplashScreen.userID);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
