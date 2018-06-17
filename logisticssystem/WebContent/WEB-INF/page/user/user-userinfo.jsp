<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>我的订单</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/tool/site.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
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
				<li><a
					href="${pageContext.request.contextPath}/user-userinfo.jsp">&nbsp;我的信息</a></li>
				<li>&nbsp;基本信息</li>
			</ol>
			<div class="yto-box">
				<div class="row">
					<div class="col-sm-2 hidden-xs">
						<div class="my-avatar center-block p_bottom_10">
							<span class="avatar"> <img alt="..."
								src="${pageContext.request.contextPath}/img/touxiang.jpg">
							</span>
						</div>
						<h5 class="text-center p_bottom_10">您好！18296929245</h5>
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a
								href="${pageContext.request.contextPath}/user-userinfo.jsp">基本信息</a></li>
							<li><a
								href="${pageContext.request.contextPath}/userinfo/userinfo_pageMyExpress">地址管理</a></li>
							<li><a
								href="${pageContext.request.contextPath}/user-updatepassword.jsp">密码修改</a></li>
							<li><a
								href="${pageContext.request.contextPath}/user-mymessage.jsp">我的消息</a></li>
						</ul>
					</div>
					<div class="col-sm-10 b-left">
						<p class="alert alert-danger hidden" role="alert" id="error"></p>
						<form id="userinfo_save" method="POST" action=""
							class="form-horizontal">
							<input name="cSRFToken"
								value="0051f077-3fd2-48a7-a72a-9404139cfa08" type="hidden">
							<div class="form-group">
								<label for="oldpass" class="col-sm-2 control-label">头像</label>
								<div class="col-sm-4">
									<div class="avatar-edit" title="" data-original-title="点击上传头像">
										<img id="headUrlImg"
											src="${pageContext.request.contextPath}/img/touxiang.jpg">
										<input name="headFile" class="headimg" type="hidden">
										<div class="loading" aria-label="Loading" role="img"
											tabindex="-1"></div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="oldpass" class="col-sm-2 control-label">昵称</label>
								<div class="col-sm-4">
									<input name="nickname" id="nickname"
										onkeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
										maxlength="15" value="18296929245" title="nickname"
										class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label for="oldpass" class="col-sm-2 control-label">手机号码</label>
								<div class="col-sm-4">
									<input name="username"
										onkeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
										value="18296929245" class="form-control" title="username"
										readonly="">
								</div>
							</div>
							<div class="form-group">
								<label for="oldpass" class="col-sm-2 control-label">性别</label>
								<div class="col-sm-4">

									<label class="radio-inline"><div
											class="iradio_flat-grey" style="position: relative;">
											<input name="sex" value="M"
												style="position: absolute; opacity: 0;" type="radio">
											<ins class="iCheck-helper"
												style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins>
										</div>男</label> <label class="radio-inline"><div
											class="iradio_flat-grey" style="position: relative;">
											<input name="sex" value="F"
												style="position: absolute; opacity: 0;" type="radio">
											<ins class="iCheck-helper"
												style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins>
										</div>女</label>

								</div>
							</div>
							<div class="form-group">
								<label for="oldpass" class="col-xs-12 col-sm-2 control-label">出生日期</label>
								<div class="col-sm-4">
									<input class="form-control time-control" value="" id="birthday"
										size="16" type="text"> <input name="birthday" value=""
										id="birthday_hidden" type="hidden">
								</div>
							</div>
							<div class="form-group">
								<label for="oldpass" class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-4">
									<input name="email"
										onkeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
										value="" title="email" class="form-control"
										placeholder="请输入邮箱">
								</div>
							</div>
							<div class="form-group">
								<label for="oldpass" class="col-sm-2 control-label">所在地区</label>
								<div class="col-sm-4">
									<input name="regionId" value="0" type="hidden">
									<div class="dropdown yto-city">
										<input class="form-control " placeholder="请输入所在地区"
											id="address" name="region" value="" data-regionid="regionId"
											readonly="readonly" type="text">
										<div class="yto-city-box dropdown-menu">
											<ul>
												<li class="hover">省份</li>
												<li>市区</li>
												<li>县区</li>
											</ul>
											<div class="yto-city-cont">
												<dl class="ytoprov"></dl>
												<dl class="ytocity"></dl>
												<dl class="ytodist"></dl>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="oldpass" class="col-sm-2 control-label">详细地址</label>
								<div class="col-sm-4">
									<input name="address"
										onkeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
										id="fullAddress" maxlength="30" value="" title="address"
										class="form-control" placeholder="请输入详细地址">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2  col-sm-4">
									<input value="修改" class="btn btn-primary btn-block m_top_20"
										type="submit">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="avatar-modal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">修改头像</h4>
				</div>

				<div class="modal-body  avatar-form">
					<div class="avatar-body">

						<!-- Upload image and data -->
						<div class="avatar-upload">
							<input class="avatar-src" name="avatar-src" type="hidden">
							<input class="avatar-data" name="avatar-data" type="hidden">

							<div class="input-group input-group-file">

								<span class="btn btn-default btn-file"> <i
									aria-hidden="true" class="icon fa-upload"></i> <span>浏览本地照片</span>
									<input class="avatar-input" id="avatarInput"
									name="avatar-input" type="file">
								</span>
							</div>
						</div>
						<!-- Crop and preview -->
						<div class="row">
							<div class="col-md-9">
								<div class="avatar-wrapper"></div>
							</div>
							<div class="col-md-3">
								<div class="avatar-preview preview-lg"></div>
								<div class="avatar-preview preview-md"></div>
								<div class="avatar-preview preview-sm"></div>
							</div>
						</div>
						<div class="row avatar-btns">
							<div class="col-md-4 col-md-offset-4">
								<button type="botton"
									class="btn btn-primary btn-block avatar-save">完成</button>
							</div>
						</div>
					</div>
				</div>
				<!-- </form> -->
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
		src="${pageContext.request.contextPath}/plugins/iCheck/icheck.js"></script>
	<script type="text/javascript"
		src="http://ec.yto.net.cn/assets/js/page.js"></script>
</body>
</html>