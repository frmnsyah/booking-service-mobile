package com.appsaja.wagiman.bookingservices;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.appsaja.wagiman.bookingservices.fragment.LoginFragment;
import com.appsaja.wagiman.bookingservices.fragment.RegisterFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    android.app.Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getApplicationContext().getSharedPreferences("session", 0);
        int userId = settings.getInt("user_id", 0);
        if (userId != 0) {
            Intent secondActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(secondActivityIntent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationLogin);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuLogin:
                        getSupportActionBar().hide();
                        fragment = new LoginFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.menuRegister:
                        getSupportActionBar().hide();
                        fragment = new RegisterFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });

        if (savedInstanceState == null) {
            getSupportActionBar().hide();
            fragment = new LoginFragment();
            loadFragment(fragment);
        }
    }

    private void loadFragment(android.app.Fragment fragment) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }
}
