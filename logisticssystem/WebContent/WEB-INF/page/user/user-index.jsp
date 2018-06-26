<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/toastr.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Note3 速递系统</title>
    <style type="text/css">
        [v-cloak] {
            display: none;
        }
    </style>
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
					</span> <span class="user-name" style="color: #FFF;"> 18296929245 </span>
                </a></li>
                <li><a href="${pageContext.request.contextPath }/loginregister/loginregister_logoff"
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
<div id="reverservationList" class="page" style="margin-top: 110px">
    <div class="showpic">
        <ul>
            <li class="activeImg"
                style="opacity: 1; background-image:url(${pageContext.request.contextPath}/img/user-index-img.jpg)"></li>
        </ul>
    </div>
    <div class="container">
        <div class="row yto-search">
            <div class="col-xs-12 col-sm-3">
                <a href="${pageContext.request.contextPath}/userinfo/userinfo_pageSendExpress"
                   class="btn btn-primary btn-lg btn-block"> <i
                        class="icon fa-send-o" aria-hidden="true"></i>&nbsp;我要寄件
                </a>
            </div>
            <div class="col-xs-12 col-sm-5">
                <form method="post" id="history_search" action="#">
                    <div class="input-group input-group-lg">
                        <input name="mailno" id="mailno" value="" class="form-control"
                               placeholder="请输入运单号"> <span class="input-group-btn">
								<button class="btn btn-primary" style="height: 48px"
                                        type="submit">
									<i class="icon fa-search" aria-hidden="true"></i> 查询
								</button>
							</span>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container m_top_30">
        <div class="yto-box">
            <h5>预约情况</h5>
            <div class="panel panel-default">
                <div class="panel-heading">我的预约单</div>
                <div class="table-responsive">
                    <table class="table table-hover" style="overflow-y: hidden">
                        <thead>
                        <tr>
                            <th>预约单号</th>
                            <th>上门取件单位编号</th>
                            <th>下单时间</th>
                            <th>快件品名</th>
                            <th>快件备注</th>
                            <th>
                                <span role="presentation" class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown">状态(全部)<span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li>
                                            <a @click="getReservationByState('')" href="javascript:void(0)">状态(所有)</a>
                                        </li>
										<li><a @click="getReservationByState('待受理')"
                                               href="javascript:void(0)">待受理</a></li>
										<li><a @click="getReservationByState('已受理')"
                                               href="javascript:void(0)">已受理</a></li>
										<li><a @click="getReservationByState('已取消')"
                                               href="javascript:void(0)">已取消</a></li>
										<li><a @click="getReservationByState('已拒绝')"
                                               href="javascript:void(0)">已拒绝</a></li>
										<li><a @click="getReservationByState('待取件')"
                                               href="javascript:void(0)">待取件</a></li>
										<li><a @click="getReservationByState('已取件')"
                                               href="javascript:void(0)">已取件</a></li>
										<li><a @click="getReservationByState('已完成')"
                                               href="javascript:void(0)">已完成</a></li>
									</ul>
								</span>
                            </th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody v-cloak>
                        <tr v-for="reser in reservationExpressList"
                            :key="reser.reservationInfo.reservation_id">
                            <td>{{reser.reservationInfo.reservation_num}}</td>
                            <td>{{reser.unitInfo.unit_num}}</td>
                            <td>{{reser.reservationInfo.reservation_createtime}}</td>
                            <td>{{reser.expressInfo.expressinfo_productname}}</td>
                            <td>{{reser.expressInfo.expressinfo_mark}}</td>
                            <td>{{reser.reservationInfo.reservation_state}}</td>
                            <td>
                                <a @click="openDetail(reser)">
                                    详情
                                </a>
                                <a @click="openDelete(reser.reservationInfo.reservation_id)">
                                    删除
                                </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="reservationDetail" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 800px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">预约单详细信息</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">预约单编号</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.reservation.reservation_num"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">预约单状态</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.reservation.reservation_state"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">下单时间</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.reservation.reservation_createtime"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">内件品名</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_productname"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">物品重量</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_productweight"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">发件人姓名</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_senderrealname"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">发件人地址</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_senderaddress"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">发件人详细地址</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_senderdetailaddress"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">发件人联系方式</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_senderphonenumber"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">收件人姓名</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_addresseerealname"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">收件人地址</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_addresseeaddress"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">收件人详细地址</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_adderdetailaddress"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">收件人联系方式</label>
                            <div class="col-sm-10">
                                <input
                                        v-model="reservationExpressDTO.expressinfo.expressinfo_addresseephonenumber"
                                        type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">配送点地址</label>
                            <div class="col-sm-10">
                                <input v-model="reservationExpressDTO.unit.unit_address"
                                       type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">配送点详细地址</label>
                            <div class="col-sm-10">
                                <input v-model="reservationExpressDTO.unit.unit_detailaddress"
                                       type="text" class="form-control" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">配送点联系方式</label>
                            <div class="col-sm-10">
                                <input v-model="reservationExpressDTO.unit.unit_phonenumber"
                                       type="text" class="form-control" disabled>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <%-- <button type="button" class="btn btn-primary">
                     提交更改
                 </button>--%>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="deleteReservation" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModal">取消预约单</h4>
                </div>
                <div class="modal-body">取消后不可更改，点击确定后删除</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button @click="cancelDetail" type="button" class="btn btn-danger">
                        删除
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
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
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
<script src="${pageContext.request.contextPath}/css/tool/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
<script src="${pageContext.request.contextPath}/js/user/user-index.js"></script>
</body>
</html>