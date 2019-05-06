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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pikachu
 */
public class Organisation {
    private final StringProperty title;
    private final IntegerProperty id;
    public ObservableList<Address> addresses;
    
    public void setTitle(String NameValue) {
        title.set(NameValue);
    }
    public String getTitle() {
        return title.get();
    }
    public void setId(int IdValue) {
        id.set(IdValue);
    }
    public int getId() {
        return id.get();
    }
    public Organisation() {
        this.title = new SimpleStringProperty();
        this.id = new SimpleIntegerProperty() ;
        this.addresses = FXCollections.observableArrayList();
    }
}
