package com.example.android.instantnews;

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
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class JSONParser {
    private static final String TAG = JSONParser.class.getSimpleName();
    private static String titltOfArtical;
    private static String sectionName;
    private static String date;
    private static String webUrl;
    private static String authorName;
    private static String requestMethod = "GET";

    public static List<NewsFeed> getNewsFeedData(String JsonUrl) {
        try {
            int i = 1000;
            Thread.sleep(i);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        URL url = createUrl1(JsonUrl);
        String JSON = null;
        JSON = HttpRequest1(url);
        List<NewsFeed> NewsFeed = JSONParsing1(JSON);
        return NewsFeed;
    }

    private static List<NewsFeed> JSONParsing1(String json) {
        List<NewsFeed> tempNews = null;
        try {
            tempNews = JSONParsing(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tempNews;
    }

    private static String HttpRequest1(URL url) {
        String tempurl = null;
        try {
            tempurl = HttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempurl;
    }


    private static URL createUrl1(String jsonUrl) {
        URL jsonurl = null;
        try {
            jsonurl = createUrl(jsonUrl);

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return jsonurl;
    }


    private static List<NewsFeed> JSONParsing(String json) throws JSONException {
        if (json != null) {
            List<NewsFeed> instant = new ArrayList<>();
            try {
                JSONObject rootJson = new JSONObject(json);
                JSONObject rootJsonResponse = rootJson.getJSONObject("response");
                JSONArray jsonArrayResults = rootJsonResponse.getJSONArray("results");

                for (int j = 0; j < jsonArrayResults.length(); j++) {
                    JSONObject atInstant = jsonArrayResults.getJSONObject(j);
                    date = simpledate(atInstant.getString("webPublicationDate"));
                    titltOfArtical = atInstant.getString("webTitle");
                    sectionName = atInstant.getString("sectionName");
                    webUrl = atInstant.getString("webUrl");
                    JSONArray jsonArrayTags = atInstant.getJSONArray("tags");
                    JSONObject index0 = jsonArrayTags.getJSONObject(0);
                    authorName = index0.getString("webTitle");

                    NewsFeed newsFeedObject = new NewsFeed(titltOfArtical, sectionName, date, webUrl, authorName);
                    instant.add(newsFeedObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return instant;
        } else {
            return null;
        }
    }

    private static String StreamData(InputStream inputStream) throws IOException {
        StringBuilder data = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readline = bufferedReader.readLine();
            while (readline != null) {
                data.append(readline);
                readline = bufferedReader.readLine();
            }
        }
        return data.toString();
    }

    private static String simpledate(String date) {
        String tempDate = null;
        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            parser.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date parsed = parser.parse(date);
            tempDate = parsed.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tempDate;
    }

    private static String HttpRequest(URL url) throws IOException {
        String JSON = "";
        if (url == null) {
            return JSON;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestMethod(requestMethod);
            urlConnection.connect();
            int code = urlConnection.getResponseCode();
            if (code != 200)
                Log.e(TAG, "Error Response Code :" + code + " :(");
            else {
                inputStream = urlConnection.getInputStream();
                JSON = StreamData(inputStream);
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (inputStream != null)
                inputStream.close();
        }
        return JSON;
    }

    private static URL createUrl(String jsonUrl) throws MalformedURLException {
        URL url1;
        url1 = null;
        url1 = new URL(jsonUrl);
        return url1;
    }
}
