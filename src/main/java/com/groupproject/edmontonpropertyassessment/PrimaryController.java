package com.groupproject.edmontonpropertyassessment;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {

    @FXML private TableView<HouseMetadata> houseTable;
    @FXML private TableColumn<HouseMetadata,String> accountNumCol;
    @FXML private TableColumn<HouseMetadata,String> addressCol;
    @FXML private TableColumn<HouseMetadata,String> assessedValueCol;
    @FXML private TableColumn<HouseMetadata,String> assessmentClassCol;
    @FXML private TableColumn<HouseMetadata,String> nbhdCol;
    @FXML private TableColumn<HouseMetadata,String> latitudeCol;
    @FXML private TableColumn<HouseMetadata,String> longitudeCol;
    
    @FXML private TextField accountField;
    @FXML private TextField addressField;
    @FXML private TextField nbhdField;
    @FXML private ComboBox<String> assessmentCombo;
    @FXML private TextArea statsText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assessmentCombo.getItems().addAll(null,
                "Residential", 
                "Non-Residential", 
                "Commercial", 
                "Other Residential", 
                "Farmland");
//        accountNumCol.setCellValueFactory(new PropertyValueFactory<>("accountNum"));
//        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
//        assessedValueCol.setCellValueFactory(new PropertyValueFactory<>("formattedValue"));
//        assessmentClassCol.setCellValueFactory(new PropertyValueFactory<>("assessmentClass1"));
//        nbhdCol.setCellValueFactory(new PropertyValueFactory<>("nbhdName"));
//        latitudeCol.setCellValueFactory(new PropertyValueFactory<>("latitude"));
//        longitudeCol.setCellValueFactory(new PropertyValueFactory<>("longitude"));
//        
//        String file = "Property_Assessment_Data.csv";
//        TableView<HouseMetadata> table = new TableView<>();
//        ObservableList<HouseMetadata> houses = DataUtils.constructList(file);
//        FilteredList<HouseMetadata> filteredHouses = new FilteredList(houses);
//        List<Double> values = DataUtils.houseValues(houses);
//        houseTable.setItems(houses);
    }
}
