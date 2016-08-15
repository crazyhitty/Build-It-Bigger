package com.udacity.gradle.builditbigger;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import com.udacity.gradle.builditbigger.data.GoogleAppEngineConnector;

import junit.framework.Assert;

import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Author: Kartik Sharma
 * Created on: 8/13/2016 , 4:01 PM
 * Project: FinalProject
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class JokeFetcherApiTest {

    @Test
    public void testJokeApi() {
        try {
            String joke = GoogleAppEngineConnector.getJokeFromApi();
            Assert.assertFalse(TextUtils.isEmpty(joke));
        } catch (IOException e) {
            Assert.assertNull(e);
        }
    }
}
