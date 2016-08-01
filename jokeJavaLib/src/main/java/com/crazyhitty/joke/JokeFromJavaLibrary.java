package com.crazyhitty.joke;

public class JokeFromJavaLibrary {
    private static final String[] JOKES = {"First joke"};

    public static String getJoke() {
        return JOKES[0];
    }

    public static void main(String[] args) {
        System.out.println(getJoke());
    }
}