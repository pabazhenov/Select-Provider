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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Way {
    // Время в часах (секунды из json /360)
    private final  DoubleProperty basetime;
    private final  DoubleProperty correctedtime;
    // Содержит координаты формата "Latitude,Longitude","Latitude,Longitude","Latitude,Longitude"..
    private ObservableList<String> shape;
    // Расстояние в км (метры из json / 1000)
    private final  DoubleProperty length;
    private Address startaddr;
    private Address endaddr;
    private Provider provider;
    private final  DoubleProperty avgspeed;
    private final  DoubleProperty summaryrating;
    private final IntegerProperty avgdanger;
    private final StringProperty weatheronway;
    
    public Way(){
        this.startaddr = null;
        this.endaddr = null;
        this.provider = null;
        this.shape = FXCollections.observableArrayList();
        this.basetime = new SimpleDoubleProperty(0.0);
        this.correctedtime =  new SimpleDoubleProperty(0.0);
        this.summaryrating =  new SimpleDoubleProperty(0.0);
        this.length =  new SimpleDoubleProperty(0.0);
        this.avgspeed =  new SimpleDoubleProperty(0.0);
        this.avgdanger = new SimpleIntegerProperty(1);
        this.weatheronway = new SimpleStringProperty("-");
    }
    public int getAvgdanger() {
        return avgdanger.get();
    }
    public void setAvgdanger(int avgdanger) {
        this.avgdanger.set(avgdanger);
    }
    public String getWeatheronway() {
        return weatheronway.get();
    }
    public void setWeatheronway(String weather) {
        this.weatheronway.set(weather);
    }
    public double getBasetime() {
        return basetime.get();
    }

    public void setBasetime(double time) {
        this.basetime.set(time);
    }
    public double getCorrectedtime() {
        return correctedtime.get();
    }

    public void setCorrectedtime(double time) {
        this.correctedtime.set(time);
    }
    
    public double getSummaryrating() {
        return summaryrating.get();
    }

    public void setSummaryrating(double summaryrating) {
        this.summaryrating.set(summaryrating);
    }

    public ObservableList<String> getShape() {
        return shape;
    }

    public void setShape(ObservableList<String> shape) {
        this.shape = shape;
    }

    public double getLength() {
        return length.get();
    }

    public void setLength(double length) {
        this.length.set(length);
    }

    public Address getStartaddr() {
        return startaddr;
    }

    public void setStartaddr(Address startaddr) {
        this.startaddr = startaddr;
    }

    public Address getEndaddr() {
        return endaddr;
    }

    public void setEndaddr(Address endaddr) {
        this.endaddr = endaddr;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Double getAvgspeed() {
        return avgspeed.get();
    }

    public void setAvgspeed(Double avgspeed) {
        this.avgspeed.set(avgspeed);
    } 
}
