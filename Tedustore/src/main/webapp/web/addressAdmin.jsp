<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>我的订单 - 达内学子商城</title>
<link href="../css/orders.css" rel="stylesheet" />
<link href="../css/header.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/personage.css" rel="stylesheet" />
<style>
	#error_message {
		color: red;
	}
	.swmr.swmr_normal{
		cursor: pointer;
	}
	.hide{
		display:none;
	}
</style>
</head>
<body>
	<!-- 页面顶部-->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- 我的订单导航栏-->
	<div id="nav_order">
		<ul>
			<li><a href="">首页<span>&gt;</span>个人中心
			</a></li>
		</ul>
	</div>
	<!--我的订单内容区域 #container-->
	<div id="container" class="clearfix">
		<!-- 左边栏-->
		<jsp:include page="left.jsp"></jsp:include>
		<!-- 右边栏-->
		<div class="rightsidebar_box rt">
			<!--标题栏-->
			<div class="rs_header">
				<span class="address_title">收获地址管理</span>
			</div>
			<!--收货人信息填写栏-->
			<div class="rs_content">
				<form method="post"  action="">
					<!--收货人姓名-->
					<div class="recipients">
						<span class="red">*</span><span class="kuan">收货人：</span><input
							type="text" name="receiverName" id="receiverName" /> <span
							id="error_message"></span>
					</div>
					<!--收货人所在城市等信息-->
					<!-- data-toggle="distpicker" -->
					<div class="address_content">
						<span class="red">*</span> <span class="kuan">省&nbsp;&nbsp;份：</span>
						<select data-province="---- 选择省 ----" id="receiverState">
							<option>---- 选择省 ----</option>
						</select> 城市：<select data-city="---- 选择市 ----" id="receiverCity">
							<option>---- 选择市 ----</option>
						</select> 区/县：<select data-district="---- 选择区 ----" id="receiverDistrict">
							<option>---- 选择区 ----</option>
						</select>
					</div>
					<!--收货人详细地址-->
					<div class="address_particular">
						<span class="red">*</span><span class="kuan">详细地址：</span>
						<textarea name="receiverAddress" id="receiverAddress" cols="60"
							rows="3" placeholder="建议您如实填写详细收货地址"></textarea>
					</div>
					<!--收货人地址-->
					<div class="address_tel">
						<span class="red">*</span><span class="kuan">手机号码：</span><input
							type="tel" id="receiverMobile" name="receiverMobile" />固定电话：<input
							type="text" name="receiverPhone" id="receiverPhone" />
					</div>
					<!--邮政编码-->
					<div class="address_postcode">
						<span class="red">&nbsp;</span class="kuan"><span>邮政编码：</span>&nbsp;<input
							type="text" name="receiverZip" id="receiverZip"/>
					</div>
					<!--地址名称-->
					<div class="address_name">
						<span class="red">&nbsp;</span class="kuan"><span>地址名称：</span>&nbsp;<input
							type="text" id="addressName" name="addressName" />如：<span
							class="sp">家</span><span class="sp">公司</span><span class="sp">宿舍</span>
					</div>
					<!--保存收货人信息-->
					<div class="save_recipient save">保存收货人信息</div>
					<div class="save_recipient modify hide" data-id="">确认修改信息</div>
				</form>
				<!--已有地址栏-->
				<div class="address_information_manage">
					<div class="aim_title">
						<span class="dzmc dzmc_title">地址名称</span><span
							class="dzxm dzxm_title">姓名</span><span class="dzxq dzxq_title">地址详情</span><span
							class="lxdh lxdh_title">联系电话</span><span
							class="operation operation_title">操作</span>
					</div>
			<!-- 	<div class="aim_content_one aim_active">
						<span class="dzmc dzmc_active">办公室</span> <span
							class="dzxm dzxm_normal">杨洋</span> <span class="dzxq dzxq_normal">北京市海淀区北下关街道中鼎大厦B座331</span>
						<span class="lxdh lxdh_normal">18435110514</span> <span
							class="operation operation_normal"> <span
							class="aco_change">修改</span>|<span class="aco_delete">删除</span>
						</span> <span class="swmr swmr_normal"></span>
					</div> -->
					<div  id="append_address"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- 底部jsp -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<script type="text/javascript" src="../js/distpicker.data.js"></script>
<script type="text/javascript" src="../js/distpicker.js"></script>
<script type="text/javascript" src="../js/personal.js"></script>
<script type="text/javascript">
	$(".lxdh_normal").each(function(i, e) {
		var phone = $(e).html();
		$(e).html(changePhone(phone));
	});
	//处理侧边栏
	$(function() {
		$("#leftsidebar_box dd").hide();
		//$("#leftsidebar_box .count_managment dd").show();
		//设置帐号管理的列表显示

		//设置所有图片为myOredr2.png

		//设置帐号管理图片为myOrder1.png

		$(".address dt").click();
	});


			//修改操作
			function aco_change(id){
				$.ajax({
					url:"../address/modify.do",
					data:"id="+id,
					type:"post",
					dataType:"json",
					success:function(obj){
						if(obj.state==1){
							$(".save_recipient").addClass("hide");
							$(".modify").removeClass("hide");
							$("#receiverName").val(obj.data.recvUsername);
							province(obj.data.recvProvinceCode,obj.data.recvCityCode,obj.data.recvAreaCode);
						/* 	$("#receiverState").val(obj.data.recvProvinceCode);
							$("#receiverCity").val(obj.data.recvCityCode);
							$("#receiverDistrict").val(obj.data.recvAreaCode); */
							$("#receiverAddress").val(obj.data.recvAddress);
							$("#receiverMobile").val(obj.data.recvPhone);
							$("#receiverPhone").val(obj.data.recvTel);
							$("#receiverZip").val(obj.data.recvZip);
							$("#addressName").val(obj.data.recvTag);
							$(".modify").data("id",id);
						}
					}
				})
			}
			//提交新建收货地址信息
			$(".save_recipient.save").click(function(){
				submit("../address/saveAddress.do");
			});
			//提交修改收货地址信息
			$(".save_recipient.modify").click(function(){
				submit("../address/submitModify.do",$(this).data("id"));
			});
			function link_href(){
				location='../address/showAddress.do';
			}
	/**
	*加载地址和三级联动
	*/
	$(function() {
		province(-1,-1,-1);
		
		append_address("2");
	})
</script>
</html>