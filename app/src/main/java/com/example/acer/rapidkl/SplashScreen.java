package com.example.acer.rapidkl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class SplashScreen extends AppCompatActivity {


    private String TAG = SplashScreen.class.getSimpleName();
    public static String username;
    public static String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Bundle extra=getIntent().getExtras();

       final String Username=extra.getString("KEY_USERNAME");
        String password=extra.getString("KEY_PASSWORD");

        login(Username,password);
    }

    final String URL=Constans.URL +"Login.php";
    private void login(final String username, final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //letak somthing untuk tau berjaya
                        if(!response.equals("login error")){
                            Log.d(TAG, "onResponse: "+response);
                            Toast.makeText(SplashScreen.this,"Welcome "+username, Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(SplashScreen.this,UserSpace.class);
                            userID = response;
                            SplashScreen.username = username;
//                            intent.putExtra("KEY_ID",response);
//                            intent.putExtra("KEY_USERNAME",username);
                            SharepreferenceManipulator(intent,response);
//                            startActivity(intent);
                        }else {
                            SharedPreferences sharedPreference=getSharedPreferences("userData",MODE_PRIVATE);
                            sharedPreference.edit().clear().commit();
//                            SharedPreferences.Editor editor=sharedPreference.edit();
//                            editor.putString("ID",response);
//                            editor.putString("Password",etPassword.getText().toString());
//                            editor.commit();
                            Toast.makeText(SplashScreen.this, response, Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SplashScreen.this,error.toString(),Toast.LENGTH_LONG ).show();
                        Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                        startActivity(intent);
                    }
                }){

            @Override

            protected Map<String, String> getParams() throws AuthFailureError //ni kita pass parameter dekat html
            {
                Map<String,String> map = new HashMap<String,String>();
                map.put("username",username);
                map.put("password",password);

                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void SharepreferenceManipulator(Intent intent, String response) {
        SharedPreferences sharedPreference=getSharedPreferences("userData",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreference.edit();
        editor.putString("ID",response);
        editor.commit();
        startActivity(intent);
    }

    ;
    public void onBackPressed(){
        Toast.makeText(SplashScreen.this,"Please Wait",Toast.LENGTH_LONG ).show();
    }
}
