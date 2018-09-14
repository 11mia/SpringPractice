package com.example.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@RestController
public class CoffeeApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeApplication.class, args);
	}
	
	@Autowired
	CoffeeRepository coffeeRepo;
	
	public void run(String... args) throws Exception {

		coffeeRepo.save(new Coffee("Ame", 2000,35,3));
		coffeeRepo.save(new Coffee("Latte", 2500,20,19));
		coffeeRepo.save(new Coffee("Tea", 3000,22,9));

		Iterable<Coffee> coffeeList = coffeeRepo.findAll();
		
		System.out.println("findAll() Method.");
		for( Coffee m : coffeeList){
			System.out.println(m.toString());
		}
		
	}
	
	
	
	
	
	@RequestMapping(value="/getdata",method=RequestMethod.GET)
	public ResponseEntity<Object> getData(){
		Coffee coffee = new Coffee();
		coffee.setName("Ame");
		coffee.setPrice(3000);
		coffee.setStock(4);
		coffee.setTotSales(12000);
		return new ResponseEntity<Object>(coffee,HttpStatus.OK);
	}
	
	@RequestMapping(value="/postdata",method=RequestMethod.POST)
	public ResponseEntity<Object> postData(@RequestBody Coffee coffee){
		System.out.println("coffee.name : "+coffee.getName());
		System.out.println("coffee.price : "+coffee.getPrice());
		return new ResponseEntity<Object>("Success",HttpStatus.OK);
	}
}
