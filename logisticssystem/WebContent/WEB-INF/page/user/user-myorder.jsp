<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>我的订单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
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
                <li><a href="${pageContext.request.contextPath}/index.jsp" style="color: #FFF;line-height:30px">退出</a></li>
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
<div class="page" style="margin-top:110px">
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
                            <a href="?">
                                全部
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="?combineStatus=1&amp;createTimeST=&amp;createTimeET=2018-06-14&amp;queryKey=&amp;queryVal=&amp;limit=10&amp;sort=orderHeadId&amp;dir=desc&amp;statuses=0">
                                未接单
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="?combineStatus=2&amp;createTimeST=&amp;createTimeET=2018-06-14&amp;queryKey=&amp;queryVal=&amp;limit=10&amp;sort=orderHeadId&amp;dir=desc&amp;statuses=ACCEPT">
                                已接单
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="?combineStatus=6&amp;createTimeST=&amp;createTimeET=2018-06-14&amp;queryKey=&amp;queryVal=&amp;limit=10&amp;sort=orderHeadId&amp;dir=desc&amp;statuses=GOT">
                                已揽收
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="?combineStatus=3&amp;createTimeST=&amp;createTimeET=2018-06-14&amp;queryKey=&amp;queryVal=&amp;limit=10&amp;sort=orderHeadId&amp;dir=desc&amp;statuses=GOT&amp;statuses=SENT_SCAN">
                                在途中
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="?combineStatus=4&amp;createTimeST=&amp;createTimeET=2018-06-14&amp;queryKey=&amp;queryVal=&amp;limit=10&amp;sort=orderHeadId&amp;dir=desc&amp;statuses=SIGNED">
                                已签收
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="?combineStatus=5&amp;createTimeST=&amp;createTimeET=2018-06-14&amp;queryKey=&amp;queryVal=&amp;limit=10&amp;sort=orderHeadId&amp;dir=desc&amp;statuses=UNACCEPT&amp;statuses=NOT_SEND&amp;statuses=FAILED&amp;statuses=WITHDRAW">
                                异常件
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-10 b-left">
                    <div class="search-head">
                        <form method="post" id="order_search" action="" class="form-inline">
                            <div class="form-group" style="float: left;">
                                <div class="input-group">
                                    <div class="input-group-btn" id="param_toggle">
                                        <button type="button" class="btn btn-default"
                                        ><span>运单号</span></button>
                                        <input id="param_search" name="queryKey" value="mailNo" type="hidden">
                                    </div>
                                    <input maxlength="20" name="queryVal" value="" class="form-control" type="text">
                                </div>
                            </div>
                            <br>
                            <div style="margin-top: -32px" class="form-group p_top_10">
                                <input value="查询" class="btn btn-primary" id="search_button" type="submit">
                                <%--<input value="导出" class="btn btn-primary" id="export_button" type="button">--%>
                            </div>
                        </form>
                    </div>
                    <div class="table-responsive">
                        <table class="table yto-table">
                            <thead>
                            <tr>
                                <th width="260">
                                    <div class="hidden">
                                        <div class="icheckbox_flat-grey" style="position: relative;"><input
                                                name="orderHeadId" style="position: absolute; opacity: 0;"
                                                type="checkbox">
                                            <ins class="iCheck-helper"
                                                 style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins>
                                        </div>
                                    </div>
                                    寄件人信息
                                </th>
                                <th width="260">收件人信息</th>
                                <th>取件网点</th>
                                <th class="text-center" width="90">快件状态</th>
                                <th class="text-center" width="100">操作</th>
                            </tr>
                            <tr>
                                <td colspan="6">
                                    <div class="btn-group btn-group-sm">
                                        <!-- <button type="button" class="btn btn-default" id="allcheck">全选</button>
                                        <button type="button" class="btn btn-default" id="udcheck">反选</button>
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#mybd" disabled="disabled">批量绑定面单</button>
                                       -->
                                    </div>
                                </td>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                    <nav>
                        <div class="yto-message p_0">
                            <%--<div class="yto-message-img">--%>
                            <%--<img src="/assets/images/photos/no-cont.png">--%>
                            <%--</div>--%>
                            <h3 class="yto-message-title">
                                对不起，结果空空的！
                            </h3>

                        </div>
                        <ul class="pagination">
                        </ul>
                        <span class="pagination-info">
	                共 0  条
	    </span>
                        <div class="pagination-set">
                            <select name="limit" class="form-control"
                                    onchange="window.location = '?uroleId=500&amp;createTimeET=2018-06-14 08:42:30&amp;userId=66787665&amp;sendMobile=18296929245&amp;total=0&amp;sort=orderHeadId&amp;dir=desc&amp;limit=' + this.value;">
                                <!--  <option value="50">-每页显示条数-</option> -->
                                <option value="10" selected="selected">10</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                                <option value="200">200</option>
                                <option value="500">500</option>
                            </select>
                            <span>条/页</span>
                        </div>
                    </nav>
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
</body>
</html>