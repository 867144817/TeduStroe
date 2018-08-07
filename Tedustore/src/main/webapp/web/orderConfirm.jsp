<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>确认订单</title>
<link href="../css/orderConfirm.css" rel="Stylesheet" />
<link href="../css/header.css" rel="Stylesheet" />
<link href="../css/footer.css" rel="Stylesheet" />
<link href="../css/personage.css" rel="Stylesheet" />
</head>
<style>
.h100 {
	height: 100px;
}
.left{
	float:left;
}
.biaoti{
	width:245px; 
	margin-top: 20px;
	
}
.marl15{
	margin-left:15px;
}
</style>
<body>
	<!-- 页面顶部-->
	<jsp:include page="header.jsp"></jsp:include>

	<div id="navlist">
		<ul>
			<li class="navlist_blue_left"></li>
			<li class="navlist_blue">确认订单信息</li>
			<li class="navlist_blue_arro"><canvas id="canvas_blue"
					width="20" height="38"></canvas></li>
			<li class="navlist_gray">支付订单<b></b></li>
			<li class="navlist_gray_arro"><canvas id="canvas_gray"
					width="20" height="38"></canvas></li>
			<li class="navlist_gray">支付完成<b></b></li>
			<li class="navlist_gray_right"></li>
		</ul>
	</div>
	<!--订单确认-->
	<div id="container" class="clear">
		<!--收货地址-->
		<div class="adress_choice">
			<p>
				收货人信息<span class="rt" id="choose">新增收货地址</span>
			</p>
			<div id="append_address"></div>

			<a id="more" href="javascript:void(0);"> 更多地址 &gt;&gt; </a>
		</div>
		<!-- 商品信息-->
		<form name="" method="post" action="#">
			<div class="product_confirm">
				<p>确认商品信息</p>
				<ul class="item_title">
					<li class="p_info">商品信息</li>
					<li class="p_price">单价(元)</li>
					<li class="p_count">数量</li>
					<li class="p_tPrice">金额</li>
				</ul>
				<c:forEach items="${list }" var="list">
					<ul class="item_detail" id="${list.id}">
						<li class="p_info">
						<div class="left marl15" ><img src="..${list.image }"
								class="h100" /></div>
							<div class="left marl15">
								<b class="product_name lf biaoti"> ${list.title } </b> <br /> <span
									class="product_color "> 颜色：深空灰 </span>
							</div></li>
						<li class="p_price"><i>达内专属价</i><br /><span
							class="pro_price">￥<span class="ppp_price">${list.price }.00</span></span>
						</li>
						<li class="p_count">X<span>${list.num }</span></li>
						<li class="p_tPrice">￥<span></span></li>
					</ul>
				</c:forEach>
			</div>
		</form>
		<!-- 结算条-->
		<div id="count_bar">
			<span class="go_cart"><a href="../cart/cart.do">&lt;&lt;返回购物车</a></span>
			<span class="count_bar_info">已选<b id="count"> 0 </b>件商品&nbsp;&nbsp;合计(不含运费):<b
				class="zj"></b> <input type="hidden" name="Payment" value="" />元
			</span> <span class="go_pay">确认并付款</span>
		</div>
	</div>
	<!--标题栏-->
	<div class="modal" style="display: none">
		<!--收货人信息填写栏-->
		<div class="rs_content rs_content_1">
			<p class="cha">×</p>
			<form method="post" action="" id="address">
				<!--收货人姓名-->
				<div class="recipients">
					<span class="red">*</span><span class="kuan">收货人：</span><input
						type="text" name="receiverName" id="receiverName" />
				</div>
				<!--收货人所在城市等信息-->
				<div class="address_content">
					<span class="red">*</span><span class="kuan">省&nbsp;&nbsp;份：</span>
					<select data-province="---- 选择省 ----" id="receiverState"></select>
					城市：<select data-city="---- 选择市 ----" id="receiverCity"></select>
					区/县：<select data-district="---- 选择区 ----" id="receiverDistrict"></select>
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
						type="text" name="receiverZip" />
				</div>
				<!--地址名称-->
				<div class="address_name">
					<span class="red">&nbsp;</span class="kuan"><span>地址名称：</span>&nbsp;<input
						type="text" id="addressName" name="addressName" />如：<span
						class="sp">家</span><span class="sp">公司</span><span class="sp">宿舍</span>
				</div>
				<!--保存收货人信息-->
				<div class="save_recipient save">保存收货人信息</div>

			</form>
		</div>
	</div>

	<!-- 页面底部-->
	<jsp:include page="footer.jsp"></jsp:include>

	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/index.js"></script>
	<script>
		var html = 0;
		var total = 0;
		var str = [];
		$(function() {
			$(".item_detail")
					.each(
							function() {
								html += 1*$(this).find(".p_count span").html();
								str.push($(this).attr("id"));
								var p = parseFloat($(this).children('.p_price')
										.children('.pro_price').children(
												'.ppp_price').html());
								//console.log(p);
								var sl = parseFloat($(this)
										.children('.p_count').children('span')
										.html());
								var xj = parseFloat(p * sl).toFixed(2);
								console.log("xiaoji" + xj);
								$(this).children('.p_tPrice').children('span')
										.html(xj);
								total += xj - 0;
							})
			// console.log("zongji"+total);
			$("#count").html(html) - 0;
			$('.zj').html(total.toFixed(2)) - 0;
			var input_zj = parseFloat($('.zj').html()).toFixed(2);
			$('#payment').val(input_zj);
		})
	</script>
	<script>

	</script>
	<script>
		var canvas = document.getElementById("canvas_gray");
		var cxt = canvas.getContext("2d");
		var gray = cxt.createLinearGradient(10, 0, 10, 38);
		gray.addColorStop(0, '#f5f4f5');
		gray.addColorStop(1, '#e6e6e5');
		cxt.beginPath();
		cxt.fillStyle = gray;
		cxt.moveTo(20, 19);
		cxt.lineTo(0, 38);
		cxt.lineTo(0, 0);
		cxt.fill();
		cxt.closePath();
	</script>
	<script>
		var canvas = document.getElementById("canvas_blue");
		var cxt = canvas.getContext("2d");
		var blue = cxt.createLinearGradient(10, 0, 10, 38);
		blue.addColorStop(0, '#27b0f6');
		blue.addColorStop(1, '#0aa1ed');
		cxt.beginPath();
		cxt.fillStyle = blue;
		cxt.moveTo(20, 19);
		cxt.lineTo(0, 38);
		cxt.lineTo(0, 0);
		cxt.fill();
		cxt.closePath();
	</script>
	<script src="../js/distpicker.data.js"></script>
	<script src="../js/distpicker.js"></script>
	<script type="text/javascript" src="../js/personal.js"></script>
	<script>
		$("#choose").click(function() {
			$(".modal").show();
		})
		$(".cha").click(function() {
			$(".modal").hide();
		})

		$("#more").click(function() {
			if ($(this).hasClass("upup")) {
				/* 	$('#append_address>div').each(function(){
						if($(this).attr("class")=="base"){
							$(this).hide();
						}
					}) */
				$(".base").hide();
				$("#more").html("更多地址>>");
				$(this).removeClass("upup");
			} else {
				/* 	$('#append_address>div').each(function(){
						if($(this).attr("class")=="base"){
							$(this).show();
						}
					}) */
				$(".base").show();
				$("#more").html("收起地址>>");
				$("#more").addClass("upup");
			}
		})
	</script>
	<script>
		$(document).on("mouseover", ".base", function() {
			//console.log(this);
			$(this).find("i:eq(2)").html("设为默认地址");
			$(this).find("i:eq(2)").show();
		})
		$(document).on("mouseout", ".base", function() {
			$(this).find("i:eq(2)").hide();
		})

		function user_site(t) {
			$(t).parent().attr("class", "base_select");
			$(t).parent().siblings().attr("class", "base");
			$(t).hide();
		}
		//提交新建收货地址信息
		$(".save_recipient.save").click(function() {
			submit("../address/saveAddress.do");
		});
		function link_href() {
			$(".cha").click();
			//$("#address").reset();
			province(-1, -1, -1);
		}
		/**
		 *加载地址和三级联动
		 */
		$(function() {
			province(-1, -1, -1);
			append_address("1");
		})
		$(".go_pay").click(function() {
			var addressId = $(".base_select .user_site").next().val();
			console.log(str);
			location.href = "../balance/payment.do?ids="+str+"&addressId="+addressId;
		})
	</script>
</body>
</html>

