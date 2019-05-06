/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MainsceneController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pikachu
 */
public class DataAccessor {
    private Connection connection;
    public String errorcode;
    private final MainsceneController mainform;
    public DataAccessor(){
        connection = null;
        errorcode="";
        mainform = Context.getInstance().getMainformController();
    }
    public void setConnection(String username, String password, String databasepath) {
        if (username.equals("")
                ||password.equals("")
                ||databasepath.equals("")) {
            errorcode="Не заданы поля: Путь к базе данных, Имя пользователя или Пароль";
            mainform.showMessage(errorcode);
        }
        else {
            try {
                String db = "jdbc:mysql://"+databasepath;
                connection = DriverManager.getConnection(db,username,password);
                errorcode="";
            } catch (SQLException ex) {
                errorcode="Ошибка подключения к базе данных.\n"
                        + ex.toString();
                mainform.showMessage(errorcode);
            }
        }       
    }
    public void setDisconnect() {
        try {
            connection.close();
            errorcode="";
        } catch (SQLException ex) {
            errorcode="Ошибка отключения от базы данных.\n";
            mainform.showMessage(errorcode+ex.toString());
        }
    }
    public ObservableList<Product> getAllProducts(){
        ObservableList<Product> tempproducts = FXCollections.observableArrayList();
        try {
            Statement stmnt = connection.createStatement();
            String query = "Select * from Products";
            ResultSet rs = stmnt.executeQuery(query);
            while (rs.next()) {
                Product tempproduct = new Product();
                tempproduct.setId(rs.getInt("id"));
                tempproduct.setTitle(rs.getString("title"));
                tempproducts.add(tempproduct);
            }
            rs.close();
            stmnt.close();   
        } catch (SQLException ex) {
            errorcode = ex.toString();
            mainform.showMessage(errorcode);
        }
        return tempproducts;
    }
    public void addProduct(Product producttoadd) {
        try {
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * From Products where title=\""+producttoadd.getTitle()+"\"");
            if (!rs.next()) {
                String query = "INSERT INTO Products SET title='"+producttoadd.getTitle()+"'";
                stmnt.execute(query);
            }
            else
                mainform.showMessage("Данный продукт поставки уже есть в списке!");
            rs.close();
            stmnt.close();
        } catch (SQLException ex) {
            errorcode = ex.toString();
            mainform.showMessage(errorcode);
        }
        
    }
    public void updateProduct(Product producttoupdate){
        try {
            Statement stmnt = connection.createStatement();
            String query = "UPDATE `Products` SET `title` = '"+
                    producttoupdate.getTitle()+"' WHERE `Products`.`id` = "+producttoupdate.getId()+"";
            stmnt.execute(query);
            stmnt.close();
        } catch (SQLException ex) {
            errorcode = ex.toString();
            mainform.showMessage(errorcode);
        }  
    }
    public void deleteProduct(Product producttodelete) {
        try {
            Statement stmnt = connection.createStatement();
            String query1="DELETE FROM `Products` WHERE `Products`.`id` = "+producttodelete.getId();
            stmnt.execute(query1);
            String query2="DELETE FROM `ProductsToProvider` WHERE `id_products` = "+producttodelete.getId();
            stmnt.execute(query2);
            stmnt.close();
        } catch (SQLException ex) {
            errorcode = ex.toString();
            mainform.showMessage(errorcode);
        }
    
    }
    
    public void addProvider(Provider providertoadd) {
        mainform.showMessage("Поставщик добавлен!");
    }
    public void deleteProvider(Provider providertodelete) {
        mainform.showMessage("поставщик удален");
    }
    public void updateProvider(Provider providertoupdate) {
        mainform.showMessage("поставщик отредактирован");
    }
    public ObservableList<Provider> getAllProviders() {
        ObservableList<Provider> tempproviders = FXCollections.observableArrayList();
        try {
            
            Statement stmnt = connection.createStatement();
            String query="SELECT * FROM Provider";
            ResultSet rs = stmnt.executeQuery(query);
            while (rs.next()) {
                Provider tempprovider = new Provider();
                tempprovider.setId(rs.getInt("id"));
                tempprovider.setInn(rs.getString("inn"));
                tempprovider.setKpp(rs.getString("kpp"));
                tempprovider.setOgrn(rs.getString("orgn"));
                tempprovider.setOkpo(rs.getString("okpo"));
                tempprovider.setOktmo(rs.getString("oktmo"));
                tempprovider.setTitle(rs.getString("title"));
                // Выбираем адреса поставщика
                Statement substmnt = connection.createStatement();
                String selectAddresses="Select * FROM Address where id_provider="+tempprovider.getId()+"";
                ResultSet subrs = substmnt.executeQuery(selectAddresses);
                while (subrs.next()) {
                    Address tempaddr = new Address();
                    tempaddr.setId(subrs.getInt("id"));
                    tempaddr.setIndex(subrs.getString("mail_index"));
                    tempaddr.setRegion(subrs.getString("region"));
                    tempaddr.setCity(subrs.getString("city"));
                    tempaddr.setComment(subrs.getString("comment"));
                    tempaddr.setLatitude(subrs.getString("latitude"));
                    tempaddr.setLongitude(subrs.getString("longitude"));
                    tempprovider.addresses.add(tempaddr);
                }
                // Выбираем продукты поставщика
                String selectPTP="SELECT p.*, ptp.id ptp_id\n" +
                                    "FROM Products p\n" +
                                    "LEFT JOIN ProductsToProvider ptp "+
                                    "ON p.id = ptp.id_products and ptp.id_provider="+tempprovider.getId()+"";
                subrs = substmnt.executeQuery(selectPTP);
                while (subrs.next()) {
                    ProductToProvider tempptp = new ProductToProvider();
                    Product tempproduct = new Product();
                    tempproduct.setId(subrs.getInt("id"));
                    tempproduct.setTitle(subrs.getString("title"));
                    tempptp.setProduct(tempproduct);
                    int isprovide = subrs.getInt("ptp_id");
                    if (isprovide==0) {
                        tempptp.setIsprovide("нет");
                    } else {
                        tempptp.setIsprovide("да");
                    }
                    tempprovider.products.add(tempptp);
                }
                // Выбираем критерии поставщика
                String selectCTP="Select c.*, ctp.value\n, ctp.id ctp_id " +
                                    "FROM Criteria c \n" +
                                    "LEFT JOIN CriteriaToProvider ctp\n" +
                                    "ON c.id=ctp.id_criteria and ctp.id_provider="+tempprovider.getId()+"";
                subrs = substmnt.executeQuery(selectCTP);
                while (subrs.next()){
                    Criteria tempcriteria = new Criteria();
                    tempcriteria.setId(subrs.getInt("id"));
                    tempcriteria.setTitle(subrs.getString("title"));
                    tempcriteria.setImportance(subrs.getInt("importance"));
                    tempcriteria.setBinarytype(subrs.getInt("binarytype"));
                    CriteriaToProvider tempctp = new CriteriaToProvider();
                    tempctp.setCriteria(tempcriteria);
                    tempctp.setId(subrs.getInt("ctp_id"));
                    int val=subrs.getInt("value");
                    if (tempcriteria.getBinarytype()==1) {
                        if (val==0)
                            tempctp.setValue("нет");
                        else
                            tempctp.setValue("да");
                    }
                    else {
                        tempctp.setValue(String.valueOf(val));
                    }
                    tempprovider.criteries.add(tempctp);
                }
                subrs.close();
                substmnt.close();
                // Выбираем продукты поставщика
                tempproviders.add(tempprovider);
            }
            rs.close();
            stmnt.close();
        } catch (SQLException ex) {
            errorcode = ex.toString();
            mainform.showMessage(errorcode);
        }
        return tempproviders;
    }
    public Organisation getOrganisation(){
        Organisation temporganisation = new Organisation();
        try {
            Statement stmnt = connection.createStatement();
            String query="Select * from Organisation";
            ResultSet rs = stmnt.executeQuery(query);
            while (rs.next()) {
                temporganisation.setId(rs.getInt("id"));
                temporganisation.setTitle(rs.getString("title"));
            }
            query="Select * from Address where id_organisation="+temporganisation.getId()+"";
            rs = stmnt.executeQuery(query);
            while (rs.next()) {
                Address tempaddr = new Address();
                tempaddr.setId(rs.getInt("id"));
                tempaddr.setIndex(rs.getString("mail_index"));
                tempaddr.setRegion(rs.getString("region"));
                tempaddr.setCity(rs.getString("city"));
                tempaddr.setComment(rs.getString("comment"));
                tempaddr.setLatitude(rs.getString("latitude"));
                tempaddr.setLongitude(rs.getString("longitude"));
                temporganisation.addresses.add(tempaddr);
            }
        } catch (SQLException ex) {
            errorcode = ex.toString();
            mainform.showMessage(errorcode);
        }
        return temporganisation;
    }
    public void updateOrganisation(Organisation organisation) {
        mainform.showMessage("Изменения сохранены");
    }
    public ObservableList<Criteria> getAllCriteries() {
        ObservableList<Criteria> tempcriteries = FXCollections.observableArrayList();
        try {
            Statement stmnt = connection.createStatement();
            String query="Select * from Criteria";
            ResultSet rs = stmnt.executeQuery(query);
            while (rs.next()) {
                Criteria tempcriteria = new Criteria();
                tempcriteria.setTitle(rs.getString("title"));
                tempcriteria.setId(rs.getInt("id"));
                tempcriteria.setImportance(rs.getInt("importance"));
                tempcriteria.setBinarytype(rs.getInt("binarytype"));
                tempcriteries.add(tempcriteria);
            }
            rs.close();
            stmnt.close();
        } catch (SQLException ex) {
            errorcode = ex.toString();
            mainform.showMessage(errorcode);
        }
        return tempcriteries;
    }
}
