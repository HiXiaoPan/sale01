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
收货地址: <c:forEach items="${addr_user }" var="addr">
	<input type="radio" name="userAddr" value="${addr.id }" onclick="order_detail_submit_user_attr(${addr.id },'${addr.yh_shhdz }','${addr.shjr }','${addr.lxfsh }')"/>${addr.yh_shhdz }${addr.shjr }${addr.lxfsh }
</c:forEach>
<c:forEach items="${orders }" var="order">
	<c:forEach items="${order.order_info_list }" var="orderInfo">
		商品名称:${orderInfo.sku_mch }商品数量${orderInfo.sku_shl }单价:${orderInfo.sku_jg }</br>
	</c:forEach>
	订单总金额：${order.zje }
</c:forEach>
<form id="order_detail_submit_form" action="order/addOrder.htm" method="post" >
	
</form>
<button onclick="order_detail_submit()">提交订单</button>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	function order_detail_submit_user_attr(id,yh_shhdz,shjr,lxfsh){
			var a = "<input type='text' name='dzh_id' value='"+id+"' />";
			var b = "<input type='text' name='dzh_mch' value='"+yh_shhdz+"' />";
			var c = "<input type='text' name='shhr' value='"+shjr+"' />";
			var d = "<input type='text' name='lxfsh' value='"+lxfsh+"' />";
			$("#order_detail_submit_form").empty();
			$("#order_detail_submit_form").append(a+b+c+d);
	}
	function order_detail_submit(){
		$("#order_detail_submit_form").submit();
	}
</script>
</body>
</html>