<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/tool/site.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/toastr.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Note3 速递系统</title>
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
					</span> <span class="user-name" style="color: #FFF;"> 18296929245 </span>
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
		<div class="showpic">
			<ul>
				<li class="activeImg"
					style="opacity: 1; background-image:url(${pageContext.request.contextPath}/img/user-index-img.jpg)"></li>
			</ul>
		</div>
		<div class="container">
			<div class="row yto-search">
				<div class="col-xs-12 col-sm-3">
					<a href="#" class="btn btn-primary btn-lg btn-block"> <i
						class="icon fa-send-o" aria-hidden="true"></i>&nbsp;我要寄件
					</a>
				</div>
				<div class="col-xs-12 col-sm-5">
					<form method="post" id="history_search" action="#">
						<div class="input-group input-group-lg">
							<input name="mailno" id="mailno" value="" class="form-control"
								placeholder="请输入运单号"> <span class="input-group-btn">
								<button class="btn btn-primary" style="height: 48px"
									type="submit">
									<i class="icon fa-search" aria-hidden="true"></i> 查询
								</button>
							</span>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="container m_top_30">
			<div class="yto-box">
				<h5>预约情况</h5>
				<div class="panel panel-default">
					<div class="panel-heading">我的预约单</div>
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>预约单号</th>
									<th>取件网点</th>
									<th>下单时间</th>
									<th>快件品名</th>
									<th>快件备注</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
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
		src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
</body>
</html>