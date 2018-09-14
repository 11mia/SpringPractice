package com.example.coffee.Controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffee.Coffee;
import com.example.coffee.CoffeeRepository;


/*

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
    
    
    @PostMapping("/jsp")
    //@RequestMapping("/jsp")
	public String jspPage(Model model){
		model.addAttribute("name","��Ƥ��ƾƤ�");
		return "hello1111";
	}
    

}
*/

/*
@Controller
public class CoffeeController{
	@RequestMapping(value="/index")
	public String indexPage(){
		return  "index";
	}
*/
///*
//@Controller

//@RequestMapping("/coffee")
@RestController
//@RequestMapping(value="/",method = {RequestMethod.GET, RequestMethod.POST})
public class CoffeeController {

	@Autowired
	private CoffeeRepository coffeeDao;

	

	//@RequestMapping("/list")
	//@ResponseBody
	@RequestMapping("/list")
	public ArrayList<Coffee> list(Model model){

		ArrayList<Coffee> coffeelist = (ArrayList<Coffee>) coffeeRepository.findAll();
		model.addAttribute("list",coffeelist);
		//return "coffee/list";
		return coffeelist;

	}
	 @Autowired
	    CoffeeRepository coffeeRepository;
	@PostMapping("/coffee")
    public Coffee insertCoffee(@RequestBody Map<String, String> params) {
        String name = params.get("name");
        String price = params.get("price");
        Coffee coffee = new Coffee(name, Integer.valueOf(price));
        coffeeRepository.save(coffee);
        return coffee;
    }

    @GetMapping("/coffee")
    public List<String> getCoffeeList() {
        return Arrays.asList("Americano", "Cappuccino", "Vanilla");
    }

}
//*/