
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
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<div id="sortingButton">
		<h1>Coffee List</h1>
		이름
		<input type='button' name='NameASC' id='1' value='오름차순' onclick='sortingClick(this.id);'/>
		<input type='button' name='NameDESC' id='2' value='내림차순' onclick='sortingClick(this.id);'/></br>
		등록일
		<input type='button' name='DateASC'	id='3' value='오름차순' onclick='sortingClick(this.id);'/>
		<input type='button' name='DateDESC' id='4' value='내림차순' onclick='sortingClick(this.id);'/>
	</div>
	<div id="printWindow"></div>
	<a href='/register'>등록<br/></a>
	<a href='/index'>돌아가기<br/></a>
	<script>
	

	var Sid='';
	var Name='';
	window.onload = function(){
		getDataFromAPI();
	}
	
	   function getShopSid(cid) {		 
			      $.ajax({
			          url: "/getShopId/"+cid,
			          type: "GET",
			          async: false,
			          success:function (data) {
						Sid=data;
			          }, error: function (jqXHR, textStatus, errorThrown) {
			          }
			     });
			  }   
		   
		   
		   function getShopName(sid){
			   console.log("getShopName으로 들어온 sid : "+sid);
			   $.ajax({
				   url: "http://9.194.103.128:8090/getShopName/"+sid,
				   type: "GET",
				   crossOrigin:true,
				   async:false,
				   success:
					   function(data){
					   Name= data;
					   console.log("getShopName으로 들어온 data : "+data);
				   }, error: function (jqXHR, textStatus, errorThrown) {
					   console.log("error at getShopName")
		           }
			   });
		   }
		   
   function getDataFromAPI() { 
       $.ajax({
           url: "/list/show",
           type: "GET",
           crossOrigin:true,
           async:false,
           success: 
        	  function (data) {
				 var htmls='';
				 htmls+="<table border='1'>";
	       			htmls+="<tr><th>이름</th>	<th>등록일</th><th>판매하는 shop</th><th>상세 정보</th><th>삭제</th></tr>";
				$.each(data,function(){
						getShopSid(this['id']);
						console.log("getShopSid한 후 Sid : "+Sid);
						htmls+="<tr>";
						htmls+="<td>"+this['name']+"</td>";
						htmls+="<td>"+this['regDate']+"</td>";
						if(Sid==''){
							htmls+="<td>해당 메뉴를 판매하는 shop이 없습니다.</td>";
							console.log("Sid='' : true");
						}else{
							getShopName(Sid);
							console.log("Sid='' : false");
							console.log("Name : "+Name);
							Name = Name.slice(0,-1);	//뒤에서 1글자 잘라내기->,지우기
							htmls+="<td>"+Name+"</td>";
						}
						htmls+="<td><a href='/detail/"+this['id']+"'>상세 정보</a></td>";
						htmls+="<td><a href='/delete/"+this['id']+"'>삭제</a></td>";
						htmls+="</tr>";
				});
				htmls+="</table>";
            	console.log("hello!");
            	printWindow.innerHTML = htmls; 

           }, error: function (jqXHR, textStatus, errorThrown) {
           }
      });
   }
   
   /////정렬버튼을 누른 후
   function sortingClick(clicked_id){
		alert("정렬되었습니다.");
	
       $.ajax({
           url: "/list/sorting/"+clicked_id,
           type: "GET",
           dataType:'json',
           
           success: 
        	  function (data) {
        	   var htmls='';
				 htmls+="<table border='1'>";
	       			htmls+="<tr><th>이름</th>	<th>등록일</th><th>판매하는 shop</th><th>상세 정보</th><th>삭제</th></tr>";
				$.each(data,function(){
						getShopSid(this['id']);
						console.log("getShopSid한 후 Sid : "+Sid);
						htmls+="<tr>";
						htmls+="<td>"+this['name']+"</td>";
						htmls+="<td>"+this['regDate']+"</td>";
						if(Sid==''){
							htmls+="<td>해당 메뉴를 판매하는 shop이 없습니다.</td>";
							console.log("Sid='' : true");
						}else{
							getShopName(Sid);
							console.log("Sid='' : false");
							console.log("Name : "+Name);
							Name = Name.slice(0,-1);	//뒤에서 1글자 잘라내기->,지우기
							htmls+="<td>"+Name+"</td>";
						}
						htmls+="<td><a href='/detail/"+this['id']+"'>상세 정보</a></td>";
						htmls+="<td><a href='/delete/"+this['id']+"'>삭제</a></td>";
						htmls+="</tr>";
				});
				htmls+="</table>";
          	console.log("hello!");
          	printWindow.innerHTML = htmls; 

           }, error: function (jqXHR, textStatus, errorThrown) {
           }
      });
   }
  
   </script>

</body>

</html>