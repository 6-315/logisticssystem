<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/toastr.css">
    <title>注册</title>
</head>
<body class="body-login">
<div class="page">
    <div class="page-content">

        <div class="page-brand-info hidden-xs">
            <div class="brand">
                <img class="brand-img"
                     src="${pageContext.request.contextPath }/img/fugai.png"
                     style="width: 180px; height: 180px;">
                <h1 class="brand-text">Note3物流系统</h1>
            </div>
            <p>我递送的每一声问候和祝福，</p>
            <p>得到的每一份支持，都是我坚定前行的动力。</p>
            <p>Note3期待为您服务</p>
        </div>
        <div class="page-login-main" id="register">
            <h3 class="hidden-xs">注册Note3</h3>
            <br/>
            <ul class="nav nav-tabs">
                <li role="presentation"><a
                        href="${pageContext.request.contextPath}/user-login.jsp">手机号密码登录</a></li>
                <li role="presentation" class="active"><a
                        href="${pageContext.request.contextPath}/register.jsp">快速注册</a></li>
            </ul>
            <form class="fv-form fv-form-bootstrap">
                <div class="form-group">
                    <input v-model="userinfo_phonenumber" class="form-control"
                           placeholder="手机号码">
                </div>
                <div class="form-group">
                    <input v-model="userinfo_email" class="form-control"
                           placeholder="电子邮箱">
                </div>
                <div class="form-group">
                    <input type="password" v-model="userinfo_password"
                           class="form-control" placeholder="密码">
                </div>
                <div class="form-group">
                    <input @change="comparePassword"
                           v-model="userinfo_confirmPassword" class="form-control"
                           placeholder="确认密码" type="password">
                </div>
            </form>
            <button @click="registerUser" type="submit"
                    class="btn btn-primary btn-block btn-flat">注册
            </button>
            <div class="clearfix"></div>
            <footer class="page-copyright">
                <p>Note物流有限公司 版权所有</p>
                <p>© 2018. All RIGHT RESERVED.</p>
            </footer>
        </div>
    </div>
</div>
<script
        src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/css/tool/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
<script
        src="${pageContext.request.contextPath}/js/user/user-register.js"></script>
</body>
</html>