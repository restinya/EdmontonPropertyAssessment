package com.groupproject.edmontonpropertyassessment;

package mile3pkg;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author meron
 */
public class ChartController implements Initializable {
    
    //Chart object
    @FXML private PieChart pieChartView;
    
    //Radio Buttons and it's toggle group
    @FXML private RadioButton classRadioButton;
    @FXML private RadioButton wardRadioButton;
    
    @FXML private ToggleGroup categoryTooggleGroup;
    
    @FXML private Label title;
    
    ObservableList<PieChart.Data> classList;
    ObservableList<PieChart.Data> wardList;
    
    String fileName = "Property_Assessment_Data.csv";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //assign the radio buttons to a toggle group
        categoryTooggleGroup = new ToggleGroup();
        this.classRadioButton.setToggleGroup(categoryTooggleGroup);
        this.wardRadioButton.setToggleGroup(categoryTooggleGroup);
        
        //get an observable list of both scenarios ready
        classList = assessmentClassData();
        wardList = wardData();
        
    }
    
    //=======================================================================================
    public void getChartButton(){
        if (this.categoryTooggleGroup.getSelectedToggle().equals(this.classRadioButton)){
            setAssessmentChart();
        }
        if (this.categoryTooggleGroup.getSelectedToggle().equals(this.wardRadioButton)){
            setWardChart();
        }   
    }
    
    public void setAssessmentChart(){
        title.setText("Chart view of Edmonton Properties by Assessment Class");
        pieChartView.setData(classList);
    }
    public void setWardChart(){
        title.setText("Chart view of Edmonton Properties by Wards");
        pieChartView.setData(wardList);
    }
    //=======================================================================================
    public ObservableList<PieChart.Data> assessmentClassData(){
        //initilaize an observable list
        ObservableList<PieChart.Data> constructedClassList = FXCollections.observableArrayList();
        Map<String, Double> classTotal = getClassTotal();
                    
        //populate observable list with totals from classTotal
        for (String className : classTotal.keySet()){
            constructedClassList.add(new PieChart.Data(className, classTotal.get(className)));
        }
        
        return constructedClassList;  //return the observable list
    }
    
    //=======================================================================================
    //method to get the class totals for each assessment class cateogry
    private Map<String, Double> getClassTotal() {
        Map<String, Double> classTotal = new HashMap<>();
        classTotal.put("RESIDENTIAL", 0.0);
        classTotal.put("NON-RES", 0.0);
        classTotal.put("COMMERCIAL", 0.0);
        classTotal.put("FARMLAND", 0.0);
        classTotal.put("OTHER RESIDENTIAL", 0.0);
        List<HouseMetadata> houses = fileParser();
        for(int i=0; i<houses.size();i++){
            //System.out.println(classTotal.get("RESIDENTIAL") + (double)Integer.parseInt(houses.get(i).getAssessedVal()));
            //classTotal.put(houses.get(i).getAssessedVal(), classTotal.get(houses.get(i).getAssessmentClass1()) + (double)Integer.parseInt(houses.get(i).getAssessedVal()));
            switch (houses.get(i).getAssessmentClass1()) {
                case "RESIDENTIAL":
                    classTotal.put("RESIDENTIAL", classTotal.get("RESIDENTIAL") + (double)Integer.parseInt(houses.get(i).getAssessedVal()));
                    break;
                case "COMMERCIAL":
                    classTotal.put("COMMERCIAL", classTotal.get("COMMERCIAL") + (double)Integer.parseInt(houses.get(i).getAssessedVal()));
                    break;
                case "OTHER RESIDENTIAL":
                    classTotal.put("OTHER RESIDENTIAL", classTotal.get("OTHER RESIDENTIAL") + 
                            (double)Integer.parseInt(houses.get(i).getAssessedVal()));
                    break;
                case "FARMLAND":
                    classTotal.put("FARMLAND", classTotal.get("FARMLAND") + 
                            (double)Integer.parseInt(houses.get(i).getAssessedVal()));
                    break;
                default:
                    classTotal.put("NON-RES", classTotal.get("NON-RES") + 
                            (double)Integer.parseInt(houses.get(i).getAssessedVal()));
                    break;
            }
        }
        return classTotal;
    }
    //=======================================================================================
    public ObservableList<PieChart.Data> wardData(){
        //initilaize an observable list
        ObservableList<PieChart.Data> constructedWardList = FXCollections.observableArrayList();;
        Map<String, Double> wardTotal = getWardTotal();
                    
        //populate observable list with totals from classTotal
        for (String wardName : wardTotal.keySet()){
            constructedWardList.add(new PieChart.Data(wardName, wardTotal.get(wardName)));
        }
        
        return constructedWardList; //return the observable list
        
    }
    private Map<String, Double> getWardTotal(){
        Map<String, Double> wardTotal = new HashMap<>();
        for (int i=1; i<13; i++){
            wardTotal.put("Ward "+i, 0.0);
        }
        List<HouseMetadata> houses = fileParser();
        for(int i=0; i<houses.size();i++){
            if (null!=houses.get(i).getWard())switch (houses.get(i).getWard()) {
                case "Ward 1":
                    wardTotal.put("Ward 1", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 1"));
                    break;
                case "Ward 2":
                    wardTotal.put("Ward 2", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 2"));
                    break;
                case "Ward 3":
                    wardTotal.put("Ward 3", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 3"));
                    break;
                case "Ward 4":
                    wardTotal.put("Ward 4", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 4"));
                    break;
                case "Ward 5":
                    wardTotal.put("Ward 5", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 5"));
                    break;
                case "Ward 6":
                    wardTotal.put("Ward 6", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 6"));
                    break;
                case "Ward 7":
                    wardTotal.put("Ward 7", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 7"));
                    break;
                case "Ward 8":
                    wardTotal.put("Ward 8", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 8"));
                    break;
                case "Ward 9":
                    wardTotal.put("Ward 9", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 9"));
                    break;
                case "Ward 10":
                    wardTotal.put("Ward 10", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 10"));
                    break;
                case "Ward 11":
                    wardTotal.put("Ward 11", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 11"));
                    break;
                case "Ward 12":
                    wardTotal.put("Ward 12", (double)Integer.parseInt(houses.get(i).getAssessedVal()) +
                            wardTotal.get("Ward 12"));
                    break;
                default:
                    break;
            }
        }
        return wardTotal;
        
    }
    //=======================================================================================
    private List<HouseMetadata> fileParser(){
        //open file
        if (!fileName.endsWith(".csv")) {
            System.out.println("Error: Not a CSV file");
            return null;
        }
        
        File fileIn = new File(fileName);
        
        if (!fileIn.isFile()) {
            System.out.println("Error: File does not exist");
            return null;
        }
        
        //parse file
        List<HouseMetadata> houses = new ArrayList<>();
        
        try (BufferedReader br = Files.newBufferedReader(fileIn.toPath())) {
            String line = br.readLine();
            line = br.readLine(); //skip header
            
            while (line != null) {
                HouseMetadata house = new HouseMetadata(line);
                houses.add(house);
                
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Returned all houses");
        return houses;
    }
    //=======================================================================================
    //Method used to return to table view 
    public void tableButtonPressed(ActionEvent event) throws IOException{
        Parent tableParent = FXMLLoader.load(getClass().getResource("Table.fxml"));
        Scene tableView = new Scene(tableParent);
        
        //stage information
        Stage newWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        newWindow.setScene(tableView);
        newWindow.setTitle("Edmonton Property assessment");
        newWindow.show();
    }
}
