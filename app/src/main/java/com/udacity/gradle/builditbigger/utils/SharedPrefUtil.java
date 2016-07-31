package com.udacity.gradle.builditbigger.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kartik_ch on 2/13/2016.
 */
public class SharedPrefUtil {
    private static final String APP_PREFS = "application_preferences";
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SharedPrefUtil(Context mContext) {
        this.mContext = mContext;
    }

    public void saveString(String type, String value) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(type, value);
        mEditor.commit();
    }

    public void saveInt(String type, int value) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(type, value);
        mEditor.commit();
    }

    public void saveBoolean(String type, boolean value) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(type, value);
        mEditor.commit();
    }

    public String getString(String type) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(type, null);
    }

    public String getString(String type, String defaultValue) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(type, defaultValue);
    }

    public int getInt(String type) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(type, 0);
    }

    public int getInt(String type, int defaultValue) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(type, defaultValue);
    }

    public boolean getBoolean(String type) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(type, false);
    }

    public boolean getBoolean(String type, boolean defaultValue) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(type, defaultValue);
    }
}
