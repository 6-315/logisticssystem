<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>密码修改</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iZimodal/iziModal.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/toastr.css">
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
                <li><a href="${pageContext.request.contextPath }/loginregister/loginregister_logoff" style="color: #FFF;line-height:30px">退出</a>
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
<div id="updatePassword" class="page" style="margin-top:110px">
    <div class="container m_top_10">
        <ol class="breadcrumb" style="background-color: transparent;">
            <li><a href="${pageContext.request.contextPath}/userinfo/userinfo_userIndex">首页&nbsp;</a></li>
            <li><a href="${pageContext.request.contextPath}/userinfo/userinfo_pageUserInfo">&nbsp;我的信息</a></li>
            <li>&nbsp;修改密码</li>
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
                        <li><a href="${pageContext.request.contextPath}/userinfo/userinfo_pageUserInfo">基本信息</a></li>
                        <li><a href="${pageContext.request.contextPath}/userinfo/userinfo_pageUserAddress">地址管理</a></li>
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/userinfo/userinfo_pageUpdatePassword">密码修改</a>
                        </li>
                        <%--<li><a href="${pageContext.request.contextPath}/user-mymessage.jsp">我的消息</a></li>--%>
                    </ul>
                </div>
                <div class="col-sm-10 b-left m-height-300">
                    <p class="alert hidden" role="alert" id="error"></p>
                    <div class="form-horizontal fv-form fv-form-bootstrap"
                         autocomplete="off" novalidate="novalidate">
                        <button type="submit" class="fv-hidden-submit"
                                style="display: none; width: 0px; height: 0px;"></button>
                        <input name="cSRFToken" value="0051f077-3fd2-48a7-a72a-9404139cfa08" type="hidden">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">旧密码</label>
                            <div class="col-sm-4">
                                <input @change="confirmOldPassword" v-model="view_oldpass" maxlength="25" value=""
                                       title="旧密码"
                                       class="form-control" placeholder="请输入旧密码"
                                       type="password">
                                <small :style="[oldEnity ? bl : no]" class="help-block"
                                >请输入旧密码
                                </small>
                                </small>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-4">
                                <input v-model="newPass" maxlength="25" value="" title="新密码"
                                       class="form-control" placeholder="只能包含大小写字母，数字，长度8-25位"
                                       type="password">
                                <small style="display: none;" class="help-block" data-fv-validator="notEmpty"
                                       data-fv-for="newpass" data-fv-result="NOT_VALIDATED">请输入新密码
                                </small>
                                <small style="display: none;" class="help-block" data-fv-validator="regexp"
                                       data-fv-for="newpass" data-fv-result="NOT_VALIDATED">密码只能包含大小写字母，数字，长度8-25位
                                </small>
                                <small style="display: none;" class="help-block" data-fv-validator="identical"
                                       data-fv-for="newpass" data-fv-result="NOT_VALIDATED">两次密码输入不一致
                                </small>
                                <small style="display: none;" class="help-block" data-fv-validator="stringLength"
                                       data-fv-for="newpass" data-fv-result="NOT_VALIDATED">Please enter a value with
                                    valid length
                                </small>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">确认新密码</label>
                            <div class="col-sm-4">
                                <input @change="confirmNewPass" v-model="newConfirmPass" maxlength="25"
                                       required="required" name="repnewpass"
                                       value="" title="确认新密码"
                                       class="form-control" placeholder="请再次输入新密码"
                                       type="password">
                                <small :style="[newEnity ? bl : no]" class="help-block">请再次输入新密码
                                </small>
                                <small :style="[newError ? bl : no]" class="help-block">两次密码输入不一致
                                </small>
                                <small style="display: none;" class="help-block" data-fv-validator="stringLength"
                                       data-fv-for="repnewpass" data-fv-result="NOT_VALIDATED">Please enter a value with
                                    valid length
                                </small>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-4">
                                <input @click="updatePassword" :disabled="disabled" value="提交"
                                       class="btn btn-primary btn-block m_top_20"
                                       type="button">
                            </div>
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
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
<script src="${pageContext.request.contextPath}/js/user/user-updatepassword.js"></script>
<%--<script type="text/javascript" src="http://ec.yto.net.cn/assets/js/page.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/iziModal.min.js"></script>--%>
<script>

</script>
</body>
</html>