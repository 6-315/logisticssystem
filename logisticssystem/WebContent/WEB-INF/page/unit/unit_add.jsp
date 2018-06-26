<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>添加员工</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <%--<link rel="stylesheet"--%>
    <%--href="${pageContext.request.contextPath}/plugins/select2/select2.min.css">--%>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/adminlte.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/plugins/city-picker/css/city-picker.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/toastr.css">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper" id="unitAdd">
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
        <!-- <form class="form-inline ml-3">
            <div class="input-group input-group-sm">
                <input class="form-control form-control-navbar" type="search"
                       placeholder="Search" aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-navbar" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
        </form> -->

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <!-- Messages Dropdown Menu -->
            <!-- <li class="nav-item dropdown"><a class="nav-link"
                                             data-toggle="dropdown" href="#"> <i class="fa fa-comments-o"></i>
                <span class="badge badge-danger navbar-badge"></span>
            </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <a href="#" class="dropdown-item dropdown-footer">See All
                        Messages</a>
                </div>
            </li>
            Notifications Dropdown Menu
            <li class="nav-item dropdown"><a class="nav-link"
                                             data-toggle="dropdown" href="#"> <i class="fa fa-bell-o"></i> <span
                    class="badge badge-warning navbar-badge">0</span>
            </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <span class="dropdown-header">0 条消息</span>
                    <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
                </div>
            </li> -->
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
                        <ul class="nav nav-treeview" style="display: none;">
                            <li class="nav-item"><a
                                    href="${pageContext.request.contextPath}/userinfo/userinfo_pageExpressList"
                                    class="nav-link"> <i class="fa fa-book nav-icon"></i>
                                <p>查询快件</p>
                            </a></li>
                            <li v-if="myRole==1 || myRole==2  || myRole==5 || myRole==6" class="nav-item"><a
                                    href="${pageContext.request.contextPath}/expressmanagement/expressmanagement_skipPage"
                                    class="nav-link">
                                <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加快件</p>
                            </a></li>
                            <li v-if="myRole==1 || myRole==2  || myRole==5 || myRole==6" class="nav-item"><a
                                    href="${pageContext.request.contextPath}/loginregister/loginregister_pageReservationManager"
                                    class="nav-link"> <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>预约管理</p>
                            </a></li>
                        </ul>
                    </li>
                    <li v-if="myRole==2 || myRole==5 || myRole==6" class="nav-item"><a
                            href="#" class="nav-link"> <i
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
                    <li v-if="myRole == 2 || myRole == 5 || myRole == 6" class="nav-item has-treeview menu-open"><a
                            href="#"
                            class="nav-link active"> <i
                            class="nav-icon fa fa-dashboard"></i>
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
                                    class="nav-link active"> <i
                                    class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加单位</p>
                            </a></li>
                        </ul>
                    </li>
                    <li v-if="myRole == 3 || myRole == 4 || myRole == 5 || myRole == 6" class="nav-item has-treeview"><a
                            href="#" class="nav-link">
                        <i class="nav-icon fa fa-dashboard"></i>
                        <p>
                            车辆管理 <i class="fa fa-angle-left right"></i>
                        </p>
                    </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item"><a
                                    href="#"
                                    class="nav-link"> <i class="fa fa-book nav-icon"></i>
                                <p>车辆列表</p>
                            </a></li>
                            <li v-if="myRole == 6" class="nav-item"><a
                                    href="#" class="nav-link">
                                <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加车辆</p>
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
                                    class="nav-link">
                                <i class="fa fa-book nav-icon"></i>
                                <p>路线列表</p>
                            </a></li>
                            <li class="nav-item"><a
                                    href="${pageContext.request.contextPath }/loginregister/loginregister_pageRouteAdd"
                                    class="nav-link">
                                <i class="fa fa-plus-square-o nav-icon"></i>
                                <p>增加路线</p>
                            </a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </aside>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>添加单位</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="/test/test/index.html">首页</a></li>
                            <li class="breadcrumb-item active">添加单位</li>
                        </ol>
                    </div>
                </div>
            </div>
        </section>
        <section class="content">
            <div class="container-fluid">
                <!-- SELECT2 EXAMPLE -->
                <div class="card card-default">
                    <div class="card-header">
                        <h3 class="card-title">单位信息</h3>
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
                                    <label>单位名称</label> <input id="shuju" type="hidden"
                                                               value="${idList}"> <input type="text"
                                                                                         class="form-control"
                                                                                         v-model="transferStation.unit_name"
                                                                                         placeholder="请输入名称..">
                                </div>
                                <div class="form-group">
                                    <label>详细地址</label> <input type="text" class="form-control"
                                                               v-model="transferStation.unit_detailaddress"
                                                               placeholder="详细地址..">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>省/市/区</label>
                                    <div class="dropdown yto-city" :class="{open : isOpen}">
                                        <input @focus="openBox" v-model="transferStation.unit_address"
                                               class="form-control" placeholder="请输入省市区" type="text">
                                        <div @mouseleave="closeBox" class="yto-city-box dropdown-menu">
                                            <ul>
                                                <li @click="selectProvinceMethod" id="sendPro"
                                                    style="color: #000;" :class="{hover : selectProvince}">省份
                                                </li>
                                                <li @click="selectCityMethod" id="city" style="color: #000;"
                                                    :class="{hover : selectCity}">市区
                                                </li>
                                                <li @click="selectCountyMethod" id="xian"
                                                    style="color: #000;" :class="{hover : selectCounty}">县区
                                                </li>
                                            </ul>
                                            <div class="yto-city-cont">
                                                <dl id="sendProC" class="ytoprov"
                                                    :style="[selectProvince?bl:no]">
                                                    <dd @click="inputProvince(pro.provinceID,pro.province)"
                                                        v-for="pro in province" :key="pro.id"
                                                        :title="pro.province" :value="pro.provinceID">{{pro.province}}
                                                    </dd>
                                                </dl>
                                                <dl id="cityC" class="ytocity"
                                                    :style="[selectCity ? bl : no]">
                                                    <dd @click="inputCity(cit.cityID,cit.city)"
                                                        v-for="cit in city" :key="cit.id" :title="cit.city"
                                                        :value="cit.cityID">{{cit.city}}
                                                    </dd>
                                                </dl>
                                                <dl id="xianC" class="ytodist"
                                                    :style="[selectCounty ? bl : no]">
                                                    <dd @click="inputCountry(cou.area)" v-for="cou in country"
                                                        :key="cou.id" :title="cou.area" :value="cou.areaID">{{cou.area}}
                                                    </dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.form-group -->
                                <div class="form-group">
                                    <label>单位类型</label> <select @change="getAdmin"
                                                                class="form-control" v-model="transferStation.unit_type"
                                                                data-placeholder="单位类型" style="width: 100%;">
                                    <option value="总公司">总公司</option>
                                    <option value="中转站">中转站</option>
                                    <option value="配送点">配送点</option>
                                </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>联系方式</label> <input type="text" class="form-control"
                                                               v-model="transferStation.unit_phonenumber"
                                                               placeholder="请输入联系方式..">
                                </div>
                                <div class="form-group">
                                    <label>状态</label> <select class="form-control"
                                                              v-model="transferStation.unit_state"
                                                              data-placeholder="单位状态"
                                                              style="width: 100%;">
                                    <option>未启用</option>
                                    <option>正常使用</option>
                                    <option>已废弃</option>
                                </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>单位编号</label> <input disabled type="text"
                                                               class="form-control" v-model="transferStation.unit_num"
                                                               placeholder="单位编号..">
                                </div>
                                <div class="form-group">
                                    <label>管理员</label>
                                    <select class="form-control"
                                            v-model="transferStation.unit_admin" placeholder="请选择管理员"
                                            style="width: 100%;">
                                        <option v-for="admin in adminList"
                                                :value="admin.staff_id">
                                            {{admin.staff_name}}
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>上级单位</label>
                                    <select disabled class="form-control"
                                            v-model="transferStation.unit_superiorunit" placeholder="单位"
                                            style="width: 100%;">
                                        <option v-for="unit in unitList"
                                                :value="unit.unit_id">
                                            {{unit.unit_name}}
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <button @click="addUnit" type="button"
                                            style="width: 120px; float: right; margin-right: 7px"
                                            class="btn btn-block btn-primary btn-lg">提交
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
<script>
    $(function () {
        $('.select2').select2()
    })
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/unit/unit_add.js"></script>
</body>
</html>