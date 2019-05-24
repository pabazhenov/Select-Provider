/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonPOJO;

import java.util.ArrayList;

/**
 *
 * @author Bazhenov_PA
 */
public class Route {
    private ArrayList<String> shape;

    public ArrayList<String> getShape() {
        return shape;
    }

    public void setShape(ArrayList<String> shape) {
        this.shape = shape;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }
    private Summary summary;
    
}
