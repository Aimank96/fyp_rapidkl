package com.example.acer.rapidkl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUserName;
    EditText etPassword;
    TextView tvSignUp;
    Button btnEnter;
    Switch aSwitch;
    TextView tvOffline;
    final String DEFAULT="N?A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Untuk mencari ID
        etUserName=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        tvSignUp=(TextView)findViewById(R.id.tvRegister);
        btnEnter=(Button)findViewById(R.id.btnEnter);
        aSwitch=(Switch)findViewById(R.id.switch1);
        tvOffline=(TextView)findViewById(R.id.tvOffline);

        //Untuk memberi fungsi
        tvSignUp.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
        tvOffline.setOnClickListener(this);

        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInf=connectivityManager.getActiveNetworkInfo();
        if(networkInf!=null&&networkInf.isConnected()){
            
            KeepLogin();
        }

    }

    private void KeepLogin() {

        SharedPreferences sharedPreference=getSharedPreferences("userData",MODE_PRIVATE);
        String Username=sharedPreference.getString("Username",DEFAULT);
        String Password=sharedPreference.getString("Password",DEFAULT);
        if(Username.equals(DEFAULT)||Password.equals(DEFAULT)){

        }
        else {
            Intent intent=new Intent(this,SplashScreen.class);
            intent.putExtra("KEY_USERNAME",Username);
            intent.putExtra("KEY_PASSWORD",Password);
            startActivity(intent);
        }


    }

    @Override
    public void onClick(View v) {
        //string utk edit text
        String Username=etUserName.getText().toString();
        String Password=etPassword.getText().toString();
        if(v==tvSignUp){
            Intent intent=new Intent(MainActivity.this,Register.class);
            startActivity(intent);
        }
        if(v==btnEnter){
         Intent intent=new Intent(MainActivity.this,SplashScreen.class);
            intent.putExtra("KEY_USERNAME",Username);
            intent.putExtra("KEY_PASSWORD",Password);
            startActivity(intent);
            
            if(aSwitch.isChecked()){
                InsertToSharePreference();
            }
        }
        if(v==tvOffline){
            Intent intent=new Intent(MainActivity.this,OfflineMode.class);
            startActivity(intent);
        }
    }

    private void InsertToSharePreference() {
        SharedPreferences sharedPreference=getSharedPreferences("userData",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreference.edit();
        editor.putString("Username",etUserName.getText().toString());
        editor.putString("Password",etPassword.getText().toString());
        editor.commit();
    }

    public void onBackPressed(){
        //finish();
        this.finishAffinity();
    }


}

