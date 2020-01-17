package com.example.innoskritproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView logout;
    TextView tvlogout;
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        findviews();
    }

    private void findviews() {
        logout = findViewById(R.id.logout);
        tvlogout = findViewById(R.id.tv_logout);
        tvlogout.setOnClickListener(this);
        logout.setOnClickListener(this);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(ApplicationFormFragment.newInstance("", ""));
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_application:
                            openFragment(ApplicationFormFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_admit:
                            openFragment(AdmitCardFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_result:
                            openFragment(ResultFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public void onClick(View v) {
        if(v==logout){
            startActivity(new Intent(OptionActivity.this, LoginActivity.class));

        }
        if(v==tvlogout){
            startActivity(new Intent(OptionActivity.this, LoginActivity.class));
        }

    }
}
