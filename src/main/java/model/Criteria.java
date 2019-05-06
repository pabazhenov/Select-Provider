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
public class Criteria {
    private final StringProperty title;
    private final IntegerProperty binarytype;
    private final IntegerProperty id;
    private final IntegerProperty importance;
    
    @Override
    public String toString() {
        return this.title.get();
    }
    public void setTitle(String NameValue) {
        this.title.set(NameValue);
    }
    public String getTitle() {
        return this.title.get();
    }
    public void setImportance(int ImportanceValue) {
        importance.set(ImportanceValue);
    }
    public int getImportance() {
        return importance.get();
    }
    public void setBinarytype(int TypeValue) {
        this.binarytype.set(TypeValue);
    }
    public int getBinarytype() {
        return this.binarytype.get();
    }
    public void setId(int idValue) {
        id.set(idValue);
    }
    public int getId() {
        return id.get();
    }
    public Criteria() {
        this.title = new SimpleStringProperty();
        this.binarytype = new SimpleIntegerProperty();
        this.id = new SimpleIntegerProperty();
        this.importance = new SimpleIntegerProperty();
    }
    public Criteria(int id, String title, int type,int importance) {
        this.title = new SimpleStringProperty(title);
        this.binarytype = new SimpleIntegerProperty(type);
        this.id = new SimpleIntegerProperty(id);
        this.importance = new SimpleIntegerProperty(importance);
    }
}
