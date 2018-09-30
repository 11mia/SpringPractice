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
	<div id="coffee"></div>
	<div id="shop"></div>
	<div>
		<a href='/list'>돌아가기<br /></a>
	</div>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
	 var cid;
	 var shopInfo = [];	//여기에 커피에서 받아온 리스트가 들어갈 예정!
	 var shopList=[];	//샵에서 받아온 샵의 id와 name 배열
   function getDetailCoffeeData(id) {		//커피자체에 대한 정보 뿌리기
	   cid = id;
       $.ajax({
           url: "/detail/show/"+id,
           type: "POST",
           dataType:'json',
           success: 
        	  function (data) {
        	   var htmls='';
				 htmls+="<h1>Coffee List</h1>";
	       			htmls+="<table border='1'>";
	       			htmls+="<tr><th>이름</th><th>가격</th><th>재고</th><th>총 판매량</th><th>총 판매액</th><th>등록일</th><th>수정일</th><th>수정</th></tr>";
					htmls+="<tr>";
					htmls+="<td>"+data.name+"</td>";
					htmls+="<td>"+data.price+"</td>";
					htmls+="<td>"+data.stock+"</td>";
					htmls+="<td>"+data.totSales+"</td>";
					htmls+="<td>"+data.totSalesAmt+"</td>";
					htmls+="<td>"+data.regDate+"</td>";
					htmls+="<td>"+data.moDate+"</td>";	
					htmls+="<td><a href='/modify/"+data.id+"'>수정</a></td>";
					htmls+="</tr>";
					htmls+="</table>";
            	coffee.innerHTML=htmls;
 
           }, error: function (jqXHR, textStatus, errorThrown) {
           }
      });
   }
  
   function getDataFromCoffee(id) {	//커피에서 샵관련 데이터 뽑아오기&화면에 뿌리기
       $.ajax({
           url: "/detail/shop/"+id,
           type: "POST",
           dataType:'json',
           crossOrigin:true,
           async:false,
           success: 
        	   
        	  function (data) {
        	   shopInfo=data;
        	   var htmls='';
       			var sum=0;
       			htmls+="<h2>판매점 상세정보</h2>";
       			htmls+="<table border='1'>";
       	    htmls+="<tr><th>shop 이름</th><th>등록일</th><th>수정일</th><th>총판매량</th></tr>";
       		$.each(data,function(){
       			var sid = this['totid'].substring(0,3);
       			console.log("sid : "+sid);
       			if(sid!='999'){	
       				htmls+="<tr>";
       				var index = shopList.indexOf(sid);
       				htmls+="<td>"+shopList[index+1]+"</td>";
       				htmls+="<td>"+this['regDate']+"</td>";
       				htmls+="<td>"+this['moDate']+"</td>";
       				htmls+="<td>"+this['totSales']+"</td>";
       				htmls+="</tr>";
       			}
       		});
       		htmls+="</table>";
               console.log("hello!");
                shop.innerHTML = htmls; 
        	   
        	   
        	   
           }, error: function (jqXHR, textStatus, errorThrown) {
           }
      });
   } 
   
    function getDataFromShop() {
       $.ajax({
           url: "http://9.194.103.128:8090/ShopList",
           crossOrigin:true,
           type: "GET",
           async:false,
           success: 
        	  function (data) {	//string으로 넘어옴.
        	   console.log(data);
        	   shopList=data.split(',');
        	
           }, error: function (jqXHR, textStatus, errorThrown) {
        	   console.log("error");
           }
      });
   }

  	$(document).ready(getDetailCoffeeData(${id}));	//커피정보 뿌리기
  	$(document).ready(getDataFromShop());	//샵테이블에서 샵네임 가져오기
  	$(document).ready(getDataFromCoffee(cid));	//커피테이블에서 샵정보 가져오기&화면에 뿌리기
	
  </script>

</body>



</html>