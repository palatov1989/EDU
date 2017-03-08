package com.test.weather;

/**
 * Created by virus on 05.03.2017.
 */

public class Weather {
    private String description="";
    private String icon="";
    private String ID;



    public void Weather(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}
