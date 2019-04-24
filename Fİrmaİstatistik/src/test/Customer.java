package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oguz
 */
public class Customer {
    public int CustomerID;
    private String Name;
    private String Surname;
    
    public Customer(){
        
    }
    public Customer(int CustomerID, String Name, String Surname){
        this.CustomerID=CustomerID;
        this.Name=Name;
        this.Surname=Surname;
    }
    public Customer(Customer x){
        this.CustomerID=x.CustomerID;
        this.Name=x.Name;
        this.Surname=x.Surname;
    }
    public String toString(){
        return CustomerID +" "+ Name +" "+ Surname+" ";
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }
    
}
