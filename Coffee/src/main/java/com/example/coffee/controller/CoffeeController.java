package com.example.coffee.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffee.Coffee;
import com.example.coffee.CoffeeRepository;

@RestController
public class CoffeeController {

    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping("/coffee")
    public List<String> getCoffeeList() {
        return Arrays.asList("Americano", "Cappuccino", "Vanilla");
    }

    @GetMapping("/coffee/{name}")
    public Coffee getCoffeeDetail(@PathVariable String name) {
        Coffee coffee = coffeeRepository.findByName(name);
        return coffee;
    }

    @PostMapping("/coffee")
    public Coffee insertCoffee(@RequestBody Map<String, String> params) {
        String name = params.get("name");
        String price = params.get("price");
        Coffee coffee = new Coffee(name, Integer.valueOf(price));
        coffeeRepository.save(coffee);
        return coffee;
    }
    
    
    @RequestMapping("/jsp")
	public String jspPage(Model model){
		model.addAttribute("name","hello springBoot1234");
		return "hello";
	}
    

}