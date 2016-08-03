package com.crazyhitty.android.joke;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.crazyhitty.android.joke.databinding.ActivityViewJokeBinding;
import com.crazyhitty.java.joke.JokeFromJavaLibrary;

import java.util.Locale;

public class ViewJokeActivity extends AppCompatActivity {
    public static final String ARG_JOKE = "joke";

    private ActivityViewJokeBinding mActivityViewJokeBinding;

    private String mJoke;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityViewJokeBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_joke);

        // retrieve joke from java library
        mJoke = String.format(Locale.getDefault(), "%s \n\n(Fetched from android library)", JokeFromJavaLibrary.getJoke());
        mActivityViewJokeBinding.setJoke(mJoke);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_close, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_close) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra(ARG_JOKE, mJoke);
        setResult(Activity.RESULT_OK, result);
        super.onBackPressed();
    }
}