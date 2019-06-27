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
public class forecast {
    private int skyInfo;
    private double temperature;
    private int humidity;
    private double precipitation6H;
    private double windSpeed;
    private String utcTime;

    public int getSkyInfo() {
        return skyInfo;
    }

    public void setSkyInfo(int skyInfo) {
        this.skyInfo = skyInfo;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPrecipitation6H() {
        return precipitation6H;
    }

    public void setPrecipitation6H(double precipitation6H) {
        this.precipitation6H = precipitation6H;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getUtcTime() {
        return utcTime;
    }

    public void setUtcTime(String utcTime) {
        this.utcTime = utcTime;
    }

    public forecast() {
        this.skyInfo = 0;
        this.temperature = 0;
        this.humidity = 0;
        this.precipitation6H = 0;
        this.windSpeed = 0;
        this.utcTime = "";
    }
    
    
}
