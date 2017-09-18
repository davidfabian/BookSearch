package com.example.d.booksearch;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */

public final class QueryUtils {

    private static final String GBAPI_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=10";
    //modify this according to API documentation for different results

    private static final String LOG_TAG = QueryUtils.class.getName();
    //JSON_RESPONSE static to used through the class
    private static String JSON_RESPONSE = "";

    //dummy creator
    private QueryUtils() {
    }

    //casting the url to URL format
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    //returns the JSon response specified by USGS_URL
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //helper method: builds JSon string from inputstream
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        JSON_RESPONSE = output.toString();
        return JSON_RESPONSE;
    }

    //returns the list of earthquakes defined by the USGS_URL
    public static List<Volume> extractVolumes() {
        Log.e("extractvolume", "started");
        //prepare url
        URL url = createUrl(GBAPI_URL);


        // get json data from USGS with makeHttpRequest
        try {
            JSON_RESPONSE = makeHttpRequest(url);
        } catch (IOException io) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", io);
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Volume> volumes = new ArrayList<>();

        // Try to parse the JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {


            JSONObject volumeList = new JSONObject(JSON_RESPONSE);
            JSONArray items = volumeList.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject currentVolume = items.getJSONObject(i);
                JSONObject volumeInfo = currentVolume.getJSONObject("volumeInfo");

                String title = volumeInfo.getString("title");

                try {
                    JSONArray authors = volumeInfo.getJSONArray("authors");
                    String author = authors.getString(0);
                    volumes.add(new Volume(title, author));


                } catch (JSONException e) {
                    volumes.add(new Volume(title, "N/A"));
                }


            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return volumes;
    }

}