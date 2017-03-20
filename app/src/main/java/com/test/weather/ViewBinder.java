package com.test.weather;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ViewBinder {

    private City city;
    private ArrayList<WeekCastElement> forecast_arr;
    public  ArrayList<WeekCastElement> getForecast_arr() {
        return forecast_arr;
    }

    static private int[] drawID = { R.drawable.w0, R.drawable.w1, R.drawable.w2,
                                    R.drawable.w3, R.drawable.w5, R.drawable.w6,
//                                    R.drawable.w7, R.drawable.w9
    };

    public ViewBinder(ArrayList<WeekCastElement> arr) {this.forecast_arr = arr;}

    public City getCity() {
        return city;
    }

    public void parse(String json){
        city = new City();
        String img_url = new String("http://openweathermap.org/img/w/");
        String icon;
        //parser here
        try {
            JSONObject obj, list_element;
            JSONArray  weather;
            JSONObject js = new JSONObject(json);

            obj     = js.getJSONObject("city");
            city.setName(obj.getString("name"));

            JSONArray arr = js.getJSONArray("list");

            for (int i=0; i<arr.length(); i++) {

                ListElement mListElement = new ListElement();
                list_element = arr.getJSONObject(i);

                mListElement.setDt_txt(list_element.getString("dt_txt"));
                mListElement.getDate().setTime(list_element.getLong("dt")*1000L);

                obj = list_element.getJSONObject("main");
                mListElement.getMainWeather().setHumidity(obj.getString("humidity"));
                mListElement.getMainWeather().setAsl_pressure(obj.getString("sea_level"));
                mListElement.getMainWeather().setGnd_pressure(obj.getString("grnd_level"));
                mListElement.getMainWeather().setTemp_max(obj.getString("temp_max"));
                mListElement.getMainWeather().setTemp_min(obj.getString("temp_min"));

                weather = list_element.getJSONArray("weather");
                obj = weather.getJSONObject(0);
                mListElement.getWeather().setDescription(obj.getString("description"));
                mListElement.getWeather().setID(obj.getString("id"));

                icon = obj.getString("icon");
                mListElement.getWeather().setIcon(icon);
                WeekCastElement mItem = new WeekCastElement();
/*
                try {
                    new DownloadImageTask(mItem.getImg()).execute(img_url+icon+".png");
                } catch (Exception e) {e.printStackTrace();}
*/
                obj = list_element.getJSONObject("wind");
                mListElement.getWindWeather().setSpeed(obj.getString("speed"));
                mListElement.getWindWeather().setDirection((float) obj.getDouble("deg"));

                city.getList().add(mListElement);
                forecast_arr.add(mItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void bind(TaskAttr attr){
    //set content view attr here
    Activity    activity       = attr.getContent();
    Date        date           = attr.getDate();
        int i=0;
        int k =city.getList().size();
        while ((i<k)&&(date.after(city.getList().get(i).getDate())))
        {i++;}
        if (i>=k) return;
            ListElement forecast = city.getList().get(i);

        TextView t = (TextView) activity.findViewById(R.id.dt_txt);
        t.setText(forecast.getDt_txt());

        t = (TextView) activity.findViewById(R.id.city);
        t.setText(city.getName());

        t = (TextView) activity.findViewById(R.id.temp);
        int min = Math.round(Float.parseFloat(forecast.getMainWeather().getTemp_min())-273);
        int max = Math.round(Float.parseFloat(forecast.getMainWeather().getTemp_max())-273);
        t.setText("Temperature: "+Integer.toString(min)+".."+Integer.toString(max));
        t = (TextView) activity.findViewById(R.id.Temper);
        t.setText(Integer.toString(min)+".."+Integer.toString(max));

        t = (TextView) activity.findViewById(R.id.humidity);
        t.setText("Humidity: "+forecast.getMainWeather().getHumidity() + "%");
        t = (TextView) activity.findViewById(R.id.asl_pressure);
        t.setText("ASL pressure: "+forecast.getMainWeather().getAsl_pressure()+"");
        t = (TextView) activity.findViewById(R.id.gnd_pressure);
        t.setText("Ground pressure: "+forecast.getMainWeather().getGnd_pressure());

        t = (TextView) activity.findViewById(R.id.Wind);
        t.setText(forecast.getWindWeather().getSpeed()+" m/s");
        t = (TextView) activity.findViewById(R.id.Wind_dir);
        t.setText(Float.toString(forecast.getWindWeather().getDirection()).substring(0,2)+"'");
        CircleImageView wDir = (CircleImageView) activity.findViewById(R.id.wind_dir);
        wDir.setRotation(180+forecast.getWindWeather().getDirection());

        String url = new String("http://openweathermap.org/img/w/");
        url += forecast.getWeather().getIcon() + ".png";
        new DownloadImageTask((ImageView) activity.findViewById(R.id.weather_img)).execute(url);
        t = (TextView) activity.findViewById(R.id.description);
        t.setText("Now here - "+forecast.getWeather().getDescription());

        ImageView weather_logo = (ImageView) activity.findViewById(R.id.weather_logo);
        int j = Integer.parseInt(forecast.getWeather().getID());
        if (j<800) {
            weather_logo.setImageResource(drawID[j / 100]);
        }
        else
        {
            if (j>=900) {weather_logo.setImageResource(drawID[7]);}
            if (j/100==8) {
                if(j-800 > 2)
                {weather_logo.setImageResource(drawID[2]);}
                else {
                    weather_logo.setImageResource(drawID[1]);
                    if (j-800==0) {weather_logo.setImageResource(drawID[0]);}
                }
            }
        }
        MainWeather weather;
        WeekCastElement mItem;
        while (i<k) {
            mItem = forecast_arr.get(i);
            String str = city.getList().get(i).getDt_txt();

            str.substring(5,11);
            mItem.setDate(str);
            weather = city.getList().get(i).getMainWeather();

            int minT = Math.round(Float.parseFloat(weather.getTemp_min())-273);
            int maxT = Math.round(Float.parseFloat(weather.getTemp_max())-273);
            str = ("  "+Integer.toString(minT)+".."+Integer.toString(maxT));
            mItem.setText(str);
            i++;
        }
    }
}
