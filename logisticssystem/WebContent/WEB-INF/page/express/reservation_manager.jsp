<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>查询快件</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap4.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/adminlte.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/toastr.css">
    <style type="text/css">
        [v-cloak] {
            display: none;
        }

        .table td, .table th {
            padding: 0.5rem;
            vertical-align: middle;
        }

        .dropdown-menu > li > a {
            display: block;
            clear: both;
            font-size: 14px;
            padding: 6px 10px;
        }

        .dropdown-menu > li > a:hover {
            color: #f9f9f9;
            background-color: #3c8dbc;
        }

        body {
            font-size: 14px;
        }

        .label {
            padding: .2em .6em .3em;
            font-size: 75%;
            border-radius: .25em;
        }

        .input-sm {
            height: 30px;
            padding: 5px 10px;
            line-height: 1.5;
            font-size: 12px;
            border-radius: 3px;
        }

        .pagePosition {
            /*float: right;*/
            margin: auto;
        }

        .pagination > li {
            display: inline;
        }

        .pagination > li > a, .pagination > li > span {
            padding: 6px 12px;
            border: 1px solid #ddd;
        }

        .huodong > a {
            z-index: 3;
            color: #fff;
            cursor: default;
            background-color: #337ab7;
            border-color: #337ab7;
            pointer-events: none;
        }

        .pagination > li > a:hover, .pagination > li > span:focus, .pagination > li > span:hover {
            color: #23527c;
            background-color: #eee;
            border-color: #ddd;
        }

        /*.pagination > li > a:focus {
                    color: #fff;
                    cursor: default;
                    background-color: #337ab7;
                    border-color: #337ab7;
                }*/
        .dropdown-menu {
            max-height: 200px;
            overflow-y: scroll;
        }

        .pagination > .huodong {
            color: #fff;
            cursor: default;
            background-color: #337ab7;
            border-color: #337ab7;
        }

        .pagination > .disabled > a, .pagination > .disabled > a:focus, .pagination > .disabled > a:hover,
        .pagination > .disabled > span, .pagination > .disabled > span:focus,
        .pagination > .disabled > span:hover {
            color: #777;
            cursor: not-allowed;
            background-color: #fff;
            border-color: #ddd;
        }
    </style>
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
            <li class="nav-item d-none d-sm-inline-block"><a
                    href="${pageContext.request.contextPath}/loginregister/loginregister_pageStaff"
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
                </div>
            </li>
            <!-- Notifications Dropdown Menu -->
            <li class="nav-item dropdown"><a class="nav-link"
                                             data-toggle="dropdown" href="#"> <i class="fa fa-bell-o"></i> <span
                    class="badge badge-warning navbar-badge">0</span>
            </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <span class="dropdown-header">0 条消息</span>
                    <!-- <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a> -->
                </div>
            </li>
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
                    <li class="nav-item has-treeview menu-open"><a href="#"
                                                                   class="nav-link active"> <i
                            class="nav-icon fa fa-dashboard"></i>
                        <p>
                            快件管理 <i class="fa fa-angle-left right"></i>
                        </p>
                    </a>
                        <ul class="nav nav-treeview" style="display: block;">
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
                                    class="nav-link active"> <i
                                    class="fa fa-plus-square-o nav-icon"></i>
                                <p>预约管理</p>
                            </a></li>
                        </ul>
                    </li>
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
                        </ul>
                    </li>
                    <li class="nav-item has-treeview"><a href="#" class="nav-link">
                        <i class="nav-icon fa fa-dashboard"></i>
                        <p>
                            单位管理 <i class="fa fa-angle-left right"></i>
                        </p>
                    </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item"><a
                                    href="${pageContext.request.contextPath }/loginregister/loginregister_pageUnitList"
                                    class="nav-link">
                                <i class="fa fa-book nav-icon"></i>
                                <p>单位列表</p>
                            </a></li>
                            <li class="nav-item"><a
                                    href="/test/test/pages/unit/unit_add.html" class="nav-link">
                                <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加单位</p>
                            </a></li>
                        </ul>
                    </li>
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
                        </ul>
                    </li>
                    <li class="nav-item has-treeview"><a href="#" class="nav-link">
                        <i class="nav-icon fa fa-dashboard"></i>
                        <p>
                            路线管理 <i class="fa fa-angle-left right"></i>
                        </p>
                    </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item"><a
                                    href="/test/test/pages/route/route_list.html" class="nav-link">
                                <i class="fa fa-book nav-icon"></i>
                                <p>路线列表</p>
                            </a></li>
                            <li class="nav-item"><a
                                    href="/test/test/pages/route/route_add.html" class="nav-link">
                                <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加路线</p>
                            </a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar --> </aside>

    <!-- Content Wrapper. Contains page content -->
    <div id="reservation_manager" class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>预约管理</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a
                                    href="${pageContext.request.contextPath}/loginregister/loginregister_pageStaff">首页</a>
                            </li>
                            <li class="breadcrumb-item"><a href="/test/test/index.html">订单管理</a></li>
                            <li class="breadcrumb-item active">预约管理</li>
                        </ol>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid --> </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">预约列表</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <div style="width: 250px; float: right; margin-bottom: 10px;"
                                 class="input-group">
                                <input placeholder="据预约单号搜索" @input="searchReservationNum"
                                       v-model="search" type="text" class="form-control input-sm"><span
                                    class="input-group-addon btn btn-default"><i
                                    class="fa fa-search"></i></span>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-hover" style="overflow-y: hidden">
                                    <thead>
                                    <tr>
                                        <th>预约单号</th>
                                        <th>发件人姓名</th>
                                        <th>发件人联系方式</th>
                                        <th>发件人详细地址</th>
                                        <th><span role="presentation" class="dropdown"> <a
                                                class="dropdown-toggle" data-toggle="dropdown">所属单位(所有)<span
                                                class="caret"></span></a>
													<ul class="dropdown-menu">
														<li><a @click="selectUnit('')" href="#">所属单位(所有)</a></li>
														<li v-for="unit in unitList" :key="unit.unit_id"><a
                                                                @click="selectUnit(unit.unit_id)" href="#">{{unit.unit_name}}</a></li>
													</ul>
											</span></th>
                                        <th><span role="presentation" class="dropdown"> <a
                                                class="dropdown-toggle" data-toggle="dropdown">是否分配配送员<span
                                                class="caret"></span></a>
													<ul class="dropdown-menu">
														<li><a @click="distributionStaff('')" href="#">所有</a></li>
														<li><a @click="distributionStaff('是')" href="#">是</a></li>
														<li><a @click="distributionStaff('否')" href="#">否</a></li>
													</ul>
											</span></th>
                                        <th><span role="presentation" class="dropdown"> <a
                                                class="dropdown-toggle" data-toggle="dropdown">状态（所有）<span
                                                class="caret"></span></a>
													<ul class="dropdown-menu">
														<li><a @click="selectState('')" href="#">所有</a></li>
														<li><a @click="selectState('待受理')" href="#">待受理</a></li>
														<li><a @click="selectState('已受理')" href="#">已受理</a></li>
														<li><a @click="selectState('已拒绝')" href="#">已拒绝</a></li>
														<li><a @click="selectState('待取件')" href="#">待取件</a></li>
														<li><a @click="selectState('已取件')" href="#">已取件</a></li>
														<li><a @click="selectState('已完成')" href="#">已完成</a></li>
													</ul>
											</span></th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody v-if="reservationVO.listReservationInfoDTO.length == 0">
                                    <td style="text-align: center" colspan="8" height="50">
                                        暂无数据
                                    </td>
                                    </tbody>
                                    <tbody v-if="!ready">
                                    <tr>
                                        <td style="text-align: center" colspan="8"><i
                                                class="fa fa-spinner fa-spin fa-3x fa-fw"></i></td>
                                    </tr>
                                    </tbody>
                                    <tbody v-cloak
                                           v-if="ready && reservationVO.listReservationInfoDTO.length != 0"
                                           style="min-height: 200px">
                                    <tr
                                            v-for="(reservationDTO,index) in reservationVO.listReservationInfoDTO"
                                            :key="index">
                                        <td v-html="reservationDTO.reservationInfo.reservation_num"></td>
                                        <td>{{reservationDTO.expressInfo.expressinfo_senderrealname}}</td>
                                        <td>{{reservationDTO.expressInfo.expressinfo_senderphonenumber}}</td>
                                        <td>{{reservationDTO.expressInfo.expressinfo_senderdetailaddress}}</td>
                                        <td>{{reservationDTO.unitInfo.unit_name}}</td>
                                        <td
                                                v-if="reservationDTO.reservationInfo.reservation_distributiontor">
                                            是
                                        </td>
                                        <td v-else>否</td>
                                        <td
                                                v-html="replaceState(reservationDTO.reservationInfo.reservation_state)">
                                            <%--<span class="label">{{reservationDTO.reservationInfo.reservation_state}}</span>--%>
                                        </td>
                                        <td>
                                            <div class="btn-group">
													<span style="cursor: pointer;" data-toggle="dropdown"
                                                          aria-haspopup="true" aria-expanded="false"> <i
                                                            class="fa fa-th-list"></i>
													</span>
                                                <ul class="dropdown-menu">
                                                    <li><a href="#">查看详情</a></li>
                                                    <li><a
                                                            @click="acceptanceReservation('已受理',reservationDTO.reservationInfo.reservation_state,reservationDTO.reservationInfo.reservation_id)"
                                                            href="#">受理</a></li>
                                                    <li><a
                                                            @click="cancleReservation('已拒绝',reservationDTO.reservationInfo.reservation_state,reservationDTO.reservationInfo.reservation_id)"
                                                            href="#">拒绝</a></li>
                                                    <li><a
                                                            @click="opendistributionReservationStaff(reservationDTO.reservationInfo.reservation_state,reservationDTO.reservationInfo.reservation_id)"
                                                            href="#">分配配送员</a></li>
                                                    <li><a
                                                            @click="takePart('已取件',reservationDTO.reservationInfo.reservation_state,reservationDTO.reservationInfo.reservation_id)"
                                                            href="#">已取件</a></li>
                                                    <li><a
                                                            @click="completeReserv('已完成',reservationDTO.reservationInfo.reservation_state,reservationDTO.reservationInfo.reservation_id)"
                                                            href="#">已完成</a></li>
                                                    <li><a
                                                            @click="skipExpressPage(reservationDTO.reservationInfo.reservation_id)"
                                                            href="#">填写快件单</a></li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="pagePosition">
                                    <ul v-cloak class="pagination">
                                        <li></li>
                                        <li><a @click="shouye" href="#">首页</a></li>
                                        <li :class="{disabled:preDisabled}"><a @click="prePage"
                                                                               href="#">上一页</a></li>
                                        <li><a>第 {{reservationVO.pageIndex}} 页/总
                                            {{reservationVO.totalPages}}
                                            页/共{{reservationVO.totalRecords}}条</a></li>
                                        <li :class="{disabled:nextDisabled}"><a
                                                :disabled="nextDisabled" @click="nextPage" href="#"> 下一页
                                            <%--<span aria-hidden="true">&raquo;</span>--%>
                                        </a></li>
                                        <li><a @click="weiye" href="#">尾页</a></li>
                                        <li></li>
                                    </ul>
                                </div>
                                <div>

                                    <%--<button @click="" class="btn btn-default">首页</button>
                                    <button @click="" class="btn btn-default">上一页</button>
                                    <button @click="" class="btn btn-default">下一页</button>
                                    <button @click="" class="btn btn-default">尾页</button>--%>
                                </div>
                            </div>
                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row --> </section>
        <div class="modal fade" id="distributionReservationStaff">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 模态弹出窗内容 -->
                    <div class="modal_header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                        </button>
                        <h5 class="modal-title">分配配送员</h5>
                    </div>
                    <hr>
                    <div class="mdoal-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>工号</th>
                                <th>姓名</th>
                                <th>联系方式</th>
                                <th>性别</th>
                                <th>员工状态</th>
                                <th>分配</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="dis in listDistributiontorAndStaffBasicinfoDTO"
                                :key="dis.staffBasicinfo.staff_id">
                                <td>{{dis.staffBasicinfo.staff_num}}</td>
                                <td>{{dis.staffBasicinfo.staff_name}}</td>
                                <td>{{dis.staffBasicinfo.staff_phonenumber}}</td>
                                <td>{{dis.staffBasicinfo.staff_sex}}</td>
                                <td>{{dis.staffBasicinfo.staff_state}}</td>
                                <td><a
                                        @click="distributePerson(dis.distributiontorNew.distributiontor_id)"
                                        class="btn btn-default">分配</a></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.content -->
        <div class="modal fade" id="reservationDetail">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 模态弹出窗内容 -->
                    <div class="modal_header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title">快件详情</h4>
                    </div>
                    <div class="mdoal-body">
                        <p>我的详情</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">关闭
                        </button>
                        <button type="button" class="btn btn-primary">保存</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="deleteModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 模态弹出窗内容 -->
                    <div class="modal_header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                        </button>
                        <h5 class="modal-title">快件详情</h5>
                    </div>
                    <hr>
                    <div class="mdoal-body">
                        <h4>是否确定删除数据</h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">关闭
                        </button>
                        <button type="button" class="btn btn-danger">删除</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer"> <!-- To the right -->
        <div class="float-right d-none d-sm-inline">Note3物流系统</div>
        <!-- Default to the left --> <strong>Copyright <a
                href="./Before-Login.html" title="">&copy;</a> 2018-2018 .
        </strong> All rights reserved.
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark"> <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap4.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
<script
        src="${pageContext.request.contextPath}/js/express/reservation_manager.js"></script>
</body>
</html>