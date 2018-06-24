<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/font/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminlte.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/toastr.css">
<title>登录</title>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="${pageContext.request.contextPath}/index.jsp"><b>Note3</b>物流</a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div id="sslogin" class="card-body login-card-body">
				<p class="login-box-msg">登录</p>
				<div class="form-group has-feedback">
					<input name="username" type="text" v-model="user"
						class="form-control" placeholder="工号">
				</div>
				<div class="form-group has-feedback">
					<input name="password" type="password" v-model="password"
						class="form-control" placeholder="密码">
				</div>
				<div class="row">
					<!-- /.col -->
					<div class="col-4">
						<button :disabled="disabled" v-on:click="login" id="user_login"
							class="btn btn-primary btn-block btn-flat">登录</button>
					</div>
					<!-- /.col -->
				</div>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
	<script src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/public/login-register.js"></script>
</body>
</html>