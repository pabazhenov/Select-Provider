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
public class JsonWeatherPOJO {
    forecasts forecasts;

    public forecasts getForecasts() {
        return forecasts;
    }

    public void setForecasts(forecasts forecasts) {
        this.forecasts = forecasts;
    }
    public JsonWeatherPOJO() {
        forecasts = new forecasts();
    }
}
