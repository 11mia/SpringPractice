package com.example.coffee.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.coffee.Coffee;
import com.example.coffee.CoffeeRepository;
import com.example.coffee.Constant.CoffeeConstant;

@CrossOrigin(origins={"*"})
@Controller
public class CoffeeController {

	@Autowired
    CoffeeRepository coffeeRepository;
	
	@RequestMapping("/list")
	public String goList(Model model){
		return "list";	//list.jsp파일로 이동해서 ajax안에서 밑에 list함수가 불리면서 출력된다.
	}
	@RequestMapping("/index")
	public String goIndex(){
		return "index";	
	}
	
	@RequestMapping(value="/list/show")
	public ResponseEntity<Object> list(){
		List<Coffee> list = coffeeRepository.findByTotidStartingWithAndIsdeletedLike("999",'n');
		return new ResponseEntity<Object>(list,HttpStatus.OK);
	}
	
	
	

	@RequestMapping(value="/list/sorting")
	public ResponseEntity<Object> sortedlist(){
		//List<Coffee> coffee = coffeeRepository.findAll(new Sort(Sort.Direction.DESC,"name"));
		//List<Coffee> coffee = coffeeRepository.findByIsdeletedLike('n',new Sort(Sort.Direction.DESC,"name"));
		List<Coffee> coffee = coffeeRepository.findByTotidStartingWithAndIsdeletedLike("999",'n',new Sort(Sort.Direction.DESC,"name"));
		return new ResponseEntity<Object>(coffee,HttpStatus.OK);
	}

	
	@RequestMapping("/detail/{id}")
	public String goDetail(@PathVariable int id,Model model){
		model.addAttribute("id",id);
		return "detail";
	}

	@RequestMapping(value="/detail/show/{id}")
	@ResponseBody
	public ResponseEntity<Object> detail(@PathVariable int id){
		String tId = "999"+id;
		Coffee coffee = coffeeRepository.findByTotid(tId);
		return new ResponseEntity<Object>(coffee,HttpStatus.OK);
	}
	

	
	@RequestMapping("/delete/{id}")
	public String goDelete(@PathVariable int id,Model model){
		List<Coffee> deleteCoffee = coffeeRepository.findByTotidEndingWith(Integer.toString(id));
		for(int i=0;i<deleteCoffee.size();i++){
			Coffee coffee = deleteCoffee.get(i);
			coffee.setIsDeleted('y');
			coffeeRepository.save(coffee);
			coffeeRepository.flush();
		}
		return "delete";
	}
	
	@RequestMapping("/register")
	public String goRegister(Model model){
		return "register";	
	}
	
	@RequestMapping("/getCoffee")
	@ResponseBody
	public String getData(@RequestBody Coffee coffee){
		int cid = ++CoffeeConstant.CID;
		String totid = "999"+cid;
		String date   = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String name = coffee.getName();
		int stock = coffee.getStock();
		int price = coffee.getPrice();
		Coffee newCoffee = new Coffee(name,price,stock,0,date);
		
		
		coffeeRepository.save(newCoffee);	//여기서 문제! java.sql.SQLException: Incorrect string value: '\xE3\x84\xB7\xE3\x84\xB7...' for column 'name' at row 1
		System.out.println("CoffeeController-save");
		return "list";
	}
/*	@RequestMapping("/getCoffee")
	@ResponseBody
	public void getData(@RequestBody Coffee coffee){
		System.out.println("coffeeController-getCoffee");
		String date   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		coffee.setMoDate(date);
		System.out.println("CoffeeController-setModate");
		coffee.setRegDate(date);
		System.out.println("CoffeeController-setRegDate");
		coffee.setIsDeleted('n');
		System.out.println("CoffeeController-setIsDeleted");
		coffeeRepository.save(coffee);	//�뿬湲곗꽌 臾몄젣! java.sql.SQLException: Incorrect string value: '\xE3\x84\xB7\xE3\x84\xB7...' for column 'name' at row 1
		System.out.println("CoffeeController-save");
		return;
	}
	*/
	
	@RequestMapping("/modify/{id}")
	public String goModify(@PathVariable int id,Model model){
		Coffee modifyCoffee = coffeeRepository.findByTotid("999"+id);
		System.out.println(modifyCoffee.getName());
		model.addAttribute("coffee",modifyCoffee);
		return "modify";
	}
	
	@RequestMapping("/modifyCoffee")
	@ResponseBody
	public void modifyData(@RequestBody Coffee coffee){
		String date   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		Coffee original = coffeeRepository.findByTotid("999"+coffee.getId());
		String newName = coffee.getName();
		original.setName(newName);
		original.setPrice(coffee.getPrice());
		original.setStock(coffee.getStock());
		original.setMoDate(date);
		coffeeRepository.save(original);
		return;
	}
	
	@RequestMapping("/getShopId/{cid}")
	@ResponseBody
	public String getShopSid(@PathVariable String cid){
		List<Coffee> list = coffeeRepository.findByTotidEndingWith(cid);
		String str = "";
		for(int i=0;i<list.size();i++){
			Coffee coffee = list.get(i);
			String totId = coffee.getTotId();
			String sid = Integer.toString(coffee.getShopId(totId));
			if(!sid.equals("999")){
				str = str + sid;
				//str = str+",";
			}
		}
		System.out.println(str);
		return str;
	}

}
