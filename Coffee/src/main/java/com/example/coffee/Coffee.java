package com.example.coffee;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coffee")
public class Coffee {
    @Id
    String name;
    int price;
    int stock;
    int tot_sales;	//ÃÑ ÆÇ¸Å·®
    //int tot_sales;	//ÃÑ ÆÇ¸Å¾×

    public Coffee() {
    }

    public Coffee(String name, int price, int stock,int tot_sales) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.tot_sales = tot_sales;
        
    }
    public Coffee(String name, int price) {
        this.name = name;
        this.price = price;
        this.stock=1;
        this.tot_sales = 3;
        
        
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
    public void setStock(int stock){
    	this.stock = stock;
    }
    public int getStock(){
    	return stock;
    }
    
    public void setTotSales(int tot_sales){
    	this.tot_sales = tot_sales;
    }
    public int getTotSales(){
    	return tot_sales;
    }
}