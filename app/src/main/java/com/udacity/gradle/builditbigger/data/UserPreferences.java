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
    private static final String ERR_WRONG_INPUT_TYPE = "Wrong input type, input type must be either " +
            "a java library, android library or google app engine";
    private static final String ARG_JOKE_FETCH_TYPE = "joke_fetch_type";

    /**
     * Get the type in which the joke will be retrieved.
     *
     * @param context Current context of the activity
     * @return type which can be from either {@link #ARG_FETCH_JOKE_FROM_JAVA_LIBRARY},
     * {@link #ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY} or {@link #ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE}
     */
    public static int getJokeFetchType(Context context) {
        return new SharedPrefUtil(context).getInt(ARG_JOKE_FETCH_TYPE, ARG_FETCH_JOKE_FROM_JAVA_LIBRARY);
    }

    /**
     * Save the joke fetching type.
     *
     * @param context Current context of the activity
     * @param type    type which can be from either {@link #ARG_FETCH_JOKE_FROM_JAVA_LIBRARY},
     *                {@link #ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY} or
     *                {@link #ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE}
     */
    public static void saveJokeFetchType(Context context, int type) {
        if(type!= ARG_FETCH_JOKE_FROM_JAVA_LIBRARY &&
                type != ARG_FETCH_JOKE_FROM_ANDROID_LIBRARY &&
                type != ARG_FETCH_JOKE_FROM_GOOGLE_APP_ENGINE) {
            throw new IllegalArgumentException(ERR_WRONG_INPUT_TYPE);
        }
        new SharedPrefUtil(context).saveInt(ARG_JOKE_FETCH_TYPE, type);
    }
}
