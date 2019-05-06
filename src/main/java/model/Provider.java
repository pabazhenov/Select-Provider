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
public class Provider {
    private final StringProperty title;
    private final StringProperty ogrn;
    private final StringProperty inn;
    private final StringProperty kpp;
    private final StringProperty okpo;
    private final StringProperty oktmo;
    private final IntegerProperty id;
    private final IntegerProperty rating;
    public ObservableList<Address> addresses;
    public ObservableList<ProductToProvider> products;
    public ObservableList<CriteriaToProvider> criteries;
    
    public void setTitle(String NameValue) {
        title.set(NameValue);
    }
    public String getTitle() {
        return title.get();
    }
    public void setRating(int RateValue) {
        rating.set(RateValue);
    }
    public int getRating() {
        return rating.get();
    }
    public void setOgrn(String ORGNValue) {
        ogrn.set(ORGNValue);
    }
    public String getOgrn() {
        return ogrn.get();
    }
    public void setInn(String INNValue) {
        inn.set(INNValue);
    }
    public String getInn() {
        return inn.get();
    }
    public void setKpp(String KPPValue) {
        kpp.set(KPPValue);
    }
    public String getKpp() {
        return kpp.get();
    }
    public void setOkpo(String OKPOValue) {
        okpo.set(OKPOValue);
    }
    public String getOkpo() {
        return okpo.get();
    }
    public void setOktmo(String OKTMOValue) {
        oktmo.set(OKTMOValue);
    }
    public String getOktmo() {
        return oktmo.get();
    }
    public void setId(int IdValue) {
        id.set(IdValue);
    }
    public int getId() {
        return id.get();
    }
    public Provider() {
        this.title = new SimpleStringProperty("");
        this.ogrn = new SimpleStringProperty("");
        this.inn = new SimpleStringProperty("");
        this.kpp = new SimpleStringProperty("");
        this.okpo = new SimpleStringProperty("");
        this.oktmo = new SimpleStringProperty("");
        this.id = new SimpleIntegerProperty(0) ;
        this.rating = new SimpleIntegerProperty(0);
        this.addresses = FXCollections.observableArrayList();
        this.criteries = FXCollections.observableArrayList();
        this.products = FXCollections.observableArrayList();
    }
}
