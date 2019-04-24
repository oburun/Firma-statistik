/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oguz
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static Customer[] Customers = new Customer[200];
    public static int[][] ratings = new int[200][5];
    public static String[] ürünler = {"A","B","C","D","E"};
    public static double[] ortalamalar = new double[5];
    public static int nationalcount=0;
    public static int internationalcount=0;
    public static int customercount=0;
    public static int customerFileCount=0;
    
    public static void main(String[] args) throws IOException {
        okuAta();
        ürünOrt();
        nationalOrt();
        internationalOrt();
        doctorOrt();
        nationalLow();
        internationalLow();
        ekle();
        yazdir();
    }

    /**
     *
     */
    public static void okuAta() throws IOException{
        File f = new File("Firma.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        String satir=br.readLine();
        satir=br.readLine();
        int i=1;
        int j=1;
        int k=0;
        int n=0;
        int c=1;
        String x;
        while(satir!=null){
            StringTokenizer st=new StringTokenizer(satir,",");
            while(st.hasMoreTokens()){
                x=st.nextToken();
                if(i%2!=0){
                    customercount++;
                    customerFileCount++;
                    if("n".equals(x)){
                        Customers[c-1]= new NationalCustomer();
                        nationalcount++;
                        n=1;
                    }
                    if("i".equals(x)){
                        Customers[c-1]= new InternationalCustomer();
                        internationalcount++;
                    }
                    if(j==2){
                        Customers[c-1].setCustomerID(Integer.parseInt(x));
                    }
                    if(j==3){
                        Customers[c-1].setName(x);
                    }
                    if(j==4){
                        Customers[c-1].setSurname(x);
                    }
                    if(j==5){
                        if(n==1){
                            ((NationalCustomer)Customers[c-1]).setLicencePlateNumber(Integer.parseInt(x));
                        }else{
                            ((InternationalCustomer)Customers[c-1]).setCountry(x);
                        }
                    }
                    if(j==6){
                        if(n==1){
                            ((NationalCustomer)Customers[c-1]).setOccupation(x);
                        }else{
                            ((InternationalCustomer)Customers[c-1]).setCity(x);
                        }
                    }
                }
                else{
                    ratings[k][j-1]=Integer.parseInt(x);
                }
                j++;
            }
            j=1;
            n=0;
            if(i%2==0){
                k++;
            }else{
                c++;
            }
            i++;
            satir=br.readLine();
        }
        }
        
    public static void ürünOrt(){
        int i=0;
        int j=0;
        int k=0;
        double toplam=0;
        double ortalama=0;
        System.out.println("ORTALAMALAR");
        while(k<5){
            while(ratings[i][0]!=0){
                toplam+=ratings[i][j];
                i++;
            }
            ortalama=(double)(toplam/i);
            System.out.println(ürünler[k]+":"+ortalama);
            ortalamalar[j]=ortalama;
            j++;
            i=0;
            k++;
            toplam=0;
            ortalama=0;
        }
    }
    public static void nationalOrt(){
        double total=0;
        int i=0;
        int j=0;
        int k=0;
        System.out.println("ULUSAL ORTALAMA");
        while(j<5){
            while(ratings[i][0]!=0){
                if(isNational(Customers[i])==true){
                    total+=ratings[i][j];
                }
                i++;
            }
            System.out.println(ürünler[k]+" : "+(double)(total/nationalcount));
            total=0;
            j++;
            i=0;
            k++;
        }
    }public static void internationalOrt(){
        double total=0;
        int i=0;
        int j=0;
        int k=0;
        System.out.println("ULUSLARARASI ORTALAMA");
        while(j<5){
            while(ratings[i][0]!=0){
                if(isInternational(Customers[i])==true){
                    total+=ratings[i][j];
                }
                i++;
            }
            System.out.println(ürünler[k]+" : "+(double)(total/internationalcount));
            total=0;
            j++;
            i=0;
            k++;
        }
    }
    public static void doctorOrt(){
        int doctorCount=0;
        int i=0;
        int j=0;
        int k=0;
        int total=0;
        System.out.println("DOKTOR ORTALAMA");
        while(j<5){
            while(ratings[i][j]!=0){
            if(isNational(Customers[i])==true){
                        if(isDoctor(Customers[i])==true){
                            total+=ratings[i][j];
                            doctorCount++;
                        }
                    }
                i++;
            }
            System.out.println(ürünler[k]+" : "+(double)(total/doctorCount));
            j++;
            i=0;
            total=0;
            doctorCount=0;
        }
    }
    public static boolean isNational(Customer x){
        return x instanceof NationalCustomer;
    }
    public static boolean isInternational(Customer x){
        return x instanceof InternationalCustomer;
    }
    public static boolean isDoctor(Customer x){
        return "Doktor".equals(((NationalCustomer)x).getOccupation());
    }
    public static void nationalLow(){
        int i=0;
        int j=0;
        while(j<5){
            System.out.println(ürünler[j]+" ÜRÜNÜ İÇİN ORTALAMANIN ALTINDA PUAN VEREN ULUSAL MÜSTERİLER");
            while(ratings[i][j]!=0){
                if(ratings[i][j]<ortalamalar[j]){
                    if(isNational(Customers[i])==true){
                        System.out.println(Customers[i].toString());
                    }
                }
                i++;
            }
            i=0;
            j++;
        }
    }
    public static void internationalLow(){
        int i=0;
        int j=0;
        while(j<5){
            System.out.println(ürünler[j]+" ÜRÜNÜ İÇİN ORTALAMANIN ALTINDA PUAN VEREN ULUSLARASI MÜSTERİLER");
            while(ratings[i][j]!=0){
                if(ratings[i][j]<ortalamalar[j]){
                    if(isInternational(Customers[i])==true){
                        System.out.println(Customers[i].toString());
                    }
                }
                i++;
            }
            i=0;
            j++;
        }
    }
    public static void ekle(){
        Scanner scan = new Scanner(System.in);
        int x=1;
        String t,name,surname,occupation,country,city;
        int id,licence;
        int j=0;
        int[] puan=new int[4];
        while(customercount<201 || x==1){
            System.out.println("Müsteri girisi yapmak istemiyorsaniz 0 a istiyorsanız 1 e basiniz");
            x=scan.nextInt();
            if(x==0){
                break;
            }
            System.out.println("National(n) or International(i)");
            t=scan.next();
            if("n".equals(t)){
                Customers[customercount-1]=new NationalCustomer();
            }else{
                Customers[customercount-1]=new InternationalCustomer();
            }
            System.out.println("ID giriniz");
            id=scan.nextInt();
            Customers[customercount-1].setCustomerID(id);
            System.out.println("isim giriniz");
            name=scan.next();
            Customers[customercount-1].setName(name);
            System.out.println("soy isim giriniz");
            surname=scan.next();
            Customers[customercount-1].setSurname(surname);
            if("n".equals(t)){
                System.out.println("il trafik plaka kodunu giriniz");
                licence=scan.nextInt();
                ((NationalCustomer)Customers[customercount-1]).setLicencePlateNumber(licence);
                System.out.println("meslek giriniz");
                occupation=scan.next();
                ((NationalCustomer)Customers[customercount-1]).setOccupation(occupation);
            }else{
                System.out.println("ulke giriniz");
                country=scan.next();
                ((InternationalCustomer)Customers[customercount-1]).setCountry(country);
                System.out.println("sehir giriniz");
                city=scan.next();
                ((InternationalCustomer)Customers[customercount-1]).setCity(city);
            }
            while(j<4){
                System.out.println(ürünler[j]+" ürünü icin puan giriniz");
                puan[j]=scan.nextInt();
                ratings[customercount][j]=puan[j];
                j++;
            }
            j=0;
            int i=0;
            double k;
            double total=0;
            double min;
            double minCount=0;
            double ort;
            int ortInt;
            k=Math.abs((double)puan[0]-ratings[i][0])+Math.abs((double)puan[1]-ratings[i][1])+Math.abs((double)puan[2]-ratings[i][2])+Math.abs((double)puan[3]-ratings[i][3]);
            min=k;
            i++;
            while(ratings[i][0]!=0){
                k=Math.abs((double)puan[0]-ratings[i][0])+Math.abs((double)puan[1]-ratings[i][1])+Math.abs((double)puan[2]-ratings[i][2])+Math.abs((double)puan[3]-ratings[i][3]);
                
                if(k<=min){
                    if(k==min){
                        total+=ratings[i][4];
                        minCount++;
                    }
                    if(k<min){
                        min=k;
                        total=0;
                        total+=ratings[i][4];
                        minCount=1;
                    }
                }
                i++;
            }
            ratings[customercount][4]=(int) ((int)total/minCount);
        }
    }
    public static void yazdir(){
        int n=customerFileCount-1;
        int i=customerFileCount-1;
        while(Customers[n]!=null){
            System.out.println(Customers[n].toString());
            n++;
        }
        int k=0;
        double toplam=0;
        double ortalama=0;
        System.out.println("ORTALAMALAR");
        while(k<5){
            while(ratings[i][0]!=0){
                toplam+=ratings[i][k];
                i++;
            }
            ortalama=(double)(toplam/(i-(customerFileCount-1)));
            System.out.println(ürünler[k]+":"+ortalama);
            i=customerFileCount-1;
            k++;
            toplam=0;
            ortalama=0;
        }
    }
        

}
