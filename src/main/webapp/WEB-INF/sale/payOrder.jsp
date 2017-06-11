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
配送地址:${orders[0].dzh_mch }${orders[0].shhr }${orders[0].lxfsh }
订单详情:<c:forEach items="${orders }" var="order" varStatus="index"> 
	订单${index.count }:<c:forEach items="${order.order_info_list }" var="info" varStatus="subindex">
		商品:${subindex.count }:${info.sku_mch }
	</c:forEach>
</c:forEach>
订单总金额: ${zje}
<a href="order/dopay.htm">支付</a>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
</script>
</body>
</html>