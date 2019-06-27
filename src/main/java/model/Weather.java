/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author pikachu
 */
public class Weather {
    private Adjustment adjustment;
    private final IntegerProperty skyinfo;
    private final IntegerProperty humidity;
    private final DoubleProperty precipitation;
    private final DoubleProperty temperature;
    private final DoubleProperty windspeed;
    private final DoubleProperty deltaweather;
    private final StringProperty description;
    private final IntegerProperty id;

    public Adjustment getAdjustment() {
        return adjustment;
    }
    public void setAdjustment(Adjustment adj) {
        this.adjustment = adj;
    }

    public Integer getSkyinfo() {
        return skyinfo.get();
    }
    public void setSkyinfo(int Skyinfo) {
        this.skyinfo.set(Skyinfo);
    }

    public Integer getHumidity() {
        return humidity.get();
    }
    
    public void setHumidity(int humidity){
        this.humidity.set(humidity);
    }

    public Double getPrecipitation() {
        return precipitation.get();
    }
    public void setPrecipitation(double precipitation) {
        this.precipitation.set(precipitation);
    }

    public Double getTemperature() {
        return temperature.get();
    }
    public void setTemperature(double temperature){
        this.temperature.set(temperature);
    }

    public Double getWindspeed() {
        return windspeed.get();
    }
    
    public void setWindspeed(double windspeed) {
        this.windspeed.set(windspeed);
    }
    public String getDescription() {
        return description.get();
    }
    public void setDescription(String description) {
        this.description.set(description);
    }
    public Double getDeltaweather(){
        return deltaweather.get();
    }
    public void setDeltaweather(double deltaweather) {
        this.deltaweather.set(deltaweather);
    }
    public Integer getId() {
        return this.id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public Weather() {
        this.adjustment = null;
        this.skyinfo = new SimpleIntegerProperty(0);
        this.humidity = new SimpleIntegerProperty(1);
        this.precipitation = new SimpleDoubleProperty(0.1);
        this.temperature = new SimpleDoubleProperty(0.1);
        this.windspeed = new SimpleDoubleProperty(0.1);
        this.description = new SimpleStringProperty("");
        this.deltaweather = new SimpleDoubleProperty(0);
        this.id = new SimpleIntegerProperty(0);
    }
    
    
}
