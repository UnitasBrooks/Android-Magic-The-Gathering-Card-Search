package com.example.android.navigationdrawerexample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: DarthDesktop
 * Date: 5/25/14
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonReader {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONObject readJsonFromUrlObj(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
        finally {
            is.close();
        }
    }

    public String getID(String url) {
        JSONArray json;
        String id = null;
        try {
            json = readJsonFromUrl(url);
            id = json.getJSONObject(0).get("id").toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            try {
                JSONObject obj = readJsonFromUrlObj(url);
                id = obj.get("id").toString();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return id;
    }

    public String getName(String url) {

        return null;
    }
}
