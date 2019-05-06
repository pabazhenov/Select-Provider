/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author pikachu
 */
public class Adjustment {
    private final IntegerProperty dangerlevel;
    private final IntegerProperty id;
    private final IntegerProperty percentagespeed;

    public void setDangerlevel(int DangerLevel) {
        dangerlevel.set(DangerLevel);
    }
    public int getDangerlevel() {
        return dangerlevel.get();
    }
    public int getId() {
        return id.get();
    }
    public void setId(int VisibilityStartRange) {
        id.set(VisibilityStartRange);
    }
    public int getPercentagespeed() {
        return percentagespeed.get();
    }
    public void setPercentageSpeed(int VisibilityEndRange) {
        percentagespeed.set(VisibilityEndRange);
    }
    public Adjustment() {
        this.id = new SimpleIntegerProperty();
        this.percentagespeed = new SimpleIntegerProperty();
        this.dangerlevel = new SimpleIntegerProperty();
        
    }     
}
