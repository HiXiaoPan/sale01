<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
<div id="mini_shopping_car">
	<a href="javascript:;" onmouseout="mini_shopping_car_inner_hide()" onmousemove="mini_shopping_car_inner_show()">迷你购物车</a><br>
	<a href="handleShoppingCar/goto_total_shoppingCar.htm" >操作购物车</a>
	<div id="mini_shopping_car_inner" style="display: none;"></div>
</div>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	function mini_shopping_car_inner_hide(){
		$("#mini_shopping_car_inner").hide();
	}
	function mini_shopping_car_inner_show(){
		$.post(
		"shoppingCert/get_inner_car.do",
		function(r){
			$("#mini_shopping_car_inner").empty();
			$("#mini_shopping_car_inner").html(r);
			$("#mini_shopping_car_inner").show();
		}
		);
	}
</script>
</body>
</html>