/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupproject.edmontonpropertyassessment;

import java.text.NumberFormat;

/**
 * FormatterUtils is a utility class for the purpose of formatting strings.
 * @author neill
 */
public class FormatterUtils {
    
    /**
     * valueFormatter takes in a double and returns it as a formatted dollar
     * string.
     * @param value double
     * @return formatted value string
     */
    public static String valueFormatter(double value) {
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        defaultFormat.setMaximumFractionDigits(0);
        String formattedValue = defaultFormat.format(value);
        
        return formattedValue;
    }
}
