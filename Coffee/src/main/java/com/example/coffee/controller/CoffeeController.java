package com.example.coffee.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

//@RestController
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
		List<Coffee> list = coffeeRepository.findNotDeleted();
		return new ResponseEntity<Object>(list,HttpStatus.OK);
	}
	
	
	
	@RequestMapping("/detail/{id}")
	public String goDetail(@PathVariable int id,Model model){
		model.addAttribute("id",id);
		return "detail";
	}

	@RequestMapping(value="/detail/show/{id}")
	@ResponseBody
	public ResponseEntity<Object> detail(@PathVariable int id){
		Coffee coffee = coffeeRepository.findById(id);
		return new ResponseEntity<Object>(coffee,HttpStatus.OK);
	}
	

	
	@RequestMapping("/delete/{id}")
	//@ResponseBody()
	public String goDelete(@PathVariable int id,Model model){
		Coffee deleteCoffee = coffeeRepository.findById(id);
		deleteCoffee.setIsDeleted('y');
		coffeeRepository.save(deleteCoffee);
		coffeeRepository.flush();
		return "delete";
		//JOptionPane.showMessageDialog(null, "삭제되었습니다.");
		//return "list";
	}
	
	@RequestMapping("/register")
	public String goRegister(Model model){
		return "register";	
	}
	
	@RequestMapping("/getCoffee")
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
		coffeeRepository.save(coffee);	//여기서 문제! java.sql.SQLException: Incorrect string value: '\xE3\x84\xB7\xE3\x84\xB7...' for column 'name' at row 1
		System.out.println("CoffeeController-save");
		
		return;
	}
	
	@RequestMapping("/modify/{id}")
	public String goModify(@PathVariable int id,Model model){
		Coffee modifyCoffee = coffeeRepository.findById(id);
		System.out.println(modifyCoffee.getName());
		model.addAttribute("coffee",modifyCoffee);
		return "modify";
	}
	
	@RequestMapping("/modifyCoffee")
	@ResponseBody
	public void modifyData(@RequestBody Coffee coffee){
		String date   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		Coffee original = coffeeRepository.findById(coffee.getId());
		String newName = coffee.getName();
		original.setName(newName);
		original.setPrice(coffee.getPrice());
		original.setStock(coffee.getStock());
		original.setMoDate(date);
		coffeeRepository.save(original);
		return;
	}
	

}
