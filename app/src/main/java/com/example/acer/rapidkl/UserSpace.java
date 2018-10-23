package com.example.acer.rapidkl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserSpace extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //declare
    private TextView tvNamaktNavi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_space);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //to find ID
        View hView =navigationView.getHeaderView(0);
        tvNamaktNavi=(TextView)hView.findViewById(R.id.tvNamaktNavi);

        //make function

      getID();
    }


    private void getID() {
        Bundle extra=getIntent().getExtras();


        tvNamaktNavi.setText(SplashScreen.username);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
          this.finishAffinity();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.user_space, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_customerservie) {
            Intent intent = new Intent(UserSpace.this, CustomerService.class);
//            intent.putExtra("KEY_ID",SplashScreen.userID);
            startActivity(intent);
            // Handle the camera action

        } else if (id == R.id.nav_ticket) {

            Intent intent = new Intent(UserSpace.this, Ticket.class);
//            intent.putExtra("KEY_ID",SplashScreen.userID);
            startActivity(intent);

        } else if (id == R.id.nav_buycreadit) {
            Intent intent=new Intent(UserSpace.this,TopupCredit.class);
//            intent.putExtra("KEY_ID", SplashScreen.userID);
            startActivity(intent);

        } else if (id == R.id.nav_balance) {
            Intent intent=new Intent(UserSpace.this,ViewDetails.class);
//            intent.putExtra("KEY_ID",SplashScreen.userID);
            startActivity(intent);

        } else if (id == R.id.nav_news) {
            Intent intent=new Intent(UserSpace.this,News.class);
//            intent.putExtra("KEY_ID",SplashScreen.userID);
            startActivity(intent);
        }else if (id == R.id.nav_logout) {
            SharedPreferences sharedPreference=getSharedPreferences("userData",MODE_PRIVATE);
            sharedPreference.edit().clear().commit();
            Intent intent=new Intent(UserSpace.this,MainActivity.class);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

}
