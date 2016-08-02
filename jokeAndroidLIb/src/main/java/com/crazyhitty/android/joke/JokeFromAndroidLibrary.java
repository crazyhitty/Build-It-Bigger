package com.crazyhitty.android.joke;

import java.util.Locale;

public class JokeFromAndroidLibrary {
    private static final String[] JOKES = {"First funny joke",
            "Second funny joke",
            "Third funny joke",
            "Fourth funny joke",
            "Fifth funny joke",
            "Sixth funny joke",
            "Seventh funny joke",
            "Eighth funny joke",
            "Ninth funny joke",
            "Finale funny joke"};

    private static int sPosition = -1;

    public static String getJoke() {
        if (sPosition == JOKES.length - 1) {
            sPosition = -1;
        }
        return String.format(Locale.getDefault(), "%s (Android library)", JOKES[++sPosition]);
    }
}