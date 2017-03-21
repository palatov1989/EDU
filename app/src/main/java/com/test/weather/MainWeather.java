package com.test.weather;

/**
 * Created by virus on 05.03.2017.
 */

public class MainWeather {
    private   String tempMin ="";
    private   String tempMax ="";
    private    String humidity="";
    private    String aslPressure ="";
    private    String gndPressure ="";

    public void Main(){}

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAslPressure() {
        return aslPressure;
    }

    public void setAslPressure(String aslPressure) {
        this.aslPressure = aslPressure;
    }

    public String getGndPressure() {
        return gndPressure;
    }

    public void setGndPressure(String gndPressure) {
        this.gndPressure = gndPressure;
    }
}
