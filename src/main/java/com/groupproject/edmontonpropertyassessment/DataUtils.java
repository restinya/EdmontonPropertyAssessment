/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupproject.edmontonpropertyassessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * DataUtils is a utility class made for accessing, and organizing data.
 * @author neill
 */
public class DataUtils {
    /**
     * Opens a file
     * @param fileName
     * @return File that is valid
     */
    private static File openCSV(String fileName) {
        if (!fileName.endsWith(".csv")) {
            System.out.println("Error: Not a CSV file");
            return null;
        }
        
        File fileIn = new File(fileName);
        
        if (!fileIn.isFile()) {
            System.out.println("Error: File does not exist");
            return null;
        } 
        
        return fileIn;
    }
    
    /**
     * Constructs HouseMetadata List from file
     * @param fileIn
     * @return List<HouseMetadata> List of HouseMetadata structure
     */
    private static List<HouseMetadata> parseCSVLines(File fileIn) {
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
        
        return houses;
    }
    
    /**
     * Creates an ObservableList using a passed fileName
     * @param fileName
     * @return ObservableList<HouseMetadata> List to use with Tableview
     */
    public static ObservableList<HouseMetadata> constructList(String fileName) {
        File fileIn = openCSV(fileName);
        List<HouseMetadata> houses = parseCSVLines(fileIn);
        ObservableList<HouseMetadata> observeHouses = FXCollections.observableArrayList(houses);
        return observeHouses;
    }
    
    /**
     * Pulls assessed values from all houses in passed list
     * @param houses
     * @return List<Double> House values to do calculations on
     */
    public static List<Double> houseValues(List<HouseMetadata> houses) {
    List<Double> houseValues = new ArrayList<>();
    houses.forEach(house -> {
        houseValues.add((double)Integer.parseInt(house.getAssessedVal()));
        });
    return houseValues;
    }
    //Returns the count of values in the passed list.
    public static int n (List<Double> values){
        return values.size();
    }
    
    /**
     * Iterates through the list and returns the lowest value found.
     * @param values
     * @return double lowest val
     */
    public static double min (List<Double> values){
        double min = (double)values.get(0);
        for (int i=1; i<values.size(); i++){
            if (min > values.get(i)) {
                min = values.get(i);
            }
        }
        return min;
    }
    
    /**
     * Iterates through the list and returns the highest value found.
     * @param values
     * @return highest value
     */
    public static double max (List<Double> values){
        double max = (double)values.get(0);
        for (int i=1; i<values.size(); i++){
            if (max < values.get(i)) {
                max = values.get(i);
            }
        }
        return max;
    }
    
    /**
     * Takes the difference between the max and the min values.
     * @param values
     * @return double range between values
     */
    public static double range (List<Double> values){
        return max(values) - min(values);
    }
    
    /**
     * Takes the average of all values in the list by summing and dividing by n.
     * @param values
     * @return mean average of all values
     */
    public static double mean (List<Double> values){
        double sum  = sum(values);
        
        return sum/(double)values.size();
    }
    
    /**
     * Sums up all values in passed list
     * @param values
     * @return double summed value
     */
    public static double sum (List<Double> values) {
        double sum = 0.0;
        for (int i=0; i<values.size(); i++){
            sum += values.get(i);
        }
        
        return sum;
    }
    
    /**
     * Finds the standard deviation of the values in a list using a formula.
     * @param values
     * @return double calculated standard deviation
     */
    public static double stdev(List<Double> values){
        double mean = mean(values), n = n(values), std = 0.0;
        
        for (double val: values) {
            std += Math.pow(val - mean, 2);
        }
        std = Math.sqrt(std/n);
        
        return std;
    }
    
    /**
     * Finds the middle value in the list, takes the average between middle two if even.
     * @param values
     * @return double middle value
     */
    public static double median(List<Double> values){
        double n = n(values);
        int mid = (int)n/2;
        
        if (n%2==0) {
            return (values.get(mid)+values.get(mid+1))*0.5;
        }
        else {
            return values.get(mid);
        }
    }
}
