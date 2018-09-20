
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
   function getDataFromAPI() {
       $.ajax({
           url: "/list/show",
           type: "GET",
           dataType:'json',
           success: 
        	  function (data) {
				 var htmls='';
				 htmls+="<h1>Coffee List</h1>";
	       			htmls+="<table border='1'>";
	       			htmls+="<tr><th>이름</th>	<th>등록일</th><th>판매하는 shop</th><th>상세 정보</th><th>삭제</th></tr>";
				$.each(data,function(){
					//if(this['isDeleted']!='y'){
						htmls+="<tr>";
						//htmls+="<td>"+this['id']+"</td>";
						htmls+="<td>"+this['name']+"</td>";
						htmls+="<td>"+this['regDate']+"</td>";
						htmls+="<td>------------</td>";
						htmls+="<td><a href='/detail/"+this['id']+"'>상세 정보</a></td>";
						htmls+="<td><a href='/delete/"+this['id']+"''>삭제</a></td>";
						htmls+="</tr>";
					//}
				});
				htmls+="</table>";
				htmls+="<a href='/register'>등록<br/></a>";
				htmls+="<a href='/index'>돌아가기<br/></a>";

            	console.log("hello!");
            	document.write(htmls);
 
           }, error: function (jqXHR, textStatus, errorThrown) {
           }
      });
   }
   

	window.onload = function(){
		getDataFromAPI();
	}

   </script>

</body>

</html>