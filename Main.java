/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DPAT;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author szesin
 */
public class Main {

    public static void main(String[] args) {   

        Payment firstCustomer = new CreditCard();
        
        //Checking strategy in overwritten
        String normal="normal";
        String promotion = "promotion";
        // Normal billing
        firstCustomer.add(100,normal);

        // Start Promotion
        firstCustomer.add(100, promotion);

        // New Customer
        Payment secondCustomer = new Paypal();
        secondCustomer.add(80, promotion);
        // The Customer pays
        firstCustomer.printPrice();

        // End Promotion
        secondCustomer.add(130, normal);
        secondCustomer.printPrice();
        
    }
}

abstract class Payment {
   
    public void add(int price, String strategy) {
        System.out.println("Default price");
    }

    // Payment of bill
    public void printPrice() {
        System.out.println("Default bill");
    }
}

class CreditCard extends Payment{
    private final List<Integer> price = new ArrayList<>();

    
    @Override
    public void add(int price, String strategy) {
        String promotion="promotion";
        if(promotion.equals(strategy)){
            this.price.add(price/2);
        }
        else
            this.price.add(price);
        
    }
    
    @Override
    public void printPrice() {
        System.out.println("Credit Card: ");
        int sum = this.price.stream().mapToInt(v -> v).sum();
        System.out.println("Total due: " + sum / 100.0);
        this.price.clear();
    }
}

class Paypal extends Payment{
    private final List<Integer> price1 = new ArrayList<>();
 
    @Override
    public void add(int price, String strategy) {
         String promotion="promotion";
        if(promotion.equals(strategy)){
            this.price1.add(price/2);
        }
        else
            this.price1.add(price);
        //System.out.println(price1);
    }
    
    @Override
    public void printPrice() {
        System.out.println("Paypal");
        int sum = this.price1.stream().mapToInt(v -> v).sum();
        System.out.println("Total due: " + sum / 100.0);
        this.price1.clear();
    }
}

