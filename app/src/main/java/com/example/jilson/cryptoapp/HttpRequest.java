package com.example.jilson.cryptoapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

final class HttpRequest {

    // Tag for Log
    private static final String LOG_TAG = HttpRequest.class.getSimpleName();

    /**
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name HttpRequest (and an object instance of HttpRequest is not needed).
     */
    private HttpRequest() {
    }

    //Function to fetch data
    static String fetchData(String requestUrl){
        Log.v(LOG_TAG,"in fetchData method ");
        URL url = createURL(requestUrl);

        if(url == null){
            return null;
        }

        String jsonResponse = null;

        try{
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG, "Error in making HttpRequest", e);
        }

        return jsonResponse;
    }

    //Creates a URL
    private static URL createURL(String stringUrl){
        Log.v(LOG_TAG,"in createURL method");
        URL url = null;
        try {
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Error in creating URL",e);
        }
        return url;
    }

    // This function makes http request and returns JŚON response
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /*milliseconds*/);
            urlConnection.setConnectTimeout(15000 /*milliseconds*/);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //checking if the request was successfull
            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        }catch (IOException e) {
            Log.e(LOG_TAG, "Ērror in retrieving JSON results.", e);
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

    private static String readFromStream(InputStream inputStream)throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();

    }
}
