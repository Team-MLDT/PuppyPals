package com.mldt.puppypals;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class LocationRequest {


//public class LocationRequest extends AsyncTask<Void, Void, Void> {

//    @Override
//    protected void onPreExecuty() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected String doInBackground(String... params) {
//
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//
//    }

    public static void getQuery() throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL locationIQURL = new URL("https://us1.locationiq.com/v1/search?key=pk.18ca9e9cd8991ecc32c5e6d32e082244&format=json&q=Seattle");
        URLConnection urlConnection = locationIQURL.openConnection();
        HttpsURLConnection locationConnection = (HttpsURLConnection) urlConnection;
        locationConnection.setRequestMethod("GET");
        InputStreamReader reader;
        BufferedReader bufferedReader;

        try {
            if(locationConnection.getResponseCode() > 299) {
                // console log error
            } else {
                reader = new InputStreamReader(locationConnection.getInputStream());
                bufferedReader = new BufferedReader(reader);
                String response = bufferedReader.readLine();
                System.out.println(response);
            }
        } catch(IOException ioe) {
            // console log error
        }

    }

    public LocationRequest() throws IOException {
    }

//    @Override
//    protected Void doInBackground(Void... voids) {
//        return null;
//    }
}
