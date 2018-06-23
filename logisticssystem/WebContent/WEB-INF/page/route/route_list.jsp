<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>在线物流管理系统</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">
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
			src="./img/AdminLTELogo.png" alt="AdminLTE Logo"
			class="brand-image img-circle elevation-3" style="opacity: .8">
			<span class="brand-text font-weight-light">Note3物流系统</span>
		</a> <!-- Sidebar -->
		<div class="sidebar">
			<!-- Sidebar user panel (optional) -->
			<div class="user-panel mt-3 pb-3 mb-3 d-flex">
				<div class="image">
					<img src="./img/user2-160x160.jpg" class="img-circle elevation-2"
						alt="User Image">
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
					<ul class="nav nav-treeview">
						<li class="nav-item"><a
							href="./pages/express/express_list.html" class="nav-link"> <i
								class="fa fa-book nav-icon"></i>
								<p>查询快件</p>
						</a></li>
						<li class="nav-item"><a
							href="./pages/express/express_add.html" class="nav-link"> <i
								class="fa fa-plus-square-o nav-icon"></i>
								<p>增加快件</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a
					href="/test/test/pages/user/user_list.html" class="nav-link"> <i
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
							href="/test/test/pages/staff/staff_list.html" class="nav-link">
								<i class="fa fa-book nav-icon"></i>
								<p>员工查询</p>
						</a></li>
						<li class="nav-item"><a
							href="/test/test/pages/staff/staff_add.html" class="nav-link">
								<i class="fa fa-plus-square-o nav-icon"></i>
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
							href="${pageContext.request.contextPath }/loginregister/loginregister_pageUnitAdd" class="nav-link">
								<i class="fa fa-book nav-icon"></i>
								<p>单位列表</p>
						</a></li>
						<li class="nav-item"><a
							href="/test/test/pages/unit/unit_add.html" class="nav-link">
								<i class="fa fa-plus-square-o nav-icon"></i>
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
						<li class="nav-item"><a
							href="/test/test/pages/vehicle/vehicle_list.html"
							class="nav-link"> <i class="fa fa-book nav-icon"></i>
								<p>车辆列表</p>
						</a></li>
						<li class="nav-item"><a
							href="/test/test/pages/vehicle/vehicle_add.html" class="nav-link">
								<i class="fa fa-plus-square-o nav-icon"></i>
								<p>增加车辆</p>
						</a></li>
					</ul></li>
				<li class="nav-item has-treeview"><a href="#" class="nav-link">
						<i class="nav-icon fa fa-dashboard"></i>
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
							href="/test/test/pages/route/route_add.html" class="nav-link">
								<i class="fa fa-plus-square-o nav-icon"></i>
								<p>增加路线</p>
						</a></li>
					</ul></li>
			</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</div>
		<!-- /.sidebar --> </aside>
		<!-- 内容结束 -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0 text-dark"></h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="/tet/test/index.html">首页</a></li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<div class="content">
				<div class="container-fluid"></div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<!-- Main Footer -->
		<footer class="main-footer"> <!-- To the right -->
		<div class="float-right d-none d-sm-inline">Note3物流系统</div>
		<!-- Default to the left --> <strong>Copyright <a
			href="/test/test/Before-Login.html" title="">&copy;</a> 2018-2018 .
		</strong> All rights reserved. </footer>
	</div>
	<script
		src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${pageContext.request.contextPath}/js/adminlte.min.js"></script>
</body>
</html>