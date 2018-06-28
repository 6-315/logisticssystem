<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车队管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap4.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/adminlte.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/toastr.css">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
    <style>
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
<body class="hold-transition sidebar-mini sidebar-collapse">
<div id="teamManager" class="wrapper">
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
                            <li v-if="myRole==1 || myRole==2" class="nav-item"><a
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
                    <li v-if="myRole == 3 || myRole == 4 || myRole == 5 || myRole == 6"
                        class="nav-item has-treeview menu-open"><a
                            href="#" class="nav-link active">
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
                                    class="nav-link active"> <i
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
                        <h1>车队管理</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a
                                    href="${pageContext.request.contextPath}/loginregister/loginregister_pageStaff">首页</a>
                            </li>
                            <li class="breadcrumb-item active">车队管理</li>
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
                            <h3 class="card-title">车队列表</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <%--<div>
                                <a @click="addTeamModal" class="btn btn-default">添加车队</a>
                            </div>--%>
                            <div style="width: 250px; float: right; margin-bottom: 10px;"
                                 class="input-group">
                                <input placeholder="据车队编号搜索" @input="selectSearch"
                                       v-model="search" type="text" class="form-control input-sm"><span
                                    class="input-group-addon btn btn-default"><i
                                    class="fa fa-search"></i></span>
                            </div>
                            <div class="table-responsive" style="min-height: 300px">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>车队编号</th>
                                        <th>车队队长工号</th>
                                        <th>运输路线</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody v-if="teamInfoVO.listTeamDTO == undefined">
                                    <td style="text-align: center" colspan="6" height="50">
                                        暂无数据
                                    </td>
                                    </tbody>
                                    <tbody v-if="!ready">
                                    <tr>
                                        <td style="text-align: center" colspan="6"><i
                                                class="fa fa-spinner fa-spin fa-3x fa-fw"></i></td>
                                    </tr>
                                    </tbody>
                                    <tbody v-cloak
                                           v-if="ready && teamInfoVO.listTeamDTO != undefined"
                                           style="min-height: 200px">
                                    <tr v-for="teamDTO in teamInfoVO.listTeamDTO">
                                        <td v-html="teamDTO.team.team_num"></td>
                                        <td v-if="teamDTO.staff_BasicInfoLeader != undefined">
                                            {{teamDTO.staff_BasicInfoLeader.staff_num}}
                                        </td>
                                        <td v-else></td>
                                        <td>{{teamDTO.routeDTO.routeInfo.route_num}}</td>
                                        <%--<td>{{teamDTO.team.team_state}}</td>--%>
                                        <td>
                                            <a @click="myTeamMember(teamDTO.listDriverInfoDTO)" href="#">车队成员</a>
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
                                        <li><a>第 {{teamInfoVO.pageIndex}} 页/总
                                            {{teamInfoVO.totalPages}}
                                            页/共{{teamInfoVO.totalRecords}}条</a></li>
                                        <li :class="{disabled:nextDisabled}"><a
                                                :disabled="nextDisabled" @click="nextPage" href="#"> 下一页
                                            <%--<span aria-hidden="true">&raquo;</span>--%>
                                        </a></li>
                                        <li><a @click="weiye" href="#">尾页</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->
                </div>
                <!-- /.col -->
            </div>
        </section>

        <%--<div class="modal fade" id="addTeam">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 模态弹出窗内容 -->
                    <div class="modal_header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                        </button>
                        <h5 class="modal-title">添加车队</h5>
                    </div>
                    <hr>
                    <div class="mdoal-body">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label>车队队长</label>
                                <select v-model="teamOb.team_leader" class="form-control">
                                    <option v-for="team in teamLeader" value="team.team_id">{{team.team_leader}}
                                    </option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>所跑路线</label>
                                <select v-model="teamOb.team_route" class="form-control">
                                    <option v-for="route in routeList" value="route.routInfo.route_id">
                                        {{route.routeInfo.route_num}}
                                    </option>
                                </select>
                            </div>
                            &lt;%&ndash;<div class="form-group">
                                <label>状态</label>
                                <select v-model="teamOb.team_state" class="form-control">

                                </select>
                            </div>&ndash;%&gt;
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary">保存</button>
                    </div>
                </div>
            </div>
        </div>--%>

        <div class="modal fade" id="myTeamMember">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 模态弹出窗内容 -->
                    <div class="modal_header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                        </button>
                        <h5 class="modal-title">车队成员</h5>
                    </div>
                    <hr>
                    <div class="mdoal-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>工号</th>
                                <th>姓名</th>
                                <th>联系方式</th>
                                <th>状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="men in member">
                                <td>{{men.staffBasicInfo.staff_num}}</td>
                                <td>{{men.staffBasicInfo.staff_name}}</td>
                                <td>{{men.staffBasicInfo.staff_phonenumber}}</td>
                                <td>{{men.driverInfo.driver_state}}</td>
                            </tr>
                            <%--<tr v-for="driver in driverManagerDTO" :key="driver.driver.driver_id">
                                <td>{{driver.driverUnDistributed.staff_num}}</td>
                                <td>{{driver.driverUnDistributed.staff_name}}</td>
                                <td>{{driver.driverUnDistributed.staff_phonenumber}}</td>
                                <td v-if="driver.driver != undefined">
                                    是
                                </td>
                                <td v-else>
                                    否
                                </td>
                                <td><a @click="selectVehicleDriver(driver.driver.driver_id)" class="btn btn-default"
                                       href="#">分配</a>
                                </td>
                            </tr>--%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer"> <!-- To the right -->
        <div class="float-right d-none d-sm-inline">Note3物流系统</div>
        <!-- Default to the left --> <strong>Copyright <a
                href="${pageContext.request.contextPath }/loginregister/loginregister_logoff" title="">&copy;</a>
            2018-2018 .
        </strong> All rights reserved.
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark"> <!-- Control sidebar content goes here -->
    </aside>

</div>
<!-- ./wrapper -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vehicle/team_manager.js"></script>

</body>
</html>