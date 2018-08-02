<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>我的订单 - 学子商城</title>
<link href="../css/orders.css" rel="stylesheet" />
<link href="../css/header.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
</head>
<body>
	<!-- 页面顶部-->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- 我的订单导航栏-->
	<div id="nav_order">
		<ul>
			<li><a href="">首页<span>&gt;</span>订单管理
			</a></li>
		</ul>
	</div>
	<!--我的订单内容区域 #container-->
	<div id="container" class="clearfix">
		<!-- 左边栏-->
		<jsp:include page="left.jsp"></jsp:include>
		<!-- 右边栏-->
		<div class="rightsidebar_box rt">
			<!-- 商品信息标题-->
			<table id="order_list_title" cellpadding="0" cellspacing="0">
				<tr>
					<th width="345">商品</th>
					<th width="82">单价（元）</th>
					<th width="50">数量</th>
					<th width="82">售后</th>
					<th width="100">实付款（元）</th>
					<th width="90">交易状态</th>
					<th width="92">操作</th>
				</tr>
			</table>
			<!-- 订单列表项 -->
			<div id="orderItemBox"></div>
			<!--  -->

			<!--分页器-->
			<div class="tcdPageCode"></div>

		</div>
	</div>

	<!-- 底部jsp -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/index.js"></script>
<script src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<script type="text/javascript">
	function limit(num) {
		/* var num = $(".tcdPageCode .current").text();
		console.log(num); */
		$
				.ajax({
					url : "../goods/showSearch.do",
					data : "num=" + num,
					type : "post",
					dataType : "json",
					success : function(obj) {
						console.log(obj);

						var args = {
							pageCount : obj.state,
							current : num,
							backFn : function(p) {

							}
						}
						ms.fillHtml($(".tcdPageCode"), args);
						$("#orderItemBox").html("");
						//ms.bindEvent($(".tcdPageCode"), args);
						for (var i = 0; i < obj.data.length; i++) {
							var str1 = '<div id="orderItem">'
									+ '<p class="orderItem_title">'
									+ '<span id="order_id">'
									+ ' &nbsp;&nbsp;订单编号:<a href="#">12345678910</a>'
									+ ' </span>'
									+ ' &nbsp;&nbsp;成交时间：2016-01-04 18:00&nbsp;&nbsp;'
									+ '<span>'
									+ '    <a href="#" class="servie">'
									+ '  联系客服<img src="../images/myOrder/kefuf.gif"/>'
									+ '     </a>'
									+ '</span>'
									+ ' </p>'
									+ ' </div>'
									+ ' <div id="orderItem_detail">'
									+ ' <ul>'
									+ '   <li class="product">'
									+ '  <b><a href="#"><img src="../images/myOrder/product_img1.png" /></a></b>'
									+ ' <b class="product_name lf" >'
									+ ' <a href="">'
									+ obj.data[i].title
									+ '</a>'
									+ '<br/>'
									+ ' </b>'
									+ ' <b class="product_color ">'
									+ ' 颜色：深空灰'
									+ ' </b>'
									+ '</li>'
									+ '<li class="unit_price">'
									+ ' 专属价'
									+ ' <br/>'
									+ '￥8800'
									+ '</li>'
									+ '<li class="count">'
									+ ' 1件'
									+ '</li>'
									+ '<li class="sale_support">'
									+ '  退款/退货'
									+ ' <br/>'
									+ '我要维权'
									+ ' </li>'
									+ '<li class=" payments_received">'
									+ '￥1222.00'
									+ ' </li>'
									+ '<li class="trading_status">'
									+ ' <img src="../images/myOrder/car.png" alt=""/>已发货'
									+ ' <br/>'
									+ ' <a href="orderInfo.html">订单详情</a>'
									+ '<br/>'
									+ '<a href="#" class="view_logistics">查看物流</a>'
									+ '</li>'
									+ ' <li class="operate">'
									+ '    <a href="#">确认收货</a>'
									+ '</li>'
									+ '</ul>' + ' </div>';
							$("#orderItemBox").append(str1);
						}

					}
				})
	}
	$(function() {
		limit(1);
	$(".tcdPageCode").createPage({
		 pageCount:5,
		 current:1,
		 backFn:function(p){

		 }
		 }); 
	})
</script>
</html>