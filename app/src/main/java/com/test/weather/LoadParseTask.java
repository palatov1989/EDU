package com.test.weather;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoadParseTask extends AsyncTask<String, Void, City> {
    private City city;
    private ViewBinder binder;

    public LoadParseTask(City city) {
        this.city=city;
    }

    public void setBinder(ViewBinder binder) {
        this.binder = binder;
    }

    public void parse(String json){
        city = new City();
        String img_url = new String("http://openweathermap.org/img/w/");
        String icon;
        //parser here
        try {
            JSONObject obj, jsonListElement;
            JSONArray weather;
            JSONObject js = new JSONObject(json);

            obj     = js.getJSONObject("city");
            city.setName(obj.getString("name"));

            JSONArray arr = js.getJSONArray("list");

            for (int i=0; i<arr.length(); i++) {

                ListElement myListElement = new ListElement();
                jsonListElement = arr.getJSONObject(i);

                myListElement.setDt_txt(jsonListElement.getString("dt_txt"));
                myListElement.getDate().setTime(jsonListElement.getLong("dt")*1000L);

                obj = jsonListElement.getJSONObject("main");
                myListElement.getMainWeather().setHumidity(obj.getString("humidity"));
                myListElement.getMainWeather().setAslPressure(obj.getString("sea_level"));
                myListElement.getMainWeather().setGndPressure(obj.getString("grnd_level"));
                myListElement.getMainWeather().setTempMax(obj.getString("temp_max"));
                myListElement.getMainWeather().setTempMin(obj.getString("temp_min"));

                weather = jsonListElement.getJSONArray("weather");
                obj = weather.getJSONObject(0);
                myListElement.getWeather().setDescription(obj.getString("description"));
                myListElement.getWeather().setID(obj.getString("id"));

                icon = obj.getString("icon");
                myListElement.getWeather().setIcon(icon);

                obj = jsonListElement.getJSONObject("wind");
                myListElement.getWindWeather().setSpeed(obj.getString("speed"));
                myListElement.getWindWeather().setDirection((float) obj.getDouble("deg"));

                city.getList().add(myListElement);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected City doInBackground(String... str) {
        String content;
        try {
            content = getContent(str[0]);
        } catch (IOException ex) {
            content = ex.getMessage();
        }
        if (content != null) this.parse(content);
        return (city);
    }

    private String getContent(String path) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(path);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setReadTimeout(10000);
            c.connect();
            reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder buf = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buf.append(line + "\n");
            }
            return (buf.toString());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }



    @Override
    protected void onPostExecute(City result) {
        binder.bind(city);
    }
}
