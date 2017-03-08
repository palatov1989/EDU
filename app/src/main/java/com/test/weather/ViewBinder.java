package com.test.weather;

import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ViewBinder {
    private City city;
    public ViewBinder(String json) {
        List_element mlist_element = new List_element();
        city = new City();
        //parser here
        try {
            JSONObject list_element, obj;
            JSONObject js = new JSONObject(json);

            obj     = js.getJSONObject("city");
            city.setName(obj.getString("name"));

            JSONArray arr = js.getJSONArray("list");

            for (int i=0; i<arr.length(); i++) {
                list_element = arr.getJSONObject(i);
                mlist_element.setDt_txt(list_element.getString("dt_txt"));
                mlist_element.getDate().setTime(list_element.getLong("dt"));

                obj = list_element.getJSONObject("main");
                mlist_element.getMain().setHumidity(obj.getString("humidity"));
                mlist_element.getMain().setAsl_pressure(obj.getString("sea_level"));
                mlist_element.getMain().setGnd_pressure(obj.getString("gnd_level"));
                mlist_element.getMain().setTemp_max(obj.getString("temp_max"));
                mlist_element.getMain().setTemp_min(obj.getString("temp_min"));

                obj = list_element.getJSONObject("weather");
                mlist_element.getWeather().setDescription(obj.getString("description"));
                mlist_element.getWeather().setIcon(obj.getString("icon"));

                obj = list_element.getJSONObject("wind");
                mlist_element.getWind().setSpeed(obj.getString("speed"));
                mlist_element.getWind().setDirection((float) obj.getDouble("deg"));

                city.getList().add(mlist_element);
            }
        } catch (JSONException e) {
            e.printStackTrace(); city=null;
        }
    }


    public void bind(View activity){
    //set content view attr here
        Date date= new Date();
        int i=0;
        while (i<city.getList().size() && date.compareTo(city.getList().get(i).getDate())<0  )
        {i++;}
        if (i>city.getList().size()) i--;

        List_element forecast = city.getList().get(i);

        TextView t = (TextView) activity.findViewById(R.id.city);
        t.setText(city.getName());

        t = (TextView) activity.findViewById(R.id.temp);
        t.setText(forecast.getMain().getTemp_min()+".."+forecast.getMain().getTemp_min());

    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
