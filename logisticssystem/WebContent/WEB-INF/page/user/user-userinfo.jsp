<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>我的信息</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/toastr.css">
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
                <%-- <li><a style="text-decoration: none;" href="#"
                       class="navbar-avatar"> <span class="avatar"
                                                    style="vertical-align: middle;"> <img
                        src="${pageContext.request.contextPath}/img/touxiang.jpg">
						</span> <span class="user-name" style="color: #FFF;"> 18296929245
						</span>
                </a></li> --%>
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
<div id="userinfo" class="page" style="margin-top: 110px">
    <div class="container m_top_10">
        <ol class="breadcrumb" style="background-color: transparent;">
            <li><a
                    href="${pageContext.request.contextPath}/userinfo/userinfo_userIndex">首页&nbsp;</a></li>
            <li><a
                    href="${pageContext.request.contextPath}/userinfo/userinfo_pageUserInfo">&nbsp;我的信息</a></li>
            <li>&nbsp;基本信息</li>
        </ol>
        <div class="yto-box">
            <div class="row">
                <div class="col-sm-2 hidden-xs">
                    <%--<div class="my-avatar center-block p_bottom_10">
							<span class="avatar"> <img alt="..."
                                                       src="${pageContext.request.contextPath}/img/touxiang.jpg">
							</span>
                    </div>--%>
                    <h5 class="text-center p_bottom_10">您好！18296929245</h5>
                    <ul class="nav nav-pills nav-stacked">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/userinfo/userinfo_pageUserInfo">基本信息</a></li>
                        <li><a
                                href="${pageContext.request.contextPath}/userinfo/userinfo_pageUserAddress">地址管理</a>
                        </li>
                        <li><a
                                href="${pageContext.request.contextPath}/userinfo/userinfo_pageUpdatePassword">密码修改</a>
                        </li>
                        <%--<li><a
                                href="${pageContext.request.contextPath}/userinfo/userinfo_pageUserMessage">我的消息</a>
                        </li>--%>
                    </ul>
                </div>
                <div class="col-sm-10 b-left">
                    <p class="alert alert-danger hidden" role="alert" id="error"></p>
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">真实姓名</label>
                            <div class="col-sm-4">
                                <input readonly="readonly" v-model="userinfo_username"
                                       title="nickname"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">昵称</label>
                            <div class="col-sm-4">
                                <input v-model="userinfo_nickname"
                                       title="nickname"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">手机号码</label>
                            <div class="col-sm-4">
                                <input v-model="userinfo_phonenumber" class="form-control" title="username" readonly="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input value="男" name="userinfo_sex" type="radio" v-model="userinfo_sex">
                                    男</label>
                                <label class="radio-inline">
                                    <input value="女" name="userinfo_sex" type="radio" v-model="userinfo_sex">
                                    女</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">邮箱</label>
                            <div class="col-sm-4">
                                <input v-model="userinfo_email" value="" title="email" class="form-control"
                                       placeholder="请输入邮箱">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2  col-sm-4">
                                <input @click="confirmUserInfo" value="修改" class="btn btn-primary btn-block m_top_20"
                                       type="button">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="updateUserInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModal">
                        更改用户信息
                    </h4>
                </div>
                <div class="modal-body">
                    更改后不可还原，点击确定后更改
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button @click="updateUserInfo" type="button" class="btn btn-danger">
                        确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div><!-- /.modal -->
</div>

<%--<div class="modal fade" id="avatar-modal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">修改头像</h4>
            </div>

            <div class="modal-body  avatar-form">
                <div class="avatar-body">

                    <!-- Upload image and data -->
                    <div class="avatar-upload">
                        <input class="avatar-src" name="avatar-src" type="hidden">
                        <input class="avatar-data" name="avatar-data" type="hidden">

                        <div class="input-group input-group-file">

								<span class="btn btn-default btn-file"> <i
                                        aria-hidden="true" class="icon fa-upload"></i> <span>浏览本地照片</span>
									<input class="avatar-input" id="avatarInput"
                                           name="avatar-input" type="file">
								</span>
                        </div>
                    </div>
                    <!-- Crop and preview -->
                    <div class="row">
                        <div class="col-md-9">
                            <div class="avatar-wrapper"></div>
                        </div>
                        <div class="col-md-3">
                            <div class="avatar-preview preview-lg"></div>
                            <div class="avatar-preview preview-md"></div>
                            <div class="avatar-preview preview-sm"></div>
                        </div>
                    </div>
                    <div class="row avatar-btns">
                        <div class="col-md-4 col-md-offset-4">
                            <button type="botton"
                                    class="btn btn-primary btn-block avatar-save">完成
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- </form> -->
        </div>
    </div>
</div>--%>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/jquery/jquery.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/plugins/iCheck/icheck.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/user-userinfo.js"></script>
</body>
</html>