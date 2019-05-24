/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author pikachu
 */
public class Address {
    private final StringProperty index;
    private final StringProperty region;
    private final StringProperty city;
    private final StringProperty comment;
    private final IntegerProperty id;
    private final StringProperty longitude;
    private final StringProperty latitude;
    
    @Override
    public String toString() {
        return ""+index.get()+","+region.get()+","+city.get();
    }
    public void setIndex(String IndexValue) {
        index.set(IndexValue);
    }
    public String getIndex() {
        return index.get();
    }
    public void setRegion (String RegionValue) {
        region.set(RegionValue);
    }
    public String getRegion() {
        return region.get();
    }
    public void setCity(String CityValue) {
        city.set(CityValue);
    }
    public String getCity() {
        return city.get();
    }
    public void setLongitude(String LongitudeValue) {
        longitude.set(LongitudeValue);
    }
    public String getLongitude() {
        return longitude.get();
    }
    public void setLatitude(String LatitudeValue) {
        latitude.set(LatitudeValue);
    }
    public String getLatitude() {
        return latitude.get();
    }
    public void setComment(String CommentValue) {
        comment.set(CommentValue);
    }
    public String getComment() {
        return comment.get();
    }
    public void setId(int IdValue) {
        id.set(IdValue);
    }
    public int getId() {
        return id.get();
    }
    public Address() {
        this.index = new SimpleStringProperty("");
        this.region = new SimpleStringProperty("");
        this.city = new SimpleStringProperty("");
        this.longitude = new SimpleStringProperty("");
        this.latitude = new SimpleStringProperty("");
        this.comment = new SimpleStringProperty("");
        this.id = new SimpleIntegerProperty(0);
    }
}
