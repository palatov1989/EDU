package com.test.weather;

import android.view.View;
import java.util.ArrayList;


/**
 * Created by virus on 05.03.2017.
 */

public class ViewBinder {
    View activity;
    private City city;
    private ArrayList<List> list;
    public ViewBinder(String json){

        list = new ArrayList<List>();
        city = new City();
    }

    public void bind(View v){
        activity = v;



    }





    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<List> getList() {
        return list;
    }

    public void setList(ArrayList<List> list) {
        this.list = list;
    }
}
