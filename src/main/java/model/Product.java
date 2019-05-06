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
public class Product {
    private final StringProperty name;
    private final IntegerProperty id;
    public void setTitle(String ProductName) {
        name.set(ProductName);
    }
    public String getTitle() {
        return name.get();
    }
    public void setId(int ProductId) {
        id.set(ProductId);
    }
    public int getId(){
        return id.get();
    }
    public Product() {
        this.name = new SimpleStringProperty();
        this.id = new SimpleIntegerProperty();
    }
    public Product(Product p) {
        this.name = new SimpleStringProperty(p.getTitle());
        this.id = new SimpleIntegerProperty(p.getId());
    }
    @Override
    public String toString(){
        return this.name.get();
    }
}
