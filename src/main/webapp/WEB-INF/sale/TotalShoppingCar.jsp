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
<div id="totalShoppingCar_div"></div>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.get("handleShoppingCar/get_total_inner.do",
			function(r){
			$("#totalShoppingCar_div").html(r);
		
		}		
		);
		
	});
	function totalShoppingCar_totalmoney(carid,skuid,shfxz){
		if(shfxz){
			shfxz="1";
		}else{
			shfxz="0";
		}
		
		$.post("handleShoppingCar/if_checked_car.do",
			{"carid":carid,"skuid":skuid,"shfxz":shfxz},
			function(r){
				$("#totalShoppingCar_div").html(r);
			}
		);
	}
	function totalShoppingCar_sum(carid ,sku_id,tjshl){
		var newTjshl=tjshl+1;
		var shfxz="1";
		$.post("handleShoppingCar/update_number.do",
				{"carid":carid,"skuid":sku_id,"shfxz":shfxz,"tjshl":newTjshl},
				function(r){
					$("#totalShoppingCar_div").html(r);
				}
			);
	}
	function totalShoppingCar_divlide(carid ,sku_id,tjshl){
		var newTjshl=tjshl-1;
		var shfxz="1";
		$.post("handleShoppingCar/update_number.do",
				{"carid":carid,"skuid":sku_id,"shfxz":shfxz,"tjshl":newTjshl},
				function(r){
					$("#totalShoppingCar_div").html(r);
				}
			);
	}
</script>
</body>
</html>