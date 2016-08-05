/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.crazyhitty.gae.joke;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.Random;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "joke.gae.crazyhitty.com",
                ownerName = "joke.gae.crazyhitty.com",
                packagePath = ""
        )
)
public class MyEndpoint {
    private static final String[] JOKES = {
            "Doctor says to his patient: \n" +
                    "\"You have Cancer and Alzheimer.\"\n" +
                    "\n" +
                    "Patient: \"At least I don't have Cancer.\"",
            "They threw me out of the cinema today for bringing my own food. But come on, the prices are way too high, plus I haven't had a barbecue in months.",
            "Why haven't you ever seen any elephants hiding up trees? Because they're really, really good at it.",
            "What is dangerous?\n" +
                    "\n" +
                    "Sneezing while having diarrhea!",
            "Secretary: \"Doctor the invisible man has come. He says he has an appointment.\"\n" +
                    "\n" +
                    "Doctor: \"Tell him I cant see him.\"",
            "\"Grandpa, why don't you have any life insurance?\"\n" +
                    "\n" +
                    "\"So you can all be really sad when I die.\"",
            "A wife is like a hand grenade. Take off the ring and say good bye to your house.",
            "I heard women love a man in uniform. Can't wait to start working at McDonalds.",
            "Why don't cannibals eat divorced women?\n" +
                    "\n" +
                    "Because they're bitter.",
            "Money doesn't buy you happiness but it can buy you a jet-ski. It is impossible to be sad when you're riding on the jet-ski."
    };

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "tellJoke", httpMethod = ApiMethod.HttpMethod.GET)
    public MyBean tellJoke() {
        int random = new Random(System.currentTimeMillis()).nextInt(10);
        MyBean response = new MyBean();
        response.setJoke(JOKES[random]);
        return response;
    }

}
