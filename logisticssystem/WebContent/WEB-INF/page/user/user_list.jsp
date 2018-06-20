<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>查找用户</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap4.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminlte.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#"><i class="fa fa-bars"></i></a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="#" class="nav-link">??</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="#" class="nav-link">????</a>
            </li>
        </ul>

        <!-- SEARCH FORM -->
        <form class="form-inline ml-3">
            <div class="input-group input-group-sm">
                <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
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
            <li class="nav-item dropdown">
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="fa fa-comments-o"></i>
                    <span class="badge badge-danger navbar-badge"></span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
                </div>
            </li>
            <!-- Notifications Dropdown Menu -->
            <li class="nav-item dropdown">
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="fa fa-bell-o"></i>
                    <span class="badge badge-warning navbar-badge">0</span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <span class="dropdown-header">0 ???</span>
                    <!-- <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a> -->
                </div>
            </li>
        </ul>
    </nav>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="#" class="brand-link">
            <img src="../../img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
                 style="opacity: .8">
            <span class="brand-text font-weight-light">Note3????</span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img src="../../img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
                </div>
                <div class="info">
                    <a href="#" class="d-block">Note3 ???</a>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                    data-accordion="false">
                    <!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                ????
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview" style="display: none;">
                            <li class="nav-item">
                                <a href="/test/test/pages/express/express_list.html" class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/test/test/pages/express/express_add.html" class="nav-link">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a href="/test/test/pages/user/user_list.html" class="nav-link active">
                            <i class="nav-icon fa fa-calendar"></i>
                            <p>????</p>
                        </a>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                ????
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="/test/test/pages/staff/staff_list.html" class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/test/test/pages/staff/staff_add.html" class="nav-link">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                ????
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="/test/test/pages/unit/unit_list.html" class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/test/test/pages/unit/unit_add.html" class="nav-link">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                ????
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="/test/test/pages/vehicle/vehicle_list.html" class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/test/test/pages/vehicle/vehicle_add.html" class="nav-link">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                ????
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="/test/test/pages/route/route_list.html" class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/test/test/pages/route/route_add.html" class="nav-link">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>????</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>????</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="/test/test/index.html">??</a></li>
                            <li class="breadcrumb-item active">????</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">????</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table id="example2" class="no-footer table table-bordered table-hover dataTable">
                                <thead>
                                <tr>
                                    <th>??</th>
                                    <th>????</th>
                                    <th>????</th>
                                    <th>??</th>
                                    <th>????</th>
                                    <th>??</th>
                                    <th>????????</th>
                                    <th>??</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>??</td>
                                    <td>???</td>
                                    <td>18279976773</td>
                                    <td>135ee@qq.com</td>
                                    <td>2018-05-10</td>
                                    <td>????</td>
                                    <td>2018-06-10</td>
                                    <td>
                                        <a href="/test/test/pages/express_history.html">????</a>|
                                        <a data-toggle="modal" data-target="#userDetailInfo" href="">??</a>|
                                        <a data-toggle="modal" data-target="#deleteUserInfo" href="">??</a>
                                    </td>
                                    <!-- <td>
                                      <button type="button" class="btn btn-block btn-primary btn-sm">Primary</button>
                                    </td> -->
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="float-right d-none d-sm-inline">
            Note3????
        </div>
        <!-- Default to the left -->
        <strong>Copyright <a href="./Before-Login.html" title="">&copy;</a> 2018-2018 .</strong> All rights reserved.
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
<div class="modal fade" id="userDetailInfo">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- ??????? -->
            <div class="modal_header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">????</h4>
            </div>
            <div class="mdoal-body">
                <p>????</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">??</button>
                <button type="button" class="btn btn-primary">??</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteUserInfo">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- ??????? -->
            <div class="modal_header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h5 class="modal-title">????</h5>
            </div>
            <hr>
            <div class="mdoal-body">
                <h4>????????</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">??</button>
                <button type="button" class="btn btn-danger">??</button>
            </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap4.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<!-- <script src="../js/demo.js"></script> -->
<!-- page script -->
<script>
    $(function () {
        $("#example2").DataTable({
            "oLanguage": {
                "sLengthMenu": "???? _MENU_ ???",
                "sZeroRecords": "??????????????",
                "sInfo": "???? _START_ ? _END_ ??? _TOTAL_???",
                "sInfoEmtpy": "???????",
                "sInfoFiltered": "?????? _MAX_ ???)",
                "sProcessing": "?????...",
                "sSearch": "??",
                "oPaginate": {
                    "sFirst": "???",
                    "sPrevious": " ??? ",
                    "sNext": " ??? ",
                    "sLast": " ???? "
                },
            }
        });
    });
</script>
</body>
</html>