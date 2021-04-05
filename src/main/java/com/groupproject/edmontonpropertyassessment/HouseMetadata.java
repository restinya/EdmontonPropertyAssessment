/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupproject.edmontonpropertyassessment;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author neill
 */
public class HouseMetadata {
    private StringProperty accountNum;
    private StringProperty suite;
    private StringProperty houseNum;
    private StringProperty streetName;
    private StringProperty garageExist;
    private StringProperty nbhdNum;
    private StringProperty nbhdName;
    private StringProperty ward;
    private StringProperty assessedVal;
    private StringProperty formattedVal;
    private StringProperty latitude;
    private StringProperty longitude;
    private StringProperty pointLoc;
    private StringProperty assessmentClassP1;
    private StringProperty assessmentClassP2;
    private StringProperty assessmentClassP3;
    private StringProperty assessmentClass1;
    private StringProperty assessmentClass2;
    private StringProperty assessmentClass3;
    private StringProperty address;

    /**
     * HouseMetadata constructor given parameters.
     * @param accountNum
     * @param suite
     * @param houseNum
     * @param streetName
     * @param garageExist
     * @param nbhdNum
     * @param nbhdName
     * @param ward
     * @param assessedVal
     * @param latitude
     * @param longitude
     * @param pointLoc
     * @param assessmentClassP1
     * @param assessmentClassP2
     * @param assessmentClassP3
     * @param assessmentClass1
     * @param assessmentClass2
     * @param assessmentClass3 
     */
    public HouseMetadata(String accountNum, 
            String suite, 
            String houseNum, 
            String streetName, 
            String garageExist, 
            String nbhdNum, 
            String nbhdName, 
            String ward, 
            String assessedVal, 
            String latitude, 
            String longitude, 
            String pointLoc, 
            String assessmentClassP1, 
            String assessmentClassP2, 
            String assessmentClassP3, 
            String assessmentClass1, 
            String assessmentClass2, 
            String assessmentClass3) {
        this.accountNum = new SimpleStringProperty(accountNum);
        this.suite = new SimpleStringProperty(suite);
        this.houseNum = new SimpleStringProperty(houseNum);
        this.streetName = new SimpleStringProperty(streetName);
        this.garageExist = new SimpleStringProperty(garageExist);
        this.nbhdNum = new SimpleStringProperty(nbhdNum);
        this.nbhdName = new SimpleStringProperty(nbhdName);
        this.ward = new SimpleStringProperty(ward);
        this.assessedVal = new SimpleStringProperty(assessedVal);
        this.formattedVal = new SimpleStringProperty(
                FormatterUtils.valueFormatter(
                        (double)Integer.parseInt(this.assessedVal.get()
                        )
                )
        );
        this.latitude = new SimpleStringProperty(latitude);
        this.longitude = new SimpleStringProperty(longitude);
        this.pointLoc = new SimpleStringProperty(pointLoc);
        this.assessmentClassP1 = new SimpleStringProperty(assessmentClassP1);
        this.assessmentClassP2 = new SimpleStringProperty(assessmentClassP2);
        this.assessmentClassP3 = new SimpleStringProperty(assessmentClassP3);
        this.assessmentClass1 = new SimpleStringProperty(assessmentClass1);
        this.assessmentClass2 = new SimpleStringProperty(assessmentClass2);
        this.assessmentClass3 = new SimpleStringProperty(assessmentClass3);
        this.address = new SimpleStringProperty((this.suite.get() 
                + " " + this.houseNum.get() 
                + " " + this.streetName.get()).strip());
    }
    
    /**
     * HouseMetadata constructor for comma-delimited lines.
     * @param line 
     */
    public HouseMetadata (String line) {
    String[] houseInfo = line.split(",");
    
    this.accountNum = new SimpleStringProperty(houseInfo[0].strip());
    this.suite = new SimpleStringProperty(houseInfo[1].strip());
    this.houseNum = new SimpleStringProperty(houseInfo[2].strip());
    this.streetName = new SimpleStringProperty(houseInfo[3].strip());
    this.garageExist = new SimpleStringProperty(houseInfo[4].strip());
    this.nbhdNum = new SimpleStringProperty(houseInfo[5].strip());
    this.nbhdName = new SimpleStringProperty(houseInfo[6].strip());
    this.ward = new SimpleStringProperty(houseInfo[7].strip());
    this.assessedVal = new SimpleStringProperty(houseInfo[8].strip());
    this.formattedVal = new SimpleStringProperty(
        FormatterUtils.valueFormatter(
                (double)Integer.parseInt(this.assessedVal.get()
                )
        )
    );
    this.latitude = new SimpleStringProperty(houseInfo[9].strip());
    this.longitude = new SimpleStringProperty(houseInfo[10].strip());
    this.pointLoc = new SimpleStringProperty(houseInfo[11].strip());
    this.assessmentClassP1 = new SimpleStringProperty(houseInfo[12].strip());
    this.assessmentClassP2 = new SimpleStringProperty(houseInfo[13].strip());
    this.assessmentClassP3 = new SimpleStringProperty(houseInfo[14].strip());
    this.assessmentClass1 = new SimpleStringProperty(houseInfo[15].strip());
    if (houseInfo.length >= 17) this.assessmentClass2 = new SimpleStringProperty(houseInfo[16].strip());
    if (houseInfo.length >= 18) this.assessmentClass3 = new SimpleStringProperty(houseInfo[17].strip());
    this.address = new SimpleStringProperty((suite.get() + " "
            + houseNum.get() + " " 
            + streetName.get()).strip());
    }

    //=============================Getters======================================
    
    public String getAccountNum() {
        return accountNum.get();
    }

    public String getSuite() {
        return suite.get();
    }

    public String getHouseNum() {
        return houseNum.get();
    }

    public String getStreetName() {
        return streetName.get();
    }

    public String getGarageExist() {
        return garageExist.get();
    }

    public String getNbhdNum() {
        return nbhdNum.get();
    }

    public String getNbhdName() {
        return nbhdName.get();
    }

    public String getWard() {
        return ward.get();
    }

    public String getAssessedVal() {
        return assessedVal.get();
    }

    public String getFormattedVal() {
        return formattedVal.get();
    }

    public String getLatitude() {
        return latitude.get();
    }

    public String getLongitude() {
        return longitude.get();
    }

    public String getPointLoc() {
        return pointLoc.get();
    }

    public String getAssessmentClassP1() {
        return assessmentClassP1.get();
    }

    public String getAssessmentClassP2() {
        return assessmentClassP2.get();
    }

    public String getAssessmentClassP3() {
        return assessmentClassP3.get();
    }

    public String getAssessmentClass1() {
        return assessmentClass1.get();
    }

    public String getAssessmentClass2() {
        return assessmentClass2.get();
    }

    public String getAssessmentClass3() {
        return assessmentClass3.get();
    }

    public String getAddress() {
        return address.get();
    }
    
    public String getCSVLine() {
    String lineCopy = String.join(",",
            accountNum.get(),
            suite.get(),
            houseNum.get(),
            streetName.get(),
            garageExist.get(),
            nbhdNum.get(),
            nbhdName.get(),
            ward.get(),
            assessedVal.get(),
            latitude.get(),
            longitude.get(),
            pointLoc.get(),
            assessmentClassP1.get(),
            assessmentClassP2.get(),
            assessmentClassP3.get(),
            assessmentClass1.get(),
            assessmentClass2.get(),
            assessmentClass3.get());

    return lineCopy;
}
    //=============================Setters======================================

    public void setAccountNum(String accountNum) {
        this.accountNum = new SimpleStringProperty(accountNum);
    }

    public void setSuite(String suite) {
        this.suite = new SimpleStringProperty(suite);
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = new SimpleStringProperty(houseNum);
    }

    public void setStreetName(String streetName) {
        this.streetName = new SimpleStringProperty(streetName);
    }

    public void setGarageExist(String garageExist) {
        this.garageExist = new SimpleStringProperty(garageExist);
    }

    public void setNbhdNum(String nbhdNum) {
        this.nbhdNum = new SimpleStringProperty(nbhdNum);
    }

    public void setNbhdName(String nbhdName) {
        this.nbhdName = new SimpleStringProperty(nbhdName);
    }

    public void setWard(String ward) {
        this.ward = new SimpleStringProperty(ward);
    }

    public void setAssessedVal(String assessedVal) {
        this.assessedVal = new SimpleStringProperty(assessedVal);
    }

    public void setFormattedVal(String formattedVal) {
        this.formattedVal = new SimpleStringProperty(formattedVal);
    }

    public void setLatitude(String latitude) {
        this.latitude = new SimpleStringProperty(latitude);
    }

    public void setLongitude(String longitude) {
        this.longitude = new SimpleStringProperty(longitude);
    }

    public void setPointLoc(String pointLoc) {
        this.pointLoc = new SimpleStringProperty(pointLoc);
    }

    public void setAssessmentClassP1(String assessmentClassP1) {
        this.assessmentClassP1 = new SimpleStringProperty(assessmentClassP1);
    }

    public void setAssessmentClassP2(String assessmentClassP2) {
        this.assessmentClassP2 = new SimpleStringProperty(assessmentClassP2);
    }

    public void setAssessmentClassP3(String assessmentClassP3) {
        this.assessmentClassP3 = new SimpleStringProperty(assessmentClassP3);
    }

    public void setAssessmentClass1(String assessmentClass1) {
        this.assessmentClass1 = new SimpleStringProperty(assessmentClass1);
    }

    public void setAssessmentClass2(String assessmentClass2) {
        this.assessmentClass2 = new SimpleStringProperty(assessmentClass2);
    }

    public void setAssessmentClass3(String assessmentClass3) {
        this.assessmentClass3 = new SimpleStringProperty(assessmentClass3);
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }
    
}
