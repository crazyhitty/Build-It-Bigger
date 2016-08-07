package com.udacity.gradle.builditbigger.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.databinding.ActivityMainBinding;
import com.udacity.gradle.builditbigger.ui.fragments.MainActivityFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(activityMainBinding.toolbar);
        activityMainBinding.setToolbarTitle(getString(R.string.app_name));

        // Only set fragment when saved instance is null
        if (savedInstanceState == null) {
            init();
        }
    }

    private void init() {
        // Set main fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main,
                MainActivityFragment.newInstance(),
                MainActivityFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

}
