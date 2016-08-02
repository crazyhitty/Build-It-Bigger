package com.crazyhitty.java.joke;

import java.util.Locale;

public class JokeFromJavaLibrary {
    private static final String[] JOKES = {"First joke",
            "Second joke",
            "Third joke",
            "Fourth joke",
            "Fifth joke",
            "Sixth joke",
            "Seventh joke",
            "Eighth joke",
            "Ninth joke",
            "Finale joke"};

    private static int sPosition = -1;

    public static String getJoke() {
        if (sPosition == JOKES.length - 1) {
            sPosition = -1;
        }
        return String.format(Locale.getDefault(), "%s (Java library)", JOKES[++sPosition]);
    }

    public static void main(String[] args) {
        System.out.println(getJoke());
    }
}