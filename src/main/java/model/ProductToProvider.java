/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author pikachu
 */
public class ProductToProvider {
    private Product product;
    private StringProperty isprovide;
    
    public ProductToProvider(){
        this.product = new Product();
        this.isprovide = new SimpleStringProperty("");
    }
    public void setIsprovide(String val){
        this.isprovide.set(val);
    }
    public String getIsprovide(){
        return this.isprovide.get();
    }
    public void setProduct(Product product){
        this.product=product;
    }
    public Product getProduct(){
        return this.product;
    }
    
}
