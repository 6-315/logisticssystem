<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>我的消息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iZimodal/iziModal.min.css">
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
            <li class="site-menu-item">
                <a href="${pageContext.request.contextPath}/user-index.jsp"><span
                        class="site-menu-title">首页</span></a>
            </li>
            <li class="site-menu-item">
                <a href="${pageContext.request.contextPath}/user-order.jsp"><span
                        class="site-menu-title">我要寄件</span></span>
                </a>
            </li>
            <li class="site-menu-item">
                <a href="${pageContext.request.contextPath}/user-search.jsp">
                    <span class="site-menu-title">我要查件</span></a>
            </li>
            <li class="site-menu-item">
                <a href="${pageContext.request.contextPath}/user-myorder.jsp">
                    <span class="site-menu-title">我的订单</span></span>
                </a>
            </li>
            <li class="site-menu-item">
                <a href="${pageContext.request.contextPath}/user-userinfo.jsp">
                    <span class="site-menu-title">我的信息</span></span>
                </a>
            </li>
        </ul>
    </div>
</div>
<div class="page" style="margin-top:110px">
    <div class="container m_top_10">
        <ol class="breadcrumb" style="background-color: transparent;">
            <li><a href="${pageContext.request.contextPath}/user-index.jsp">首页&nbsp;</a></li>
            <li><a href="${pageContext.request.contextPath}/user-userinfo.jsp">&nbsp;我的信息</a></li>
            <li>&nbsp;我的消息</li>
        </ol>
        <div class="yto-box">
            <div class="row">
                <div class="col-sm-2 hidden-xs">
                    <div class="my-avatar center-block p_bottom_10">
							<span class="avatar">
							      <img src="${pageContext.request.contextPath}/img/touxiang.jpg">
							</span>
                    </div>
                    <h5 class="text-center p_bottom_10">您好！18296929245</h5>
                    <ul class="nav nav-pills nav-stacked">
                        <li><a href="${pageContext.request.contextPath}/user-userinfo.jsp">基本信息</a></li>
                        <li><a href="${pageContext.request.contextPath}/user-useraddress.jsp">地址管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/user-updatepassword.jsp">密码修改</a>
                        </li>
                        <li class="active"><a href="${pageContext.request.contextPath}/user-mymessage.jsp">我的消息</a></li>
                    </ul>
                </div>
                <div class="col-sm-10 b-left">
                    <p class="alert alert-danger hidden" role="alert" id="error"></p>
                    <div class="tab-content">
                        <div class="m_bottom_15 clearfix">
                            <h5 class="pull-left">您有<span class="mes-num">1</span>条新消息</h5>
                            <input value="全部设为已读" data-message="all" class="btn btn-primary pull-right " type="button">
                        </div>

                        <div class="list-group list-group-dividered">

                            <a class="list-group-item " id="message_remind_ul_44471531"
                               href="${pageContext.request.contextPath}/user-messagedetail.jsp">
                                <div class="list-group-item-heading">
											<span class="p_right_10">
													[系统通知]
											</span>
                                    <span class="font-size-16">
												积分消息
												</span>

                                    <span class="label  label-sm label-danger">new</span>

                                    <span class="text-muted">
												2018-06-08 09:04:18</span>
                                </div>
                                <p class="list-group-item-text clearfix">
                                    尊敬的会员您好，系统管理员于2018-06-08 09:04......
                                    <input messageid="44471531" class="btn btn-primary pull-right btn-xs" value="设为已读"
                                           type="button">
                                </p>
                            </a>

                        </div>
                        <nav>
                            <ul class="pagination">
                                <li class="active"><a
                                        href="#">1</a>
                                </li>
                            </ul>
                            <span class="pagination-info">
	                共 1  条
	    </span>
                            <div class="pagination-set">
                                <select name="limit" class="form-control"
                                <%--onchange="window.location = '' "--%>>
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
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="http://ec.yto.net.cn/assets/js/page.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/iziModal.min.js"></script>--%>
<script>

</script>
</body>
</html>