package com.udacity.gradle.builditbigger.data;


import com.crazyhitty.android.joke.JokeFromAndroidLibrary;
import com.crazyhitty.java.joke.JokeFromJavaLibrary;

/**
 * Author: Kartik Sharma
 * Created on: 8/1/2016 , 10:40 PM
 * Project: FinalProject
 */

public class JokeFetcher {
    public static String fetchJoke(int type) {
        switch (type) {
            case UserPreferences.ARG_FETCH_JOKE_FROM_JAVA_LIBRARY:
                return fetchJokeFromJavaLibrary();
            case UserPreferences.ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY:
                return fetchJokeFromAndroidLibrary();
            case UserPreferences.ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE:
                return fetchJokeFromGoogleAppEngine();
            default:
                return null;
        }
    }

    private static String fetchJokeFromJavaLibrary() {
        return JokeFromJavaLibrary.getJoke();
    }

    private static String fetchJokeFromAndroidLibrary() {
        return JokeFromAndroidLibrary.getJoke();
    }

    private static String fetchJokeFromGoogleAppEngine() {
        return null;
    }
}
