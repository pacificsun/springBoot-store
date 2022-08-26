
package com.store.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SALES")
public class Sales {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int uId;
    private int pId;
    private int quantity;
    private double taxAmount;
    private double totalAmount;
    

    public Sales(){
     super();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

   
    
    
    
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "Sales{" + "id=" + id + ", uId=" + uId + ", pId=" + pId + ", quantity=" + quantity + ", taxAmount=" + taxAmount + ", totalAmount=" + totalAmount + '}';
    }

    


    
    
}
