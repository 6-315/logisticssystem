<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>历史快件</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap4.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminlte.min.css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" data-widget="pushmenu"
                                    href="#"><i class="fa fa-bars"></i></a></li>
            <li class="nav-item d-none d-sm-inline-block"><a href="#"
                                                             class="nav-link">首页</a></li>
            <li class="nav-item d-none d-sm-inline-block"><a href="#"
                                                             class="nav-link">快件管理</a></li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li><a
                    href="${pageContext.request.contextPath }/loginregister/loginregister_logoff"
                    style="float: right;">注销</a></li>
        </ul>
    </nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <a href="#" class="brand-link"> <img
                src="${pageContext.request.contextPath}/img/houtai.png" alt="Logo"
                class="brand-image img-circle elevation-3" style="opacity: .8">
            <span class="brand-text font-weight-light">Note3物流系统</span>
        </a>
        <div class="sidebar">
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img src="${pageContext.request.contextPath}/img/houtouxiang.jpg"
                         class="img-circle elevation-2" alt="User Image">
                </div>
                <div class="info">
                    <a href="#" class="d-block">Note3 管理员</a>
                </div>
            </div>
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column"
                    data-widget="treeview" role="menu" data-accordion="false">
                    <li v-if="myRole==1 || myRole==2 || myRole==3 || myRole==5 || myRole==6"
                        class="nav-item has-treeview"><a href="#" class="nav-link">
                        <i class="nav-icon fa fa-dashboard"></i>
                        <p>
                            快件管理 <i class="fa fa-angle-left right"></i>
                        </p>
                    </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item"><a
                                    href="${pageContext.request.contextPath}/userinfo/userinfo_pageExpressList"
                                    class="nav-link"> <i class="fa fa-book nav-icon"></i>
                                <p>查询快件</p>
                            </a></li>
                            <li v-if="myRole==1 || myRole==2  || myRole==5 || myRole==6" class="nav-item"><a
                                    href="${pageContext.request.contextPath}/expressmanagement/expressmanagement_skipPage"
                                    class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加快件</p>
                            </a></li>
                            <li v-if="myRole==1 || myRole==2  || myRole==5 || myRole==6" class="nav-item"><a
                                    href="${pageContext.request.contextPath}/loginregister/loginregister_pageReservationManager"
                                    class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>预约管理</p>
                            </a></li>
                        </ul>
                    </li>
                    <li v-if="myRole==2 || myRole==5 || myRole==6" class="nav-item"><a href="#" class="nav-link"> <i
                            class="nav-icon fa fa-calendar"></i>
                        <p>用户管理</p>
                    </a></li>
                    <li v-if="myRole == 2 || myRole == 5 || myRole == 6" class="nav-item has-treeview"><a href="#"
                                                                                                          class="nav-link">
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
                        </ul>
                    </li>
                    <li v-if="myRole == 2 || myRole == 5 || myRole == 6" class="nav-item has-treeview"><a href="#"
                                                                                                          class="nav-link">
                        <i class="nav-icon fa fa-dashboard"></i>
                        <p>
                            单位管理 <i class="fa fa-angle-left right"></i>
                        </p>
                    </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item"><a
                                    href="${pageContext.request.contextPath }/loginregister/loginregister_pageUnitList"
                                    class="nav-link"> <i class="fa fa-book nav-icon"></i>
                                <p>单位列表</p>
                            </a></li>
                            <li class="nav-item"><a
                                    href="${pageContext.request.contextPath }/loginregister/loginregister_pageUnitAdd"
                                    class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加单位</p>
                            </a></li>
                        </ul>
                    </li>
                    <li v-if="myRole == 3 || myRole == 4 || myRole == 5 || myRole == 6" class="nav-item has-treeview"><a
                            href="#" class="nav-link">
                        <i class="nav-icon fa fa-dashboard"></i>
                        <p>
                            运输管理 <i class="fa fa-angle-left right"></i>
                        </p>
                    </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item"><a
                                    href="${pageContext.request.contextPath }/loginregister/loginregister_pageVehicleList"
                                    class="nav-link"> <i
                                    class="fa fa-book nav-icon"></i>
                                <p>车辆列表</p>
                            </a></li>
                            <li v-if="myRole == 6" class="nav-item"><a
                                    href="${pageContext.request.contextPath }/loginregister/loginregister_pageVehicleAdd"
                                    class="nav-link"> <i
                                    class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加车辆</p>
                            </a></li>
                            <li v-if="myRole == 5 || myRole == 3 || myRole == 4" class="nav-item"><a
                                    href="${pageContext.request.contextPath }/loginregister/loginregister_pageTeamManager"
                                    class="nav-link"> <i
                                    class="fa fa-plus-square-o nav-icon"></i>
                                <p>车队管理</p>
                            </a></li>
                        </ul>
                    </li>
                    <li v-if="myRole == 6" class="nav-item has-treeview"><a href="#" class="nav-link">
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
                                    href="${pageContext.request.contextPath }/loginregister/loginregister_pageRouteAdd"
                                    class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加路线</p>
                            </a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
    </aside>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>历史快件</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="/test/test/index.html">首页</a></li>
                            <li class="breadcrumb-item active">历史快件</li>
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
                            <h3 class="card-title">历史快件</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table id="example2" class="no-footer table table-bordered table-hover dataTable">
                                <thead>
                                <tr>
                                    <th>单号</th>
                                    <th>内件品名</th>
                                    <th>增值服务</th>
                                    <th>始发站</th>
                                    <th>终点站</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>3150980098909</td>
                                    <td>服饰</td>
                                    <td>保价</td>
                                    <td>萍乡市中转站</td>
                                    <td>南昌市中转站</td>
                                    <td>已揽件</td>
                                    <td>
                                        <a data-toggle="modal" data-target="#detailExpress" href="">详细</a>|
                                        <a href="">删除</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3150980098908</td>
                                    <td>服饰</td>
                                    <td>无</td>
                                    <td>萍乡市中转站</td>
                                    <td>深圳市中转站</td>
                                    <td>已签收</td>
                                    <td>
                                        <a data-toggle="modal" data-target="#detailExpress" href="">详细</a>|
                                        <a data-toggle="modal" data-target="#deleteExpressRecord" href="">删除</a>
                                    </td>
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
            Note3物流系统
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
<div class="modal fade" id="detailExpress">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- 模态弹出窗内容 -->
            <div class="modal_header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">快件详情</h4>
            </div>
            <div class="mdoal-body">
                <p>我的详情</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">修改</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteExpressRecord">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- 模态弹出窗内容 -->
            <div class="modal_header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h5 class="modal-title">删除快件</h5>
            </div>
            <hr>
            <div class="mdoal-body">
                <h4>是否确定删除数据</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-danger">删除</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap4.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.min.js"></script>
<script>
    $(function () {
        $("#example2").DataTable({
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "对不起，查询不到任何相关数据",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_条记录",
                "sInfoEmtpy": "找不到相关数据",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录)",
                "sProcessing": "正在加载中...",
                "sSearch": "搜索",
                "oPaginate": {
                    "sFirst": "第一页",
                    "sPrevious": " 上一页 ",
                    "sNext": " 下一页 ",
                    "sLast": " 最后一页 "
                },
            }
        });
    });
</script>
</body>
</html>