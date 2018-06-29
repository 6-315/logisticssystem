<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>我要寄件</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/tool/site.min.css">
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
					<%-- 	<li><a style="text-decoration: none;" href="#"
						class="navbar-avatar"> <span class="avatar"
							style="vertical-align: middle;"> <img
								src="${pageContext.request.contextPath}/img/touxiang.jpg">
						</span> <span class="user-name" style="color: #FFF;"> 18296929245
						</span>
					</a></li> --%>
					<li><a
						href="${pageContext.request.contextPath }/loginregister/loginregister_logoff"
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
	<div class="page" style="margin-top: 110px">
		<div class="container m_top_20" id="reservation_express">
			<form id="form" class="fv-form fv-form-bootstrap" method="post"
				action="" novalidate="novalidate">
				<div class="row">
					<div class="col-sm-2">
						<ol class="breadcrumb" style="background-color: transparent;">
							<li><a
								href="${pageContext.request.contextPath}/user-index.jsp">首页&nbsp;</a></li>
							<li class="active">&nbsp;我要寄件</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<div class="yto-item">
							<h5 class="yto-item-hd item-org">寄件方</h5>
							<div class="form-group form-material">
								<label class="control-label">真实姓名</label> <input
									v-model="view_express.expressinfo_senderrealname"
									name="sendName" class="form-control" placeholder="请输入姓名"
									data-fv-field="Names" type="text">
							</div>
							<div class="form-group form-material">
								<label class="control-label">省/市/区</label>
								<div class="dropdown yto-city" :class="{open : isOpen}">
									<input @focus="openBox"
										v-model="view_express.expressinfo_senderaddress"
										class="form-control" placeholder="请输入省市区" type="text">
									<div @mouseleave="closeBox" class="yto-city-box dropdown-menu">
										<ul>
											<li @click="selectProvinceMethod" id="sendPro"
												style="color: #000;" :class="{hover : selectProvince}">省份
											</li>
											<li @click="selectCityMethod" id="city" style="color: #000;"
												:class="{hover : selectCity}">市区</li>
											<li @click="selectCountyMethod" id="xian"
												style="color: #000;" :class="{hover : selectCounty}">县区</li>
										</ul>
										<div class="yto-city-cont">
											<dl id="sendProC" class="ytoprov"
												:style="[selectProvince?bl:no]">
												<dd @click="inputProvince(pro.provinceID,pro.province)"
													v-for="pro in province" :key="pro.id" :title="pro.province"
													:value="pro.provinceID">{{pro.province}}</dd>
											</dl>
											<dl id="cityC" class="ytocity"
												:style="[selectCity ? bl : no]">
												<dd @click="inputCity(cit.cityID,cit.city)"
													v-for="cit in city" :key="cit.id" :title="cit.city"
													:value="cit.cityID">{{cit.city}}</dd>
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
							<div class="form-group form-material">
								<label class="control-label">配送点</label> <input @focus="selectD"
									v-model="reservation_unit_view" name="sendReservation"
									class="form-control" placeholder="请选择配送点" value=""
									readonly="readonly" id="sendReservation" type="text">
								<ul @mouseleave="noSelectD"
									class="list-value list-value-appreciation"
									:style="[selectDistribution ? bl : no]">
									<li v-for="(item,index) of distributionList"
										:key="item.unit_id">
										<button
											@click="selectPeisong(item.unit_id,item.unit_detailaddress)"
											class="btn btn-default btn-sm" type="button">{{item.unit_detailaddress}}
										</button>
									</li>
								</ul>
								</ul>
							</div>
							<div class="form-group form-material">
								<label class="control-label">地址</label> <input
									v-model="view_express.expressinfo_senderdetailaddress"
									name="sendAddress" class="form-control" placeholder="请输入详细地址"
									value="" id="sendAddress" data-fv-field="Address" type="text">
							</div>
							<div class="form-group form-material">
								<label class="control-label">手机号码</label> <input
									v-model="view_express.expressinfo_senderphonenumber"
									name="sendMobile" class="form-control" placeholder="手机号码为必填项"
									data-fv-field="sendmobile" type="text">
							</div>
							<div
								style="margin-top: -10px; height: 13.5px; margin-left: 260px">
								<a @click="sendAddressModal" href="#">录入地址</a>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="yto-item">
							<h5 class="yto-item-hd item-green">收件方</h5>
							<div class="form-group form-material">
								<label class="control-label">真实姓名</label> <input
									v-model="view_express.expressinfo_addresseerealname"
									name="receiveName" class="form-control" placeholder="请输入姓名"
									data-fv-field="Names" type="text">
							</div>
							<div class="form-group form-material">
								<label class="control-label">省/市/区</label>
								<div class="dropdown yto-city"
									:class="{open : addressBoxIsOpen}">
									<input @focus="openAddressBox" readonly="readonly"
										v-model="view_express.expressinfo_addresseeaddress"
										class="form-control" readonly="readonly" placeholder="请输入省市区"
										type="text">
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
									v-model="view_express.expressinfo_adderdetailaddress"
									name="receiveAddress" id="receiveaddress" class="form-control"
									placeholder="请输入详细地址" data-fv-field="Address" type="text">
							</div>
							<div class="form-group form-material">
								<label class="control-label">手机号码</label> <input
									v-model="view_express.expressinfo_addresseephonenumber"
									name="receiveMobile" class="form-control" placeholder="手机必填项"
									data-fv-field="receivemobile" type="text">
							</div>
							<div
								style="margin-top: -10px; height: 13.5px; margin-left: 260px">
								<a @click="addAddressModal" href="#">录入地址</a>
							</div>

						</div>
					</div>
					<div class="col-sm-4">
						<div class="yto-item" style="min-height: 370px">
							<h5 class="yto-item-hd">物品信息</h5>
							<div class="form-group form-material">
								<label class="control-label">特殊备注</label> <input
									v-model="view_express.expressinfo_mark" name="remark"
									id="receiveRemark" class="form-control appreciation-input"
									maxlength="100" placeholder="请选着下面的内容或者输入其他内容"
									data-fv-field="remark" type="text">
							</div>
							<div class="form-group form-material" id="limit">
								<label class="control-label">增值服务</label> <input
									@focus="openProtectBox" readonly="readonly"
									v-model="view_express.expressinfo_protectprice" id="server"
									class="form-control support-input" placeholder="请选择增值服务"
									type="text">
								<ul @mouseleave="closeProtectBox"
									class="list-value support-value" :style="[isProtect?bl:no]">
									<div class="row" style="padding-left: 15px;">
										<li>
											<p class="flip">
												<input type="hidden"
													v-model="view_express.expressinfo_protectprice"
													class="form-control support-input" name="payIdn"
													id="payIdn" value="N"> <a
													@click="selectProtectService"
													class="btn btn-default btn-smb btn-insuredSign" value="保价"
													type="button" data-value="保价" id="insuredSign">保价</a>
											</p>
										</li>
									</div>
								</ul>
							</div>
							<div class="form-group form-material">
								<label class="control-label">内件品名</label> <input
									v-model="view_express.expressinfo_productname" id="productName"
									class="form-control user-input" maxlength="100"
									placeholder="请输入物品的名称" type="text">
							</div>
							<div class="form-group form-material">
								<label class="control-label">物品重量（kg）</label> <input
									v-model="view_express.expressinfo_productweight" id="weight"
									name="orderProductList[0].weight"
									class="form-control weight-input" placeholder="请输入物品的大概重量"
									data-fv-field="weight" type="number"><span
									id="errorWeight"></span>
							</div>

							<%--<div class="check-position">
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
                    </div>--%>
						</div>
					</div>
					<div class="col-sm-4 col-md-offset-4 p_bottom_10"
						style="margin: auto">
						<input :disabled="disabled" v-on:click="addReservation"
							value="提交订单" id="saveBtn"
							class="btn btn-primary btn-block btn-lg" title="" type="button">
					</div>
				</div>
				<div class="modal fade" id="sendAddressModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title" id="deleteMyAddress">录入地址</h4>
							</div>
							<div class="modal-body">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>姓名</th>
												<th>联系方式</th>
												<th>地址</th>
												<th>详细地址</th>
												<th>邮政编号</th>
												<th>是否为默认地址</th>
												<th>选择</th>
											</tr>
										</thead>
										<tbody>
											<tr v-for="addre in sendAddress" :key="addre.address_id">
												<td>{{addre.address_realname}}</td>
												<td>{{addre.address_phonenumber}}</td>
												<td>{{addre.address_address}}</td>
												<td>{{addre.address_detailaddress}}</td>
												<td>{{addre.address_postalnumber}}</td>
												<td>{{addre.address_isdefault}}</td>
												<td><a @click="selectAddress(addre)" href="#">选择</a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->


				</div>
				<!-- /.modal -->


				<div class="modal fade" id="addAddressModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title" id="addMyAddress">录入地址</h4>
							</div>
							<div class="modal-body">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>姓名</th>
												<th>联系方式</th>
												<th>地址</th>
												<th>详细地址</th>
												<th>邮政编号</th>
												<th>是否为默认地址</th>
												<th>选择</th>
											</tr>
										</thead>
										<tbody>
											<tr v-for="addre in addAddress" :key="addre.address_id">
												<td>{{addre.address_realname}}</td>
												<td>{{addre.address_phonenumber}}</td>
												<td>{{addre.address_address}}</td>
												<td>{{addre.address_detailaddress}}</td>
												<td>{{addre.address_postalnumber}}</td>
												<td>{{addre.address_isdefault}}</td>
												<td><a @click="selectAddAddress(addre)" href="#">选择</a>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->

				<!-- </form> -->
		</div>
	</div>
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
					<%--<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">开始演示模态</button>--%>
				</div>
			</div>
		</div>
	</footer>
	<%-- 模态框 --%>

	<%--结束模态框--%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/jquery/jquery.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/public/toastr.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/express/reservation_express.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/public/getSessionData.js"></script>
</body>
</html>