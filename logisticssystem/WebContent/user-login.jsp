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
</head>
<body class="body-login">
	<div class="page">
		<div class="page-content">

			<div class="page-brand-info hidden-xs">
				<div class="brand">
					<img class="brand-img"
						src="${pageContext.request.contextPath }/img/fugai.png"
						style="width: 180px; height: 180px;">
					<h1 class="brand-text">Note3物流系统</h1>
				</div>
				<p>我递送的每一声问候和祝福，</p>
				<p>得到的每一份支持，都是我坚定前行的动力。</p>
				<p>圆通期待为您服务</p>
			</div>
			<div class="page-login-main">
				<h3 class="hidden-xs">登陆Note3</h3>
				<br />
				<ul class="nav nav-tabs">
					<li role="presentation" class="active"><a href="#">账号密码登录</a>
					</li>
					<li role="presentation"><a href="#">手机号快速注册</a></li>
				</ul>
				<form method="post" class="fv-form fv-form-bootstrap">
					<div class="form-group">
						<input name="" class="form-control" placeholder="用户名">
					</div>
					<div class="form-group">
						<input name="" class="form-control" placeholder="密码"
							type="password">
					</div>

					<input value="登录" class="btn btn-primary btn-block"
						autocomplete="off" type="submit">
				</form>
				<div class="clearfix"></div>
				<footer class="page-copyright">
				<p>Note3物流有限公司 版权所有</p>
				<p>© 2018. All RIGHT RESERVED.</p>
				</footer>
			</div>
		</div>
	</div>
</body>
</html>