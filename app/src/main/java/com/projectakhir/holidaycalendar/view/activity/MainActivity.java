package com.projectakhir.holidaycalendar.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.projectakhir.holidaycalendar.view.fragment.CalendarFragment;
import com.projectakhir.holidaycalendar.view.fragment.HolidayFragment;
import com.projectakhir.holidaycalendar.R;
import com.projectakhir.holidaycalendar.view.fragment.AboutFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment selectedFragment = new HolidayFragment();
    private BottomNavigationView bottomNavigationView;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(selectedFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_nav_holiday:
                selectedFragment = new HolidayFragment();
                loadFragment(selectedFragment);
                title = "Holiday Calendar 2020";
                setTitle(title);
                break;
            case R.id.bottom_nav_calendar:
                selectedFragment = new CalendarFragment();
                loadFragment(selectedFragment);
                title = "Calendar";
                setTitle(title);
                break;
            case R.id.bottom_nav_about:
                selectedFragment = new AboutFragment();
                loadFragment(selectedFragment);
                title = "Developer";
                setTitle(title);
                break;
        }
        return loadFragment(selectedFragment);
    }

    private boolean loadFragment(Fragment selectedFragment) {
        if (selectedFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, selectedFragment)
                    .commit();
            return true;
        }
        return false;
    }
}