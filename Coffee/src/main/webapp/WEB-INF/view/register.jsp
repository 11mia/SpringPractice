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
   	이름 :
   <input type="text" name="coffee_name" id="coffee_name"/></br>
   가격 : 
   <input type="text" name="coffee_price" id="coffee_price"/></br>
   재고 : 
   <input type="text" name="coffee_stock" id="coffee_stock"/></br>
   <input type="button" name="getData" id="getData" value="등 록" onclick="getDataFromAPI();"/>
   <script>
   
   function getDataFromAPI() {
      var modelObj = {
            name: $("#coffee_name").val(),
            price: $("#coffee_price").val(),
            stock: $("#coffee_stock").val(),
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
		   alert("이름을 입력하세요.");
		   return;
	   }
	    
      $.ajax({
          type: "POST",
          url: "/getCoffee",
          headers: {
              "Content-Type": "application/json"
          },
          data: JSON.stringify(modelObj),
          success: function () {
        		  alert("등록되었습니다");
            	  location.href="/list";
          },
          error: function (jqXHR, textStatus, errorThrown) {
          }
      });
      
   }
   </script>
   </body>
</html>