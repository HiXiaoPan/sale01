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
<div id="skuShow_select_attr">${class2.flmch2}:</div>
<h1>hello everyOne </h1><!-- 只展示了属性，品牌还没有展示 -->
<div><%@include file="MiniShoppingCar.jsp" %>
<%-- <jsp:include page="MiniShoppingCar.jsp"></jsp:include> --%>
	<c:forEach items="${attrAndValue}" var="attrAndValue"
		varStatus="sku_attr">
		<div id="skuShow_attr_${attrAndValue.id }">
			${attrAndValue.shxm_mch }:<input type="hidden"
			name="attr_value[${sku_attr.index }].id" value="${attrAndValue.id }" />
		<c:forEach items="${attrAndValue.values }" var="value">
					<a id="skuShow_value_${value.id }" href="javascript:skuShow_select_attr_hidden(${attrAndValue.id },${value.id });">${value.shxzh }${value.shxzh_mch }</a>
		</c:forEach>
		</div>
	</c:forEach>
</div>
<hr>
排序:<a href="javascript:skuShow_order('order by jg');">价格</a> &nbps <a href="javascript:skuShow_order('order by sku_xl');">销量</a>&nbps <a href="javascript:skuShow_order('order by chjshj');">商品上架时间</a>
<hr><input id="skuShow_origin_sql" type="hidden" value="order by jg"/><!-- 默认排序方式 ,这里以 input 标签保存排序方式的目的在于用户选择了其他商品属性时并不会影响排序方式，思想:用第三方变量保存变量使其不影响另外两方的操作-->
<div id="skuShow_skuInfo">
	
</div>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	/*------------------------页面完成后---------------------------  */
	$(function(){
		skuShow_get_skus();
	});
	/* ——————————————————————————页面完成之后———————————————————————————————— */
	/* 排序 */
	function skuShow_order(sql){
		var originSql  = $("#skuShow_origin_sql").val();
		if(sql != originSql){
			$("#skuShow_origin_sql").val(sql);
		}else{
			$("#skuShow_origin_sql").val(sql+" DESC");
		}
		skuShow_get_skus();
	}
	/* 隐藏属性 */
	function skuShow_select_attr_hidden(attrId,valueId){
		var valueName = $("#skuShow_value_"+valueId).html();
		var valueText = "<div id='skuShow_selected_attr_show_"+attrId+"'>"+
		"<input type='hidden' value='{\"shxm_id\":"+attrId+",\"shxzh_id\":"+valueId+"}'/>"
		+"<a href='javascript:skuShow_select_attr_show("+attrId+","+valueId+");'>"
		+valueName+"</a></div>";
		$("#skuShow_select_attr").append(valueText);
		$("#skuShow_attr_"+attrId).hide();
		skuShow_get_skus();
	}
	/* 显示属性 */
	function skuShow_select_attr_show(attrId){
		$("#skuShow_selected_attr_show_"+attrId).remove();
		$("#skuShow_attr_"+attrId).show();
		skuShow_get_skus();
	}
	/* 获取sku 信息 */
	function skuShow_get_skus(){
		var originSql  = $("#skuShow_origin_sql").val();
		var flbh2id = ${class2.id};
		var paramJson = {"flbh2id":flbh2id,
							"sql":	originSql};
		$("#skuShow_select_attr input").each(function(i,r){
			var jsonObj = $.parseJSON(r.value);
			paramJson["attrAndValueids["+i+"]"]=jsonObj;
			
		});
		$.post(
				"sort/get_sku_info.do",
				paramJson,
				function(r){
					$("#skuShow_skuInfo").html(r);
				}
			);
	}
</script>
</body>
</html>