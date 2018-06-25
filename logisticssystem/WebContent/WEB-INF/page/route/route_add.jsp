<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>添加车辆</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/iCheck/all.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/select2/select2.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminlte.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/plugins/city-picker/css/city-picker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datetimepicker.min.css">
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<!-- Navbar -->
		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand bg-white navbar-light border-bottom">
		<!-- Left navbar links -->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
				href="#"><i class="fa fa-bars"></i></a></li>
			<li class="nav-item d-none d-sm-inline-block"><a href="#"
				class="nav-link">首页</a></li>
			<li class="nav-item d-none d-sm-inline-block"><a href="#"
				class="nav-link">快件管理</a></li>
		</ul>

		<!-- SEARCH FORM -->
		<form class="form-inline ml-3">
			<div class="input-group input-group-sm">
				<input class="form-control form-control-navbar" type="search"
					placeholder="Search" aria-label="Search">
				<div class="input-group-append">
					<button class="btn btn-navbar" type="submit">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</div>
		</form>

		<!-- Right navbar links -->
		<ul class="navbar-nav ml-auto">
			<!-- Messages Dropdown Menu -->
			<li class="nav-item dropdown"><a class="nav-link"
				data-toggle="dropdown" href="#"> <i class="fa fa-comments-o"></i>
					<span class="badge badge-danger navbar-badge"></span>
			</a>
				<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
					<a href="#" class="dropdown-item dropdown-footer">See All
						Messages</a>
				</div></li>
			<!-- Notifications Dropdown Menu -->
			<li class="nav-item dropdown"><a class="nav-link"
				data-toggle="dropdown" href="#"> <i class="fa fa-bell-o"></i> <span
					class="badge badge-warning navbar-badge">0</span>
			</a>
				<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
					<span class="dropdown-header">0 条消息</span>
					<!-- <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a> -->
				</div></li>
		</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
		<!-- Brand Logo --> <a href="#" class="brand-link"> <img
			src="${pageContext.request.contextPath}/img/houtai.png"
			alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
			style="opacity: .8"> <span class="brand-text font-weight-light">Note3物流系统</span>
		</a> <!-- Sidebar -->
		<div class="sidebar">
			<!-- Sidebar user panel (optional) -->
			<div class="user-panel mt-3 pb-3 mb-3 d-flex">
				<div class="image">
					<img src="${pageContext.request.contextPath}/img/houtouxiang.jpg"
						class="img-circle elevation-2" alt="User Image">
				</div>
				<div class="info">
					<a href="#" class="d-block">Note3 管理员</a>
				</div>
			</div>

			<!-- Sidebar Menu -->
			<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->
				<li class="nav-item has-treeview"><a href="#" class="nav-link">
						<i class="nav-icon fa fa-dashboard"></i>
						<p>
							快件管理 <i class="fa fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" style="display: none;">
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/userinfo/userinfo_pageExpressList"
							class="nav-link"> <i class="fa fa-book nav-icon"></i>
								<p>查询快件</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/expressmanagement/expressmanagement_skipPage"
							class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
								<p>增加快件</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/loginregister/loginregister_pageReservationManager"
							class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
								<p>预约管理</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fa fa-calendar"></i>
						<p>用户管理</p>
				</a></li>
				<li class="nav-item has-treeview"><a href="#" class="nav-link">
						<i class="nav-icon fa fa-dashboard"></i>
						<p>
							人事管理 <i class="fa fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/loginregister/loginregister_pageStaffList"
							class="nav-link"> <i class="fa fa-book nav-icon"></i>
								<p>员工查询</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath}/loginregister/loginregister_pageStaffAdd"
							class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
								<p>招聘员工</p>
						</a></li>
					</ul></li>
				<li class="nav-item has-treeview"><a href="#" class="nav-link">
						<i class="nav-icon fa fa-dashboard"></i>
						<p>
							单位管理 <i class="fa fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a
							href="${pageContext.request.contextPath }/loginregister/loginregister_pageUnitAdd"
							class="nav-link"> <i class="fa fa-book nav-icon"></i>
								<p>单位列表</p>
						</a></li>
						<li class="nav-item"><a
							href="${pageContext.request.contextPath }/loginregister/loginregister_pageUnitAdd"
							class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
								<p>增加单位</p>
						</a></li>
					</ul></li>
				<li class="nav-item has-treeview"><a href="#" class="nav-link">
						<i class="nav-icon fa fa-dashboard"></i>
						<p>
							车辆管理 <i class="fa fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fa fa-book nav-icon"></i>
								<p>车辆列表</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="fa fa-plus-square-o nav-icon"></i>
								<p>增加车辆</p>
						</a></li>
					</ul></li>
				<li class="nav-item has-treeview menu-open"><a href="#"
					class="nav-link active"> <i class="nav-icon fa fa-dashboard"></i>
						<p>
							路线管理 <i class="fa fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a
							href="${pageContext.request.contextPath }/loginregister/loginregister_pageRouteList"
							class="nav-link"> <i class="fa fa-book nav-icon"></i>
								<p>路线列表</p>
						</a></li>
						<li class="nav-item"><a
							href=${pageContext.request.contextPath }
							/loginregister/loginregister_pageRouteAdd" class="nav-link active">
								<i class="fa fa-plus-square-o nav-icon"></i>
								<p>增加路线</p>
						</a></li>
					</ul></li>
			</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</div>
		<!-- /.sidebar --> </aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>新增路线</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="/test/test/index.html">首页</a></li>
							<li class="breadcrumb-item active">新增路线</li>
						</ol>
					</div>
				</div>
			</div>
			<!-- /.container-fluid --> </section>

			<!-- Main content -->
			<section class="content">
			<div class="container-fluid">
				<!-- SELECT2 EXAMPLE -->
				<div class="card card-default">
					<div class="card-header">
						<h3 class="card-title">车辆信息</h3>
						<div class="card-tools">
							<button type="button" class="btn btn-tool" data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<!-- /.card-header -->
					<div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="route_num">路线编号</label> <input type="text"
										class="form-control" id="route_num" placeholder="请输入路线编号..">
								</div>
								<div class="form-group">
									<label>路线始发站</label> <select class="form-control select2"
										style="width: 100%;">
										<option selected="selected">中转站1</option>
										<option>中转站2</option>
										<option>中转站3</option>
									</select>
								</div>
								<div class="form-group">
									<label>路线终点站</label> <select class="form-control select2"
										style="width: 100%;">
										<option>中转站1</option>
										<option selected="selected">中转站2</option>
										<option>中转站3</option>
									</select>
								</div>
								<div class="form-group">
									<label>路线创建者</label> <select class="form-control select2"
										style="width: 100%;">
										<option selected="selected">0001</option>
										<option>0002</option>
										<option>0003</option>
									</select>
								</div>
								<div class="form-group">
									<label>创建时间:</label>
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"> <i
												class="fa fa-calendar"></i>
											</span>
										</div>
										<input type="text" class="form-control float-right"
											id="route_createtime">
									</div>
									<!-- /.input group -->
								</div>
								<div class="form-group">
									<label>车辆购置人</label> <select class="form-control select2"
										style="width: 100%;">
										<option selected="selected">张三</option>
										<option>李四</option>
										<option>王五</option>
									</select>
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<button type="button"
										style="width: 120px; float: right; margin-right: 7px"
										class="btn btn-block btn-primary btn-lg">提交</button>
								</div>
							</div>
							<!-- /.col -->
							<!-- /.col -->
						</div>
						<!-- /.row -->
					</div>
					<!-- /.card-body -->
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer"> <!-- To the right -->
		<div class="float-right d-none d-sm-inline">Note3物流系统</div>
		<!-- Default to the left --> <strong>Copyright <a
			href="./Before-Login.html" title="">&copy;</a> 2018-2018 .
		</strong> All rights reserved. </footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark"> <!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
	<script src="${pageContext.request.contextPath}/js/adminlte.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/city-picker/js/city-picker.data.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/city-picker/js/city-picker.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datetimepicker.min.js"></script>
	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()
			//Datemask dd/mm/yyyy
			$('#datemask').inputmask('dd/mm/yyyy', {
				'placeholder' : 'dd/mm/yyyy'
			})
			//Datemask2 mm/dd/yyyy
			$('#datemask2').inputmask('mm/dd/yyyy', {
				'placeholder' : 'mm/dd/yyyy'
			})
			//Money Euro
			$('[data-mask]').inputmask()
			$('#route_createtime').datetimepicker({
				minView : "month",//设置只显示到月份
				format : "yyyy-mm-dd",//日期格式
				autoclose : true,//选中关闭
				todayBtn : true
			//今日按钮
			})
			// $('#birthday').datetimepicker({
			// 	minView: "month",//设置只显示到月份
			//  		format : "yyyy-mm-dd",//日期格式
			//  		autoclose:true,//选中关闭
			//  		todayBtn: true//今日按钮
			// })
			//iCheck for checkbox and radio inputs
			$('input[type="checkbox"].minimal, input[type="radio"].minimal')
					.iCheck({
						checkboxClass : 'icheckbox_minimal-blue',
						radioClass : 'iradio_minimal-blue'
					})
			//Red color scheme for iCheck
			$(
					'input[type="checkbox"].minimal-red, input[type="radio"].minimal-red')
					.iCheck({
						checkboxClass : 'icheckbox_minimal-red',
						radioClass : 'iradio_minimal-red'
					})
			//Flat red color scheme for iCheck
			$('input[type="checkbox"].flat-red, input[type="radio"].flat-red')
					.iCheck({
						checkboxClass : 'icheckbox_flat-green',
						radioClass : 'iradio_flat-green'
					})
		})
	</script>
</body>
</html>