<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>地址管理</title>
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
<div id="useraddress" class="page" style="margin-top:110px">
    <div class="container m_top_10">
        <ol class="breadcrumb" style="background-color: transparent;">
            <li><a href="${pageContext.request.contextPath}/user-index.jsp">首页&nbsp;</a></li>
            <li><a href="${pageContext.request.contextPath}/user-userinfo.jsp">&nbsp;我的信息</a></li>
            <li>&nbsp;地址管理</li>
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
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/userinfo/userinfo_pageUserAddress">地址管理</a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/userinfo/userinfo_pageUpdatePassword">密码修改</a>
                        </li>
                        <%--<li><a href="${pageContext.request.contextPath}/user-mymessage.jsp">我的消息</a></li>--%>
                    </ul>
                </div>
                <div class="col-sm-10 b-left">
                    <%--<ul class="nav nav-tabs">
                        <li role="presentation" class="active">地址管理</li>
                    </ul>--%>
                    <div class="m_top_30">
                        <a @click="addAddressShow" class="btn btn-primary btn-sm">+ 新增地址</a>
                    </div>
                    <table class="table table-bordered table-hover m_top_10">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>联系方式</th>
                            <th>地址</th>
                            <th>详细地址</th>
                            <th>邮政编号</th>
                            <th>是否为默认地址</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="addre in addressList" :key="addre.address_id">
                            <td>{{addre.address_realname}}</td>
                            <td>{{addre.address_phonenumber}}</td>
                            <td>{{addre.address_address}}</td>
                            <td>{{addre.address_detailaddress}}</td>
                            <td>{{addre.address_postalnumber}}</td>
                            <td>{{addre.address_isdefault}}</td>
                            <td>
                                <a href="#" class="btn btn-primary">修改</a>
                                <a href="#" class="btn btn-danger">删除</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="addAddress" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModal">
                        添加地址
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal fv-form fv-form-bootstrap" novalidate="novalidate">
                        <div class="form-group has-feedback">
                            <label class="col-sm-4 control-label">姓名</label>
                            <div class="col-sm-6">
                                <input v-model="address.address_realname" class="form-control" placeholder="请输入姓名"/>
                                <small style="display: none;" class="help-block">请输入您的姓名
                                </small>
                                <small style="display: none;" class="help-block">请填写15字符之内、中文或英文
                                </small>
                                <small style="display: none;" class="help-block">Please enter a value with valid
                                    length
                                </small>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-sm-4 control-label">联系方式</label>
                            <div class="col-sm-6">
                                <input v-model="address.address_phonenumber" class="form-control"
                                       placeholder="手机号码">
                                <small style="display: none;" class="help-block">请输入正确的手机
                                </small>
                                <small style="display: none;" class="help-block">Please enter a valid value
                                </small>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-sm-4 control-label">地址 省/市/区</label>
                            <div class="col-sm-6 dropdown yto-city" :class="{open : isOpen}">
                                <input v-model="address.address_address" @focus="openBox" readonly="readonly"
                                       type="text" placeholder="请输入省市区"
                                       class="form-control">
                                <div @mouseleave="closeBox" class="yto-city-box dropdown-menu">
                                    <ul>
                                        <li @click="selectProvinceMethod" style="color: #000;"
                                            :class="{hover : selectProvince}">省份
                                        </li>
                                        <li @click="selectCityMethod" id="city" :class="{hover : selectCity}"
                                            style="color: #000;">市
                                        </li>
                                        <li @click="selectCountyMethod" :class="{hover : selectCounty}"
                                            style="color: #000;">区
                                        </li>
                                    </ul>
                                    <div class="yto-city-cont">
                                        <dl id="sendProC" class="ytoprov"
                                            :style="[selectProvince?bl:no]">
                                            <dd @click="inputProvince(pro.provinceID,pro.province)"
                                                v-for="pro in province" :key="pro.id" :title="pro.province"
                                                :value="pro.provinceID">{{pro.province}}
                                            </dd>
                                        </dl>
                                        <dl id="cityC" class="ytocity" :style="[selectCity ? bl : no]">
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
                        <div class="form-group has-feedback">
                            <label class="col-sm-4 control-label">详细地址</label>
                            <div class="col-sm-6">
                                <input v-model="address.address_detailaddress" class="form-control"
                                       placeholder="输入详细地址">
                                <small style="display: none;" class="help-block">请输入正确的详细地址
                                </small>
                                <small style="display: none;" class="help-block">Please enter a value with
                                    valid length
                                </small>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-sm-4 control-label">邮政编码</label>
                            <div class="col-sm-6">
                                <input v-model="address.address_postalnumber" class="form-control"
                                       placeholder="可填写邮政编码（选填）">
                                <small style="display: none;" class="help-block">请输入正确的邮政编码
                                </small>
                                <small style="display: none;" class="help-block">Please enter a value with
                                    valid length
                                </small>
                            </div>
                        </div>
                        <div class="form-group has-feedback">
                            <label class="col-sm-4 control-label">设置为默认地址</label>
                            <div class="col-sm-6" style="margin-top:8px;">
                                <input v-model="setMoRen" type="checkbox">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button @click="addAddress" :disabled="disabled" type="button"
                            class="btn btn-primary">
                        保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
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
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/user-useraddress.js"></script>
</body>
</html>