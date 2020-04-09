package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {
    private static final String TAG = "JsonUtilsActivity";


    public static Sandwich parseSandwichJson(String json) {
        List<String> ingredientsList = new ArrayList<>();
        List<String> alsoKnownAsList = new ArrayList<>();

        try {

            //full details
            JSONObject parsedSandwich = new JSONObject(json);

            //only mainName and alsoKnownAs
            JSONObject parsedSandwich2 = parsedSandwich.getJSONObject("name");

            Sandwich sandwich = new Sandwich();

            sandwich.setMainName(parsedSandwich2.getString("mainName"));
            for (int i = 0; i < parsedSandwich2.getJSONArray("alsoKnownAs").length(); i++) {
                alsoKnownAsList.add(parsedSandwich2.getJSONArray("alsoKnownAs").get(i).toString());
            }

            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setPlaceOfOrigin(parsedSandwich.getString("placeOfOrigin"));
            for (int i = 0; i < parsedSandwich.getJSONArray("ingredients").length(); i++) {
                ingredientsList.add(parsedSandwich.getJSONArray("ingredients").get(i).toString());
            }

            sandwich.setIngredients(ingredientsList);
            sandwich.setDescription(parsedSandwich.getString("description"));
            sandwich.setImage(parsedSandwich.getString("image"));

            Log.d(TAG, "parseSandwichJson: " + ingredientsList);
            Log.d(TAG, "parseSandwichJson: " + alsoKnownAsList);

            return sandwich;
        } catch (JSONException e) {
            Log.d(TAG, "JSONException " + e);
        }

        return null;
    }
}
