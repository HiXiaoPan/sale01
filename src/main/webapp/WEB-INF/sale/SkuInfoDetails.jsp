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
图片展示:<div>
	<img alt="" src="imgs/${skuDetail.imgs[0].url }">
	</div><br>
本sku信息<div>
	商品名:${skuDetail.sku_mch }</br>
	价格:${skuDetail.jg }</br>
	库存:${skuDetail.kc }</br>
	
</div>
同spu下其他sku 展示:<div>
	<c:forEach items="${skus }" var="sku">
		<a href="skuInfo/skuInfo_goto_skuDetails/${sku.id }/${sku.shp_id}.htm">${sku.sku_mch }</a></br>
	</c:forEach>
</div>
<jsp:include page="MiniShoppingCar.jsp"></jsp:include>

<form action="shoppingCert/goto_shoppingCert.htm" method="post">
	<input type="hidden" name="shp_id" value="${skuDetail.shp_id }"/>
	<input type="hidden" name="sku_id" value="${skuDetail.id }"/>
	<input type="hidden" name="sku_mch" value="${skuDetail.sku_mch }"/>
	<input type="hidden" name="shp_tp" value="${skuDetail.imgs[0].url }"/>
	<input type="hidden" name="sku_jg" value="${skuDetail.jg }"/>
	<input type="hidden" name="kcdz" value="${skuDetail.kcdz }"/>
	<input type="hidden" name="tjshl" value="1"/>
	<input type="submit" value="添加购物车"/>
</form>
商品属性:<div>
	<c:forEach items="${skuDetail.attrAndValue }" var="attrValue">
		${attrValue.shxm_mch }:${attrValue.shxzh }${attrValue.shxzh_mch }</br>
	</c:forEach>
</div>
商品展示:<div>
	<c:forEach items="${skuDetail.imgs }" var="img">
		<img alt="" src="imgs/${img.url }" style="width: 100px;"></br>
	</c:forEach>
	</div>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>