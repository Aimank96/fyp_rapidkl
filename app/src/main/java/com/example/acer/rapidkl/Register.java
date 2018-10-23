package com.example.acer.rapidkl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText etFullName;
    EditText etPassRegister;
    EditText etConfPass;
    EditText etEmail;
    EditText etPhoneNum;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Untuk mencari ID
        etFullName=(EditText)findViewById(R.id.etFullName);
        etPassRegister=(EditText)findViewById(R.id.etPassRegister);
        etConfPass=(EditText)findViewById(R.id.etConfPass);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPhoneNum=(EditText)findViewById(R.id.etPhoneNum);
        btnRegister=(Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String FullName=etFullName.getText().toString();
        String Password=etPassRegister.getText().toString();
        String RePassword=etConfPass.getText().toString();
        String Email=etEmail.getText().toString();
        String PhoneNumber=etPhoneNum.getText().toString();
        if(v==btnRegister){
            if (Password.equals(RePassword)){
                RegisterRequest(FullName,Password,Email,PhoneNumber);
                Toast.makeText(Register.this,"Please wait",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(Register.this,"Password did not match",Toast.LENGTH_LONG).show();
             }
        }
    }
        final String URL= Constans.URL + "Register.php";
        private void RegisterRequest(final String fullName, final String password,final String email,final String phoneNumber) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //letak somthing untuk tau berjaya
                        if(response.equals("successfully registered")) {
                            Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){

            @Override

            protected Map<String, String> getParams() throws AuthFailureError //ni kita pass parameter dekat html
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("Username",fullName);
                map.put("Password",password);
                map.put("Email",email);
                map.put("PhoneNumb",phoneNumber);

                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    };

    }
    /**
     final String URL = "Letak url kau";

     public void SimplePostRequest(){ //letak parameter kalau nak post data $_post



      **/
