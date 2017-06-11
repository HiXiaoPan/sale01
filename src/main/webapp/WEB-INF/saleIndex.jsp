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

<title>商城首頁</title>
</head>
<body>
	<c:if test="${user ==null }" >
		<a href="user/toLogin.htm">请登录</a>
	</c:if>
	<c:if test="${user !=null}" >
		欢迎<h4>${user.yh_nch }</h4>大驾光临 <a href="user/logout.htm">注销</a>
	</c:if>
	<jsp:include page="sale/MiniShoppingCar.jsp"></jsp:include>
	<ul id="saleIndex_fl1_ul" ></ul>
	<ul id="saleIndex_fl2_ul"></ul>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.getJSON(
				"js/json/class_1.js",
				function(r){
					$(r).each(function(i,n){
						$("#saleIndex_fl1_ul").append("<li value="+n.id+" onmouseover='saleIndex_get_fl2(this.value)' style='width:80px'>"+n.flmch1+"</li>");					
					});
				});
	});
	function saleIndex_get_fl2(fl1id){
		$.getJSON(
				"js/json/class_2_"+fl1id+".js",
				function(r){
					$("#saleIndex_fl2_ul").empty();
					$(r).each(function(i,n){
						$("#saleIndex_fl2_ul").append("<li ><a href='sort/goto_product_page/"+n.id+"/"+n.flmch2+".htm'>"+n.flmch2+"</a></li>");					
					});
				});
	}
</script>
</body>
</html>