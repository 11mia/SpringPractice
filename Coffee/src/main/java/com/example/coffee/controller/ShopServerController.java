package com.example.coffee.Controller;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.coffee.Coffee;
import com.example.coffee.CoffeeRepository;

@CrossOrigin(origins={"*"})
@Controller
public class ShopServerController {
	@Autowired
    CoffeeRepository coffeeRepository;
	
	@RequestMapping(value="/getCoffeeNameId")
	@ResponseBody
	public ResponseEntity<Object> coffeeNameId(){	
		List<Coffee> list = coffeeRepository.findByTotidStartingWithAndIsdeletedLike("999", 'n');
		return new ResponseEntity<Object>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getShopInfo")	//샵 등록
	@ResponseBody
	public void RegisterShop(@RequestBody String ShopInfo){
		String str = ShopInfo.replaceAll("^\"|\"$", "");
		String[] idArr=str.split(",");
		for(int i=1;i<idArr.length;i++){
			int cid = Integer.parseInt(idArr[i].substring(3));
			Coffee coffee = coffeeRepository.findByTotid("999"+cid);
			String date   = new SimpleDateFormat("yyyyMMdd").format(new Date());
			String name = coffee.getName();
			int stock = 0;
			int price = coffee.getPrice();
			Coffee newCoffee = new Coffee(name,price,stock,date,idArr[i],cid);
			coffeeRepository.save(newCoffee);
		}
		return;
	}
	
	@RequestMapping(value = "/CoffeeList")
	@ResponseBody
	public ResponseEntity<Object> CoffeeList(){
		List<Coffee> list = coffeeRepository.findAll();
		return new ResponseEntity<Object>(list,HttpStatus.OK);
	}
	
	
	@RequestMapping("/ShopDel/{sid}")	//샵 삭제한경우
	@ResponseBody
	public void shopDelete(@PathVariable int sid){
		List<Coffee> list = coffeeRepository.findByTotidStartingWith(""+sid);
		for(int i=0;i<list.size();i++){
			Coffee coffee = list.get(i);
			coffee.setIsdeleted('y');
			coffeeRepository.save(coffee);
		}
		return;
	}
	
	@RequestMapping("/modShop")
	@ResponseBody
	public void modShop(@RequestBody String str){
		String repstr = str.replaceAll("^\"|\"$", "");
		String[] idArr=repstr.split(",");
		//0에는 name,1부터는 totid
		for(int i=1;i<idArr.length;i++){
			if(coffeeRepository.findByTotid(idArr[i])==null){
				int cid = Integer.parseInt(idArr[i].substring(3));
				Coffee originalCoffee = coffeeRepository.findByTotid("999"+cid);
				String date   = new SimpleDateFormat("yyyyMMdd").format(new Date());
				int price = originalCoffee.getPrice();
				String name = originalCoffee.getName();
				Coffee coffee = new Coffee(name,price,0,date,idArr[i],cid);
				coffeeRepository.save(coffee);
			}
		}//새로운 판매 커피 추가
		//더이상 판매하지 않을 커피 삭제(isDelted를 'y'로)
		String sid = idArr[1].substring(0,3);
		List<Coffee> list = coffeeRepository.findByTotidStartingWithAndIsdeletedLike(sid, 'n');
		for(int i=0;i<list.size();i++){
			Coffee coffee = list.get(i);
			String totid = coffee.getTotid();
			if(Arrays.asList(idArr).contains(totid)==false){
				coffee.setIsdeleted('y');
				coffeeRepository.save(coffee);
			}
		}
	}
	
	
	@RequestMapping("/sid")
	@ResponseBody
	public String sendShopInfo(@RequestBody String sid){
		String shopinfo="";
		List<Coffee> list = coffeeRepository.findByTotidStartingWith(sid);	//샵의 판매 커피리스트
		for(int i=0;i<list.size();i++){	
			Coffee shopCoffee = list.get(i);//샵에서 판매하는 커피 하나
			int cid = shopCoffee.getId();
			Coffee originalCoffee = coffeeRepository.findByTotid("999"+cid);
			int stock = originalCoffee.getStock();
			int price = originalCoffee.getPrice();
			String name = originalCoffee.getName();
			shopinfo = shopinfo + cid + ","+ name + "," + price + ","+ stock+",";
		}
		System.out.println(shopinfo);
		return shopinfo;
	}
	
	
	
	
	@RequestMapping("/SaleInfo")
	@ResponseBody
	public int AfterCoffeeSale(@RequestBody String str){
		if(str==null)return -1;
		int sum=0;
		System.out.println(str);
		String substr = str.replaceAll("^\"|\"$", "");
		System.out.println(substr);
		String[] info=substr.split(",");//0,2,4~에는 totid, 1,3,5~~에는 개수
		for(int i=0;i<info.length;i=i+1){
			Coffee coffee = coffeeRepository.findByTotid(info[i]);	//샵의 커피 하나
			int amt = Integer.parseInt(info[++i]);	//판매 개수
			int sale = coffee.getTotSales() + amt;	//판매량+판매개수 
			coffee.setTotSales(sale);
			
			
			//더미에 업데이트(판매량, 판매액 ,재고)
			int cid = coffee.getId();
			Coffee originalCoffee = coffeeRepository.findByTotid("999"+cid);
			int stock = originalCoffee.getStock();
			stock-=amt;
			originalCoffee.setStock(stock);
			
			int coffeeSale = originalCoffee.getTotSales();	//판매량
			coffeeSale += amt;
			originalCoffee.setTotSales(coffeeSale);
			
			int coffeeSaleAmt= originalCoffee.getTotSalesAmt();//원래 판매금액-더미메뉴
			int price = originalCoffee.getPrice();	//dummy의 가격
			coffeeSaleAmt += price*amt;
			originalCoffee.setTotSalesAmt(coffeeSaleAmt);
			
			int sales_amt = coffee.getTotSalesAmt();	//원래 판매금액-샵의 메뉴
			sales_amt += price*amt;//판매액 업데이트
			coffee.setTotSalesAmt(sales_amt);
			coffeeRepository.save(coffee);
			
			coffeeRepository.save(originalCoffee);
			sum+=price*amt;
		}
		
		
		return sum;
	}

}
