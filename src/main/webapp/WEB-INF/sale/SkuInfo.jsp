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
	<c:forEach items="${skuInfo }" var="sku">
		<div style="float left;width: 240px;height: 180px;">
			<img alt="" src="imgs/${sku.product.shp_tp }" style="width: 150px;"><br>
			<a href="skuInfo/skuInfo_goto_skuDetails/${sku.id }/${sku.product.id}.htm" target="_blank">${sku.sku_mch }</a>&nbsp&nbsp 价格: ${sku.jg } &nbsp&nbsp 销量: ${sku.sku_xl }
		</div>
	</c:forEach>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript"></script>
</body>
</html>