<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8"/>
      <title>Demo Application</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   </head>
   <body>   
   
   <script>
   function initiate(){
	   var html='';
	   html+="이름 : <input type='text' name='coffee_name' id='coffee_name' value='${coffee.name}'/></br>";
	   html+="가격 : <input type='text' name='coffee_price' id='coffee_price' value='${coffee.price}'/></br>";
	   html+="재고 : <input type='text' name='coffee_stock' id='coffee_stock' value='${coffee.stock}'/></br>";
	html+="<input type='button' name='getData' id='getData' value='수 정' onclick='getDataFromAPI();'/>";
	document.write(html);

   }
   
   function getDataFromAPI() {
	      var modelObj = {
	            name: $("#coffee_name").val(),
	            price: $("#coffee_price").val(),
	            stock: $("#coffee_stock").val(),
	            id:${coffee.id}
	      };
	      if(modelObj.stock<0){
			  alert("재고는 음수일 수 없습니다. 다시 입력하세요.");
			  return;
		   }
		   if(modelObj.price<0){
				  alert("가격은 음수일 수 없습니다. 다시 입력하세요.");
				  return;
		   }
		   if(modelObj.name==''){
			   alert("수정할 이름을 입력하세요.");
			   return;
		   }
		    
	      $.ajax({
	          type: "POST",
	          url: "/modifyCoffee",
	          headers: {
	              "Content-Type": "application/json"
	          },
	          data: JSON.stringify(modelObj),
	          success: function () {
	          	 alert("수정되었습니다");
	        	  location.href="/detail/"+${coffee.id};
	          },
	          error: function (jqXHR, textStatus, errorThrown) {
	          }
	      });
	      
	   }
   
   
   
   window.onload = function(){
	  initiate();
	}
   </script>
   </body>
</html>