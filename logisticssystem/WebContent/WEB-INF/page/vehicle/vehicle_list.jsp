<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车辆列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/font/font-awesome.min.css">--%>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <%--<link rel="stylesheet"
                href="htt    ps://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">--%>
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
<div id="vehicleList" class="wrapper">
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
                                    class="nav-link active"> <i
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
                        <h1>车辆查询</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a
                                    href="${pageContext.request.contextPath}/loginregister/loginregister_pageStaff">首页</a>
                            </li>
                            <li class="breadcrumb-item active">查询车辆</li>
                        </ol>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">车辆列表</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <div style="width: 250px; float: right; margin-bottom: 10px;"
                                 class="input-group">
                                <input placeholder="据车牌号或编号搜索" @input="selectSearch"
                                       v-model="search" type="text" class="form-control input-sm"><span
                                    class="input-group-addon btn btn-default"><i
                                    class="fa fa-search"></i></span>
                            </div>
                            <div class="table-responsive" style="min-height: 300px">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" @click="checkAll"
                                                   v-model="checkData"></th>
                                        <th>编号</th>
                                        <th>车牌号</th>
                                        <th v-if="myRole==6">
                                            <span role="presentation" class="dropdown">
                                                <a class="dropdown-toggle" data-toggle="dropdown">单位(所有)
                                                    <span class="caret"></span></a>
													<ul class="dropdown-menu">
														<li><a @click="selectUnit('')" href="#">所属单位(所有)</a></li>
														<li v-for="unit in unitList" :key="unit.unit_id"><a
                                                                @click="selectUnit(unit.unit_id)" href="#">{{unit.unit_name}}</a></li>
													</ul>
											</span>
                                        </th>
                                        <th v-if="myRole != 6">
                                            <span role="presentation" class="dropdown">
                                                <a class="dropdown-toggle" data-toggle="dropdown">车队(所有)
                                                    <span class="caret"></span></a>
													<ul class="dropdown-menu">
														<li><a @click="selectTeam('')" href="#">所属车队(所有)</a></li>
														<li v-for="team in teamList" :key="team.team_id"><a
                                                                @click="selectTeam(team.team_id)" href="#">{{team.team_num}}</a></li>
													</ul>
											</span>
                                        </th>
                                        <th>驾驶员工号</th>
                                        <th><span role="presentation" class="dropdown"> <a
                                                class="dropdown-toggle" data-toggle="dropdown">分配情况<span
                                                class="caret"></span></a>
													<ul class="dropdown-menu">
                                                        <li @click="isFenPei('')"><a href="#">所有</a></li>
														<li @click="isFenPei('未分配至单位')"><a href="#">未分配至单位</a></li>
														<li><a @click="isFenPei('未分配至车队')" href="#">未分配至车队</a></li>
														<li><a @click="isFenPei('已分配至驾驶员')" href="#">已完成分配</a></li>
													</ul>
											</span></th>
                                        <th><span role="presentation" class="dropdown"> <a
                                                class="dropdown-toggle" data-toggle="dropdown">载货情况<span
                                                class="caret"></span></a>
													<ul class="dropdown-menu">
                                                        <li><a @click="isZaiHuo('')" href="#">所有</a></li>
														<li><a @click="isZaiHuo('空闲')" href="#">空闲</a></li>
														<li><a @click="isZaiHuo('可载货')" href="#">可载货</a></li>
														<li><a @click="isZaiHuo('已装满')" href="#">已装满</a></li>
														<li><a @click="isZaiHuo('已发车')" href="#">已发车</a></li>
													</ul>
											</span></th>
                                        <th><span
                                                role="presentation" class="dropdown"> <a
                                                class="dropdown-toggle" data-toggle="dropdown">状态（所有）<span
                                                class="caret"></span></a>
													<ul class="dropdown-menu">
                                                        <li><a @click="selectState('')" href="#">所有</a></li>
														<li><a @click="selectState('正常')" href="#">正常</a></li>
														<li><a @click="selectState('维修')" href="#">维修</a></li>
														<li><a @click="selectState('报废')" href="#">报废</a></li>
													</ul>
											</span></th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody v-if="vehicleInfoVO.listVehicleDTO == undefined">
                                    <td style="text-align: center" colspan="10" height="200">
                                        <h3>暂无数据</h3>
                                    </td>
                                    </tbody>
                                    <tbody v-if="!ready">
                                    <tr>
                                        <td style="text-align: center" colspan="10"><i
                                                class="fa fa-spinner fa-spin fa-3x fa-fw"></i></td>
                                    </tr>
                                    </tbody>
                                    <tbody v-cloak
                                           v-if="ready && vehicleInfoVO.listVehicleDTO != undefined"
                                           style="min-height: 200px">
                                    <tr v-for="(listVehicleDTO,index) in vehicleInfoVO.listVehicleDTO">
                                        <td><input :id="listVehicleDTO.vehicleInfo.vehicle_id"
                                                   :uni="listVehicleDTO.unit.unit_id"
                                                   type="checkbox" name="flag" check="check"></td>
                                        <td v-html="listVehicleDTO.vehicleInfo.vehicle_num"></td>
                                        <td v-html="listVehicleDTO.vehicleInfo.vehicle_platenum"></td>
                                        <td v-if="myRole==6 && listVehicleDTO.unit != undefined">
                                            {{listVehicleDTO.unit.unit_name}}
                                        </td>
                                        <td v-if="myRole!=6 && listVehicleDTO.vehicle_TeamDTO.team != undefined">
                                            {{listVehicleDTO.vehicle_TeamDTO.team.team_num}}
                                        </td>
                                        <td v-if="listVehicleDTO.driverDTO && listVehicleDTO.driverDTO.staffBasicInfo">
                                            {{listVehicleDTO.driverDTO.staffBasicInfo.staff_num}}
                                        </td>
                                        <td v-else></td>
                                        <td>{{listVehicleDTO.vehicleInfo.vehicle_distribution_state}}</td>
                                        <td>{{listVehicleDTO.vehicleInfo.vehicle_express_state}}</td>
                                        <td>{{listVehicleDTO.vehicleInfo.vehicle_state}}</td>
                                        <td>
                                            <div class="btn-group">
													<span style="cursor: pointer;" data-toggle="dropdown"
                                                          aria-haspopup="true" aria-expanded="false"> <i
                                                            class="fa fa-th-list"></i>
													</span>
                                                <ul class="dropdown-menu">
                                                    <li><a @click="detailVehicle(listVehicleDTO.vehicleInfo.vehicle_id)"
                                                           href="javascript:void(0);">查看详情</a></li>
                                                    <%--<li>
                                                        <a @click="distVehicleTran(listVehicleDTO.unit.unit_id,listVehicleDTO.vehicleInfo.vehicle_id)"
                                                           href="javascript:void(0);">单位调度</a>
                                                    </li>--%>
                                                    <%--<li><a href="javascript:void(0);">分配车辆到车队</a>--%>
                                                    </li>
                                                    <li v-if="myRole==4">
                                                        <a @click="distVehicleDriver(listVehicleDTO.vehicleInfo.vehicle_id)"
                                                           href="javascript:void(0);">分配车辆到驾驶员</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div style="float: right;">
                                    <a v-if="myRole==6" href="#" @click="distVehicleTran" class="btn btn-info">单位调度</a>
                                    <a v-if="myRole==5" href="#" @click="distVehicleTeam" class="btn btn-info">车队调度</a>
                                    <%--<a v-if="myRole==1 || myRole==3" href="#" @click="expressAddJ"
                                       class="btn btn-danger">快件到站</a>--%>
                                </div>
                                <div class="pagePosition">
                                    <ul v-cloak class="pagination">
                                        <li></li>
                                        <li><a href="#">首页</a></li>
                                        <li :class="{disabled:preDisabled}"><a @click="prePage"
                                                                               href="#">上一页</a></li>
                                        <li><a>第 {{vehicleInfoVO.pageIndex}} 页/总
                                            {{vehicleInfoVO.totalPages}}
                                            页/共{{vehicleInfoVO.totalRecords}}条</a></li>
                                        <li :class="{disabled:nextDisabled}"><a
                                                :disabled="nextDisabled" @click="nextPage" href="#">
                                            下一页 <%--<span aria-hidden="true">&raquo;</span>--%>
                                        </a></li>
                                        <li><a href="#">尾页</a></li>
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
        <%-- 车队列表 --%>
        <div class="modal fade" id="driverDistList">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 模态弹出窗内容 -->
                    <div class="modal_header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                        </button>
                        <h5 class="modal-title">分配驾驶员</h5>
                    </div>
                    <hr>
                    <div class="mdoal-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>工号</th>
                                <th>姓名</th>
                                <th>联系方式</th>
                                <th>是否分配车</th>
                                <th>状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="driver in driverManagerDTO" :key="driver.driver.driver_id">
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
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%-- 车队列表 --%>
        <div class="modal fade" id="teamDistList">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 模态弹出窗内容 -->
                    <div class="modal_header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                        </button>
                        <h5 class="modal-title">车辆车队调度</h5>
                    </div>
                    <hr>
                    <div class="mdoal-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>车队编号</th>
                                <th>车队队长</th>
                                <th>车队路线</th>
                                <th>状态</th>
                                <th>选择</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="team in teamInfoList" :key="team.team.team_id">
                                <td>{{team.team.team_num}}</td>
                                <td>{{team.staff_BasicInfoLeader.staff_num}}</td>
                                <td>{{team.routeDTO.routeInfo.route_num}}</td>
                                <td>{{team.team.team_state}}</td>
                                <td><a @click="selectVehicleTeam(team.team.team_id)" class="btn btn-default"
                                       href="#">分配</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%-- 单位列表 --%>
        <div class="modal fade" id="unitDistList">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 模态弹出窗内容 -->
                    <div class="modal_header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                        </button>
                        <h5 class="modal-title">车辆单位调度</h5>
                    </div>
                    <hr>
                    <div class="mdoal-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>单位编号</th>
                                <th>单位名称</th>
                                <th>单位详细地址</th>
                                <th>联系方式</th>
                                <th>选择</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="unit in unitList" :key="unit.unit_id"
                                v-if="unit.unit_id != distVehicleTranIndex">
                                <td>{{unit.unit_num}}</td>
                                <td>{{unit.unit_name}}</td>
                                <td>{{unit.unit_detailaddress}}</td>
                                <td>{{unit.unit_phonenumber}}</td>
                                <td><a @click="selectVehicleTran(unit.unit_id)" class="btn btn-default"
                                       href="#">分配</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/vehicle_list.js"></script>
</body>
</html>