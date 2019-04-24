/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Oguz
 */
public class NationalCustomer extends Customer {
    
    private int LicencePlateNumber;
    private String Occupation;
    
    public NationalCustomer(){
        
    }
    public NationalCustomer(int LicencePlateNumber, String Occupation){
        this.LicencePlateNumber=LicencePlateNumber;
        this.Occupation=Occupation;
    }
    public NationalCustomer(NationalCustomer y){
        this.LicencePlateNumber=y.LicencePlateNumber;
        this.Occupation=y.Occupation;
    }
    public String toString(){
        return super.toString() + LicencePlateNumber +" "+ Occupation;
    }
    
    public int getLicencePlateNumber() {
        return LicencePlateNumber;
    }

    public void setLicencePlateNumber(int LicencePlateNumber) {
        this.LicencePlateNumber = LicencePlateNumber;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String Occupation) {
        this.Occupation = Occupation;
    }
    
}