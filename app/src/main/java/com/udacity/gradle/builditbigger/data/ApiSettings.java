package com.udacity.gradle.builditbigger.data;

/**
 * Author: Kartik Sharma
 * Created on: 8/7/2016 , 9:10 PM
 * Project: FinalProject
 */

public class ApiSettings {
    private static final String LOCAL_HOST_IP_ADDRESS = "192.168.1.2";
    private static final String PORT = "8080";
    public static final String ROOT_URL = "http://" + LOCAL_HOST_IP_ADDRESS + ":" + PORT + "/_ah/api/";
}
