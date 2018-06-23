<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font/demo.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font/iconfont.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/toastr.css">
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
			<%--<form id="form" class="fv-form fv-form-bootstrap" method="post"
action="" novalidate="novalidate">--%>
			<div class="row" id="reservation_express">
				<div class="col-sm-4">
					<div class="yto-item">
						<h5 class="yto-item-hd item-org">寄件方</h5>
						<div class="form-group form-material">
							<label class="control-label">真实姓名</label> <input
								v-model="loginBeforeInfo.expressinfo_senderrealname"
								name="sendName" class="form-control" placeholder="请输入姓名"
								data-fv-field="Names" type="text">
						</div>
						<div class="form-group form-material">
							<label class="control-label">省/市/区</label>
							<div id="address" class="dropdown yto-city"
								:class="{open : isOpen}">
								<input @focus="openBox"
									v-model="loginBeforeInfo.expressinfo_senderaddress"
									readonly="readonly" class="form-control" placeholder="请输入省市区"
									type="text">
								<div @mouseleave="closeBox" class="yto-city-box dropdown-menu">
									<ul>
										<li @click="selectProvinceMethod" id="sendPro"
											style="color: #000;" :class="{hover : selectProvince}">省份
										</li>
										<li @click="selectCityMethod" id="city" style="color: #000;"
											:class="{hover : selectCity}">市区</li>
										<li @click="selectCountyMethod" id="xian" style="color: #000;"
											:class="{hover : selectCounty}">县区</li>
										<%--<li @click="closeBox" id="close"><i
class="icon iconfont icon-guanbi"></i></li>--%>
									</ul>
									<div class="yto-city-cont">
										<dl id="sendProC" class="ytoprov"
											:style="[selectProvince?bl:no]">
											<dd @click="inputProvince(pro.provinceID,pro.province)"
												v-for="pro in province" :key="pro.id" :title="pro.province"
												:value="pro.provinceID">{{pro.province}}</dd>
										</dl>
										<%-- <dl  class="ytoprov" style="display: block;">

</dl>--%>
										<dl id="cityC" class="ytocity" :style="[selectCity ? bl : no]">
											<dd @click="inputCity(cit.cityID,cit.city)"
												v-for="cit in city" :key="cit.id" :title="cit.city"
												:value="cit.cityID">{{cit.city}}</dd>
										</dl>
										<%--<dl class="ytocity" style="display: none;">
</dl>--%>
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
						<div class="form-group form-material">
							<label class="control-label">配送点</label> <input @focus="selectD"
								v-model="reservation_unit_view" name="sendReservation"
								class="form-control" placeholder="请选择配送点" value=""
								id="sendReservation" type="text">
							<ul @mouseleave="noSelectD"
								class="list-value list-value-appreciation"
								:style="[selectDistribution ? bl : no]">
								<li v-for="(item,index) of distributionList" :key="item.unit_id">
									<button
										@click="selectPeisong(item.unit_id,item.unit_detailaddress)"
										class="btn btn-default btn-sm" type="button">{{item.unit_detailaddress}}
									</button>
								</li>
							</ul>
						</div>
						<div class="form-group form-material">
							<label class="control-label">地址</label> <input name="sendAddress"
								v-model="loginBeforeInfo.expressinfo_senderdetailaddress"
								class="form-control" placeholder="请输入详细地址" value=""
								id="sendAddress" data-fv-field="Address" type="text">
						</div>
						<div class="form-group form-material">
							<label class="control-label">手机</label> <input name="sendMobile"
								v-model="loginBeforeInfo.expressinfo_senderphonenumber"
								class="form-control" placeholder="手机号码必填一项"
								data-fv-field="sendmobile" type="text">
						</div>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="yto-item">
						<h5 class="yto-item-hd item-green">收件方</h5>
						<div class="form-group form-material">
							<label class="control-label">真实姓名</label> <input
								name="receiveName"
								v-model="loginBeforeInfo.expressinfo_addresseerealname"
								class="form-control" placeholder="请输入姓名" data-fv-field="Names"
								type="text">
						</div>
						<div class="form-group form-material">
							<label class="control-label">省/市/区</label>
							<div class="dropdown yto-city" :class="{open : addressBoxIsOpen}">
								<input @focus="openAddressBox" readonly="readonly"
									v-model="loginBeforeInfo.expressinfo_addresseeaddress"
									class="form-control" placeholder="请输入省市区" type="text">
								<div @mouseleave="closeAddressBox"
									class="yto-city-box dropdown-menu">
									<ul>
										<li @click="selectAddProvinceMethod" id="addressPro"
											style="color: #000;" :class="{hover : selectAddProvince}">省份
										</li>
										<li @click="selectAddCityMethod" id="addressCity"
											style="color: #000;" :class="{hover : selectAddCity}">市区
										</li>
										<li @click="selectAddCountyMethod" id="addressXian"
											style="color: #000;" :class="{hover : selectAddCounty}">县区
										</li>
									</ul>
									<div class="yto-city-cont">
										<dl id="addProC" class="ytoprov"
											:style="[selectAddProvince?bl:no]">
											<dd @click="inputAddProvince(pro.provinceID,pro.province)"
												v-for="pro in addProvince" :key="pro.id"
												:title="pro.province" :value="pro.provinceID">{{pro.province}}
											</dd>
										</dl>
										<dl id="addCityC" class="ytocity"
											:style="[selectAddCity ? bl : no]">
											<dd @click="inputAddCity(cit.cityID,cit.city)"
												v-for="cit in addCity" :key="cit.id" :title="cit.city"
												:value="cit.cityID">{{cit.city}}</dd>
										</dl>
										<dl id="addXianC" class="ytodist"
											:style="[selectAddCounty ? bl : no]">
											<dd @click="inputAddCountry(cou.area)"
												v-for="cou in addCountry" :key="cou.id" :title="cou.area"
												:value="cou.areaID">{{cou.area}}</dd>
										</dl>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group form-material">
							<label class="control-label">地址</label> <input
								name="receiveAddress"
								v-model="loginBeforeInfo.expressinfo_adderdetailaddress"
								id="receiveaddress" class="form-control" placeholder="请输入详细地址"
								data-fv-field="Address" type="text">
						</div>
						<div class="form-group form-material">
							<label class="control-label">手机</label> <input
								name="receiveMobile"
								v-model="loginBeforeInfo.expressinfo_addresseephonenumber"
								class="form-control" placeholder="手机或电话必填一项"
								data-fv-field="receivemobile" type="text">
						</div>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="yto-item" style="min-height: 460px">
						<h5 class="yto-item-hd">物品信息</h5>
						<div class="form-group form-material">
							<label class="control-label">特殊备注</label> <input name="remark"
								id="receiveRemark" class="form-control appreciation-input"
								v-model="loginBeforeInfo.expressinfo_mark" maxlength="100"
								placeholder="请选着下面的内容或者输入其他内容" data-fv-field="remark"
								type="text">
						</div>
						<div class="form-group form-material" id="limit">
							<label class="control-label">增值服务</label> <input id="server"
								@focus="openProtectBox" class="form-control support-input"
								v-model="loginBeforeInfo.expressinfo_protectprice"
								placeholder="请选择增值服务" readonly="readonly" type="text">
							<ul @mouseleave="closeProtectBox"
								class="list-value support-value" :style="[isProtect?bl:no]">
								<div class="row" style="padding-left: 15px;">
									<li>
										<p class="flip">
											<input type="hidden"
												v-model="loginBeforeInfo.expressinfo_protectprice"
												class="form-control support-input" name="payIdn" id="payIdn"
												value="N"> <a @click="selectProtectService"
												class="btn btn-default btn-smb btn-insuredSign" value="保价"
												type="button" data-value="保价" id="insuredSign">保价</a>
										</p>
									</li>
								</div>
							</ul>


							<div class="form-group form-material">
								<label class="control-label">内件品名</label> <input required="true"
									name="orderProductList[0].name" id="productName"
									v-model="loginBeforeInfo.expressinfo_productname"
									class="form-control user-input" maxlength="100"
									placeholder="请输入物品的名称" data-fv-field="orderProductList[0].name"
									type="text">
							</div>
							<div class="form-group form-material">
								<label class="control-label">物品重量（kg）</label> <input id="weight"
									name="orderProductList[0].weight"
									v-model="loginBeforeInfo.expressinfo_productweight"
									class="form-control weight-input" placeholder="请输入物品的大概重量"
									data-fv-field="weight" type="text"><span
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
						<input value="提交预约单" id="saveBtn"
							class="btn btn-primary btn-block btn-lg" autocomplete="off"
							title="" type="button">
					</div>
				</div>
				<%--</form>--%>
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
		<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript"
			src="http://ec.yto.net.cn/assets/js/plugins/ytocity/yto_cityselect.js"></script>
		<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/express/reservation_express.js"></script>--%>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/city-select.js"></script>
</body>
</html>