<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
<div>
	<c:forEach items="${car_list }" var="car">
		<div>
			<input type="checkbox" ${car.shfxz=="1"?"checked":""} 
			onchange="totalShoppingCar_totalmoney(${car.id },${car.sku_id },this.checked)"/>
			<img  src="imgs/${car.shp_tp }" style="width: 50px;">
			<p>${car.sku_mch }</p>
			 <p><a href="javascript:totalShoppingCar_sum(${car.id },${car.sku_id },${car.tjshl });">+</a>${car.tjshl }
			 <a href="javascript:totalShoppingCar_divlide(${car.id },${car.sku_id },${car.tjshl });">-</a></p>
			 <p>${car.hj }</p>
		</div>
	</c:forEach>
	总价格:<p id="innerCar_total_money">${car_sum_checked }</p>
	<a href="order/order.htm" target="_blank">结算</a>
</div>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
</script>
</body>
</html>