/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.crazyhitty.gae.joke;

import com.crazyhitty.java.joke.JokeFromJavaLibrary;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

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

    /**
     * A simple endpoint method that returns a joke
     */
    @ApiMethod(name = "tellJoke", httpMethod = ApiMethod.HttpMethod.GET)
    public MyBean tellJoke() {
        MyBean response = new MyBean();
        String joke = JokeFromJavaLibrary.getGAEJoke();
        response.setJoke(joke);
        return response;
    }

}
