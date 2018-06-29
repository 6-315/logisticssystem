<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/tool/site.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/toastr.css">
</head>
<body class="body-login">
	<div class="page">
		<div class="page-content">

			<div class="page-brand-info hidden-xs">
				<div class="brand" style="margin-left: -20px">
					<%-- <img class="brand-img"
						src="${pageContext.request.contextPath }/img/fugai.png"
						style="width: 180px; height: 180px;"> --%>
					<h1 class="brand-text">Note3物流系统</h1>
				</div>
				<p>我递送的每一声问候和祝福，</p>
				<p>得到的每一份支持，都是我坚定前行的动力。</p>
				<p>Note3期待为您服务</p>
			</div>
			<div id="sslogin" class="page-login-main">
				<h3 class="hidden-xs">登录Note3</h3>
				<br />
				<ul class="nav nav-tabs">
					<li role="presentation" class="active"><a
						href="${pageContext.request.contextPath}/user-login.jsp">手机号密码登录</a>
					</li>
					<li role="presentation"><a
						href="${pageContext.request.contextPath}/register.jsp">快速注册</a></li>
				</ul>
				<form class="fv-form fv-form-bootstrap">
					<div class="form-group">
						<input v-model="user" class="form-control" placeholder="手机号码">
					</div>
					<div class="form-group">
						<input v-model="password" class="form-control" placeholder="密码"
							type="password">
					</div>
				</form>
				<button :disabled="disabled" v-on:click="login" id="user_login"
					class="btn btn-primary btn-block btn-flat">登录</button>
				<div class="clearfix"></div>
				<footer class="page-copyright">
				<p>Note3物流有限公司 版权所有</p>
				
				<p>2018. <a href="${pageContext.request.contextPath }/login.jsp">© </a>All RIGHT RESERVED.</p>
				</footer>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/css/tool/vue.js"></script>
	<script src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/public/login-register.js"></script>
</body>
</html>