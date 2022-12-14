
package com.store.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class Product{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    private String location;
    private boolean expired;
    private int quantity;
    
    public Product(){
        super();
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
    return name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setLocation( String location){
        this.location =  location;
    }
    public String getLocation(){
        return location;
    }
    
    public void setExpired(boolean expired){
        this.expired = expired;
    }
    
    public boolean getExpired(){
        return expired;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", location=" + location + ", expired=" + expired + ", quantity=" + quantity + '}';
    }

   
    
    
    
}
