package com.appsaja.wagiman.bookingservices;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.appsaja.wagiman.bookingservices.fragment.AccountFragment;
import com.appsaja.wagiman.bookingservices.fragment.BookingFragment;
import com.appsaja.wagiman.bookingservices.fragment.HomeFragment;
import com.appsaja.wagiman.bookingservices.fragment.RegisterFragment;
import com.appsaja.wagiman.bookingservices.fragment.HistoryServiceFragment;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationHome);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menuBooking :
                        getSupportActionBar().setTitle("Booking Service");
                        fragment = new BookingFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.menuHistory :
                        getSupportActionBar().setTitle("History Service");
                        fragment = new HistoryServiceFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.menuAccount:
                        getSupportActionBar().setTitle("Account");
                        fragment = new AccountFragment();
                        loadFragment(fragment);
                        return true;
                }

                return false;
            }
        });

        if (savedInstanceState == null){
            getSupportActionBar().setTitle("Home");
            fragment = new HomeFragment();
            loadFragment(fragment);
        }

    }

    @Override
    public void onBackPressed() {
        if(fragment.isVisible()){
            //Do Nothing - DOES NOT CLOSE THE APP**
        }
        else{
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }

}
