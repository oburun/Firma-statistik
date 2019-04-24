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
public class InternationalCustomer extends Customer{
    private String Country;
    private String City;
    
    public InternationalCustomer(){
    
    }
    public InternationalCustomer(String Country, String City){
        this.Country=Country;
        this.City=City;
    }
    public InternationalCustomer(InternationalCustomer z){
        this.Country=z.Country;
        this.City=z.City;
    }
    
    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
    public String toString(){
        return super.toString() + Country +" "+City;
    }
}
