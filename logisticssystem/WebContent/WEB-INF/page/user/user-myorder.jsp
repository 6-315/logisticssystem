<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>我的订单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/toastr.css">
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <style>
        [v-cloak] {
            display: none;
        }

        ul li {
            text-decoration: none;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" style="padding: 0px;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" style="display:inline-block;">
                <img style="float: left;" title="Note3速递"
                     src="${pageContext.request.contextPath}/img/logo.png"
                     class="navbar-brand-logo">
                <span class="navbar-brand-text" style="color: #FFF;line-height:30px">Note3速递系统</span>
            </a>
            <div class="navbar-other">2018，全新开始</div>
        </div>
        <div style="float:right;marign:auto;color:#FFF" class="navlogin">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a style="text-decoration:none;" href="#" class="navbar-avatar">
				     <span class="avatar" style="vertical-align:middle;">
						   <img src="${pageContext.request.contextPath}/img/touxiang.jpg">
					</span> <span class="user-name" style="color: #FFF;"> 18296929245 </span>
                    </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/index.jsp" style="color: #FFF;line-height:30px">退出</a>
                </li>
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
<div id="userMyOrder" class="page" style="margin-top:110px">
    <div class="container m_top_10">
        <ol class="breadcrumb" style="background-color: transparent;">
            <li><a href="${pageContext.request.contextPath}/user-index.jsp">首页&nbsp;</a></li>
            <li class="active">&nbsp;我的订单</li>
        </ol>
        <div class="yto-box">
            <div class="row">
                <div class="col-sm-2">
                    <ul class="nav nav-pills nav-stacked" id="ul_status">
                        <li role="presentation" class="active">
                            <a href="">
                                全部
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="">
                                未接单
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="">
                                已接单
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="">
                                已揽收
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="">
                                在途中
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="">
                                已签收
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="">
                                异常件
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-10 b-left">
                    <div class="search-head">
                        <form class="form-inline">
                            <div class="form-group" style="float: left;">
                                <div class="input-group">
                                    <div class="input-group-btn" id="param_toggle">
                                        <button type="button" class="btn btn-default"
                                        ><span>快递单号</span></button>
                                        <input id="param_search" value="mailNo" type="hidden">
                                    </div>
                                    <input maxlength="20" value="" class="form-control" type="text">
                                </div>
                            </div>
                            <br>
                        </form>
                        <div style="margin-top: -30px" class="form-group p_top_10">
                            <a class="btn btn-primary">查询</a>
                            <%--<input value="导出" class="btn btn-primary" id="export_button" type="button">--%>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover m_top_10">
                            <thead>
                            <tr>
                                <th>快件单号</th>
                                <th>发件人姓名</th>
                                <th>发件人联系方式</th>
                                <th>发件人详细地址</th>
                                <th>收件人姓名</th>
                                <th>收件人联系方式</th>
                                <th>收件人详细地址</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody v-if="expressinfoAndExpressVO.listExpressinfoAndExpressDTO == undefined">
                            <td style="text-align: center" colspan="8" height="50">
                                暂无数据
                            </td>
                            </tbody>
                            <tbody v-if="!ready">
                            <tr>
                                <td style="text-align: center" colspan="10"><i
                                        class="fa fa-spinner fa-spin fa-3x fa-fw"></i></td>
                            </tr>
                            </tbody>
                            <tbody v-cloak
                                   v-if="ready && expressinfoAndExpressVO.listExpressinfoAndExpressDTO != undefined"
                                   style="min-height: 200px">
                            <tr v-for="myOrder in expressinfoAndExpressVO.listExpressinfoAndExpressDTO">
                                <td v-html="myOrder.expressNew.express_number"></td>
                                <td>{{myOrder.expressInfo.expressinfo_senderrealname}}</td>
                                <td>{{myOrder.expressInfo.expressinfo_senderphonenumber}}</td>
                                <td>{{myOrder.expressInfo.expressinfo_senderdetailaddress}}</td>
                                <td>{{myOrder.expressInfo.expressinfo_addresseerealname}}</td>
                                <td>{{myOrder.expressInfo.expressinfo_addresseephonenumber}}</td>
                                <td>{{myOrder.expressInfo.expressinfo_adderdetailaddress}}</td>
                                <td>
                                    <a href="#">详情</a>
                                    <a href="#">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="pagePosition">
                            <ul v-cloak class="pagination">
                                <li></li>
                                <li><a href="#">首页</a></li>
                                <li :class="{disabled:preDisabled}"><a @click="prePage"
                                                                       href="#">上一页</a></li>
                                <li><a>第 {{expressinfoAndExpressVO.pageIndex}} 页/总
                                    {{expressinfoAndExpressVO.totalPages}}
                                    页/共{{expressinfoAndExpressVO.totalRecords}}条</a></li>
                                <li :class="{disabled:nextDisabled}"><a
                                        :disabled="nextDisabled" href="#">
                                    下一页 <%--<span aria-hidden="true">&raquo;</span>--%>
                                </a></li>
                                <li><a href="#">尾页</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="page-footer" style="height: 72px">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-6">
                <div class="page-copyright">
                    <p>© 2018. All RIGHT RESERVED. Note3速递公司 版权所有
                        <a target="_blank" href="#">我的编号</a>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
<script src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
<script src="${pageContext.request.contextPath}/js/user/user-myorder.js"></script>
</body>
</html>