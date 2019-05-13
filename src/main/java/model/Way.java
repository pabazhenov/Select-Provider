/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author pikachu
 */
public class Way {
    private IntegerProperty time;
    // Содержит координаты 
    private ObservableList<String> shape;
    private IntegerProperty length;
    private Address startaddr;
    private Address endaddr;
    private Provider provider;
    private ObservableList<Leg> legs;

    public int getTime() {
        return time.get();
    }

    public void setTime(int time) {
        this.time.set(time);
    }

    public ObservableList<String> getShape() {
        return shape;
    }

    public void setShape(ObservableList<String> shape) {
        this.shape = shape;
    }

    public int getLength() {
        return length.get();
    }

    public void setLength(int length) {
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

    public ObservableList<Leg> getLegs() {
        return legs;
    }

    public void setLegs(ObservableList<Leg> legs) {
        this.legs = legs;
    }
    
    
}
