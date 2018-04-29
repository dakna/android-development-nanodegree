package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {
        //parse details
        JSONObject jsonObject;
        JSONObject nameJsonObject;
        Sandwich sandwich = new Sandwich();

        try {
            jsonObject = new JSONObject(json);

            // name has sub level
            nameJsonObject = jsonObject.getJSONObject("name");
            sandwich.setMainName(nameJsonObject.getString("mainName"));
            sandwich.setAlsoKnownAs(getListFromArray(nameJsonObject.getJSONArray("alsoKnownAs")));

            // these are all on main level
            sandwich.setDescription(jsonObject.getString("description"));
            sandwich.setImage(jsonObject.getString("image"));
            sandwich.setIngredients(getListFromArray(jsonObject.getJSONArray("ingredients")));
            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));

        } catch (JSONException e) {
            Log.d(TAG, "parseSandwichJson: Exception " + e.toString());
            e.printStackTrace();
        }

        return sandwich;
    }

    private static List<String> getListFromArray(JSONArray array) {

        List<String> list = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {
                list.add(array.getString(i));
            }
        } catch (JSONException e) {
            Log.d(TAG, "getListFromArray: Exception " + e.toString());
            e.printStackTrace();
        }

        return list;
    }
}
