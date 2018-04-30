package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        return new Sandwich(
                getMainName(json),
                getAlsoKnownAs(json),
                getJSONStringValue(json,"placeOfOrigin"),
                getJSONStringValue(json,"description"),
                getJSONStringValue(json,"image"),
                getJSONListValue(json, "ingredients")
        );
    }

    private static String getMainName(String json) {
        JSONObject name;
        String mainName = null;
        try {
            JSONObject sandwich = new JSONObject(json);
            name = sandwich.getJSONObject("name");
            mainName = name.getString("mainName");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mainName;
    }

    private static List<String> getAlsoKnownAs(String json) {
        List<String> alsoKnownAs = new ArrayList<>();
        JSONObject name;
        try {
            JSONObject sandwich = new JSONObject(json);
            name = sandwich.getJSONObject("name");
            JSONArray jsonArray = name.getJSONArray("alsoKnownAs");
            for (int i = 0; i < jsonArray.length(); i++) {
                alsoKnownAs.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return alsoKnownAs;
    }

    private static String getJSONStringValue(String json, String name) {
        String value = null;
        try {
            JSONObject sandwich = new JSONObject(json);
            value = sandwich.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value;
    }

    private static List<String> getJSONListValue(String json, String name) {
        List<String> value = new ArrayList<>();
        try {
            JSONObject sandwich = new JSONObject(json);
            JSONArray jsonArray = sandwich.getJSONArray(name);
            for (int i = 0; i < jsonArray.length(); i++) {
                value.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value;
    }
}
