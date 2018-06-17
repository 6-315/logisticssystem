<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>Note3速递</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/demo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font/iconfont.css">
</head>
<body class="body-order">
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
        <div style="float: right; color: #FFF; line-height: 60px"
             class="navlogin">
            <ul>
                <li><a href="${pageContext.request.contextPath}/login.jsp">登录</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/register.jsp">注册</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="page">
    <div class="container m_top_20">
        <form id="form" class="fv-form fv-form-bootstrap" method="post"
              action="" novalidate="novalidate">
            <div class="row">
                <div class="col-sm-4">
                    <div class="yto-item">
                        <h5 class="yto-item-hd item-org">寄件方</h5>
                        <div class="form-group form-material">
                            <label class="control-label">真实姓名</label> <input name="sendName"
                                                                             class="form-control" placeholder="请输入姓名"
                                                                             data-fv-field="Names"
                                                                             type="text">
                        </div>
                        <div for="sendRegion" class="form-group form-material">
                            <label class="control-label">省/市/区</label>
                            <div class="dropdown yto-city">
                                <input id="sendRegion" data-regionid="sendRegionId"
                                       class="form-control" placeholder="请输入省市区"
                                       data-fv-field="sendRegion" type="text">
                                <div class="yto-city-box dropdown-menu">
                                    <ul>
                                        <li id="sendPro" style="color: #000;" class="hover">省份</li>
                                        <li id="city" style="color: #000;" class="">市区</li>
                                        <li id="xian" style="color: #000;" class="">县区</li>
                                        <li id="close"><i class="icon iconfont icon-guanbi"></i></li>
                                    </ul>
                                    <div class="yto-city-cont">
                                        <dl id="sendProC" class="ytoprov" style="display: block;">
                                            <dd title="北京" value="10">北京</dd>
                                            <dd title="上海" value="21">上海</dd>
                                            <dd title="天津" value="22">天津</dd>
                                            <dd title="重庆" value="23">重庆</dd>
                                            <dd title="河北省" value="130000">河北省</dd>
                                            <dd title="山西省" value="140000">山西省</dd>
                                            <dd title="内蒙古自治区" value="150000">内蒙古自治区</dd>
                                            <dd title="辽宁省" value="210000">辽宁省</dd>
                                            <dd title="吉林省" value="220000">吉林省</dd>
                                            <dd title="黑龙江省" value="230000">黑龙江省</dd>
                                            <dd title="江苏省" value="320000">江苏省</dd>
                                            <dd title="浙江省" value="330000">浙江省</dd>
                                            <dd title="安徽省" value="340000">安徽省</dd>
                                            <dd title="福建省" value="350000">福建省</dd>
                                            <dd title="江西省" value="360000">江西省</dd>
                                            <dd title="山东省" value="370000">山东省</dd>
                                            <dd title="河南省" value="410000">河南省</dd>
                                            <dd title="湖北省" value="420000">湖北省</dd>
                                            <dd title="湖南省" value="430000">湖南省</dd>
                                            <dd title="广东省" value="440000">广东省</dd>
                                            <dd title="广西壮族自治区" value="450000">广西壮族自治区</dd>
                                            <dd title="海南省" value="460000">海南省</dd>
                                            <dd title="四川省" value="510000">四川省</dd>
                                            <dd title="贵州省" value="520000">贵州省</dd>
                                            <dd title="云南省" value="530000">云南省</dd>
                                            <dd title="西藏自治区" value="540000">西藏自治区</dd>
                                            <dd title="陕西省" value="610000">陕西省</dd>
                                            <dd title="甘肃省" value="620000">甘肃省</dd>
                                            <dd title="青海省" value="630000">青海省</dd>
                                            <dd title="宁夏回族自治区" value="640000">宁夏回族自治区</dd>
                                            <dd title="新疆维吾尔自治区" value="650000">新疆维吾尔自治区</dd>
                                            <dd title="香港特别行政区" value="852">香港特别行政区</dd>
                                            <dd title="澳门特别行政区" value="853">澳门特别行政区</dd>
                                            <dd title="台湾省" value="886001">台湾省</dd>
                                        </dl>
                                        <%-- <dl  class="ytoprov" style="display: block;">

                                         </dl>--%>
                                        <dl id="cityC" class="ytocity" style="display: none;">
                                            <dd title="南昌市" value="360100">南昌市</dd>
                                            <dd title="景德镇市" value="360200">景德镇市</dd>
                                            <dd title="萍乡市" value="360300">萍乡市</dd>
                                            <dd title="九江市" value="360400">九江市</dd>
                                            <dd title="新余市" value="360500">新余市</dd>
                                            <dd title="鹰潭市" value="360600">鹰潭市</dd>
                                            <dd title="赣州市" value="360700">赣州市</dd>
                                            <dd title="吉安市" value="360800">吉安市</dd>
                                            <dd title="宜春市" value="360900">宜春市</dd>
                                            <dd title="抚州市" value="361000">抚州市</dd>
                                            <dd title="上饶市" value="361100">上饶市</dd>
                                        </dl>
                                        <%--<dl class="ytocity" style="display: none;">
                                        </dl>--%>
                                        <dl id="xianC" class="ytodist" style="display: none;">
                                        </dl>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">地址</label> <input
                                name="sendAddress" class="form-control" placeholder="请输入详细地址"
                                value="" id="sendAddress" data-fv-field="Address" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">手机</label> <input name="sendMobile"
                                                                           class="form-control" placeholder="手机或电话必填一项"
                                                                           data-fv-field="sendmobile" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="yto-item">
                        <h5 class="yto-item-hd item-green">收件方</h5>
                        <div class="form-group form-material">
                            <label class="control-label">真实姓名</label> <input
                                name="receiveName" class="form-control" placeholder="请输入姓名"
                                data-fv-field="Names" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">省/市/区</label>
                            <div class="dropdown yto-city">
                                <input id="receiveRegion" data-regionid="receiveRegionId"
                                       class="form-control" placeholder="请输入省市区" <%--readonly="readonly"--%>
                                       data-fv-field="sendRegion" type="text">
                            </div>
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">地址</label> <input
                                name="receiveAddress" id="receiveaddress" class="form-control"
                                placeholder="请输入详细地址" data-fv-field="Address" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">手机</label> <input
                                name="receiveMobile" class="form-control"
                                placeholder="手机或电话必填一项" data-fv-field="receivemobile"
                                type="text">
                        </div>

                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="yto-item" style="min-height: 460px">
                        <h5 class="yto-item-hd">物品信息</h5>
                        <div class="form-group form-material">
                            <label class="control-label">特殊备注</label> <input name="remark"
                                                                             id="receiveRemark"
                                                                             class="form-control appreciation-input"
                                                                             maxlength="100"
                                                                             placeholder="请选着下面的内容或者输入其他内容"
                                                                             data-fv-field="remark" type="text">
                        </div>
                        <div class="form-group form-material" id="limit">
                            <label class="control-label">增值服务</label> <input id="server"
                                                                             class="form-control support-input"
                                                                             maxlength="100"
                                                                             placeholder="请选择增值服务" readonly="readonly"
                                                                             type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">内件品名</label> <input required="true"
                                                                             name="orderProductList[0].name"
                                                                             id="productName"
                                                                             class="form-control user-input"
                                                                             maxlength="100"
                                                                             placeholder="请输入物品的名称"
                                                                             data-fv-field="orderProductList[0].name"
                                                                             type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">物品重量（kg）</label> <input id="weight"
                                                                                 name="orderProductList[0].weight"
                                                                                 class="form-control weight-input"
                                                                                 placeholder="请输入物品的大概重量"
                                                                                 data-fv-field="weight"
                                                                                 type="text"><span
                                id="errorWeight"></span>
                        </div>

                        <div class="check-position">
                            <!-- <input type="checkbox" id="agingProducts"  name="agingProducts"> -->
                            <div class="form-group form-material">
                                <label for="getProductType">时效产品</label>
                                <div id="getProductType">
                                    <input name="siteCode" id="siteCode" value="" type="hidden">
                                    <input name="siteName" id="siteName" value="" type="hidden">
                                    <input name="serviceType" id="serviceType" value=""
                                           type="hidden">
                                </div>
                                <input class="form-control time-input" value="标准快递"
                                       readonly="readonly" type="text">
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
                <div class="col-sm-4 col-md-offset-4 p_bottom_10"
                     style="margin: auto">
                    <input value="提交订单" id="saveBtn"
                           class="btn btn-primary btn-block btn-lg" autocomplete="off"
                           title="" type="button">
                </div>
            </div>
        </form>
    </div>
</div>
<footer class="page-footer">
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
<!-- <script type="text/javascript"
    src="http://ec.yto.net.cn/assets/js/plugins/ytocity/yto_cityselect.js"></script> -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/city-select.js"></script>
</body>
</html>