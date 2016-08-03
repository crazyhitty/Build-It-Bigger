package com.udacity.gradle.builditbigger.ui.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.data.JokeFetcher;
import com.udacity.gradle.builditbigger.data.UserPreferences;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;


public class MainActivityFragment extends Fragment implements JokeFetcher.JokeFetcherListener {
    private FragmentMainBinding mFragmentMainBinding;
    private JokeFetcher mJokeFetcher;

    public static MainActivityFragment newInstance() {
        Bundle args = new Bundle();
        MainActivityFragment fragment = new MainActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mFragmentMainBinding.setMainActivityFragment(this);
        return mFragmentMainBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mFragmentMainBinding.adView.loadAd(adRequest);

        // set initial joke hint
        mFragmentMainBinding.setJokeText(getString(R.string.your_joke_will_appear_here));

        // set initial loading hint
        mFragmentMainBinding.setLoadingText(getString(R.string.no_joke_loaded));

        // initialize the JokeFetcher instance
        mJokeFetcher = new JokeFetcher(this);
    }

    public void loadJoke(View view) {
        mFragmentMainBinding.setLoadingStatus(true);
        mFragmentMainBinding.setLoadingText(getString(R.string.loading_joke));

        mJokeFetcher.fetchJoke(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItemJavaLibrary = menu.findItem(R.id.action_java_library);
        MenuItem menuItemAndroidLibrary = menu.findItem(R.id.action_android_library);
        MenuItem menuItemGoogleAppEngine = menu.findItem(R.id.action_google_app_engine);

        // uncheck all the menu items
        menuItemJavaLibrary.setChecked(false);
        menuItemAndroidLibrary.setChecked(false);
        menuItemGoogleAppEngine.setChecked(false);

        // check the menu item based upon pre defined selection
        switch (UserPreferences.getJokeFetchType(getActivity())) {
            case UserPreferences.ARG_FETCH_JOKE_FROM_JAVA_LIBRARY:
                menuItemJavaLibrary.setChecked(true);
                break;
            case UserPreferences.ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY:
                menuItemAndroidLibrary.setChecked(true);
                break;
            case UserPreferences.ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE:
                menuItemGoogleAppEngine.setChecked(true);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        // check the menu item based upon user selection
        item.setChecked(true);
        switch (itemId) {
            case R.id.action_java_library:
                UserPreferences.saveJokeFetchType(getActivity(), UserPreferences.ARG_FETCH_JOKE_FROM_JAVA_LIBRARY);
                break;
            case R.id.action_android_library:
                UserPreferences.saveJokeFetchType(getActivity(), UserPreferences.ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY);
                break;
            case R.id.action_google_app_engine:
                UserPreferences.saveJokeFetchType(getActivity(), UserPreferences.ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onJokeRetrievedSuccessfully(String joke) {
        mFragmentMainBinding.setLoadingStatus(false);
        mFragmentMainBinding.setLoadingText(getString(R.string.joke_loaded));

        mFragmentMainBinding.setJokeText(joke);
    }

    @Override
    public void onJokeRetrievalFailure(String errorMsg) {
        mFragmentMainBinding.setLoadingText(getString(R.string.error_loading_joke));
        mFragmentMainBinding.setLoadingStatus(false);

        mFragmentMainBinding.setJokeText(errorMsg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mJokeFetcher.onActivityResult(requestCode, resultCode, data);
    }
}
