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
public class CriteriaToProvider {
    private Criteria criteria;
    private final StringProperty value;
    private final IntegerProperty id;
    
    public CriteriaToProvider() {
        this.criteria = new Criteria();
        this.value = new SimpleStringProperty("");
        this.id = new SimpleIntegerProperty(0);
    }
    public void setValue(String val) {
        this.value.set(val);
    }
    public String getValue() {
        return this.value.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public int getId() {
        return this.id.get();
    }
    public void setCriteria(Criteria criteria){
        this.criteria = criteria;
    }
    public Criteria getCriteria() {
        return this.criteria;
    }
    
}
