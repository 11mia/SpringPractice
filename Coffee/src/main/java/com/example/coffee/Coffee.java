package com.example.coffee;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.coffee.Constant.CoffeeConstant;


@Entity
@Table(name="coffee")
public class Coffee {
    //@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Id
	String totid;
	
	//@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
    String name;
    int price;
    int stock;
    int totSales;	//총 판매량
    String regDate;//등록일
    String moDate;//수정일
	char isdeleted;
	int totSalesAmt;//총 매출액
	
 
    public Coffee() {
    }
    
    public Coffee(String name, int price, int stock,int totsales,String date) {	//등록용
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.totSales = totsales;
        this.isdeleted='n';
		this.regDate=this.moDate=date;
		this.id = ++CoffeeConstant.CID;
		this.totid = "999"+id;
		this.totSalesAmt=0;
    }
    public Coffee(String name,int price,int stock,String date,String totid,int id){	//샵등록시 사용
    	this.name = name;
    	this.price = price;
    	this.stock=stock;
    	this.totSales = 0;
        this.isdeleted='n';
 		this.regDate=this.moDate=date;
 		this.id = id;
 		this.totid = totid;
 		this.totSalesAmt=0;
    }
    public Coffee(String name, int price) {
		String date   = new SimpleDateFormat("yyyyMMdd").format(new Date());
		this.regDate=this.moDate=date;
        this.name = name;
        this.price = price;
        this.stock=10;
        this.totSales=this.totSalesAmt=0;
        this.isdeleted='n';
		this.id = ++CoffeeConstant.CID;

		this.totid = "999"+id;
    }

    public String getTotId() {
		return totid;
	}

	public void setTotId(String totId) {
		this.totid = totId;
	}

	public String getTotid() {
		return totid;
	}

	public void setTotid(String totid) {
		this.totid = totid;
	}

	public char getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(char isdeleted) {
		this.isdeleted = isdeleted;
	}

	public int getTotSalesAmt() {
		return totSalesAmt;
	}

	public void setTotSalesAmt(int totSalesAmt) {
		this.totSalesAmt = totSalesAmt;
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
		return isdeleted;
	}

	public void setIsDeleted(char isDeleted) {
		this.isdeleted = isDeleted;
	}
	
	public int getShopId(String totId){
		int sid = Integer.parseInt(totId.substring(0, 3));
		return sid;
	}
	public int getCoffeeId(String totId){
		int cid = Integer.parseInt(totId.substring(3));
		return cid;
	}
}