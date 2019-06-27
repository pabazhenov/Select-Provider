/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonPOJO;

/**
 *
 * @author pikachu
 */
public class forecasts {
    forecastLocation forecastLocation;

    public forecastLocation getForecastLocation() {
        return forecastLocation;
    }

    public void setForecastLocation(forecastLocation forecastLocation) {
        this.forecastLocation = forecastLocation;
    }
    public forecasts() {
        forecastLocation = new forecastLocation();
    }
}
