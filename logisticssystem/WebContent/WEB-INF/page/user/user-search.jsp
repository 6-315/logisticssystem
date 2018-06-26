<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>我要查件</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool/timeline.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/toastr.css">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
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
                <li><a href="${pageContext.request.contextPath}/index.jsp"
                       style="color: #FFF;line-height:30px">退出</a>
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
<div id="searchId" class="page" style="margin-top:110px">
    <div class="container m_top_10">
        <ol class="breadcrumb" style="background-color: transparent;">
            <li><a href="">首页&nbsp;</a></li>
            <li class="active">&nbsp;我要查件</li>
        </ol>
        <div class="yto-box">
            <form method="post" id="history_search" action="" class="form-horizontal m_bottom_30">
                <div class="input-group input-group-lg col-sm-8 col-sm-offset-2">
                    <input v-model="search" class="form-control" placeholder="请输入运单号" type="text">
                    <span class="input-group-btn">
					    <a @click="searchExpress" style="height: 46px;" class="btn btn-primary"
                           type="submit">
						    查询
                        </a>
                    </span>
                </div>
            </form>
            <div class="row">
                <div class="col-sm-2" <%--style="border-right: 1px dashed #ddd; "--%>>
                    <ul id="ul_status" class="nav nav-pills nav-stacked">
                        <%--<li role="presentation" class="active"><a href="javascript:void(0)">
                            全部
                        </a></li>
                        <li role="presentation" class=""><a href="javascript:void(0)">
                            待揽件
                        </a></li>
                        <li role="presentation" class=""><a href="javascript:void(0)">
                            已揽件
                        </a></li>
                        <li role="presentation" class=""><a href="javascript:void(0)">
                            在途中
                        </a></li>
                        <li role="presentation" class=""><a href="javascript:void(0)">
                            待派送
                        </a></li>
                        <li role="presentation" class=""><a href="javascript:void(0)">
                            派送中
                        </a></li>
                        <li role="presentation" class=""><a href="javascript:void(0)">
                            已签收
                        </a></li>--%>
                    </ul>
                </div>
                <div class="trace-table">
                    <table cellspacing="0" cellpadding="0" border="0">
                        <tbody>
                        <tr v-for="liuZhuan in liuZhuanList">
                            <td v-if="liuZhuan.number == 'one'" class="time">
                                {{liuZhuan.expressCirculation.express_circulation_createtime}}
                            </td>
                            <td v-else>
                                {{liuZhuan.expressCirculation.express_circulation_modifytime}}
                            </td>
                            <td v-if="liuZhuan.number == 'one'" class="data">
                                <span class="netColor">
                                    {{liuZhuan.unitByLaunchpeople.unit_name}}
                                </span>
                                <span>
                                    {{liuZhuan.motion}}
                                </span>
                            </td>
                            <td v-else class="data">
                                <span class="netColor">
                                    {{liuZhuan.unitByReceiver.unit_name}}
                                </span>
                                <span>
                                    {{liuZhuan.motion}}
                                </span>
                            </td>
                        </tr>
                        <%-- <tr>
                             <td class="time">
                                 2018-06-09 14:08:40
                             </td>
                             <td class="data ">
                                 <span class="netColor">广东省广州市番禺区火车南站VIP窗口公司(点击查询电话)</span> 已揽收&nbsp;&nbsp;
                             </td>
                         </tr>--%>
                        </tbody>
                    </table>
                </div>
            </div>
            <%--<div style="border:1px solid #999;" class="detailExpress">
                <div class="detailNavExpress">

                </div>
            </div>--%>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/user-search.js"></script>
</body>
</html>