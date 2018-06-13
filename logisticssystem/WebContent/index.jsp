<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <title>Note3速递</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tool/site.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body class="body-order">
<nav class="navbar navbar-inverse navbar-fixed-top" style="padding: 0px;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" style="padding:15px"> <img title="Note3速递"
                                                                        src="${pageContext.request.contextPath}/img/logo.png"
                                                                        class="navbar-brand-logo"> <span
                    class="navbar-brand-text">Note3速递系统</span>

            </a>
            <div class="navbar-other">2018，全新开始</div>
        </div>
        <div style="float:left;marign:auto;color:#FFF" class="navlogin">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/register.jsp">注册</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="page">
    <div class="container m_top_20">
        <form id="form" class="fv-form fv-form-bootstrap" method="post" action="" novalidate="novalidate">
            <div class="row">
                <div class="col-sm-4">
                    <div class="yto-item">
                        <h5 class="yto-item-hd item-org">寄件方</h5>
                        <div class="form-group form-material">
                            <label class="control-label">真实姓名</label>
                            <input name="sendName" class="form-control" placeholder="请输入姓名" data-fv-field="Names"
                                   type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">省/市/区</label>
                            <div class="dropdown yto-city">
                                <input id="sendRegion" data-regionid="sendRegionId"
                                       class="form-control" placeholder="请输入省市区"
                                       readonly="readonly" data-fv-field="sendRegion"
                                       type="text">
                            </div>
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">地址</label>
                            <input name="sendAddress" class="form-control" placeholder="请输入详细地址" value=""
                                   id="sendAddress" data-fv-field="Address" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">手机</label>
                            <input name="sendMobile" class="form-control" placeholder="手机或电话必填一项"
                                   data-fv-field="sendmobile" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="yto-item">
                        <h5 class="yto-item-hd item-green">收件方</h5>
                        <div class="form-group form-material">
                            <label class="control-label">真实姓名</label>
                            <input name="receiveName" class="form-control" placeholder="请输入姓名" data-fv-field="Names"
                                   type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">省/市/区</label>
                            <div class="dropdown yto-city">
                                <input id="receiveRegion" data-regionid="receiveRegionId"
                                       class="form-control" placeholder="请输入省市区"
                                       readonly="readonly" data-fv-field="sendRegion"
                                       type="text">
                            </div>
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">地址</label>
                            <input name="receiveAddress" id="receiveaddress" class="form-control" placeholder="请输入详细地址"
                                   data-fv-field="Address" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">手机</label>
                            <input name="receiveMobile" class="form-control" placeholder="手机或电话必填一项"
                                   data-fv-field="receivemobile" type="text">
                        </div>

                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="yto-item" style="min-height: 460px">
                        <h5 class="yto-item-hd">物品信息</h5>
                        <div class="form-group form-material">
                            <label class="control-label">特殊备注</label>
                            <input name="remark" id="receiveRemark" class="form-control appreciation-input"
                                   maxlength="100" placeholder="请选着下面的内容或者输入其他内容" data-fv-field="remark" type="text">
                        </div>
                        <div class="form-group form-material" id="limit">
                            <label class="control-label">增值服务</label>
                            <input id="server" class="form-control support-input" maxlength="100" placeholder="请选择增值服务"
                                   readonly="readonly" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">内件品名</label>
                            <input required="true" name="orderProductList[0].name" id="productName"
                                   class="form-control user-input" maxlength="100" placeholder="请输入物品的名称"
                                   data-fv-field="orderProductList[0].name" type="text">
                        </div>
                        <div class="form-group form-material">
                            <label class="control-label">物品重量（kg）</label>
                            <input id="weight" name="orderProductList[0].weight" class="form-control weight-input"
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
                    <input value="提交订单" disabled="disabled" id="saveBtn" class="btn btn-primary btn-block btn-lg"
                           autocomplete="off" data-toggle="tooltip" data-placement="left" title=""
                           data-original-title="请同意协议" type="button">
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