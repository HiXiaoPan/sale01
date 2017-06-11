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
<link rel="shortcut icon" type="image/icon" href="images/jd.ico">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div class="up">
		<img src="images/logo.jpg" height="45px;" class="hy_img" />
		<div class="hy">欢迎登录</div>
	</div>
	<div class="middle">
		<div class="login">
			<div class="l1 ">
				<div class="l1_font_01 ">硅谷会员</div>
				<a class="l1_font_02 "
					href="<%=application.getContextPath()%>/to_regist.action">用户注册</a>
			</div>
			<div class="blank_01"></div>
			<div class="ts">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${err}</div>
			<div class="blank_01"></div>
			<form action="user/xxx/login.htm" id="login_form" method="post">
				<div class="input1">
					<input type="text" class="input1_01" name="yh_mch" />
				</div>
				<div class="blank_01"></div>
				<div class="blank_01"></div>
				<div class="input2">
					<input type="password" class="input1_01" name="yh_mm" />
				</div>
				<div class="blank_01"></div>
				<div class="blank_01"></div>
				<div class="box_01">
					<input type="checkbox" class="box_01_box" name="autoLogin" value="1" />
					<div class="box_01_both">
						<div class="box_01_both_1">自动登陆</div>
						<div class="box_01_both_2">忘记密码？</div>
					</div>
				</div>
				<div class="blank_01"></div>
				<a href="javascript:;" class="aline">
					<div class="red_button" onclick="to_submit()">
						登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</div>
				</a>
			</form>
			
			<div class="blank_01"></div>
			<div class="blank_01"></div>
			<div class="box_down">
				<div class="box_down_1">使用合作网站账号登录京东：</div>
				<div class="box_down_1">京东钱包&nbsp;&nbsp;|&nbsp;&nbsp;QQ
					&nbsp;&nbsp;|&nbsp;&nbsp;微信</div>
			</div>
		</div>
	</div>

	<div class="down">
		<br /> Copyright©2004-2015 xu.jb.com 版权所有
	</div>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		
	});
	function to_submit(){
		$("#login_form").submit();
	}
</script>
</body>
</html>