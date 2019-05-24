/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/*
Формат получаемого jsona чтобы добраться до переменных:
{
    response
        route
            shape
            summary
                distance
                baseTime
}
Если буду использовать либу GSon -> сделать @_override для переменных Way 
с указанием точного имени переменной как в response
Алгоритм:
    Когда бежим по списку адресов поставщиков (for поставщик -> for -> адреса -> for routes (Json) )
    Сначала смотрим summary: считаем distance, потом baseTime. Находим скорость avgspeed на участке маршрута.
    Потом находим с какой очередностью пролистывать корды:
    shape/distance -> сколько кордов на 1км = M
    M*50 -> сколько кордов на 50км = K
    и считаем: 
    cordsIterator = 1 ; tempdistance=0km ; coordTime=0; - начинаем со старта
    ПОКА tempdistance < distance  
        ВЗЯТЬ погоду по shape[cordsIterator]
        ЕСЛИ tempdistance+50km < distance
            Откорректировать скорость на участке [tempdistance, tempdistance+50km] <- тут берется процент от текущей нормальной скорости
            Посчитать время coordTime+=50/coordSpeed
        ИНАЧЕ
            Откорректировать скорость на участке [tempdistance, distance] <- тут берется процент от текущей нормальной скорости
            Посчитать время coordTime+=(distance-tempdistance)/coordSpeed
        tempdistance+=50
        cordsIterator+=K
    
ПОДУМАТЬ как можно откорректировать погодные условия таким образом, что если условие не попадает под [koef-epsila,koef+epsila] принимать его за норму.
*/
public class Way {
    // Время в часах (секунды из json /360)
    private  DoubleProperty basetime;
    private  DoubleProperty correctedtime;
    // Содержит координаты формата "Latitude,Longitude","Latitude,Longitude","Latitude,Longitude"..
    private ObservableList<String> shape;
    // Расстояние в км (метры из json / 1000)
    private  DoubleProperty length;
    private Address startaddr;
    private Address endaddr;
    private Provider provider;
    private  DoubleProperty avgspeed;
    private  DoubleProperty summaryrating;
    private IntegerProperty avgdanger;
    private StringProperty weatheronway;
    
    public Way(){
        this.startaddr = null;
        this.endaddr = null;
        this.provider = null;
        this.shape = FXCollections.observableArrayList();
        this.basetime = new SimpleDoubleProperty(0.0);
        this.correctedtime =  new SimpleDoubleProperty(0.0);
        this.summaryrating =  new SimpleDoubleProperty(0.0);
        this.length =  new SimpleDoubleProperty(0.0);
        this.avgspeed =  new SimpleDoubleProperty(0.0);
        this.avgdanger = new SimpleIntegerProperty(1);
        this.weatheronway = new SimpleStringProperty("-");
    }
    public int getAvgdanger() {
        return avgdanger.get();
    }
    public void setAvgdanger(int avgdanger) {
        this.avgdanger.set(avgdanger);
    }
    public String getWeatheronway() {
        return weatheronway.get();
    }
    public void setWeatheronway(String weather) {
        this.weatheronway.set(weather);
    }
    public double getBasetime() {
        return basetime.get();
    }

    public void setBasetime(double time) {
        this.basetime.set(time);
    }
    public double getCorrectedtime() {
        return correctedtime.get();
    }

    public void setCorrectedtime(double time) {
        this.correctedtime.set(time);
    }
    
    public double getSummaryrating() {
        return summaryrating.get();
    }

    public void setSummaryrating(double summaryrating) {
        this.summaryrating.set(summaryrating);
    }

    public ObservableList<String> getShape() {
        return shape;
    }

    public void setShape(ObservableList<String> shape) {
        this.shape = shape;
    }

    public double getLength() {
        return length.get();
    }

    public void setLength(double length) {
        this.length.set(length);
    }

    public Address getStartaddr() {
        return startaddr;
    }

    public void setStartaddr(Address startaddr) {
        this.startaddr = startaddr;
    }

    public Address getEndaddr() {
        return endaddr;
    }

    public void setEndaddr(Address endaddr) {
        this.endaddr = endaddr;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Double getAvgspeed() {
        return avgspeed.get();
    }

    public void setAvgspeed(Double avgspeed) {
        this.avgspeed.set(avgspeed);
    } 
}
