package com.example.coffee;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.coffee.Constant.CoffeeConstant;


@Entity
@Table(name="coffee")
public class Coffee {
    //@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Id
	String totId;
	int id;
    String name;
    int price;
    int stock;
    int totSales;	//총 판매량
    String regDate;//등록일
    String moDate;//수정일
	char isDeleted;
	
 
    public Coffee() {
    }
    
    public Coffee(String name, int price, int stock,int totsales,String date) {	//등록용
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.totSales = totsales;
        this.isDeleted='n';
		this.regDate=this.moDate=date;
		this.id = CoffeeConstant.CID;
		this.totId = "999"+id;
    }
    
    public Coffee(String name, int price) {
        this.name = name;
        this.price = price;
        this.stock=1;
        this.totSales = 3;
        this.isDeleted='n';
        this.id = CoffeeConstant.CID;
		this.totId = "999"+id;
    }

    public String getTotId() {
		return totId;
	}

	public void setTotId(String totId) {
		this.totId = totId;
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
    
    public void setTotSales(int totsales){
    	this.totSales = totsales;
    }
    public int getTotSales(){
    	return totSales;
    }
    public void setRegDate(String date) {
        this.regDate = date;
    }
    public String getRegDate() {
        return regDate;
    }
    public void setMoDate(String date) {
        this.moDate = date;
    }
    public String getMoDate() {
        return moDate;
    }
    public void setId(int id){
    	this.id = id;
    }
    public int getId(){
    	return id;
    }
    public char getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(char isDeleted) {
		this.isDeleted = isDeleted;
	}
}