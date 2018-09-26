<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Spring Boot</title>

</head>

<body>

	<h1>Coffee List</h1>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	 <script>
	
   function getDataFromAPI(id) {
       $.ajax({
           url: "/detail/show/"+id,
           type: "POST",
           dataType:'json',
           success: 
        	  function (data) {
        	   var htmls='';
				 htmls+="<h1>Coffee List</h1>";
	       			htmls+="<table border='1'>";
	       			htmls+="<tr><th>이름</th><th>가격</th><th>재고</th><th>총 판매량</th><th>총 판매액</th><th>등록일</th><th>수정일</th><th>판매 shop 정보</th><th>수정</th></tr>";
					htmls+="<tr>";
					//htmls+="<td>"+data.id+"</td>";
					htmls+="<td>"+data.name+"</td>";
					htmls+="<td>"+data.price+"</td>";
					htmls+="<td>"+data.stock+"</td>";
					htmls+="<td>"+data.totSales+"</td>";
					htmls+="<td>"+data.price*data.totSales+"</td>";
					htmls+="<td>"+data.regDate+"</td>";
					htmls+="<td>"+data.moDate+"</td>";	
					htmls+="<td>------------</td>";
					htmls+="<td><a href='/modify/"+data.id+"'>수정</a></td>";

					htmls+="</tr>";
				
				htmls+="</table>";
				htmls+="<a href='/list'>돌아가기<br/></a>";

            	console.log("hello!");
            	 document.write(htmls);
 
           }, error: function (jqXHR, textStatus, errorThrown) {
           }
      });
   }
  
	window.onload = function(){
		var id=${id};
		getDataFromAPI(id);
	}
  </script>	
  
</body>



</html>