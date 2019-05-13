/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author pikachu
 */

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
    private IntegerProperty time;
    // Содержит координаты формата "Latitude,Longitude","Latitude,Longitude","Latitude,Longitude"..
    private ObservableList<String> shape;
    // Расстояние в км (метры из json / 1000)
    private IntegerProperty length;
    private Address startaddr;
    private Address endaddr;
    private Provider provider;
    private int avgspeed;

    public int getTime() {
        return time.get();
    }

    public void setTime(int time) {
        this.time.set(time);
    }

    public ObservableList<String> getShape() {
        return shape;
    }

    public void setShape(ObservableList<String> shape) {
        this.shape = shape;
    }

    public int getLength() {
        return length.get();
    }

    public void setLength(int length) {
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
  
}
