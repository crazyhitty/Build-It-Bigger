package com.udacity.gradle.builditbigger.data;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.crazyhitty.android.joke.ViewJokeActivity;
import com.crazyhitty.java.joke.JokeFromJavaLibrary;
import com.udacity.gradle.builditbigger.utils.NetworkConnectionUtil;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Kartik Sharma
 * Created on: 8/1/2016 , 10:40 PM
 * Project: FinalProject
 */

public class JokeFetcher {
    private static final String TAG = JokeFetcher.class.getSimpleName();

    private static final String ERR_MSG_NO_TYPE_DEFINED = "No type defined !";
    private static final String ERR_MSG_UNABLE_TO_GET_JOKE_ANDROID_LIB = "Unable to fetch joke from android library!";

    private static final int RC_FETCH_JOKE_ANDROID_LIB = 1337;

    private JokeFetcherListener mJokeFetcherListener;

    private Subscription mSubscription;

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
        // Unsubscribe the subscription so that pending tasks can be removed.
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        int type = UserPreferences.getJokeFetchType(fragment.getActivity());
        switch (type) {
            case UserPreferences.ARG_FETCH_JOKE_FROM_JAVA_LIBRARY:
                fetchJokeFromJavaLibrary();
                break;
            case UserPreferences.ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY:
                fetchJokeFromAndroidLibrary(fragment);
                break;
            case UserPreferences.ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE:
                fetchJokeFromGoogleAppEngine(fragment.getActivity().getApplicationContext());
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
        mJokeFetcherListener.onJokeRetrievedSuccessfully(joke, UserPreferences.ARG_FETCH_JOKE_FROM_JAVA_LIBRARY);
    }

    /**
     * Start the {@link ViewJokeActivity} and loads a joke from {@link JokeFromJavaLibrary} class.
     *
     * @param fragment Instance of the fragment in which this class is being used
     */
    private void fetchJokeFromAndroidLibrary(Fragment fragment) {
        Intent intentStartJokeLibActivity = new Intent(fragment.getActivity(), ViewJokeActivity.class);
        intentStartJokeLibActivity.putExtra(ViewJokeActivity.ARG_JOKE_RETRIEVED, JokeFromJavaLibrary.getJoke());
        fragment.startActivityForResult(intentStartJokeLibActivity, RC_FETCH_JOKE_ANDROID_LIB);
    }

    /**
     * Fetches the joke from the google app engine.
     * @param context    current context of the application
     */
    private void fetchJokeFromGoogleAppEngine(final Context context) {
        mSubscription = getGaeJokeObservable(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getGaeJokeObserver());
    }

    /**
     * Get google app engine observable
     *
     * @param context current context of the application
     * @return Google app engine Observable
     */
    private Observable<String> getGaeJokeObservable(final Context context) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (NetworkConnectionUtil.isConnectedToInternet(context)) {
                        String joke = GoogleAppEngineConnector.getJokeFromApi();
                        subscriber.onNext(joke);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new JokeUnavailableException(NetworkConnectionUtil.ERR_DIALOG_TITLE));
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * Get google app engine observer
     *
     * @return
     * Google app engine Observer
     */
    private Observer<String> getGaeJokeObserver() {
        return new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage(), e);
                if (e instanceof JokeUnavailableException) {
                    mJokeFetcherListener.onNoInternetAvailable(e.getMessage());
                } else {
                    mJokeFetcherListener.onJokeRetrievalFailure(e.getMessage());
                }
            }

            @Override
            public void onNext(String s) {
                mJokeFetcherListener.onJokeRetrievedSuccessfully(s, UserPreferences.ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE);
            }
        };
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
                mJokeFetcherListener.onJokeRetrievedSuccessfully(joke, UserPreferences.ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY);
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
         * @param joke      joke retrieved
         * @param source    source from which the joke is retrieved
         */
        void onJokeRetrievedSuccessfully(String joke, int source);

        /**
         * When joke cannot be loaded due to some error.
         *
         * @param errorMsg    error message
         */
        void onJokeRetrievalFailure(String errorMsg);

        /**
         * When joke cannot be loaded because no internet connection is available
         *
         * @param errorMsg error message
         */
        void onNoInternetAvailable(String errorMsg);
    }

    /**
     * Custom exception class for handling joke related exceptions.
     */
    private static class JokeUnavailableException extends RuntimeException {
        JokeUnavailableException(String message) {
            super(message);
        }
    }
}
