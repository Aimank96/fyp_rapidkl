package com.example.acer.rapidkl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopupCredit extends AppCompatActivity implements View.OnClickListener{

    //declare
    private Spinner spnReload;
    private Spinner spnPayMethod;
    private Button btnPayNow;
    private TextView tvReload;
    private TextView tvPayMethod;
    private TextView tvNewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_credit);



            //Untuk mencari ID
            spnReload=(Spinner) findViewById(R.id.spnReload);
            spnPayMethod=(Spinner) findViewById(R.id.spnPayMethod);
            btnPayNow=(Button) findViewById(R.id.btnPayNow);
            tvReload=(TextView) findViewById(R.id.tvReload);
            tvPayMethod=(TextView) findViewById(R.id.tvPayMethod);
            tvNewCard=(TextView) findViewById(R.id.tvNewCard);

            //make function
            tvNewCard.setOnClickListener(this);
            btnPayNow.setOnClickListener(this);

        populateReload();
        //retrieve lbh dr 1 data (JSON=javascript objek notation)
        GetdataJSON();
       // populatePaymentMethod();

        }
    String URL=Constans.URL + "JsonPaymentMethod.php";
    private void GetdataJSON(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //letak somthing untuk tau berjay
                       populatePaymentMethod(response);

                }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TopupCredit.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){

            @Override

            protected Map<String, String> getParams() throws AuthFailureError //ni kita pass parameter dekat html
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("UserID",SplashScreen.userID);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void populateReload() {
        //make a list data
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("RM10");
        spinnerArray.add("RM30");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnReload.setAdapter(adapter);
    }
    List<String> spinnerArray =  new ArrayList<String>();
    private void populatePaymentMethod(String response) {
        try {
            JSONObject jsonRootObject = new JSONObject(response);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("result");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(jsonObject.optString("ID").toString());
                String CardNumber = jsonObject.optString("CardNumber").toString();
                String Expiry = jsonObject.optString("Expiry").toString();

                spinnerArray.add(CardNumber);
                //System.out.println(Expiryw);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPayMethod.setAdapter(adapter);

        } catch (JSONException e) {e.printStackTrace();}
    }


    @Override
    public void onClick(View v) {
        String Reload;
        String CardNumber;

        if(v==tvNewCard){
            Intent intent = new Intent(TopupCredit.this, RegisterCard.class);
            startActivity(intent);
        }
        if(v==btnPayNow){
            Reload= (String) spnReload.getSelectedItem();
            Reload=Reload.substring(2);
            CardNumber=(String)spnPayMethod.getSelectedItem();
            //Toast.makeText(TopupCredit.this,CardNumber+"",Toast.LENGTH_LONG ).show();
            int method= spnPayMethod.getAdapter().getCount();

            if(method==0){
                Toast.makeText(TopupCredit.this,"please add payment method",Toast.LENGTH_LONG ).show();
            }else {
                Payment(Reload,CardNumber);
            }}
    }
    String URLs=Constans.URL +"Reload.php";
    private void Payment(final String reload, final String cardNumber) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(TopupCredit.this,response,Toast.LENGTH_LONG ).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TopupCredit.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){

            @Override

            protected Map<String, String> getParams() throws AuthFailureError //ni kita pass parameter dekat html
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("id",SplashScreen.userID);
                map.put("credit",reload);
                map.put("CardNumber",cardNumber);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}

