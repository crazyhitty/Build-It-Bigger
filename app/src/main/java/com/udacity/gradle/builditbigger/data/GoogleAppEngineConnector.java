package com.udacity.gradle.builditbigger.data;

import com.crazyhitty.gae.joke.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Author: Kartik Sharma
 * Created on: 8/13/2016 , 5:09 PM
 * Project: FinalProject
 */

public class GoogleAppEngineConnector {
    private static JokeApi sJokeApi;

    /**
     * Retrieve joke from google app engine api.
     * This method <b>must run on background thread</b>, running it on main thread will freeze the UI.
     *
     * @return joke in string format
     * @throws IOException
     */
    public static String getJokeFromApi() throws IOException {
        if (sJokeApi == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(ApiSettings.ROOT_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            sJokeApi = builder.build();
        }
        return sJokeApi.tellJoke().execute().getJoke();
    }
}
