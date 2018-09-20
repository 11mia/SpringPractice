package com.example.coffee;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@RestController

public class CoffeeApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeApplication.class, args);
	}
	
	
	@Autowired
	CoffeeRepository coffeeRepo;
	
	public void run(String... args) throws Exception {
		String date   = new SimpleDateFormat("yyyyMMdd").format(new Date());
		coffeeRepo.save(new Coffee("Ame", 2000,35,3,date));
		coffeeRepo.save(new Coffee("Latte", 2500,20,19,date));
		coffeeRepo.save(new Coffee("Tea", 3000,22,9,date));

		Iterable<Coffee> coffeeList = coffeeRepo.findAll();
		
		System.out.println("findAll() Method.");
		for( Coffee m : coffeeList){
			System.out.println(m.toString());
		}
		
	}
	

	
	
	
}
