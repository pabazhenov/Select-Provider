/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MainsceneController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author pikachu
 * 
 * Класс предназначен для передачи ссылок на контроллеры из одного контроллера в другой
 * HOW TO:
 *  создать геттер и сеттер для нужного контроллера, зарегистрировать этот класс в том контроллере 
 *          через Context.getInstance().setter_Контроллера(); 
 *          в методе initialize()
 *  создать нужные геттеры и сеттеры для свойств обьектов нужного контроллера.
 * Получать доступ к контроллеру можно через Context.getInstance().getMFController();
 * 
 * 
 */
public class Context {
    //Создать глобальный реестр fxml, куда нужно будет переходить.
    private final String mainFormPath = "/fxml/MainScene.fxml";
    // Функция загрузки FXML на форму.
    private  Parent loadFXML(String name) {
        try {
            return FXMLLoader.load(getClass().getResource(name));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Parent showMainForm() {
        return loadFXML(mainFormPath);
    }
    private final static Context instance = new Context();
    public static Context getInstance() {
        return instance;
    }
    // функции получения контроллеров форм.
    private MainsceneController mainsceneController;
    public void setMainformController(MainsceneController controller) {
        this.mainsceneController = controller;
    }
    public MainsceneController getMainformController() {
        return this.mainsceneController;
    }

}
