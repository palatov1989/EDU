package com.test.weather;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewBinder {


    ViewBinder(String Json){
    try {
        JSONObject dataJsonObj = new JSONObject(Json);
        JSONArray friends = dataJsonObj.getJSONArray("friends");
        JSONObject secondFriend = friends.getJSONObject(1);

        for (int i = 0; i < friends.length(); i++) {
            JSONObject friend = friends.getJSONObject(i);
            JSONObject contacts = friend.getJSONObject("contacts");
        }

    } catch (JSONException e) {
        e.printStackTrace();
    }
}


}
