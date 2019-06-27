/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonPOJO;

import java.util.ArrayList;

/**
 *
 * @author pikachu
 */
public class forecastLocation {

    public ArrayList<forecast> getForecast() {
        return forecast;
    }

    public void setForecast(ArrayList<forecast> forecast) {
        this.forecast = forecast;
    }
    ArrayList<forecast> forecast;
    
}
