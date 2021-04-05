package com.groupproject.edmontonpropertyassessment;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) {
        String file = "Property_Assessment_Data.csv";
        Scene scene = new Scene(new Group());
        //===========================Table Stuff================================
        final Label tableLabel = new Label("Edmonton Property Assessments");
        tableLabel.setFont(new Font("Courier", 16));
        
        TableView<HouseMetadata> table = new TableView<>();
        ObservableList<HouseMetadata> houses = DataUtils.constructList(file);
        FilteredList<HouseMetadata> filteredHouses = new FilteredList(houses);
        List<Double> values = DataUtils.houseValues(houses);
        table.setItems(houses);
        
        //Set up all table columns
        TableColumn<HouseMetadata,String> accountNumCol = new TableColumn<>("Account");
        accountNumCol.setCellValueFactory(new PropertyValueFactory("accountNum"));
        
        TableColumn<HouseMetadata,String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory("address"));
        
        TableColumn<HouseMetadata,String> assessedValueCol = new TableColumn<>("Assessed Value");
        assessedValueCol.setCellValueFactory(new PropertyValueFactory("formattedVal"));
        
        TableColumn<HouseMetadata,String> assessedClassCol = new TableColumn<>("Assessment Class");
        assessedClassCol.setCellValueFactory(new PropertyValueFactory("assessmentClass1"));
        
        TableColumn<HouseMetadata,String> neighbourhoodCol = new TableColumn<>("Neighbourhood");
        neighbourhoodCol.setCellValueFactory(new PropertyValueFactory("nbhdName"));
        
        TableColumn<HouseMetadata,String> latitudeCol = new TableColumn<>("Latitude");
        latitudeCol.setCellValueFactory(new PropertyValueFactory("latitude"));
        
        TableColumn<HouseMetadata,String> longitudeCol = new TableColumn<>("Longitude");
        longitudeCol.setCellValueFactory(new PropertyValueFactory("longitude"));

        table.getColumns().setAll(accountNumCol,
                addressCol,
                assessedValueCol,
                assessedClassCol,
                neighbourhoodCol,
                latitudeCol,
                longitudeCol
                );
        table.setPrefHeight(1024);
        table.setPrefWidth(1000);

        //==========================Stats and Search Stuff======================
 
        TextArea statsText = new TextArea();
        statsText.setText("Statistics of Assessed Values: \n"
        + "\nNumber of properties: " + DataUtils.n(values) 
        + "\nMin: " + FormatterUtils.valueFormatter(DataUtils.min(values))
        + "\nMax: " + FormatterUtils.valueFormatter(DataUtils.max(values))
        + "\nRange: " + FormatterUtils.valueFormatter(DataUtils.range(values))
        + "\nMean: " + FormatterUtils.valueFormatter(DataUtils.mean(values))
        + "\nMedian: " + FormatterUtils.valueFormatter(DataUtils.median(values))
        + "\nStandard deviation: " + FormatterUtils.valueFormatter(DataUtils.stdev(values))
        );
        
        final Label searchLabel = new Label("Find Property Assessment");        
        final Label accLabel = new Label("Account Number:");
        final Label addressLabel = new Label("Address (#suite #house street");
        final Label nbhdLabel = new Label("Neighbourhood:");
        final Label acLabel = new Label("Assessment Class:");
        
        final TextField accField = new TextField();
        accField.setPromptText("e.g. 1234567");
        accField.setPrefWidth(100);
        
        final TextField addressField = new TextField();
        addressField.setPromptText("e.g. 1234 101 Avenue NW");
        
        final TextField nbhdField = new TextField();
        nbhdField.setPromptText("e.g. Downtown");
        
        final ComboBox acCombo = new ComboBox();
        acCombo.getItems().add(null);
        acCombo.getItems().add("Residential");
        acCombo.getItems().add("Nonres");
        acCombo.getItems().add("Commercial");
        acCombo.getItems().add("Other Residential");
        acCombo.getItems().add("Farmland");
        Button search = new Button("Search");
        Button clear = new Button("Clear");
        
        //==========================Construct Scene=============================
        final VBox tableVBox = new VBox();
        tableVBox.setSpacing(5);
        tableVBox.setPadding(new Insets(5, 5, 5, 5));
        tableVBox.getChildren().addAll(tableLabel, table);
        tableVBox.setPrefWidth(1024);
        tableVBox.setPrefHeight(800);
        
        final HBox buttonHBox = new HBox();
        buttonHBox.setSpacing(5);
        buttonHBox.setPadding(new Insets(5,5,5,5));
        buttonHBox.getChildren().addAll(search, clear);
        
        
        final VBox searchVBox = new VBox();
        searchVBox.setSpacing(5);
        searchVBox.setPadding(new Insets(5,5,5,5));
        searchVBox.getChildren().addAll(searchLabel,
                accLabel,
                accField,
                addressLabel,
                addressField,
                nbhdLabel,
                nbhdField,
                acLabel,
                acCombo);
        ////////
        // BEGIN EDITS FOR EXPORT OPTIONS
        ////////
        VBox expOptions = new VBox(10);
        Text exp = new Text("Export Options:");

        CheckBox check1 = new CheckBox("Filtered Property Data (.csv)");
        CheckBox check2 = new CheckBox("Data Summary (.txt)");
        
        Button exportButton = new Button("Export Data");
        Text indicator = new Text("");
        indicator.setFont(Font.font("ariel", FontWeight.NORMAL, FontPosture.ITALIC, 14));
        
        expOptions.getChildren().addAll(exp, check1, check2, exportButton);
        
        FileChooser fc = new FileChooser();
        
        final VBox statsVBox = new VBox();
        statsVBox.setSpacing(5);
        statsVBox.setPadding(new Insets(5, 5, 5, 5));
        statsVBox.getChildren().addAll(searchVBox, buttonHBox, statsText, expOptions, indicator);
        statsVBox.setPrefWidth(250);
        
        ////////
        // END EDITS FOR EXPORT OPTIONS
        ////////
        
        HBox hbox = new HBox(statsVBox, tableVBox);
        ((Group) scene.getRoot()).getChildren().addAll(hbox);
        stage.setScene(scene);
        stage.show();
        
        //=================Button and Filter Functionality======================
        
        //https://stackoverflow.com/questions/42138867/filtering-jfx-tableview-with-multiple-values
        
        ObjectProperty<Predicate<HouseMetadata>> accountNumFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<HouseMetadata>> addressFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<HouseMetadata>> nbhdFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<HouseMetadata>> assessmentClassFilter = new SimpleObjectProperty<>();
        
        accountNumFilter.bind(Bindings.createObjectBinding(() ->
            house -> house.getAccountNum().startsWith(accField.getText()),
                accField.textProperty()));
        
        addressFilter.bind(Bindings.createObjectBinding(() ->
            house -> house.getAddress().contains(addressField.getText().toUpperCase()),
                addressField.textProperty()));
        
        nbhdFilter.bind(Bindings.createObjectBinding(() ->
            house -> house.getNbhdName().contains(nbhdField.getText().toUpperCase()),
                nbhdField.textProperty()));
        
        assessmentClassFilter.bind(Bindings.createObjectBinding(() ->
            house -> acCombo.getValue() == null || house.getAssessmentClass1().contains(acCombo.getValue().toString().toUpperCase()),
                acCombo.valueProperty()));
              
        List<HouseMetadata> exportList = new ArrayList<>();
        
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                filteredHouses.predicateProperty().bind(Bindings.createObjectBinding(
                () -> accountNumFilter.get()
                        .and(nbhdFilter.get())
                        .and(addressFilter.get())
                        .and(assessmentClassFilter.get()),
                        accountNumFilter,
                        nbhdFilter,
                        addressFilter,
                        assessmentClassFilter));

                SortedList<HouseMetadata> sortableHouses = new SortedList<>(filteredHouses);
                table.setItems(sortableHouses);
                sortableHouses.comparatorProperty().bind(table.comparatorProperty());
                if (sortableHouses.size() > 1) {
                    exportList.addAll(sortableHouses);
                    List<Double> filteredValues = DataUtils.houseValues(filteredHouses);
                        statsText.setText("Statistics of Assessed Values: \n"
                        + "\nNumber of properties: " + DataUtils.n(filteredValues) 
                        + "\nMin: " + FormatterUtils.valueFormatter(DataUtils.min(filteredValues))
                        + "\nMax: " + FormatterUtils.valueFormatter(DataUtils.max(filteredValues))
                        + "\nRange: " + FormatterUtils.valueFormatter(DataUtils.range(filteredValues))
                        + "\nMean: " + FormatterUtils.valueFormatter(DataUtils.mean(filteredValues))
                        + "\nMedian: " + FormatterUtils.valueFormatter(DataUtils.median(filteredValues))
                        + "\nStandard deviation: " + FormatterUtils.valueFormatter(DataUtils.stdev(filteredValues))
                        );
                } else {
                    exportList.removeAll(exportList);
                    statsText.clear();
                }
            }
        });
        
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            accField.clear();
            addressField.clear();
            nbhdField.clear();
            acCombo.setValue(null);
            
            table.setItems(houses);
            }
        });
        
        ///////
        // BEGIN EXPORT BUTTON FUNCTIONALITY
        ///////
        exportButton.setOnAction( actionEvent -> {
            if (!(check1.isSelected() || check2.isSelected())){
                indicator.setText("No data selected!");
                return;
            }else{
                indicator.setText("");
            }
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"));
            fc.setInitialFileName("propertyout.csv");
            File out = fc.showSaveDialog(stage);
            if (out != null){
                try{
                    PrintWriter pw = new PrintWriter(out);
                    StringBuilder propOut = new StringBuilder();
                    
                    if (check1.isSelected()){
                        
                        for (HouseMetadata p: filteredHouses){
                            propOut.append(p.getAccountNum());
                            propOut.append(",");
                            propOut.append(p.getSuite());
                            propOut.append(",");
                            propOut.append(p.getHouseNum());
                            propOut.append(",");
                            propOut.append(p.getStreetName());
                            propOut.append(",");
                            propOut.append(p.getGarageExist());
                            propOut.append(",");
                            propOut.append(p.getNbhdNum());
                            propOut.append(",");
                            propOut.append(p.getNbhdName());
                            propOut.append(",");
                            propOut.append(p.getWard());
                            propOut.append(",");
                            propOut.append(p.getAssessedVal());
                            propOut.append(",");
                            propOut.append(p.getLatitude());
                            propOut.append(",");
                            propOut.append(p.getLongitude());
                            propOut.append(",");
                            propOut.append(p.getAssessmentClassP1());
                            propOut.append(",");
                            propOut.append(p.getAssessmentClassP2());
                            propOut.append(",");
                            propOut.append(p.getAssessmentClassP3());
                            propOut.append("\n");
                        }
                            //System.out.format("propout %s:", propOut);
                            pw.append(propOut);
                            pw.close();
                    }
                           
                            
                    
                    if (check2.isSelected()){
                        fc.setInitialFileName("filteredstats.txt");
                        out = fc.showSaveDialog(stage);
                        PrintWriter pw2 = new PrintWriter(out);
                        pw2.append(statsText.getText());
                        pw2.flush();
                        pw2.close();
                    }
                    

                }catch (Exception e){
                    System.out.format("Error: %s", e);
                    //e.printStackTrace();
                    return;
                }
            }
        } );
        ///////
        // END EXPORT BUTTON FUNCTIONALITY
        ///////

    }

    public static void main(String[] args) {
        launch();
    }
   
}