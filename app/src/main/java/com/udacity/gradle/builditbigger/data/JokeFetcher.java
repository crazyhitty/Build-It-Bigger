package com.udacity.gradle.builditbigger.data;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.crazyhitty.android.joke.ViewJokeActivity;
import com.crazyhitty.java.joke.JokeFromJavaLibrary;

/**
 * Author: Kartik Sharma
 * Created on: 8/1/2016 , 10:40 PM
 * Project: FinalProject
 */

public class JokeFetcher {
    private static final String ERR_MSG_NO_TYPE_DEFINED = "No type defined !";
    private static final String ERR_MSG_NOT_IMPLEMENTED = "Not yet implemented !";
    private static final String ERR_MSG_UNABLE_TO_GET_JOKE_ANDROID_LIB = "Unable to fetch joke from android library!";

    private static final int RC_FETCH_JOKE_ANDROID_LIB = 1337;

    public JokeFetcherListener mJokeFetcherListener;

    /**
     * Constructor of {@link JokeFetcher} class with a listener which can further provide joke.
     *
     * @param jokeFetcherListener Listener that further provides jokes and error messages
     *                            if unable to retrieve joke
     */
    public JokeFetcher(JokeFetcherListener jokeFetcherListener) {
        this.mJokeFetcherListener = jokeFetcherListener;
    }

    /**
     * Fetches the joke either from java library, android library or google app engine,
     * depending upon user preferences.
     *
     * @param fragment Instance of the fragment in which this class is being used
     */
    public void fetchJoke(Fragment fragment) {
        int type = UserPreferences.getJokeFetchType(fragment.getActivity());
        switch (type) {
            case UserPreferences.ARG_FETCH_JOKE_FROM_JAVA_LIBRARY:
                fetchJokeFromJavaLibrary();
                break;
            case UserPreferences.ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY:
                fetchJokeFromAndroidLibrary(fragment);
                break;
            case UserPreferences.ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE:
                fetchJokeFromGoogleAppEngine();
                break;
            default:
                mJokeFetcherListener.onJokeRetrievalFailure(ERR_MSG_NO_TYPE_DEFINED);
        }
    }

    /**
     * Fetches the joke from {@link JokeFromJavaLibrary} class.
     */
    private void fetchJokeFromJavaLibrary() {
        String joke = JokeFromJavaLibrary.getJoke();
        mJokeFetcherListener.onJokeRetrievedSuccessfully(joke);
    }

    /**
     * Start the {@link ViewJokeActivity} and loads a joke from {@link JokeFromJavaLibrary} class.
     *
     * @param fragment    Instance of the fragment in which this class is being used
     */
    private void fetchJokeFromAndroidLibrary(Fragment fragment) {
        Intent intentStartJokeLibActivity = new Intent(fragment.getActivity(), ViewJokeActivity.class);
        fragment.startActivityForResult(intentStartJokeLibActivity, RC_FETCH_JOKE_ANDROID_LIB);
    }

    /**
     * Fetches the joke from the google app engine.
     */
    private void fetchJokeFromGoogleAppEngine() {
        mJokeFetcherListener.onJokeRetrievalFailure(ERR_MSG_NOT_IMPLEMENTED);
    }

    /**
     * Used to retrieve the joke from {@link ViewJokeActivity}, if it is started.
     *
     * @param requestCode Request code sent in {@link Fragment#startActivityForResult(Intent, int)}
     * @param resultCode  Result code retrieved from other activity
     * @param data        Intent retrieved from other activity
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == RC_FETCH_JOKE_ANDROID_LIB) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String joke = data.getExtras().getString(ViewJokeActivity.ARG_JOKE);
                mJokeFetcherListener.onJokeRetrievedSuccessfully(joke);
            } else {
                mJokeFetcherListener.onJokeRetrievalFailure(ERR_MSG_UNABLE_TO_GET_JOKE_ANDROID_LIB);
            }
        }
    }

    /**
     * Interface for get joke or error if application fails to load the joke.
     */
    public interface JokeFetcherListener {
        /**
         * When joke is retrieved successfully.
         *
         * @param joke joke retrieved
         */
        void onJokeRetrievedSuccessfully(String joke);

        /**
         * When joke cannot be loaded due to some error.
         *
         * @param errorMsg error message
         */
        void onJokeRetrievalFailure(String errorMsg);
    }
}
