<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"
	+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script>
	$(function (){
		$("#loginBtn").click(function () {
				login();
		})
		//给整个浏览器窗口判定回车事件
		$(window).keydown(function (event){
			if(event.key == "Enter"){
				login();
				//让登录按钮触发单击事件
				//$("#loginBtn").click();
			}
		})
	})
	function login(){
		var loginAct=$.trim($("#loginAct").val());
		var loginPwd=$.trim($("#loginPwd").val());

		$.ajax({
			url:"settings/qx/user/login.do",
			data:{
				"loginAct":loginAct,
				"loginPwd":loginPwd,
				"isRemPwd":$("#isRemPwd").prop("checked")
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.code == 1){
					window.location.href="workbench/index.do";
				}else{
					$("#msg").text(data.msg);
				}
				$("#loginPwd").val("")
			},
			beforeSend:function(){
				//ajax请求发送之前，执行该函数，如果该函数返回true，则ajax正常发送请求
				//否则ajax请求将不会发送
				$("#msg").text("")
				if(loginAct==""||loginPwd==""){
					$("#msg").text("用户名或者密码不能为空")
					return false;
				}
				return true;
				}
		})
	}
</script>

</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2019&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" id="loginAct" value="${cookie.loginAct.value}" placeholder="用户名">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" id="loginPwd" value="${cookie.loginPwd.value}" placeholder="密码">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						<label>

						<c:if test="${not empty cookie.loginAct and not empty cookie.loginPwd}">
								<input type="checkbox" id="isRemPwd" checked>
							</c:if>
							<c:if test="${ empty cookie.loginAct and  empty cookie.loginPwd}">
								<input type="checkbox" id="isRemPwd">
							</c:if>
							十天内记住密码
							</label>
						&nbsp;&nbsp;
						<span id="msg" style="color: #b92c28"></span>
					</div>
					<button type="button" class="btn btn-primary btn-lg btn-block" id="loginBtn" style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>