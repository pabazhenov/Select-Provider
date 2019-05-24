/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.*;

/**
 * FXML Controller class
 *
 * @author pikachu
 */
public class MainsceneController implements Initializable {
    
    // БЛОК ПЕРЕМЕННЫХ
    
    //SidePane
    @FXML
    private Button ChooseProviderBtn;
    @FXML
    private Button OrganisationBtn;
    @FXML
    private Button ProvidersBtn;
    @FXML
    private Button ProductsBtn;
    @FXML
    private Button SavedSamplesBtn;
    @FXML
    private Button PropertiesBtn;
    @FXML
    private Button ConnectionBtn;
    @FXML
    private Label StatusTextLeft;
    @FXML
    public Label StatusTextRight;
    
    //ConnectionPane
    @FXML
    private AnchorPane ConnectionPane;
    @FXML
    private TextField ConnectionUsernameEdit;
    @FXML
    private Button DisconnectBtn;
    @FXML
    private Button ConnectBtn;
    @FXML
    private PasswordField ConnectionPassEdit;
    @FXML
    private TextField ConnectionDatabaseEdit;
    @FXML
    private CheckBox ConnectionSaveArgumentsEdit;
    
    //ProvidersPane
    @FXML
    private AnchorPane ProvidersPane;
    @FXML
    private TableView<Provider> ProvidersTable;
    @FXML
    private Button ProvidersAddBtn;
    @FXML
    private Button ProvidersEditBtn;
    @FXML
    private Button ProvidersDeleteBtn;
    @FXML
    private TableColumn<Provider, String> ProvidersNameColumn;
    @FXML
    private TableColumn<Provider, String> ProvidersINNColumn;
    @FXML
    private TableColumn<Provider, String> ProvidersOGRNColumn;
    @FXML
    private TableColumn<Provider, String> ProvidersKPPColumn;
    @FXML
    private TableColumn<Provider, String> ProvidersOKPOColumn;
    @FXML
    private TableColumn<Provider, String> ProvidersOKTMOColumn;
    
    //EditProviderPane
    @FXML
    private AnchorPane EditProviderPane;
    @FXML
    private Label EditProviderLabel;
    @FXML
    private TableView<Address> EditProviderAddressTable;
    @FXML
    private TableColumn<Address, String> EditProviderIndexColumn;
    @FXML
    private TableColumn<Address, String> EditProviderRegionColumn;
    @FXML
    private TableColumn<Address, String> EditProviderCityColumn;
    @FXML
    private TableColumn<Address, String> EditProviderCommentColumn;
    @FXML
    private TableView<CriteriaToProvider> EditProviderCriteriaTable;
    @FXML
    private TableColumn<CriteriaToProvider, String> EditProviderCriteriaNameColumn;
    @FXML
    private TableColumn<CriteriaToProvider, String> EditProviderCriteriaValueColumn;
    @FXML
    private TextField EditProviderNameEdit;
    @FXML
    private TextField EditProviderINNEdit;
    @FXML
    private TextField EditProviderOGRNEdit;
    @FXML
    private TextField EditProviderKPPEdit;
    @FXML
    private TextField EditProviderOKPOEdit;
    @FXML
    private TextField EditProviderOKTMOEdit;
    @FXML
    private TextField EditProviderIndexEdit;
    @FXML
    private TextField EditProviderRegionEdit;
    @FXML
    private TextField EditProviderCityEdit;
    @FXML
    private TextField EditProviderCommentEdit;
    @FXML
    private Button EditProviderDeleteAddressBtn;
    @FXML
    private Button EditProviderAddAddressBtn;
    @FXML
    private Button EditProviderSaveAddressBtn;
    @FXML
    private TableView<ProductToProvider> EditProviderProductTable;
    @FXML
    private TableColumn<ProductToProvider, String> EditProviderProductNameColumn;
    @FXML
    private TableColumn<ProductToProvider, String> EditProviderProductProvidesColumn;
    @FXML
    private TextField EditProviderProductNameEdit;
    @FXML
    private ComboBox<String> EditProviderProductIsProvideEdit;
    @FXML
    private Button EditProviderSaveProductsBtn;
    @FXML
    private Button EditProviderSaveCriteriaBtn;
    @FXML
    private ComboBox<String> EditProviderCriteriaValueEdit;
    @FXML
    private TextField EditProviderCriteriaNameEdit;
    
    //DeleteProviderPane
    @FXML
    private AnchorPane DeleteProviderPane;
    @FXML
    private Label DeleteProviderNameEdit;
    @FXML
    private Label DeleteProviderINNEdit;
    @FXML
    private Label DeleteProviderOGRNEdit;
    @FXML
    private Label DeleteProviderKPPEdit;
    @FXML
    private Label DeleteProviderOKPOEdit;
    @FXML
    private Label DeleteProviderOKTMOEdit;
    @FXML
    private TableView<Address> DeleteProviderAddressTable;
    @FXML
    private TableColumn<Address, String> DeleteProviderIndexColumn;
    @FXML
    private TableColumn<Address, String> DeleteProviderRegionColumn;
    @FXML
    private TableColumn<Address, String> DeleteProviderCityColumn;
    @FXML
    private TableColumn<Address, String> DeleteProviderCommentColumn;
    @FXML
    private TableView<CriteriaToProvider> DeleteProviderCriteriaTable;
    @FXML
    private TableColumn<CriteriaToProvider, String> DeleteProviderCriteriaNameColumn;
    @FXML
    private TableColumn<CriteriaToProvider, String> DeleteProviderCriteriaValueColumn;
    @FXML
    private TextField DeleteProviderProductsEdit;
    
    //PropertiesPane
    @FXML
    private AnchorPane PropertiesPane;
    @FXML
    private TableView<?> PropertiesCriteriaTable;
    @FXML
    private TableColumn<?, ?> PropertiesCriteriaNameColumn;
    @FXML
    private TableColumn<?, ?> PropertiesCriteriaImportanceColumn;
    @FXML
    private TextField PropertiesCriteriaNameEdit;
    @FXML
    private TableView<?> PropertiesAdjustmentTable;
    @FXML
    private TableColumn<?, ?> PropertiesAdjustmentValueColumn;
    @FXML
    private TableColumn<?, ?> PropertiesAdjustmentSpeedColumn;
    @FXML
    private Button PropertiesSaveAdjustmentBtn;
    @FXML
    private ComboBox<?> PropertiesAdjustmentSpeedEdit;
    @FXML
    private TextField PropertiesAdjustmentDangerLevelEdit;
    @FXML
    private TableView<?> PropertiesWeatherDangerTable;
    @FXML
    private TableColumn<?, ?> PropertiesWeatherDangerNameColumn;
    @FXML
    private TableColumn<?, ?> PropertiesWeatherDangerLevelColumn;
    @FXML
    private Button PropertiesSaveCriteriaBtn;
    @FXML
    private TextField PropertiesCriteriaImportanceEdit;
    @FXML
    private TextField OpenCageGeocodeAPIKeyEdit;
    @FXML
    private TextField HERERouteAPIKeyEdit;
    @FXML
    private TextField HEREWeatherAPIKeyEdit;
    
    //MessagePane
    @FXML
    private AnchorPane MessagePane;
    @FXML
    private TextArea MessageEdit;
    
    //Choose Provider Phase1
    @FXML
    private AnchorPane ChoosePhase1Pane;
    @FXML
    private TableView<Address> Phase1OrganisationAddressTable;
    @FXML
    private TableColumn<Address, String> Phase1OrganisationIndexColumn;
    @FXML
    private TableColumn<Address, String> Phase1OrganisationRegionColumn;
    @FXML
    private TableColumn<Address, String> Phase1OrganisationCityColumn;
    @FXML
    private TableColumn<Address, String> Phase1OrganisationCommentColumn;
    @FXML
    private Button Phase1GetForwardBtn;
    

    //Choose Provider Phase2
    @FXML
    private AnchorPane ChoosePhase2Pane;
    @FXML
    private TableView<Product> Phase2ProductTable;
    @FXML
    private TableColumn<Product, String> Phase2ProductNameColumn;
    @FXML
    private Button Phase2GetForwardBtn;
    
    //Choose Provider Phase3
    @FXML
    private AnchorPane ChoosePhase3Pane;
    @FXML
    private TableView<Provider> Phase3ProviderTable;
    @FXML
    private TableColumn<Provider, String> Phase3ProviderNameColumn;
    @FXML
    private TableColumn<Provider, Integer> Phase3ProviderRatingColumn;
    @FXML
    private TextField Phase3MinProviderRatingEdit;
    @FXML
    private TextField Phase3MaxTimeEdit;
    @FXML
    private ComboBox<Integer> Phase3ImportanceWayEdit;
    @FXML
    private ComboBox<Integer> Phase3ImportanceRatingEdit;
    
    //Choose Provider Phase4
    @FXML
    private AnchorPane ChoosePhase4Pane;
    @FXML
    private TextArea Phase4LogEdit;
    @FXML
    private TableView<Way> Phase4OtherWayTable;
    @FXML
    private TableColumn<Way, String> Phase4OtherProviderColumn;
    @FXML
    private TableColumn<Way, String> Phase4OtherAddressColumn;
    @FXML
    private TableColumn<Way, Double> Phase4OtherWayLengthColumn;
    @FXML
    private TableColumn<Way, Double> Phase4OtherWayTime;
    @FXML
    private TableColumn<Way, Double> Phase4OtherWayRatingColumn;
    @FXML
    private TableView<Way> Phase4BestWayTable;
    @FXML
    private TableColumn<Way, String> Phase4BestProviderColumn;
    @FXML
    private TableColumn<Way, String> Phase4BestAddressColumn;
    @FXML
    private TableColumn<Way, Double> Phase4BestWayLengthColumn;
    @FXML
    private TableColumn<Way, Double> Phase4BestWayTime;
    @FXML
    private TableColumn<Way, Double> Phase4BestWayRatingColumn;
    @FXML
    private Button Phase4GetForwardBtn;
    @FXML
    private Button Phase4SaveToSamplesBtn;
    
    //Choose Provider Phase5
    @FXML
    private AnchorPane ChoosePhase5Pane;
    @FXML
    private TextField Phase5ProviderEdit;
    @FXML
    private TextField Phase5ProductEdit;
    @FXML
    private TextField Phase5StartEdit;
    @FXML
    private TextField Phase5AVGTimeEdit;
    @FXML
    private TextField Phase5LengthEdit;
    @FXML
    private TextField Phase5EndEdit;
    @FXML
    private TextField Phase5RatingProviderEdit;
    @FXML
    private TextField Phase5RatingCommonEdit;
    @FXML
    private TextField Phase5RatingWayEdit;
    @FXML
    private TextField Phase5AverageDangerEdit;
    
    //Products Pane
    @FXML
    private AnchorPane ProductsPane;
    @FXML
    private TableColumn<Product, String> ProductNameColumn;
    @FXML
    private TableView<Product> ProductTable;
    @FXML
    private TextField ProductNameEdit;
    @FXML
    private Button ProductAddBtn;
    @FXML
    private Button ProductSaveBtn;
    @FXML
    private Button ProductDeleteBtn;
    
    //Organization Pane
    @FXML
    private AnchorPane OrganisationPane;
    @FXML
    private TextField OrganisationTitleEdit;
    @FXML
    private Button OrganisationSaveAddressBtn;
    @FXML
    private TableView<Address> OrganisationAddressTable;
    @FXML
    private TableColumn<Address, String> OrganisationIndexColumn;
    @FXML
    private TableColumn<Address, String> OrganisationRegionColumn;
    @FXML
    private TableColumn<Address, String> OrganisationCityColumn;
    @FXML
    private TableColumn<Address, String> OrganisationCommentColumn;
    @FXML
    private TextField OrganisationIndexEdit;
    @FXML
    private TextField OrganisationRegionEdit;
    @FXML
    private TextField OrganisationCityEdit;
    @FXML
    private TextField OrganisationCommentEdit;
    @FXML
    private Button OrganisationDeleteAddressBtn;
    @FXML
    private Button OrganisationAddAddressBtn;
    
    //SavedSamplesPane
    @FXML
    private AnchorPane SavedSamplesPane;

    // Кастомные переменные
    public DataAccessor dataaccessor;
    Provider providertoedit;
    Organisation organisation;
    // Предназначен для временных адресов организации или поставщика
    // чтобы при Update address не менялись все только что созданные (по умолчанию id=0)
    // методом уменьшения значения (т.к. в базе только положительные и чтобы их исключить)
    // унифицируем каждый добавленный адрес. При Save Changes значение обнуляется.
    int iteratorId;
    // Сделано на случай, если будут появлятся несколько экзепшенов. Для удобства.
    int emessageid;
    //----------------------------------
    // Переменные выбора поставщиков
    Product selectedproduct;
    ObservableList<Provider> providerwithproduct, providersortedbyrating;
    Address organisationaddress;
    int maxtime;
    Way selectedway;
    int importanceway, importancerating;
    

    

    
    
    
    // БЛОК ФУНКЦИЙ 
    //Общие функции (применимы ко всем Panes)
    public void showMessage(String msg) {
        MessagePane.setVisible(true);
        emessageid++;
        MessageEdit.appendText("\n"+emessageid+": "+msg);
    }
    @FXML
    private void CloseMessageBox(ActionEvent event) {
        MessageEdit.setText(null);
        emessageid=0;
        MessagePane.setVisible(false);
    }


    // Показывает выбранный Pane и дизейблит выбранную кнопку
    public void showPane(AnchorPane panetoshow, Button btntoshow) {
        // Скрываем все формы
        ProvidersPane.setVisible(false);
        ConnectionPane.setVisible(false);
        DeleteProviderPane.setVisible(false);
        EditProviderPane.setVisible(false);
        ChoosePhase1Pane.setVisible(false);
        ChoosePhase2Pane.setVisible(false);
        ChoosePhase3Pane.setVisible(false);
        ChoosePhase4Pane.setVisible(false);
        ChoosePhase5Pane.setVisible(false);
        OrganisationPane.setVisible(false);
        ProductsPane.setVisible(false);
        SavedSamplesPane.setVisible(false);
        PropertiesPane.setVisible(false);
        // Андизейблим все кнопки
        ChooseProviderBtn.setDisable(false);
        OrganisationBtn.setDisable(false);
        ProvidersBtn.setDisable(false);
        ProductsBtn.setDisable(false);
        SavedSamplesBtn.setDisable(false);
        PropertiesBtn.setDisable(false);
        ConnectionBtn.setDisable(false);
        // Выведем нужную форму и заблокируем нужную форму
        panetoshow.setVisible(true);
        btntoshow.setDisable(true);
    }
    // Дизейблит все кнопки и показывает Connection Pane
    public void showStartPane() {
        // Скрываем все формы, кроме ConnectionPane
        ProvidersPane.setVisible(false);
        ConnectionPane.setVisible(true);
        DeleteProviderPane.setVisible(false);
        EditProviderPane.setVisible(false);
        ChoosePhase1Pane.setVisible(false);
        ChoosePhase2Pane.setVisible(false);
        ChoosePhase3Pane.setVisible(false);
        ChoosePhase4Pane.setVisible(false);
        ChoosePhase5Pane.setVisible(false);
        OrganisationPane.setVisible(false);
        ProductsPane.setVisible(false);
        SavedSamplesPane.setVisible(false);
        PropertiesPane.setVisible(false);
        // дизейблим все кнопки
        ChooseProviderBtn.setDisable(true);
        OrganisationBtn.setDisable(true);
        ProvidersBtn.setDisable(true);
        ProductsBtn.setDisable(true);
        SavedSamplesBtn.setDisable(true);
        PropertiesBtn.setDisable(true);
        ConnectionBtn.setDisable(true);
        // настраиваем функционал в Connection Pane для подключения
        ConnectBtn.setDisable(false);
        DisconnectBtn.setDisable(true);
        ConnectionUsernameEdit.setDisable(false);
        ConnectionDatabaseEdit.setDisable(false);
        ConnectionPassEdit.setDisable(false);
    }
    public void clearSelection(String PaneTitle) {
        switch(PaneTitle) {
            case "OrganisationPane":
                OrganisationAddressTable.getSelectionModel().clearSelection();
                OrganisationAddAddressBtn.setDisable(false);
                OrganisationSaveAddressBtn.setDisable(true);
                OrganisationDeleteAddressBtn.setDisable(true);
                OrganisationIndexEdit.setText("");
                OrganisationCityEdit.setText("");
                OrganisationRegionEdit.setText("");
                OrganisationCommentEdit.setText("");
                break;
            case "ProductsPane":
                ProductTable.getSelectionModel().clearSelection();
                ProductAddBtn.setDisable(false);
                ProductSaveBtn.setDisable(true);
                ProductDeleteBtn.setDisable(true);
                ProductNameEdit.setText("");
                break;
            case "ProvidersPane":
                ProvidersTable.getSelectionModel().clearSelection();
                ProvidersAddBtn.setDisable(false);
                ProvidersEditBtn.setDisable(true);
                ProvidersDeleteBtn.setDisable(true);
                providertoedit=null;
                break;
            case "EditProviderPane":
                EditProviderAddressTable.getSelectionModel().clearSelection();
                EditProviderProductTable.getSelectionModel().clearSelection();
                EditProviderCriteriaTable.getSelectionModel().clearSelection();
                // Сбрасываем селект таблицы адресов
                EditProviderCommentEdit.setText("");
                EditProviderRegionEdit.setText("");
                EditProviderCityEdit.setText("");
                EditProviderIndexEdit.setText("");
                EditProviderAddAddressBtn.setDisable(false);
                EditProviderSaveAddressBtn.setDisable(true);
                EditProviderDeleteAddressBtn.setDisable(true);
                // сбрасываем селект таблицы продуктов
                EditProviderProductNameEdit.setText("");
                EditProviderProductIsProvideEdit.setValue("");
                EditProviderProductIsProvideEdit.getItems().setAll();
                EditProviderSaveProductsBtn.setDisable(true);
                //Сбрасываем селект таблицы критериев
                EditProviderCriteriaNameEdit.setText("");
                EditProviderCriteriaValueEdit.setValue("");
                EditProviderCriteriaValueEdit.getItems().setAll();                
                break;
            case "ChoosePhase1Pane":
                Phase1OrganisationAddressTable.getSelectionModel().clearSelection();
                Phase1GetForwardBtn.setDisable(true);
                organisationaddress=null;
                break;
            case "ChoosePhase2Pane":
                Phase2ProductTable.getSelectionModel().clearSelection();
                Phase2GetForwardBtn.setDisable(true);
                break;
            case "ChoosePhase4Pane":
                Phase4BestWayTable.getSelectionModel().clearSelection();
                Phase4OtherWayTable.getSelectionModel().clearSelection();
                Phase4GetForwardBtn.setDisable(true);
                break;
        }
    }
    private void Phase4Log(String s) {
        Phase4LogEdit.appendText(s+"\n");
    }
    
    private void Phase4ClearLog() {
        Phase4LogEdit.clear();
    }
    /**
     * Функция инициализации
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // register Controller in Context class
        Context.getInstance().setMainformController(this);
        // Отображаем форму и вызываем конструктор соединения и кастомных переменных
        dataaccessor = new DataAccessor();
        //Индикатор error message
        emessageid=0;
        // ПАНО РЕДАКТИРОВАНИЯ\УДАЛЕНИЯ\ДОБАВЛЕНИЯ поставщика
                providertoedit = null;
        // ПАНО ОРГАНИЗАЦИИ
                organisation = null;
                
        providerwithproduct = FXCollections.observableArrayList();
        providersortedbyrating = FXCollections.observableArrayList();
        maxtime=8760;
        
        importanceway = 5; importancerating = 5;
        
        
        selectedproduct=null;
        organisationaddress=null;
        
        showStartPane();
        
        // Подготавливаем таблицы к выгрузке данных
        // Страница продукты
        ProductNameColumn.setCellValueFactory(new PropertyValueFactory("title"));
        //Страница поставщики
        ProvidersNameColumn.setCellValueFactory(new PropertyValueFactory("title"));
        ProvidersNameColumn.setCellFactory(TooltippedTableCell.forTableColumn());
        ProvidersINNColumn.setCellValueFactory(new PropertyValueFactory("inn"));
        ProvidersINNColumn.setCellFactory(TooltippedTableCell.forTableColumn());
        ProvidersOGRNColumn.setCellValueFactory(new PropertyValueFactory("ogrn"));
        ProvidersOGRNColumn.setCellFactory(TooltippedTableCell.forTableColumn());
        ProvidersKPPColumn.setCellValueFactory(new PropertyValueFactory("kpp"));
        ProvidersKPPColumn.setCellFactory(TooltippedTableCell.forTableColumn());
        ProvidersOKPOColumn.setCellValueFactory(new PropertyValueFactory("okpo"));
        ProvidersOKPOColumn.setCellFactory(TooltippedTableCell.forTableColumn());
        ProvidersOKTMOColumn.setCellValueFactory(new PropertyValueFactory("oktmo"));
        ProvidersOKTMOColumn.setCellFactory(TooltippedTableCell.forTableColumn());
        //Страница редактирования поставщика
        EditProviderIndexColumn.setCellValueFactory(new PropertyValueFactory("index"));
        EditProviderRegionColumn.setCellValueFactory(new PropertyValueFactory("region"));
        EditProviderCityColumn.setCellValueFactory(new PropertyValueFactory("city"));
        EditProviderCommentColumn.setCellValueFactory(new PropertyValueFactory("comment"));
        EditProviderCriteriaNameColumn.setCellValueFactory(new PropertyValueFactory("criteria"));
        EditProviderCriteriaValueColumn.setCellValueFactory(new PropertyValueFactory("value"));
        EditProviderProductNameColumn.setCellValueFactory(new PropertyValueFactory("product"));
        EditProviderProductProvidesColumn.setCellValueFactory(new PropertyValueFactory("isprovide"));
        // Страница удаления поставщика
        DeleteProviderIndexColumn.setCellValueFactory(new PropertyValueFactory("index"));
        DeleteProviderRegionColumn.setCellValueFactory(new PropertyValueFactory("region"));
        DeleteProviderCityColumn.setCellValueFactory(new PropertyValueFactory("city"));
        DeleteProviderCommentColumn.setCellValueFactory(new PropertyValueFactory("comment"));
        DeleteProviderCriteriaNameColumn.setCellValueFactory(new PropertyValueFactory("criteria"));
        DeleteProviderCriteriaValueColumn.setCellValueFactory(new PropertyValueFactory("value"));
        //Страница Организации
        OrganisationIndexColumn.setCellValueFactory(new PropertyValueFactory("index"));
        OrganisationRegionColumn.setCellValueFactory(new PropertyValueFactory("region"));
        OrganisationCityColumn.setCellValueFactory(new PropertyValueFactory("city"));
        OrganisationCommentColumn.setCellValueFactory(new PropertyValueFactory("comment"));
        //Страница выбор поставщика
        Phase1OrganisationIndexColumn.setCellValueFactory(new PropertyValueFactory("index"));
        Phase1OrganisationRegionColumn.setCellValueFactory(new PropertyValueFactory("region"));
        Phase1OrganisationCityColumn.setCellValueFactory(new PropertyValueFactory("city"));
        Phase1OrganisationCommentColumn.setCellValueFactory(new PropertyValueFactory("comment"));
        Phase2ProductNameColumn.setCellValueFactory(new PropertyValueFactory("title"));
        Phase3ProviderNameColumn.setCellValueFactory(new PropertyValueFactory("title"));
        Phase3ProviderRatingColumn.setCellValueFactory(new PropertyValueFactory("rating"));
        ObservableList<Integer> vals = FXCollections.observableArrayList();
        for(int i=1;i<10;i++) {
            vals.add(i);
        }
        Phase3ImportanceWayEdit.setItems(vals);
        Phase3ImportanceRatingEdit.setItems(vals);
        Phase3ImportanceRatingEdit.setValue(importancerating);
        Phase3ImportanceWayEdit.setValue(importanceway);
        Phase4BestProviderColumn.setCellValueFactory(new PropertyValueFactory("provider"));
        Phase4BestAddressColumn.setCellValueFactory(new PropertyValueFactory("startaddr"));
        Phase4BestWayLengthColumn.setCellValueFactory(new PropertyValueFactory("length"));
        Phase4BestWayRatingColumn.setCellValueFactory(new PropertyValueFactory("summaryrating"));
        Phase4BestWayTime.setCellValueFactory(new PropertyValueFactory("correctedtime"));
        Phase4OtherProviderColumn.setCellValueFactory(new PropertyValueFactory("provider"));
        Phase4OtherAddressColumn.setCellValueFactory(new PropertyValueFactory("startaddr"));
        Phase4OtherWayLengthColumn.setCellValueFactory(new PropertyValueFactory("length"));
        Phase4OtherWayRatingColumn.setCellValueFactory(new PropertyValueFactory("summaryrating"));
        Phase4OtherWayTime.setCellValueFactory(new PropertyValueFactory("correctedtime"));
        
        
 
    }
    // БЛОК ФУНКЦИЙ Connection =============================
    @FXML
    private void setDisconnect(ActionEvent event) {
        dataaccessor.setDisconnect();
        if (dataaccessor.errorcode.equals("")) {
            showStartPane();
            StatusTextLeft.setText("");
        } else {
        }
    }
    @FXML
    private void setConnect(ActionEvent event) {
        dataaccessor.setConnection(ConnectionUsernameEdit.getText(),
                                    ConnectionPassEdit.getText(),
                                    ConnectionDatabaseEdit.getText());
        if (dataaccessor.errorcode.equals("")) {
            StatusTextLeft.setText("Подключен к Базе данных: "+ConnectionDatabaseEdit.getText()+
                        ","+" пользователь:"+ConnectionUsernameEdit.getText());
            ConnectBtn.setDisable(true);
            DisconnectBtn.setDisable(false);
            ConnectionUsernameEdit.setDisable(true);
            ConnectionDatabaseEdit.setDisable(true);
            ConnectionPassEdit.setDisable(true);
            ConnectionSaveArgumentsEdit.setDisable(true);
            showPane(ConnectionPane,ConnectionBtn);
        }
    }
    @FXML
    private void showConnection(ActionEvent event) {
        showPane(ConnectionPane,ConnectionBtn);
    }
    
    // Функции Product Pane =============================
    @FXML
    private void showProducts(ActionEvent event) {
        showPane(ProductsPane, ProductsBtn);
        ProductTable.setItems(dataaccessor.getAllProducts());
    }
    @FXML
    private void ProductsSelectProduct(MouseEvent event) {
        Product prod = ProductTable.getSelectionModel().getSelectedItem();
        if (prod!=null) {
            ProductAddBtn.setDisable(true);
            ProductSaveBtn.setDisable(false);
            ProductDeleteBtn.setDisable(false);
            ProductNameEdit.setText(prod.getTitle());
        }    
    }
    @FXML
    private void ProductsClearSelection(MouseEvent event) {
        clearSelection("ProductsPane");
    }
    @FXML
    private void ProductAdd(ActionEvent event) {
        Product producttoadd = new Product();
        producttoadd.setTitle(ProductNameEdit.getText());
        if (!producttoadd.getTitle().equals("")) {
            if (dataaccessor.findProduct(producttoadd.getTitle())!=null) {
                showMessage("Данный продукт уже есть в списке!");
            }
            else {
                dataaccessor.addProduct(producttoadd);
                ProductTable.setItems(dataaccessor.getAllProducts());
                clearSelection("ProductsPane");
            }
        }
    }

    @FXML
    private void ProductSave(ActionEvent event) {
        if (!ProductNameEdit.getText().equals("")) {
            Product producttoupdate = new Product(ProductTable.getSelectionModel().getSelectedItem());
            producttoupdate.setTitle(ProductNameEdit.getText());
            dataaccessor.updateProduct(producttoupdate);
            ProductTable.setItems(dataaccessor.getAllProducts());
            clearSelection("ProductsPane");
        }   
    }

    @FXML
    private void ProductDelete(ActionEvent event) {
        if (!ProductNameEdit.getText().equals("")) {
            Product producttodelete = new Product(ProductTable.getSelectionModel().getSelectedItem());
            producttodelete.setTitle(ProductNameEdit.getText());
            dataaccessor.deleteProduct(producttodelete);
            ProductTable.setItems(dataaccessor.getAllProducts());
            clearSelection("ProductsPane");
        }
    }
    
    // Функции Organisation Pane =============================
    @FXML
    private void showOrganisation(ActionEvent event) {
        organisation = dataaccessor.getOrganisation();
        OrganisationTitleEdit.setText(organisation.getTitle());
        OrganisationAddressTable.setItems(organisation.addresses);
        showPane(OrganisationPane, OrganisationBtn);
        iteratorId=0;
    }
    @FXML
    private void OrganisationSelectAddress(MouseEvent event) {
        Address selectedaddress = OrganisationAddressTable.getSelectionModel().getSelectedItem();
        if (selectedaddress!=null) {
            OrganisationIndexEdit.setText(selectedaddress.getIndex());
            OrganisationCityEdit.setText(selectedaddress.getCity());
            OrganisationRegionEdit.setText(selectedaddress.getRegion());
            OrganisationCommentEdit.setText(selectedaddress.getComment());
            OrganisationAddAddressBtn.setDisable(true);
            OrganisationSaveAddressBtn.setDisable(false);
            OrganisationDeleteAddressBtn.setDisable(false);
        } 
    }

    @FXML
    private void OrganisationDeleteAddress(ActionEvent event) {
        Address selectedaddress = OrganisationAddressTable.getSelectionModel().getSelectedItem();
        organisation.addresses.remove(selectedaddress);
        clearSelection("OrganisationPane");
    }

    @FXML
    private void OrganisationAddAddress(ActionEvent event) {
        if (OrganisationIndexEdit.getText().equals("")||OrganisationCityEdit.getText().equals("")||
            OrganisationRegionEdit.getText().equals("")) {
            showMessage("Ошибка добавления адреса. Не заполнены одно или несколько полей: Индекс, Регион, Город");
        }
        else {
            Address addresstoadd = new Address();
            addresstoadd.setIndex(OrganisationIndexEdit.getText());
            addresstoadd.setCity(OrganisationCityEdit.getText());
            addresstoadd.setComment(OrganisationCommentEdit.getText());
            addresstoadd.setRegion(OrganisationRegionEdit.getText());
            addresstoadd.setId(iteratorId);
            organisation.addresses.add(addresstoadd);
            iteratorId--;
            clearSelection("OrganisationPane");
        }
    }

    @FXML
    private void OrganisationSaveAddress(ActionEvent event) {
        Address selectedaddress = OrganisationAddressTable.getSelectionModel().getSelectedItem();
        if (OrganisationIndexEdit.getText().equals("")||OrganisationCityEdit.getText().equals("")||
            OrganisationRegionEdit.getText().equals("")) {
            showMessage("Ошибка сохранения адреса. Не заполнены одно или несколько полей: Индекс, Регион, Город");
        }
        else {
            for (Address a:organisation.addresses) {
                if (a.getId()==selectedaddress.getId()) {
                    a.setIndex(OrganisationIndexEdit.getText());
                    a.setRegion(OrganisationRegionEdit.getText());
                    a.setCity(OrganisationCityEdit.getText());
                    a.setComment(OrganisationCommentEdit.getText());
                }    
            }
            OrganisationAddressTable.refresh();
            clearSelection("OrganisationPane");
        }
    }

    @FXML
    private void OrganisationSaveChanges(ActionEvent event) {
        organisation.setTitle(OrganisationTitleEdit.getText());
        organisation.addresses=OrganisationAddressTable.getItems();
        dataaccessor.updateOrganisation(organisation);
        organisation = dataaccessor.getOrganisation();
        OrganisationTitleEdit.setText(organisation.getTitle());
        OrganisationAddressTable.setItems(organisation.addresses);
        showPane(OrganisationPane, OrganisationBtn);
        iteratorId=0;
    }

    @FXML
    private void OrganisationClearSelect(MouseEvent event) {
        clearSelection("OrganisationPane");
    }
    
    // Функции Providers Pane =============================
    private void prepareEditProviderPane() {
        if (providertoedit==null) {
            providertoedit = new Provider();
            // Подготовка формы для добавления поставщика
            EditProviderLabel.setText("Добавление поставщика");
            ObservableList<Product> allproducts = dataaccessor.getAllProducts();
            for (Product product:allproducts) {
                ProductToProvider tempptp = new ProductToProvider();
                tempptp.setProduct(product);
                tempptp.setIsprovide("нет");
                providertoedit.products.add(tempptp);
            }
            ObservableList<Criteria> allcriteries = dataaccessor.getAllCriteries();
            for (Criteria criteria:allcriteries) {
                CriteriaToProvider tempctp = new CriteriaToProvider();
                tempctp.setCriteria(criteria);
                if (criteria.getBinarytype()==0)
                    tempctp.setValue("0");
                else tempctp.setValue("нет");
                providertoedit.criteries.add(tempctp);
            }
            EditProviderNameEdit.setText(providertoedit.getTitle());
            EditProviderINNEdit.setText(providertoedit.getInn());
            EditProviderOGRNEdit.setText(providertoedit.getOgrn());
            EditProviderOKPOEdit.setText(providertoedit.getOkpo());
            EditProviderOKTMOEdit.setText(providertoedit.getOktmo());
            EditProviderKPPEdit.setText(providertoedit.getKpp());
            EditProviderAddressTable.setItems(providertoedit.addresses);
            EditProviderCriteriaTable.setItems(providertoedit.criteries);
            EditProviderProductTable.setItems(providertoedit.products);
            iteratorId=0;
        }
        else {
            EditProviderLabel.setText("Редактирование поставщика");
            EditProviderNameEdit.setText(providertoedit.getTitle());
            EditProviderINNEdit.setText(providertoedit.getInn());
            EditProviderOGRNEdit.setText(providertoedit.getOgrn());
            EditProviderOKPOEdit.setText(providertoedit.getOkpo());
            EditProviderOKTMOEdit.setText(providertoedit.getOktmo());
            EditProviderKPPEdit.setText(providertoedit.getKpp());
            EditProviderAddressTable.setItems(providertoedit.addresses);
            EditProviderCriteriaTable.setItems(providertoedit.criteries);
            EditProviderProductTable.setItems(providertoedit.products);
            iteratorId=0;
        }
    }
    @FXML
    private void showProviders(ActionEvent event) {
        showPane(ProvidersPane, ProvidersBtn);
        ProvidersTable.setItems(dataaccessor.getAllProviders());
    }
    @FXML
    private void ProvidersAdd(ActionEvent event) {
        prepareEditProviderPane();
        showPane(EditProviderPane,ProvidersBtn);
        
    }
    @FXML
    private void ProvidersEdit(ActionEvent event) {
        prepareEditProviderPane();
        showPane(EditProviderPane,ProvidersBtn);
    }
    @FXML
    private void ProvidersDelete(ActionEvent event) {
        DeleteProviderAddressTable.setItems(providertoedit.addresses);
        DeleteProviderCriteriaTable.setItems(providertoedit.criteries);
        DeleteProviderINNEdit.setText(providertoedit.getInn());
        DeleteProviderKPPEdit.setText(providertoedit.getKpp());
        DeleteProviderNameEdit.setText(providertoedit.getTitle());
        DeleteProviderOGRNEdit.setText(providertoedit.getOgrn());
        DeleteProviderOKPOEdit.setText(providertoedit.getOkpo());
        DeleteProviderOKTMOEdit.setText(providertoedit.getOktmo());
        String providerproducts = "";
        for (ProductToProvider tempptp:providertoedit.products)
            if (tempptp.getIsprovide().equals("да"))
                providerproducts+=tempptp.getProduct().getTitle()+"; ";
        DeleteProviderProductsEdit.setText(providerproducts);
        showPane(DeleteProviderPane,ProvidersBtn);
    }
    @FXML
    private void ProvidersSelectFromTable(MouseEvent event) {
        providertoedit = ProvidersTable.getSelectionModel().getSelectedItem();
        if (providertoedit!=null) {
            ProvidersAddBtn.setDisable(true);
            ProvidersEditBtn.setDisable(false);
            ProvidersDeleteBtn.setDisable(false);
        }
    }
    @FXML
    private void ProvidersClearSelection(MouseEvent event) {
        clearSelection("ProvidersPane");
    }
    // Функции Edit Provider =============================
    @FXML
    private void EditProviderSaveProvider(ActionEvent event) {
        if(providertoedit!=null) {
            // Если поставщик новый (ID=0)
            if (providertoedit.getId()==0) {
                providertoedit.setTitle(EditProviderNameEdit.getText());
                providertoedit.setInn(EditProviderINNEdit.getText());
                providertoedit.setKpp(EditProviderKPPEdit.getText());
                providertoedit.setOgrn(EditProviderOGRNEdit.getText());
                providertoedit.setOkpo(EditProviderOKPOEdit.getText());
                providertoedit.setOktmo(EditProviderOKTMOEdit.getText());
                providertoedit.addresses=EditProviderAddressTable.getItems();
                providertoedit.criteries=EditProviderCriteriaTable.getItems();
                providertoedit.products=EditProviderProductTable.getItems();
                if (dataaccessor.findProvider(providertoedit.getTitle())!=null) {
                    showMessage("Данное имя поставщика уже занято!");
                }
                else {
                    dataaccessor.addProvider(providertoedit);
                    ProvidersTable.setItems(dataaccessor.getAllProviders());
                    clearSelection("ProvidersPane");
                    showPane(ProvidersPane,ProvidersBtn);
                }
            }
            // Если поставщик редактируется (ID!=0, соотв ID>0)
            else {
                providertoedit.setTitle(EditProviderNameEdit.getText());
                providertoedit.setInn(EditProviderINNEdit.getText());
                providertoedit.setKpp(EditProviderKPPEdit.getText());
                providertoedit.setOgrn(EditProviderOGRNEdit.getText());
                providertoedit.setOkpo(EditProviderOKPOEdit.getText());
                providertoedit.setOktmo(EditProviderOKTMOEdit.getText());
                providertoedit.addresses=EditProviderAddressTable.getItems();
                providertoedit.criteries=EditProviderCriteriaTable.getItems();
                providertoedit.products=EditProviderProductTable.getItems();
                dataaccessor.updateProvider(providertoedit);
                ProvidersTable.setItems(dataaccessor.getAllProviders());
                clearSelection("ProvidersPane");
                showPane(ProvidersPane,ProvidersBtn);
            }
        }
    }

    @FXML
    private void EditProviderGetBack(ActionEvent event) {
        clearSelection("ProvidersPane");
        ProvidersTable.setItems(dataaccessor.getAllProviders());
        showPane(ProvidersPane,ProvidersBtn);
    }

    @FXML
    private void EditProviderDeleteAddress(ActionEvent event) {
        Address selectedaddress = EditProviderAddressTable.getSelectionModel().getSelectedItem();
        providertoedit.addresses.remove(selectedaddress);
        clearSelection("EditProviderPane");
        EditProviderAddAddressBtn.requestFocus();
    }

    @FXML
    private void EditProviderAddAddress(ActionEvent event) {
        if (EditProviderIndexEdit.getText().equals("")||EditProviderCityEdit.getText().equals("")||
            EditProviderRegionEdit.getText().equals("")) {
            showMessage("Ошибка добавления адреса. Не заполнены одно или несколько полей: Индекс, Регион, Город");
        }
        else {
            Address addresstoadd = new Address();
            addresstoadd.setIndex(EditProviderIndexEdit.getText());
            addresstoadd.setCity(EditProviderCityEdit.getText());
            addresstoadd.setComment(EditProviderCommentEdit.getText());
            addresstoadd.setRegion(EditProviderRegionEdit.getText());
            addresstoadd.setId(iteratorId);
            providertoedit.addresses.add(addresstoadd);
            iteratorId--;
            clearSelection("EditProviderPane");
        }
    }

    @FXML
    private void EditProviderSaveAddress(ActionEvent event) {
        Address selectedaddress = EditProviderAddressTable.getSelectionModel().getSelectedItem();
        if (EditProviderIndexEdit.getText().equals("")||EditProviderCityEdit.getText().equals("")||
            EditProviderRegionEdit.getText().equals("")) {
            showMessage("Ошибка сохранения адреса. Не заполнены одно или несколько полей: Индекс, Регион, Город");
        }
        else {
            for (Address a:providertoedit.addresses) {
                if (a.getId()==selectedaddress.getId()) {
                    a.setIndex(EditProviderIndexEdit.getText());
                    a.setRegion(EditProviderRegionEdit.getText());
                    a.setCity(EditProviderCityEdit.getText());
                    a.setComment(EditProviderCommentEdit.getText());
                }    
            }
            EditProviderAddressTable.refresh();
            clearSelection("EditProviderPane");
            EditProviderAddAddressBtn.requestFocus();
        }
    }


    @FXML
    private void EditProviderSaveProducts(ActionEvent event) {
        ProductToProvider selectedptp = EditProviderProductTable.getSelectionModel().getSelectedItem();
        for (ProductToProvider ptp:providertoedit.products) {
            if (ptp.getProduct().getTitle().equals(selectedptp.getProduct().getTitle())) {
                ptp.setIsprovide(EditProviderProductIsProvideEdit.getValue());
            }
        }
        EditProviderProductTable.refresh();
        clearSelection("EditProviderPane");
        EditProviderProductNameEdit.requestFocus();
    }

    @FXML
    private void EditProviderSaveCriteria(ActionEvent event) {
        CriteriaToProvider selectedctp = EditProviderCriteriaTable.getSelectionModel().getSelectedItem();
        for (CriteriaToProvider ctp:providertoedit.criteries) {
            if (ctp.getCriteria().getTitle().equals(selectedctp.getCriteria().getTitle())) {
                ctp.setValue(EditProviderCriteriaValueEdit.getValue());
            }
        }
        EditProviderCriteriaTable.refresh();
        clearSelection("EditProviderPane");
        EditProviderCriteriaNameEdit.requestFocus();
    }
    @FXML
    private void EditProviderSelectAddress(MouseEvent event) {
        Address selectedaddress = EditProviderAddressTable.getSelectionModel().getSelectedItem();
        if (selectedaddress!=null) {
            EditProviderAddAddressBtn.setDisable(true);
            EditProviderSaveAddressBtn.setDisable(false);
            EditProviderDeleteAddressBtn.setDisable(false);
            EditProviderCityEdit.setText(selectedaddress.getCity());
            EditProviderCommentEdit.setText(selectedaddress.getComment());
            EditProviderRegionEdit.setText(selectedaddress.getRegion());
            EditProviderIndexEdit.setText(selectedaddress.getIndex());
        }
    }

    @FXML
    private void EditProviderSelectCriteria(MouseEvent event) {
        CriteriaToProvider selectedctp = EditProviderCriteriaTable.getSelectionModel().getSelectedItem();
        if (selectedctp!=null) {
            EditProviderSaveCriteriaBtn.setDisable(false);
            EditProviderCriteriaNameEdit.setText(selectedctp.getCriteria().getTitle());
            ObservableList<String> boxitems = FXCollections.observableArrayList();
            String item="";
            if (selectedctp.getValue().equals("да")||selectedctp.getValue().equals("нет")) {
                item = "да";
                boxitems.add(item);
                item = "нет";
                boxitems.add(item);
            }
            else {
                boxitems.add("0");
                for (int i=0;i<10;i++){
                    item = String.valueOf(i+1);
                    boxitems.add(item);
                }
            }
            EditProviderCriteriaValueEdit.setItems(boxitems);
            EditProviderCriteriaValueEdit.setValue(selectedctp.getValue());
        }
    }

    @FXML
    private void EditProviderSelectProduct(MouseEvent event) {
        ProductToProvider selectedptp = EditProviderProductTable.getSelectionModel().getSelectedItem();
        if (selectedptp!=null) {
            EditProviderSaveProductsBtn.setDisable(false);
            EditProviderProductNameEdit.setText(selectedptp.getProduct().getTitle());
            ObservableList<String> boxitems = FXCollections.observableArrayList();
            boxitems.add("да");
            boxitems.add("нет");
            EditProviderProductIsProvideEdit.setItems(boxitems);
            EditProviderProductIsProvideEdit.setValue(selectedptp.getIsprovide());
        }
    }

    @FXML
    private void EditProviderClearSelection(MouseEvent event) {
        clearSelection("EditProviderPane");
    }
    // Функции Delete Provider =============================
    @FXML
    private void DeleteProviderFromProviders(ActionEvent event) {
        dataaccessor.deleteProvider(providertoedit);
        clearSelection("ProvidersPane");
        ProvidersTable.setItems(dataaccessor.getAllProviders());
        showPane(ProvidersPane,ProvidersBtn);
    }
    @FXML
    private void DeleteProviderGetBack(ActionEvent event) {
        clearSelection("ProvidersPane");
        ProvidersTable.setItems(dataaccessor.getAllProviders());
        showPane(ProvidersPane,ProvidersBtn);
    }
    
    // Функции Properties Pane =============================
    @FXML
    private void showProperties(ActionEvent event) {
        showPane(PropertiesPane,PropertiesBtn);
    }
    @FXML
    private void PropertiesSavePreferences(ActionEvent event) {
    }
    @FXML
    private void PropertiesSelectAdjustment(MouseEvent event) {
    }
    @FXML
    private void PropertiesSaveAdjustment(ActionEvent event) {
    }
    @FXML
    private void PropertiesSaveCriteria(ActionEvent event) {
    }
    @FXML
    private void PropertiesSelectCriteria(MouseEvent event) {
    }
    @FXML
    private void PropertiesClearSelection(MouseEvent event) {
    }
    
    // Функции Choose Provider Pane =============================
    @FXML
    private void showChooseProviders(ActionEvent event) {
        Phase1OrganisationAddressTable.setItems(dataaccessor.getOrganisation().addresses);
        showPane(ChoosePhase1Pane,ChooseProviderBtn);
    }
    
    @FXML
    private void Phase1SelectAddress(MouseEvent event) {
        organisationaddress = Phase1OrganisationAddressTable.getSelectionModel().getSelectedItem();
        if (organisationaddress!=null)
            Phase1GetForwardBtn.setDisable(false);
    }

    @FXML
    private void Phase1GetForward(ActionEvent event) {
        Phase2ProductTable.setItems(dataaccessor.getAllProducts());
        showPane(ChoosePhase2Pane,ChooseProviderBtn);
    }

    @FXML
    private void Phase1ClearSelection(MouseEvent event) {
        clearSelection("ChoosePhase1Pane");
    }

    @FXML
    private void Phase2SelectProduct(MouseEvent event) {
        selectedproduct = Phase2ProductTable.getSelectionModel().getSelectedItem();
        if (selectedproduct!=null)
            Phase2GetForwardBtn.setDisable(false);
    }

    @FXML
    private void Phase2GetForward(ActionEvent event) {
        providerwithproduct = dataaccessor.getAllProviders(selectedproduct);
        for (Provider p:providerwithproduct) {
            int rating =0;
            for (CriteriaToProvider ctp:p.criteries) {
                int valctp=0;
                if (ctp.getValue().equals("да")||ctp.getValue().equals("нет")) {
                    if (ctp.getValue().equals("да"))
                        valctp=10;
                    else
                        valctp=0;
                }
                else 
                    valctp = Integer.parseInt(ctp.getValue());
                rating+=ctp.getCriteria().getImportance()*valctp;
            }
            p.setRating(rating);
        }
        Phase3ProviderTable.setItems(providerwithproduct);
        showPane(ChoosePhase3Pane,ChooseProviderBtn);
    }

    @FXML
    private void Phase2GetBack(ActionEvent event) {
        clearSelection("ChoosePhase1Pane");
        showPane(ChoosePhase1Pane,ChooseProviderBtn);
    }

    @FXML
    private void Phase2ClearSelection(MouseEvent event) {
        clearSelection("ChoosePhase2Pane");
    }

    @FXML
    private void Phase3GetBack(ActionEvent event) {
        clearSelection("ChoosePhase2Pane");
        showPane(ChoosePhase2Pane,ChooseProviderBtn);
    }

    @FXML
    private void Phase3GetForward(ActionEvent event) throws FileNotFoundException {
        showPane(ChoosePhase4Pane,ChooseProviderBtn);
        maxtime = Integer.parseInt(Phase3MaxTimeEdit.getText())*24;
        importanceway = Phase3ImportanceWayEdit.getValue();
        importancerating = Phase3ImportanceRatingEdit.getValue();
        int providerrating = Integer.parseInt(Phase3MinProviderRatingEdit.getText());
        providersortedbyrating.clear();
        // Сортируем по рейтингу поставщика
        for (Provider provider:providerwithproduct) {
            if (provider.getRating()>=providerrating) {
                providersortedbyrating.add(provider);
            }
        }
        // Считываем пути
        ObservableList<Way> unsortedways = FXCollections.observableArrayList();
        for (Provider provider:providersortedbyrating) {
            for (Address provideraddress:provider.addresses) {
                String request = "https://route.api.here.com/routing/7.2/calculateroute.json?"+
                    "waypoint0="+provideraddress.getLatitude()+","+provideraddress.getLongitude()+
                    "&waypoint1="+organisationaddress.getLatitude()+","+organisationaddress.getLongitude()+
                    "&mode=fastest;truck"+
                    "&routeattributes=sh"+
                    "&alternatives=4"+
                    "&app_id="+dataaccessor.getHereappkey_ID()+""+
                    "&app_code="+dataaccessor.getHereappkey_Code()+"";
                Phase4Log("отправка запроса: "+request);
                // Считываем основную информацию из json
                ObservableList<Way> ways = dataaccessor.getRoutes(request);
                // Дополняем под конкретного поставщика и конкретный конечный адрес
                for (Way way:ways) {
                    way.setProvider(provider);
                    way.setEndaddr(organisationaddress);
                    way.setStartaddr(provideraddress);
                }
                unsortedways.addAll(ways);
            }
        }
        Phase4Log("===");
        Phase4Log("Список найденных путей:");
        for (Way way:unsortedways) {
            Phase4Log("===");
            Phase4Log("Поставщик: "+way.getProvider().toString());
            Phase4Log("Адрес: "+way.getStartaddr().toString());
            Phase4Log("Протяженность: "+way.getLength()+" км, Время в пути: "+way.getBasetime()+" ч, Средняя скорость: "+way.getAvgspeed()+" км/ч");
            Phase4Log("===");
        }
        // Сортируем пути по максимальному времени
        ObservableList<Way> uncorrectedways = FXCollections.observableArrayList();
        for (Way way:unsortedways) {
            if(way.getBasetime()<maxtime) {
                uncorrectedways.add(way);
            }
        }
        Phase4Log("Список сортированных по времемни путей:");
        for (Way way:uncorrectedways) {
            Phase4Log("===");
            Phase4Log("Поставщик: "+way.getProvider().toString());
            Phase4Log("Адрес: "+way.getStartaddr().toString());
            Phase4Log("Протяженность: "+way.getLength()+" км, Время в пути: "+way.getBasetime()+" ч, Средняя скорость: "+way.getAvgspeed()+" км/ч");
            Phase4Log("===");
        }
        // Корректируем пути по погоде
        ObservableList<Way> correctedways = dataaccessor.correctRoutes(uncorrectedways);
        Phase4Log("Список сортированных по погоде путей:");
        for (Way way:correctedways) {
            Phase4Log("===");
            Phase4Log("Поставщик: "+way.getProvider().toString());
            Phase4Log("Адрес: "+way.getStartaddr().toString());
            Phase4Log("Протяженность: "+way.getLength()+" км, Время в пути: "+way.getBasetime()+" ч, Средняя скорость: "+way.getAvgspeed()+" км/ч");
            Phase4Log("Средний балл опасности на дороге: "+way.getAvgdanger()+" , Погодные условия на пути: "+way.getWeatheronway());
            Phase4Log("===");
        }
        // Сортируем по максимальному времени
        ObservableList<Way> summaryways = FXCollections.observableArrayList();
        for (Way way: correctedways) {
            if(way.getCorrectedtime()<maxtime) {
                summaryways.add(way);
            }
        }
        Phase4Log("Список сортированных по времемни путей:");
        for (Way way:summaryways) {
            Phase4Log("===");
            Phase4Log("Поставщик: "+way.getProvider().toString());
            Phase4Log("Адрес: "+way.getStartaddr().toString());
            Phase4Log("Протяженность: "+way.getLength()+" км, Время в пути: "+way.getBasetime()+" ч, Средняя скорость: "+way.getAvgspeed()+" км/ч");
            Phase4Log("Средний балл опасности на дороге: "+way.getAvgdanger()+" , Погодные условия на пути: "+way.getWeatheronway());
            Phase4Log("===");
        }
        // Считаем общий рейтинг пути
        for (Way way:summaryways) {
            double currentproviderrating = way.getProvider().getRating()/100.0;
            // Отбираем пути по принципу меньше, равно максимуму быть не может!
            //Т.е. значение ожидаемо больше 0. Значения 0 быть не может тоже.
            double dividetime = way.getCorrectedtime()/maxtime;
            double currentwayrating = 1 - dividetime;
            double summaryrating;
            summaryrating = (currentproviderrating*importancerating) + (currentwayrating*importanceway);
            way.setSummaryrating(summaryrating);  
        }
        // Ищем лучший путь с максимальным рейтингом
        Way bestratingway = new Way();
        bestratingway.setSummaryrating(0);
        for (Way way:summaryways) {
            if (way.getSummaryrating()>=bestratingway.getSummaryrating()) {
                if (way.getSummaryrating()==bestratingway.getSummaryrating()) {
                    if (way.getCorrectedtime()<bestratingway.getCorrectedtime())
                        bestratingway=way;
                }
                else {
                    bestratingway=way;
                }
            }
        }
        Phase4Log("Лучший путь:");
        Phase4Log("Поставщик: "+bestratingway.getProvider().toString());
        Phase4Log("Адрес: "+bestratingway.getStartaddr().toString());
        Phase4Log("Протяженность: "+bestratingway.getLength()+" км, Время в пути: "+bestratingway.getBasetime()+" ч, Средняя скорость: "+bestratingway.getAvgspeed()+" км/ч");
        Phase4Log("Средний балл опасности на дороге: "+bestratingway.getAvgdanger()+" , Погодные условия на пути: "+bestratingway.getWeatheronway());
        Phase4Log("Общий рейтинг пути:"+bestratingway.getSummaryrating());
        ObservableList<Way> bestway = FXCollections.observableArrayList();
        bestway.add(bestratingway);
        // Формируем список остальных путей без данного пути
        ObservableList<Way> otherways = summaryways;
        // Удаляем из списка других путей лучший путь
        otherways.remove(bestratingway);
        Phase4BestWayTable.setItems(bestway);
        Phase4OtherWayTable.setItems(otherways);
        
    }
    
    @FXML
    private void Phase3showTimeInformation(ActionEvent event) {
        showMessage("На данный момент прогноз поддерживается только до 6 дней"
                + "\n Если указать больше 6 дней, прогноз свыше 6 дней учитываться не будет!");
    }
    @FXML
    private void Phase3ImportanceRatingChanged(ActionEvent event) {
        int val = 10 - Phase3ImportanceRatingEdit.getValue();
        Phase3ImportanceWayEdit.setValue(val);
    }

    @FXML
    private void Phase3ImportanceWayChanged(ActionEvent event) {
        int val = 10 - Phase3ImportanceWayEdit.getValue();
        Phase3ImportanceRatingEdit.setValue(val);
    }
    
    @FXML
    private void Phase4GetBack(ActionEvent event) {
        showPane(ChoosePhase3Pane,ChooseProviderBtn);
    }

    @FXML
    private void Phase4GetForward(ActionEvent event) {
        Phase5AverageDangerEdit.setText("1");
        Phase5ProviderEdit.setText(selectedway.getProvider().toString());
        Phase5EndEdit.setText(selectedway.getEndaddr().toString());
        Phase5StartEdit.setText(selectedway.getStartaddr().toString());
        double wayrate = (1 - (selectedway.getCorrectedtime()/maxtime))*importanceway;
        Phase5RatingWayEdit.setText(String.format("%.3f%n", wayrate));
        double provrate = (selectedway.getProvider().getRating()/100.0)*importancerating;
        Phase5RatingProviderEdit.setText(String.format("%.3f%n", provrate));
        Phase5RatingCommonEdit.setText(String.format("%.3f%n",selectedway.getSummaryrating()));
        Phase5AVGTimeEdit.setText(selectedway.getCorrectedtime()+" часов");
        Phase5LengthEdit.setText(selectedway.getLength()+" км.");
        Phase5ProductEdit.setText(selectedproduct.getTitle());
        showPane(ChoosePhase5Pane,ChooseProviderBtn);
    }

    @FXML
    private void Phase4SelectOtherWay(MouseEvent event) {
        Phase4BestWayTable.getSelectionModel().clearSelection();
        selectedway = Phase4OtherWayTable.getSelectionModel().getSelectedItem();
        if (selectedway!=null) {
            Phase4GetForwardBtn.setDisable(false);
        }
        else {Phase4GetForwardBtn.setDisable(true);}
    }

    @FXML
    private void Phase4SelectBestWay(MouseEvent event) {
        Phase4OtherWayTable.getSelectionModel().clearSelection();
        selectedway = Phase4BestWayTable.getSelectionModel().getSelectedItem();
        if (selectedway!=null) {
            Phase4GetForwardBtn.setDisable(false);
        }
        else {Phase4GetForwardBtn.setDisable(true);}
    }
     @FXML
    private void Phase4SaveToSamples(ActionEvent event) {
        Phase4SaveToSamplesBtn.setText("Выборка сохранена");
        Phase4SaveToSamplesBtn.setDisable(true);
    }

    @FXML
    private void Phase4ClearSelection(MouseEvent event) {
        clearSelection("ChoosePhase4Pane");
    }

    @FXML
    private void Phase5GetBack(ActionEvent event) {
        clearSelection("ChoosePhase4Pane");
        showPane(ChoosePhase4Pane,ChooseProviderBtn);
    }

    @FXML
    private void Phase5GetGPXFile(ActionEvent event) {
    }

    // Функции SavedSamples Pane =============================
    @FXML
    private void showSavedSamples(ActionEvent event) {
        showPane(SavedSamplesPane, SavedSamplesBtn);
    }

   








    
}
