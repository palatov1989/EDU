package com.test.weather;

import java.util.ArrayList;

/**
 * Created by virus on 05.03.2017.
 */

public class City {
    private String name;
    private ArrayList<List_element> list;

    public City(){
        name ="";
        list = new ArrayList<List_element>(15);}

    public String getName() {
        return name;
    }

    public ArrayList<List_element> getList() {
        return list;
    }

    public void setList(ArrayList<List_element> list) {
        this.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }
}
