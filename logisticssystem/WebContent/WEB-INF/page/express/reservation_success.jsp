<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>我要查件</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/tool/site.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top"
		style="padding: 0px;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#" style="display: inline-block;">
					<img style="float: left;" title="Note3速递"
					src="${pageContext.request.contextPath}/img/logo.png"
					class="navbar-brand-logo"> <span class="navbar-brand-text"
					style="color: #FFF; line-height: 30px">Note3速递系统</span>
				</a>
				<div class="navbar-other">2018，全新开始</div>
			</div>
			<div style="float: right; marign: auto; color: #FFF" class="navlogin">
				<ul class="nav navbar-nav navbar-right">
					<li><a style="text-decoration: none;" href="#"
						class="navbar-avatar"> <span class="avatar"
							style="vertical-align: middle;"> <img
								src="${pageContext.request.contextPath}/img/touxiang.jpg">
						</span> <span class="user-name" style="color: #FFF;"> 18296929245
						</span>
					</a></li>
					<li><a href="${pageContext.request.contextPath}/index.jsp"
						style="color: #FFF; line-height: 30px">退出</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="site-menubar navbar-nav">
		<div class="site-menubar-body">
			<ul class="site-menu">
				<li class="site-menu-item"><a
					href="${pageContext.request.contextPath}/userinfo/userinfo_userIndex"><span
						class="site-menu-title">首页</span></a></li>
				<li class="site-menu-item"><a
					href="${pageContext.request.contextPath}/userinfo/userinfo_pageSendExpress"><span
						class="site-menu-title">我要寄件</span></span> </a></li>
				<li class="site-menu-item"><a
					href="${pageContext.request.contextPath}//userinfo/userinfo_pageSearchExpress">
						<span class="site-menu-title">我要查件</span>
				</a></li>
				<li class="site-menu-item"><a
					href="${pageContext.request.contextPath}/userinfo/userinfo_pageMyExpress">
						<span class="site-menu-title">我的订单</span></span>
				</a></li>
				<li class="site-menu-item"><a
					href="${pageContext.request.contextPath}/userinfo/userinfo_pageUserInfo">
						<span class="site-menu-title">用户信息</span></span>
				</a></li>
			</ul>
		</div>
	</div>
	<div class="page" style="margin-top: 110px">
		<div class="container m_top_10">
			<ol class="breadcrumb" style="background-color: transparent;">
				<li><a href="${pageContext.request.contextPath}/user-index.jsp">首页&nbsp;</a></li>
				<li class="active">&nbsp;预约成功</li>
			</ol>
			<div class="yto-box">
				<div id="reservation_success"
					class="input-group input-group-lg col-sm-8 col-sm-offset-2">
					<div
						style="floatl: left; width: 500px; height: 80px; margin-top: 100px; text-align: center">
						<h2>
							预约成功，等待受理，您的预约单号为 <b style="color: orangered">${reversationNum}</b>!
						</h2>
					</div>
					<div style="width: 500px; height: 80px">
						<span id="miaoshu"></span>秒后跳转到列表页
					</div>
				</div>
				<%--<div class="yto-trace">

            </div>--%>
			</div>
		</div>
	</div>
	<footer class="page-footer" style="height: 72px">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<div class="page-copyright">
						<p>
							© 2018. All RIGHT RESERVED. Note3速递公司 版权所有 <a target="_blank"
								href="#">我的编号</a>
						</p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<p class="yto-tel">
						<a target="_blank" class="link-yto" href="#">我的官网</a>
						客服服务热线：111111，111-11111111
					</p>
				</div>
			</div>
		</div>
	</footer>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/jquery/jquery.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
	<script>
		(function() {
			var i = 3
			setInterval(
					function() {
						$('#miaoshu').text(i)
						i--
						if (i <= 0) {
							window.location = '/logisticssystem/userinfo/userinfo_userIndex'
						}
					}, 1000)
		})()
	</script>
</body>
</html>