<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>我要寄件</title>
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
<div class="page" style="margin-top:110px">
    <div class="container m_top_20" id="reservation_express">
        <form id="form" class="fv-form fv-form-bootstrap" method="post" action="" novalidate="novalidate">
            <div class="row">
                <div class="col-sm-2">
                    <ol class="breadcrumb" style="background-color: transparent;">
                        <li><a href="${pageContext.request.contextPath}/user-index.jsp">首页&nbsp;</a></li>
                        <li class="active">&nbsp;我要寄件</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="yto-item">
                        <h5 class="yto-item-hd item-org">寄件方</h5>
                        <div class="form-group form-material">
                            <label class="control-label">真实姓名</label>
                            <input v-model="view_express.expressinfo_senderrealname" name="sendName"
                                   class="form-control" placeholder="请输入姓名" data-fv-field="Names"
                                   type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">省/市/区</label>
                            <div class="dropdown yto-city">
                                <input v-model="view_express.expressinfo_senderaddress" id="sendRegion"
                                       data-regionid="sendRegionId"
                                       class="form-control" placeholder="请输入省市区"
                                       data-fv-field="sendRegion"
                                       type="text">
                            </div>
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">地址</label>
                            <input v-model="view_express.expressinfo_senderdetailaddress" name="sendAddress"
                                   class="form-control" placeholder="请输入详细地址" value=""
                                   id="sendAddress" data-fv-field="Address" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">手机</label>
                            <input v-model="view_express.expressinfo_senderphonenumber" name="sendMobile"
                                   class="form-control" placeholder="手机或电话必填一项"
                                   data-fv-field="sendmobile" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="yto-item">
                        <h5 class="yto-item-hd item-green">收件方</h5>
                        <div class="form-group form-material">
                            <label class="control-label">真实姓名</label>
                            <input v-model="view_express.expressinfo_addresseerealname" name="receiveName"
                                   class="form-control" placeholder="请输入姓名" data-fv-field="Names"
                                   type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">省/市/区</label>
                            <div class="dropdown yto-city">
                                <input v-model="view_express.expressinfo_addresseeaddress" id="receiveRegion"
                                       data-regionid="receiveRegionId"
                                       class="form-control" placeholder="请输入省市区"
                                       data-fv-field="sendRegion"
                                       type="text">
                            </div>
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">地址</label>
                            <input v-model="view_express.expressinfo_adderdetailaddress" name="receiveAddress"
                                   id="receiveaddress" class="form-control" placeholder="请输入详细地址"
                                   data-fv-field="Address" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">手机</label>
                            <input v-model="view_express.expressinfo_addresseephonenumber" name="receiveMobile"
                                   class="form-control" placeholder="手机或电话必填一项"
                                   data-fv-field="receivemobile" type="text">
                        </div>

                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="yto-item" style="min-height: 460px">
                        <h5 class="yto-item-hd">物品信息</h5>
                        <div class="form-group form-material">
                            <label class="control-label">特殊备注</label>
                            <input v-model="view_express.expressinfo_mark" name="remark" id="receiveRemark"
                                   class="form-control appreciation-input"
                                   maxlength="100" placeholder="请选着下面的内容或者输入其他内容" data-fv-field="remark" type="text">
                        </div>
                        <div class="form-group form-material" id="limit">
                            <label class="control-label">增值服务</label>
                            <input v-model="view_express.expressinfo_protectprice" id="server"
                                   class="form-control support-input" maxlength="100" placeholder="请选择增值服务"
                                   type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">内件品名</label>
                            <input v-model="view_express.expressinfo_productname" required="true"
                                   name="orderProductList[0].name" id="productName"
                                   class="form-control user-input" maxlength="100" placeholder="请输入物品的名称"
                                   data-fv-field="orderProductList[0].name" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">物品重量（kg）</label>
                            <input v-model="view_express.expressinfo_productweight" id="weight"
                                   name="orderProductList[0].weight" class="form-control weight-input"
                                   placeholder="请输入物品的大概重量" data-fv-field="weight" type="text"><span
                                id="errorWeight"></span>
                        </div>

                        <div class="check-position">
                            <!-- <input type="checkbox" id="agingProducts"  name="agingProducts"> -->
                            <div class="form-group form-material">
                                <label for="getProductType">时效产品</label>
                                <div id="getProductType">
                                    <input name="siteCode" id="siteCode" value="" type="hidden">
                                    <input name="siteName" id="siteName" value="" type="hidden">
                                    <input name="serviceType" id="serviceType" value="" type="hidden">
                                </div>
                                <input class="form-control time-input" value="标准快递" readonly="readonly" type="text">
                            </div>
                            <div id="accordProducts" style="display: none;">
                                <ul class="time-value-list">
                                    <li>
                                        <div id="accordProductss"></div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4 col-md-offset-4 p_bottom_10" style="margin:auto">
                    <input :disabled="disabled" v-on:click="addReservation" value="提交订单" id="saveBtn"
                           class="btn btn-primary btn-block btn-lg"
                           autocomplete="off" data-toggle="tooltip" data-placement="left" title=""
                           data-original-title="请同意协议" type="button">
                </div>
            </div>
        </form>
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
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/express/reservation_express.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
</body>
</html>