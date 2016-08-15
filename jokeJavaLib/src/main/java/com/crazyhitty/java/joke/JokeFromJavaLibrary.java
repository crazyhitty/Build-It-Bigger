package com.crazyhitty.java.joke;

import java.util.Locale;
import java.util.Random;

public class JokeFromJavaLibrary {
    /**
     * String array containing some jokes.
     */
    private static final String[] JOKES = {"Can a kangaroo jump higher than a house? Of course, a house doesn't jump at all.",
            "Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                    "\n" +
                    "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                    "\n" +
                    "Doctor: \"Nine.\"",
            "A man asks a farmer near a field, \"Sorry sir, would you mind if I crossed your field instead of going around it? You see, I have to catch the 4:23 train.\"\n" +
                    "\n" +
                    "The farmer says, \"Sure, go right ahead. And if my bull sees you, you'll even catch the 4:11 one.\"",
            "Anton, do you think I'm a bad mother?\n" +
                    "\n" +
                    "My name is Paul.",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.",
            "What is the difference between a snowman and a snowwoman?\n" +
                    "\n" +
                    "Snowballs.",
            "Mother, \"How was school today, Patrick?\"\n" +
                    "\n" +
                    "Patrick, \"It was really great mum! Today we made explosives!\"\n" +
                    "\n" +
                    "Mother, \"Ooh, they do very fancy stuff with you these days. And what will you do at school tomorrow?\"\n" +
                    "\n" +
                    "Patrick, \"What school?\"",
            "Scientists have now discovered how women keep their secrets. They do so within groups of 40.",
            "When my wife starts to sing I always go out and do some garden work so our neighbors can see there's no domestic violence going on.",
            "It is so cold outside I saw a politician with his hands in his own pockets."};

    /**
     * Get a random joke
     *
     * @return random joke in string format
     */
    public static String getJoke() {
        int random = new Random(System.currentTimeMillis()).nextInt(10);
        return String.format(Locale.getDefault(), "%s", JOKES[random]);
    }

    /**
     * Main method to test if jokes are being printed correctly or not.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getJoke());
    }
}