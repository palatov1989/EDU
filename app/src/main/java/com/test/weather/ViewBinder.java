package com.test.weather;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class ViewBinder {

    static private int[] drawID = {R.drawable.w0, R.drawable.w1, R.drawable.w2,
            R.drawable.w3, R.drawable.w5, R.drawable.w6,
    };
    private TextView tvCity;
    private TextView tvTemp;
    private TextView tvTemper;
    private TextView tvWind;
    private TextView tvWindDir;
    private TextView tvHumidity;
    private TextView tvAslPressure;
    private TextView tvGndPressure;
    private TextView tvDescription;
    private TextView tvDate;

    private ImageView imgWeatherLogo;
    private ImageView imgWeatherId;
    private CircleImageView   imgWindDir;

    private Date date;
    private City city;
    private ArrayList<WeekCastElement> forecastArray;

    public ViewBinder(Activity activity) {
        date = new Date();

        tvCity = (TextView) activity.findViewById(R.id.city);
        tvTemp = (TextView) activity.findViewById(R.id.temp);
        tvTemper = (TextView) activity.findViewById(R.id.Temper);
        tvHumidity = (TextView) activity.findViewById(R.id.humidity);
        tvAslPressure = (TextView) activity.findViewById(R.id.aslPressure);
        tvGndPressure = (TextView) activity.findViewById(R.id.gndPressure);
        tvWindDir = (TextView) activity.findViewById(R.id.windDir);
        tvWind = (TextView) activity.findViewById(R.id.wind);
        tvDescription = (TextView) activity.findViewById(R.id.description);
        tvDate = (TextView) activity.findViewById(R.id.dt_txt);

        imgWeatherLogo = (ImageView) activity.findViewById(R.id.weatherLogo);
        imgWeatherId   = (ImageView) activity.findViewById(R.id.weather_img);
        imgWindDir = (CircleImageView) activity.findViewById(R.id.wind_dir);
    }

    public void bind(City city) {
        this.city=city;
        int i = 0;
        int k = city.getList().size();
        while ((i < k) && (date.after(city.getList().get(i).getDate()))) {
            i++;
        }
        if (i >= k) return;
        ListElement forecast = city.getList().get(i);

        tvDate.setText(forecast.getDt_txt());
        tvCity.setText(city.getName());
        int min = Math.round(Float.parseFloat(forecast.getMainWeather().getTempMin()) - 273);
        int max = Math.round(Float.parseFloat(forecast.getMainWeather().getTempMax()) - 273);
        tvTemp.setText("Temperature: " + Integer.toString(min) + ".." + Integer.toString(max));
        tvTemper.setText(Integer.toString(min) + ".." + Integer.toString(max));

        tvHumidity.setText("Humidity: " + forecast.getMainWeather().getHumidity() + "%");
        tvAslPressure.setText("ASL pressure: " + forecast.getMainWeather().getAslPressure() + "");
        tvGndPressure.setText("Ground pressure: " + forecast.getMainWeather().getGndPressure());
        tvWind.setText(forecast.getWindWeather().getSpeed() + " m/s");
        tvWindDir.setText(Float.toString(forecast.getWindWeather().getDirection()).substring(0, 2) + "'");
        imgWindDir.setRotation(180 + forecast.getWindWeather().getDirection());

        String url = new String("http://openweathermap.org/img/w/");
        url += forecast.getWeather().getIcon() + ".png";
        new DownloadImageTask(imgWeatherId).execute(url);
        tvDescription.setText("Now here - " + forecast.getWeather().getDescription());

        int weatherID = Integer.parseInt(forecast.getWeather().getID());
        if (weatherID < 800) {
            imgWeatherLogo.setImageResource(drawID[weatherID / 100]);
        } else {
            if (weatherID >= 900) {
                imgWeatherLogo.setImageResource(drawID[7]);
            }
            if (weatherID / 100 == 8) {
                if (weatherID - 800 > 2) {
                    imgWeatherLogo.setImageResource(drawID[2]);
                } else {
                    imgWeatherLogo.setImageResource(drawID[1]);
                    if (weatherID - 800 == 0) {
                        imgWeatherLogo.setImageResource(drawID[0]);
                    }
                }
            }
        }
        MainWeather weather;
        WeekCastElement mItem;
        while (i < k) {
            mItem = new WeekCastElement();
            forecastArray.add(mItem);
            mItem = forecastArray.get(i);
            String str = city.getList().get(i).getDt_txt();

            str.substring(5, 11);
            mItem.setDate(str);
            weather = city.getList().get(i).getMainWeather();

            int minT = Math.round(Float.parseFloat(weather.getTempMin()) - 273);
            int maxT = Math.round(Float.parseFloat(weather.getTempMax()) - 273);
            str = ("  " + Integer.toString(minT) + ".." + Integer.toString(maxT));
            mItem.setText(str);
            i++;
        }
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<WeekCastElement> getForecastArray() {
        return forecastArray;
    }

    public void setForecastArray(ArrayList<WeekCastElement> forecastArray) {
        this.forecastArray = forecastArray;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
