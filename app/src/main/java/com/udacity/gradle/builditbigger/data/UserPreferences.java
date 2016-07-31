package com.udacity.gradle.builditbigger.data;

import android.content.Context;

import com.udacity.gradle.builditbigger.utils.SharedPrefUtil;

/**
 * Author: Kartik Sharma
 * Created on: 7/31/2016 , 12:24 AM
 * Project: FinalProject
 */

public class UserPreferences {
    public static final int ARG_FETCH_JOKE_FROM_JAVA_LIBRARY = 1;
    public static final int ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY = 2;
    public static final int ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE = 3;
    private static final String ARG_JOKE_FETCH_TYPE = "joke_fetch_type";

    public static int getJokeFetchType(Context context) {
        return new SharedPrefUtil(context).getInt(ARG_JOKE_FETCH_TYPE, ARG_FETCH_JOKE_FROM_JAVA_LIBRARY);
    }

    public static void saveJokeFetchType(Context context, int type) {
        new SharedPrefUtil(context).saveInt(ARG_JOKE_FETCH_TYPE, type);
    }
}
