<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>人事管理-添加员工</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/select2/select2.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/adminlte.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/plugins/city-picker/css/city-picker.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/toastr.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
    <style>
        body {
            font-size: 14px;
        }
    </style>
</head>
<body class="hold-transition sidebar-mini" style="font-size: 14px;">
<div id="staffAdd" class="wrapper">
    <!-- Navbar -->
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#"><i class="fa fa-bars"></i></a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="#" class="nav-link">首页</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="#" class="nav-link">快件管理</a>
            </li>
        </ul>

        <!-- SEARCH FORM -->
        <%--<form class="form-inline ml-3">
            <div class="input-group input-group-sm">
                <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-navbar" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
        </form>--%>

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <!-- Messages Dropdown Menu -->

        </ul>
    </nav>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="#" class="brand-link">
            <img src="${pageContext.request.contextPath}/img/houtai.png" alt="AdminLTE Logo"
                 class="brand-image img-circle elevation-3"
                 style="opacity: .8">
            <span class="brand-text font-weight-light">Note3物流系统</span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img src="${pageContext.request.contextPath}/img/houtouxiang.jpg" class="img-circle elevation-2"
                         alt="User Image">
                </div>
                <div class="info">
                    <a href="#" class="d-block">Note3 管理员</a>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                    data-accordion="false">
                    <!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->
                    <li v-if="myRole==1 || myRole==2 || myRole==3 || myRole==5 || myRole==6"
                        class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                快件管理
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview" style="display: none;">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/userinfo/userinfo_pageExpressList"
                                   class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>查询快件</p>
                                </a>
                            </li>
                            <li v-if="myRole==1 || myRole==2  || myRole==5 || myRole==6" class="nav-item"
                                class="nav-item">
                                <a href="${pageContext.request.contextPath}/expressmanagement/expressmanagement_skipPage"
                                   class="nav-link">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>增加快件</p>
                                </a>
                            </li>
                            <li v-if="myRole==1 || myRole==2  || myRole==5 || myRole==6" class="nav-item"><a
                                    href="${pageContext.request.contextPath}/loginregister/loginregister_pageReservationManager"
                                    class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>预约管理</p>
                            </a></li>
                        </ul>
                    </li>
                    <li v-if="myRole==2 || myRole==5 || myRole==6" class="nav-item">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-calendar"></i>
                            <p>用户管理</p>
                        </a>
                    </li>
                    <li v-if="myRole == 2 || myRole == 5 || myRole == 6" class="nav-item has-treeview menu-open">
                        <a href="#" class="nav-link active">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                人事管理
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/loginregister/loginregister_pageStaffList"
                                   class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>员工查询</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/loginregister/loginregister_pageStaffAdd"
                                   class="nav-link active">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>招聘员工</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li v-if="myRole == 2 || myRole == 5 || myRole == 6" class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                单位管理
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath }/loginregister/loginregister_pageUnitAdd"
                                   class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>单位列表</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath }/loginregister/loginregister_pageUnitAdd"
                                   class="nav-link">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>增加单位</p>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li v-if="myRole == 3 || myRole == 4 || myRole == 5 || myRole == 6" class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                车辆管理
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath }/loginregister/loginregister_pageVehicleList" class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>车辆列表</p>
                                </a>
                            </li>
                            <li v-if="myRole == 6" class="nav-item">
                                <a href="${pageContext.request.contextPath }/loginregister/loginregister_pageVehicleAdd"
                                   class="nav-link">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>增加车辆</p>
                                </a>
                            </li>
                            <li class="nav-item"><a
                                    href="${pageContext.request.contextPath }/loginregister/loginregister_pageTeamManager"
                                    class="nav-link"> <i
                                    class="fa fa-plus-square-o nav-icon"></i>
                                <p>车队管理</p>
                            </a></li>
                        </ul>
                    </li>
                    <li v-if="myRole == 6" class="nav-item has-treeview">
                        <a href="#" class="nav-link">
                            <i class="nav-icon fa fa-dashboard"></i>
                            <p>
                                路线管理
                                <i class="fa fa-angle-left right"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath }/loginregister/loginregister_pageRouteList"
                                   class="nav-link">
                                    <i class="fa fa-book nav-icon"></i>
                                    <p>路线列表</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath }/loginregister/loginregister_pageRouteAdd"
                                   class="nav-link">
                                    <i class="fa fa-plus-square-o nav-icon"></i>
                                    <p>增加路线</p>
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
                        <h1>添加员工</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">首页</a></li>
                            <li class="breadcrumb-item active">添加员工</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <div v-cloak class="content">
            <div class="container-fluid">
                <!-- SELECT2 EXAMPLE -->
                <div class="card card-default">
                    <div class="card-header">
                        <h3 class="card-title">员工信息</h3>

                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i></button>
                        </div>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>员工工号</label>
                                    <input type="hidden" id="shuju" value="${idList}">
                                    <input disabled v-model="staffBasicInfo.staff_num" type="text"
                                           class="form-control"
                                           placeholder="员工工号..">
                                </div>
                                <div class="form-group">
                                    <label>员工姓名</label>
                                    <input v-model="staffBasicInfo.staff_name" type="text" class="form-control"
                                           placeholder="请输入员工姓名..">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>联系方式</label>
                                    <input v-model="staffBasicInfo.staff_phonenumber" type="text" class="form-control"
                                           placeholder="请输入联系方式..">
                                </div>
                                <div class="form-group">
                                    <label>所属单位</label>
                                    <select v-model="staffBasicInfo.staff_unit" class="form-control"
                                            style="width: 100%;">
                                        <option :value="unit.unit_id" v-for="unit in unitList">{{unit.unit_name}}
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>职位</label>
                                    <select v-model="staffBasicInfo.staff_position" class="form-control"
                                            style="width: 100%;">
                                        <option v-for="pos in positionList" :value="pos.position_id">
                                            {{pos.position_name}}
                                        </option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>性别:</label>
                                    <select v-model="staffBasicInfo.staff_sex" class="form-control"
                                            style="width: 100%;">
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>入职时间:</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <i class="fa fa-calendar"></i>
                                                </span>
                                        </div>
                                        <input v-model="staffBasicInfo.staff_entrytime" type="text"
                                               class="form-control float-right" id="entrytime">
                                    </div>
                                    <!-- /.input group -->
                                </div>
                                <div class="from-group">
                                    <label>出生年月:</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                              <span class="input-group-text">
                                                <i class="fa fa-calendar"></i>
                                              </span>
                                        </div>
                                        <input v-model="staffBasicInfo.staff_birthday" type="text"
                                               class="form-control float-right" id="birthday">
                                    </div>
                                    <!-- /.input group -->
                                </div>
                                <div class="form-group">
                                    <button @click="saveStaff" type="button"
                                            style="width: 120px;float: right;margin-right: 7px"
                                            class="btn btn-block btn-primary btn-lg">提交
                                    </button>
                                </div>
                                <!-- /.form-group -->
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.card-body -->
                    <div class="card-footer">
                        所添加的员工信息应当真实可靠
                    </div>
                </div>
            </div>
        </div>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/city-picker/js/city-picker.data.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/city-picker/js/city-picker.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
<script>
    $(function () {
        $('.select2').select2()
    })
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/staff/staff_add.js"></script>
</body>
</html>