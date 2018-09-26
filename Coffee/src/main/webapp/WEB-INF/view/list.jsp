
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
	<div id="printWindow"></div>
	<a href='/register'>등록<br/></a>
	<a href='/index'>돌아가기<br/></a>
	<script>
	var Sid='';
	  function getShopSid(cid) {		 
	      $.ajax({
	          url: "/getShopId/"+cid,
	          type: "GET",
	          dataType:'json',
	          
	          success: 
	       	  function (data) { 
	        	  console.log(data);
	        	 // if(data!=''){
	        	 	alert("cid :"+cid+" data :"+data);
	        	 	Sid=data;
	        	 // }
				return data;
	          }, error: function (jqXHR, textStatus, errorThrown) {
	          }
	     });
	  }
	
	
   function getDataFromAPI() { 
       $.ajax({
           url: "/list/show",
           type: "GET",
           dataType:'json',
           async:false,
           success: 
        	  function (data) {
				 var htmls='';
				 htmls+="<h1>Coffee List</h1>";
				 htmls+="<th class='dropdown'><select class='form-control' style='width:130px' id='ascOrDesc'><option>ASC</option><option>DESC</option></select></th></br>";
				 htmls+="<input type='button' name='paging' id='paging' value='정렬' onclick='sortingClick();'/></br>"
				 htmls+="<table border='1'>";
	       			htmls+="<tr><th>이름</th>	<th>등록일</th><th>판매하는 shop</th><th>상세 정보</th><th>삭제</th></tr>";
				$.each(data,function(){
						htmls+="<tr>";
						htmls+="<td>"+this['name']+"</td>";
						htmls+="<td>"+this['regDate']+"</td>";
					
						//shopDetail(sid);
						
						;
						console.log(Sid);
						htmls+="<td>"+getShopSid(this['id'])+"</td>";
						htmls+="<td><a href='/detail/"+this['id']+"'>상세 정보</a></td>";
						htmls+="<td><a href='/delete/"+this['id']+"'>삭제</a></td>";
						htmls+="</tr>";
				});
				htmls+="</table>";
				//htmls+="<a href='/register'>등록<br/></a>";
				//htmls+="<a href='/index'>돌아가기<br/></a>";

            	console.log("hello!");
            	//document.write(htmls);
            	printWindow.innerHTML = htmls; 

           }, error: function (jqXHR, textStatus, errorThrown) {
           }
      });
   }
   
 

   
  /* 
   function getDataFromAPI() { 
       $.ajax({
           url: "/list/show",
           type: "GET",
           dataType:'json',
           success: 
        	  function (data) {
				 var htmls='';
				 htmls+="<h1>Coffee List</h1>";
			//	 htmls+="<th class='dropdown'><select class='form-control' style='width:150px' id='rowsNom'><option value='2'>2</option><option value='5'>5</option><option value='7'>Show All</option></select></th></br>";
				 htmls+="<th class='dropdown'><select class='form-control' style='width:130px' id='ascOrDesc'><option>ASC</option><option>DESC</option></select></th></br>";
				 htmls+="<input type='button' name='paging' id='paging' value='정렬' onclick='sortingClick();'/></br>"
				 htmls+="<table border='1'>";
	       			htmls+="<tr><th>이름</th>	<th>등록일</th><th>판매하는 shop</th><th>상세 정보</th><th>삭제</th></tr>";
				$.each(data,function(){
						htmls+="<tr>";
						htmls+="<td>"+this['name']+"</td>";
						htmls+="<td>"+this['regDate']+"</td>";
						htmls+="<td>------------</td>";	//shop list
						htmls+="<td><a href='/detail/"+this['id']+"'>상세 정보</a></td>";
						htmls+="<td><a href='/delete/"+this['id']+"'>삭제</a></td>";
						htmls+="</tr>";
				});
				htmls+="</table>";
				htmls+="<a href='/register'>등록<br/></a>";
				htmls+="<a href='/index'>돌아가기<br/></a>";

            	console.log("hello!");
            	//document.write(htmls);
            	printWindow.innerHTML = htmls; 
           }, error: function (jqXHR, textStatus, errorThrown) {
           }
      });
   }*/
   
   
   
   
   
   function sortingClick() {		 
		alert("정렬되었습니다!");
       $.ajax({
           url: "/list/sorting",
           type: "GET",
           dataType:'json',
           success: 
        	  function (data) {
        	  	
        		 var htmls='';
				 htmls+="<h1>Coffee List</h1>";
				 htmls+="<th class='dropdown'><select class='form-control' style='width:150px' id='rowsNom'><option value='2'>2</option><option value='5'>5</option><option value='7'>Show All</option></select></th></br>";
				 htmls+="<th class='dropdown'><select class='form-control' style='width:130px' id='ascOrDesc'><option>ASC</option><option>DESC</option></select></th></br>";
				 htmls+="<input type='button' name='paging' id='paging' value='정렬' onclick='sortingClick();'/></br>"
				 htmls+="<table border='1'>";
	       			htmls+="<tr><th>이름</th>	<th>등록일</th><th>판매하는 shop</th><th>상세 정보</th><th>삭제</th></tr>";
				$.each(data,function(){
						htmls+="<tr>";
						htmls+="<td>"+this['name']+"</td>";
						htmls+="<td>"+this['regDate']+"</td>";
						htmls+="<td>------------</td>";	//shop list
						htmls+="<td><a href='/detail/"+this['id']+"'>상세 정보</a></td>";
						htmls+="<td><a href='/delete/"+this['id']+"'>삭제</a></td>";
						htmls+="</tr>";
				});
				htmls+="</table>";
				htmls+="<a href='/register'>등록<br/></a>";
				htmls+="<a href='/index'>돌아가기<br/></a>";

            	console.log("hello!");
            	//document.replace(htmls);
            	printWindow.innerHTML = htmls; 

           }, error: function (jqXHR, textStatus, errorThrown) {
           }
      });
   }
   /*
   var shopList;
   shopList = '';
   function shopDetail(sid) {		 
      $.ajax({
          url: "/list/shopDetail"+sid,
          type: "GET",
          dataType:'json',
          success: 
       	  function (data) {
        	  
        	  $.each(data,function(){
					shopList+=this['name'];
			});
			
          }, error: function (jqXHR, textStatus, errorThrown) {
          }
     });
  }
   
   */
   
   
/*    function shopDetail() {		 
	      $.ajax({
	          url: "http://9.194.103.128:8090/ShopList",
	          type: "GET",
	          dataType:'json',
	          success: 
	       	  function (data) { 
	        	
	        	  $.each(data,function(){
						alert(this['sid']);
				});
				
	          }, error: function (jqXHR, textStatus, errorThrown) {
	          }
	     });
	  }
    */
   

	window.onload = function(){
		
		getDataFromAPI();
		//shopDetail();
	}

   </script>

</body>

</html>