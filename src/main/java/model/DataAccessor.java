/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import controller.MainsceneController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import JsonPOJO.*;
import java.io.InputStream;
import java.math.BigDecimal;
import org.apache.http.HttpEntity;
import static org.apache.http.HttpHeaders.USER_AGENT;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author pikachu
 */
public class DataAccessor {
    private Connection connection;
    public String errorcode;
    private final MainsceneController mainform;
    private final JOpenCageGeocoder jOpenCageGeocoder;
    private String hereappkey_id;
    private String hereappkey_code;
    public DataAccessor(){
        connection = null;
        errorcode="";
        mainform = Context.getInstance().getMainformController();
        jOpenCageGeocoder = new JOpenCageGeocoder("8800b41d55de4774bea5723fd22aa2b1");
        hereappkey_id = "E887pM085rdrBuQgmyUD";
        hereappkey_code = "mUJzRgDsyjWIu1QJIie9Mw";
    }
    public String getHereappkey_ID(){
        return hereappkey_id;
    }
    public void setHereappkey_ID(String s) {
        hereappkey_id = s;
    }
    public String getHereappkey_Code(){
        return hereappkey_code;
    }
    public void setHereappkey_Code(String s) {
        hereappkey_code = s;
    }
    public boolean isConnected(){
        return connection!=null;
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
            Statement stmnt;
            stmnt = connection.createStatement();
            String query = "Select * from Product";
            ResultSet rs;
            rs = stmnt.executeQuery(query);
            while (rs.next()) {
                Product tempproduct = new Product();
                tempproduct.setId(rs.getInt("id"));
                tempproduct.setTitle(rs.getString("title"));
                tempproducts.add(tempproduct);
            }
            rs.close();
            stmnt.close();   
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }
        return tempproducts;
    }
    public Product findProduct(String productTitle){
        Product tempproduct = null;
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            String query="Select * from Product where title=\""+productTitle+"\"";
            ResultSet rs;
            rs = stmnt.executeQuery(query);
            while (rs.next()) {
                tempproduct = new Product();
                tempproduct.setId(rs.getInt("id"));
                tempproduct.setTitle(rs.getString("title"));
            }
            rs.close();
            stmnt.close();
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }
        return tempproduct;
    }
    public void addProduct(Product producttoadd) {
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            String query = "INSERT INTO Product SET title='"+producttoadd.getTitle()+"'";
            stmnt.execute(query);
            stmnt.close();
            mainform.StatusTextRight.setText("Продукт добавлен в список.");
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }
        
    }
    public void updateProduct(Product producttoupdate){
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            String query = "UPDATE `Product` SET `title` = '"+
                    producttoupdate.getTitle()+"' WHERE `Product`.`id` = "+producttoupdate.getId()+"";
            stmnt.execute(query);
            stmnt.close();
            mainform.StatusTextRight.setText("Продукт отредактирован.");
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }  
    }
    public void deleteProduct(Product producttodelete) {
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            String query1="DELETE FROM `Product` WHERE `Product`.`id` = "+producttodelete.getId();
            stmnt.execute(query1);
            String query2="DELETE FROM `ProductToProvider` WHERE `id_product` = "+producttodelete.getId();
            stmnt.execute(query2);
            stmnt.close();
            mainform.StatusTextRight.setText("Продукт удален из списка.");
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }
    
    }
    private Address getCoordinates(Address adr){
        String requestQuery = adr.getIndex()+","+adr.getRegion()+","+adr.getCity();
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(requestQuery);
        request.setRestrictToCountryCode("ru"); // restrict results to a specific country
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition();
        adr.setLatitude(firstResultLatLng.getLat().toString());
        adr.setLongitude(firstResultLatLng.getLng().toString());
        return adr;
    }
    public Provider findProvider(String ProviderTitle) {
        Provider tempprovider = null;
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            String query = "Select * From Provider where title=\""+ProviderTitle+"\"";
            ResultSet rs;
            rs = stmnt.executeQuery(query);
            while (rs.next()) {
                tempprovider = new Provider();
                tempprovider.setTitle(rs.getString("title"));
                tempprovider.setId(rs.getInt("id"));
                tempprovider.setInn(rs.getString("inn"));
                tempprovider.setKpp(rs.getString("kpp"));
                tempprovider.setOgrn(rs.getString("ogrn"));
                tempprovider.setOkpo(rs.getString("okpo"));
                tempprovider.setOktmo(rs.getString("oktmo"));
            }
            rs.close();
            stmnt.close();
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }
        return tempprovider;
    }
    public void addProvider(Provider providertoadd) {
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            // добавляем поставщика
            String query = "INSERT INTO Provider SET title=\""+providertoadd.getTitle()+"\","
                            +" ogrn='"+providertoadd.getOgrn()+"',"
                            +" inn='"+providertoadd.getInn()+"',"
                            +" kpp='"+providertoadd.getKpp()+"',"
                            +" okpo='"+providertoadd.getOkpo()+"',"
                            +" oktmo='"+providertoadd.getOktmo()+"'";
            stmnt.execute(query);
            Provider foundProvider = findProvider(providertoadd.getTitle());
            if (foundProvider!=null) {
                providertoadd.setId(foundProvider.getId());
                //добавляем адреса поставщика
                for (Address adr:providertoadd.addresses) {
                    adr = getCoordinates(adr);
                    query="Insert Into Address Set mail_index='"+adr.getIndex()+"',"
                            + " region=\""+adr.getRegion()+"\","
                            + " city=\""+adr.getCity()+"\","
                            + " comment=\""+adr.getComment()+"\","
                            + " latitude='"+adr.getLatitude()+"',"
                            + " longitude='"+adr.getLongitude()+"',"
                            + " id_organisation='-1',"
                            + " id_provider='"+providertoadd.getId()+"'";
                    stmnt.execute(query);
                }
                // Добавляем продукты поставщика
                for (ProductToProvider ptp:providertoadd.products) {
                    if (ptp.getIsprovide().equals("да")){
                        query="INSERT INTO ProductToProvider SET id_provider='"+providertoadd.getId()+"', "
                                + "id_product='"+ptp.getProduct().getId()+"'";
                        stmnt.execute(query);
                    }
                }
                // Добавляем критерии поставщика
                for (CriteriaToProvider ctp:providertoadd.criteries) {
                    String basequery="INSERT INTO CriteriaToProvider SET id_provider='"+providertoadd.getId()+"',"
                            + " id_criteria='"+ctp.getCriteria().getId()+"', ";
                    if (ctp.getValue().equals("да")||ctp.getValue().equals("нет")) {
                        if (ctp.getValue().equals("да")) {
                            query=basequery+"value='10'";
                            stmnt.execute(query);
                        }
                        else {
                            query=basequery+"value='0'";
                            stmnt.execute(query);
                        }
                    }
                    else {
                        query=basequery+"value='"+ctp.getValue()+"'";
                        stmnt.execute(query);
                    }
                }
            }
            stmnt.close();
            mainform.StatusTextRight.setText("Поставщик добавлен.");
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }
    }
    public void deleteProvider(Provider providertodelete) {
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            String query="Delete from Provider where id='"+providertodelete.getId()+"'";
            stmnt.execute(query);
            query="Delete from ProductToProvider where id_provider='"+providertodelete.getId()+"'";
            stmnt.execute(query);
            query="Delete from CriteriaToProvider where id_provider='"+providertodelete.getId()+"'";
            stmnt.execute(query);
            query="Delete from Address where id_provider='"+providertodelete.getId()+"'";
            stmnt.execute(query);
            stmnt.close();
            mainform.StatusTextRight.setText("Поставщик удален.");
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }
    }
    public void updateProvider(Provider providertoupdate) {
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            // Сначала обновляем основную информацию о поставщике
            String query = "Update Provider set title=\""+providertoupdate.getTitle()+"\","
                    + " ogrn='"+providertoupdate.getOgrn()+"',"
                    + " inn='"+providertoupdate.getInn()+"',"
                    + " kpp='"+providertoupdate.getKpp()+"',"
                    + " okpo='"+providertoupdate.getOkpo()+"',"
                    + " oktmo='"+providertoupdate.getOktmo()+"'"
                    + " where id='"+providertoupdate.getId()+"'";
            stmnt.execute(query);
            // Апдейтим адреса
            // Удалим те строки, которых нет в списке адресов организации.
            //Delete from Address where id_provider=1 and not id in (1,2,3,4,5)
            // Формируем список id из списка. Т.к новые - либо 0 либо отрицательные, то повлияет только
            // на те, которых в списке нет.
            String idList="";
            for (Address adr:providertoupdate.addresses) {
                idList+=String.valueOf(adr.getId())+",";
            }
            if (!idList.equals("")) {
                idList = idList.substring(0, idList.length()-1);
                query="Delete from Address where id_provider="+providertoupdate.getId()+
                        " and not id in ("+idList+")";
                stmnt.execute(query);
            }
            // Если список idList пустой, т.е. поставщик не имеет адресов, 
            // то удаляем адреса поставщика из базы если есть
            else {
                query="Delete from Address where id_provider='"+providertoupdate.getId()+"'";
                stmnt.execute(query);
            }
            // Апдейтим существующие и добавляем новые адреса
            // Если список адресов всетаки не пустой
            for (Address adr:providertoupdate.addresses) {
                if (adr.getId()>0) {
                    adr = getCoordinates(adr);
                    query="Update Address set mail_index='"+adr.getIndex()+"',"
                            + " region=\""+adr.getRegion()+"\","
                            + " city=\""+adr.getCity()+"\","
                            + " comment=\""+adr.getComment()+"\","
                            + " latitude='"+adr.getLatitude()+"',"
                            + " longitude='"+adr.getLongitude()+"'"
                            + " where id=\""+adr.getId()+"\"";
                    stmnt.execute(query);
                }
                else {
                    adr = getCoordinates(adr);
                    query="Insert into Address set mail_index='"+adr.getIndex()+"',"
                            + " region=\""+adr.getRegion()+"\","
                            + " city=\""+adr.getCity()+"\","
                            + " comment=\""+adr.getComment()+"\","
                            + " latitude='"+adr.getLatitude()+"',"
                            + " longitude='"+adr.getLongitude()+"',"
                            + " id_organisation='-1',"
                            + " id_provider='"+providertoupdate.getId()+"'";
                    stmnt.execute(query);
                }
            }
            // Апдейтим значения критериев
            for (CriteriaToProvider ctp:providertoupdate.criteries) {
                if(ctp.getValue().equals("да")||ctp.getValue().equals("нет")) {
                    if(ctp.getValue().equals("да")){
                        query="Update CriteriaToProvider set value ='10'"
                                + " where id_criteria='"+ctp.getCriteria().getId()+"' and"
                                + " id_provider='"+providertoupdate.getId()+"'";
                        stmnt.execute(query);
                    }
                    // if .equals("нет")
                    else {
                        query="Update CriteriaToProvider set value ='0'"
                                + " where id_criteria='"+ctp.getCriteria().getId()+"' and"
                                + " id_provider='"+providertoupdate.getId()+"'";
                        stmnt.execute(query);
                    }
                }
                else {
                    query="Update CriteriaToProvider set value ='"+ctp.getValue()+"'"
                                + " where id_criteria='"+ctp.getCriteria().getId()+"' and"
                                + " id_provider='"+providertoupdate.getId()+"'";
                    stmnt.execute(query);
                }
            }
            // Апдейтим значения продуктов поставки
            for (ProductToProvider ptp:providertoupdate.products) {
                if (ptp.getIsprovide().equals("да")) {
                    // Убедимся, что такая строка есть в БД
                    query="Select * from ProductToProvider where id_provider='"+providertoupdate.getId()+"'"
                            + " and id_product='"+ptp.getProduct().getId()+"'";
                    ResultSet rs;
                    rs = stmnt.executeQuery(query);
                    // Если ее нет, то вставляем строку
                    if (!rs.next()) {
                        query="Insert into ProductToProvider set id_provider='"+providertoupdate.getId()+"',"
                                + " id_product='"+ptp.getProduct().getId()+"'";
                        stmnt.execute(query);
                    }
                    rs.close();
                }
                // Если не поставляет, удаляем данную пару.
                else {
                    query="Delete from ProductToProvider where id_provider='"+providertoupdate.getId()+"'"
                            + " and id_product='"+ptp.getProduct().getId()+"'";
                    stmnt.execute(query);
                }
            }
            stmnt.close();
            mainform.StatusTextRight.setText("Изменения сохранены");
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }
    }
    public ObservableList<Provider> getAllProviders() {
        ObservableList<Provider> tempproviders = FXCollections.observableArrayList();
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            String query="SELECT * FROM Provider";
            ResultSet rs;
            rs = stmnt.executeQuery(query);
            while (rs.next()) {
                Provider tempprovider = new Provider();
                tempprovider.setId(rs.getInt("id"));
                tempprovider.setInn(rs.getString("inn"));
                tempprovider.setKpp(rs.getString("kpp"));
                tempprovider.setOgrn(rs.getString("ogrn"));
                tempprovider.setOkpo(rs.getString("okpo"));
                tempprovider.setOktmo(rs.getString("oktmo"));
                tempprovider.setTitle(rs.getString("title"));
                // Выбираем адреса поставщика
                Statement substmnt;
                substmnt = connection.createStatement();
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
                                    "FROM Product p\n" +
                                    "LEFT JOIN ProductToProvider ptp "+
                                    "ON p.id = ptp.id_product and ptp.id_provider="+tempprovider.getId()
                                    +" ORDER BY p.id";
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
            mainform.showMessage(ex.toString());
        }
        return tempproviders;
    }
    public ObservableList<Provider> getAllProviders(Product product) {
        ObservableList<Provider> tempproviders = FXCollections.observableArrayList();
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            String query="SELECT p.* FROM Provider p, ProductToProvider ptp where "
                    + "p.id=ptp.id_provider and ptp.id_product='"+product.getId()+"'";
            ResultSet rs;
            rs = stmnt.executeQuery(query);
            while (rs.next()) {
                Provider tempprovider = new Provider();
                tempprovider.setId(rs.getInt("id"));
                tempprovider.setInn(rs.getString("inn"));
                tempprovider.setKpp(rs.getString("kpp"));
                tempprovider.setOgrn(rs.getString("ogrn"));
                tempprovider.setOkpo(rs.getString("okpo"));
                tempprovider.setOktmo(rs.getString("oktmo"));
                tempprovider.setTitle(rs.getString("title"));
                // Выбираем адреса поставщика
                Statement substmnt;
                substmnt = connection.createStatement();
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
                                    "FROM Product p\n" +
                                    "LEFT JOIN ProductToProvider ptp "+
                                    "ON p.id = ptp.id_product and ptp.id_provider="+tempprovider.getId()
                                    +" ORDER BY p.id";
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
            mainform.showMessage(ex.toString());
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
            mainform.showMessage(ex.toString());
        }
        return temporganisation;
    }
    public void updateOrganisation(Organisation organisation) {
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            // Апдейтим организацию
            String query="Update Organisation set title=\""+organisation.getTitle()+"\" "
                        + "where id='"+organisation.getId()+"'";
            stmnt.execute(query);
            // Апдейтим адреса
            // Удалим те строки, которых нет в списке адресов организации.
            //Delete from Address where id_organisation=1 and not id in (1,2,3,4,12)
            // Формируем список id из списка. Т.к новые - либо 0 либо отрицательные, то повлияет только
            // на те, которых в списке нет.
            String idList="";
            for (Address adr:organisation.addresses) {
                idList+=String.valueOf(adr.getId())+",";
            }
            if (!idList.equals("")) {
                idList = idList.substring(0, idList.length()-1);
                query="Delete from Address where id_organisation="+organisation.getId()+
                        " and not id in ("+idList+")";
                stmnt.execute(query);
            }
            // Апдейтим существующие и добавляем новые адреса
            for (Address adr:organisation.addresses) {
                if (adr.getId()>0) {
                    adr = getCoordinates(adr);
                    query="Update Address set mail_index='"+adr.getIndex()+"',"
                            + " region=\""+adr.getRegion()+"\","
                            + " city=\""+adr.getCity()+"\","
                            + " comment=\""+adr.getComment()+"\","
                            + " latitude='"+adr.getLatitude()+"',"
                            + " longitude='"+adr.getLongitude()+"'"
                            + " where id=\""+adr.getId()+"\"";
                    stmnt.execute(query);
                }
                else {
                    adr = getCoordinates(adr);
                    query="Insert into Address set mail_index='"+adr.getIndex()+"',"
                            + " region=\""+adr.getRegion()+"\","
                            + " city=\""+adr.getCity()+"\","
                            + " comment=\""+adr.getComment()+"\","
                            + " latitude='"+adr.getLatitude()+"',"
                            + " longitude='"+adr.getLongitude()+"',"
                            + " id_organisation='"+organisation.getId()+"',"
                            + " id_provider='-1'";
                    stmnt.execute(query);
                }
            }
            stmnt.close();
            mainform.StatusTextRight.setText("Изменения сохранены.");
        } catch (SQLException ex) {
            mainform.showMessage(ex.toString());
        }     
    }
    public ObservableList<Criteria> getAllCriteries() {
        ObservableList<Criteria> tempcriteries = FXCollections.observableArrayList();
        try {
            Statement stmnt;
            stmnt = connection.createStatement();
            String query="Select * from Criteria";
            ResultSet rs;
            rs = stmnt.executeQuery(query);
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
            mainform.showMessage(ex.toString());
        }
        return tempcriteries;
    }
    
    public ObservableList<Way> correctRoutes(ObservableList<Way> uncorrectedways) {
        ObservableList<Way> correctedways = FXCollections.observableArrayList();
        for (Way way:uncorrectedways) {
            way.setCorrectedtime(way.getBasetime());
            correctedways.add(way);
        }
        return correctedways;
    }
    public ObservableList<Way> getRoutes(String request) {
        ObservableList<Way> ways = FXCollections.observableArrayList();
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet (request);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    InputStream is = httpEntity.getContent();
            JsonReader br = new JsonReader(new InputStreamReader(is, "UTF-8"));
            JsonRoutePOJO resp = new Gson().fromJson(br, JsonRoutePOJO.class);
            ArrayList<Route> gottenroutes = resp.getJsonroot().getRoute();
            for (Route route: gottenroutes) {
                Way way = new Way();
                // Путь 
                ObservableList<String> shape = FXCollections.observableArrayList(route.getShape());
                way.setShape(shape);
                double basetime = route.getSummary().getBaseTime()/3600;
                way.setBasetime(basetime);
                double length = route.getSummary().getDistance()/1000;
                way.setLength(length);
                way.setAvgspeed(way.getLength()/way.getBasetime());
                
                ways.add(way);
            }
        } catch (MalformedURLException ex) {
            mainform.showMessage(ex.toString());
        } catch (IOException ex) {
            mainform.showMessage(ex.toString());
        }
        return ways;
    }
}
