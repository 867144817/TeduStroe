<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>商品搜索页面</title>
<link rel="stylesheet" href="../css/header.css" />
<link rel="stylesheet" href="../css/search.css" />
<link rel="stylesheet" href="../css/footer.css" />
<style>
#wrap {
	overflow: hidden;
}

.overfl {
	width: 210px;
	margin: 5px auto;
	overflow: hidden;
	height: 34px;
	text-align: center;
}

.tcdPageCode {
	width: 100%;
}

#section .lf {
	margin-top: 12px;
	padding: 5px;
}

.addCart {
	float: right;
	margin-top: 10px;
	margin-right: 10px;
}
</style>
</head>
<body>
	<!-- 页面顶部-->
	<jsp:include page="header.jsp"></jsp:include>

	<div class="big">
		<form name="" action="" method="post">
			<section id="section">
				<p class="header">全部结果>${name.name }</p>
				<div id="wrap">
					<%-- 	<div class="lf box" id="d1">
						<div class="info">
							<div class="img pic">
								<img src="../images/search/product_img.png" alt=""
									onclick="toItemInfo(${item.id})" />
							</div>
							<div class="describe">
								<p onclick="toItemInfo(${item.id})">联想(Lenovo) YOGA900
									(YOGA4 PRO)多彩版</p>
								<span class="price"><b>￥</b><span class="priceContent">4399.00</span></span>
								<span class="addCart"><img id="collect"
									src="../images/search/care.png" alt="" /><a
									href="javascript:void(0);" class="add_cart">加入购物车</a></span>
								<!--<span class="succee" style="display: none"> 
								<img src="/images/search/product_true.png" alt="" /> 
								<span>已移入购物车</span>
							</span>-->
							</div>
						</div>
					</div> --%>
				</div>
				<!--分页器-->
				<div class="tcdPageCode"></div>
			</section>
		</form>
	</div>
	<!-- 尾部-->
	<!-- 页面底部-->
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/index.js"></script>
	<script src="../js/jquery.page.js"></script>

	<!--<script type="text/javascript">
	// var status = ${status};
	var pages = ${pageBean.totalPages};
	var index = ${pageBean.pageIndex};
	$(".tcdPageCode").createPage({
		// 总页数
	    pageCount:pages,
	 	// 起始页
	    current:index,
	    backFn:function(p){
	    	// 执行代码
	    	window.location.href="http://localhost:18888/search.html?q=${q}&page="+p;
	    }
	});
</script>-->
	<!--<script type="text/javascript">
    /* 商品详情页  */
	function toItemInfo(id) {
		if (id) {
			window.location.href="/toItemInfo/"+id+".html";
		}else {
			alert("商品id不存在");
		}
	} 
</script>-->
	<script type="text/javascript">
	/**添加到收藏**/
    $("#collect").click(function(e){
    	$(".modal").show();
		$(".modal .modal_information span").html("将您的宝贝加入收藏夹");
    })
    $(".yes").click(function(){
	    $(".modal").hide();
	    $('#collect').attr("src","../images/search/care1.png");
    })
</script>
	<script type="text/javascript">
	var pageCount = ${len};
function limit(page){
	var categoryId = window.location.search;
	categoryId = categoryId.split("=")[1];
	console.log(categoryId);
	$.ajax({
		url:"../goods/limit.do",
		data:{
			categoryId:categoryId,
			page:page
		},
		type:"post",
		dataType:"json",
		success:function(obj){
				console.log(obj);
				
			    /* var args = {
					    pageCount:obj.message,
					    current:page,
					    backFn:function(p){

					          }
						}
				   ms.fillHtml($(".tcdPageCode"), args);  */
			   pageCount=obj.message;
			   console.log(pageCount);
		 
		   $("#wrap").html("");
		   for (var i = 0; i < obj.data.length; i++) {
					var str1 = '<div class="lf" id="">'
						+'<div class="info">'
					+'<div class="img pic">'
					+	'<img src="..'+obj.data[i].image+'" alt="" onclick="toItemInfo('+obj.data[i].id+')" />'
					+'</div>'
					+'<div class="describe">'
						+'<a href="###"><p onclick="toItemInfo('+obj.data[i].id+')" class="overfl">'+obj.data[i].title+'</p></a>'
						+'<span class="price"><b>￥</b><span class="priceContent">'+obj.data[i].price+'.00</span></span>'
						+'<span class="addCart"><img id="collect" src="../images/search/care.png" alt="" />'
						+	'<a href="javascript:void(0);" class="add_cart">加入购物车</a></span>'
					+'</div>'
					+'</div>'
					+'</div>';
					$("#wrap").append(str1);
			}
		    
		}
		})
}
function toItemInfo(id) {
	if (id) {
		window.location.href="../goods/productDetails.do?id="+id;
	}else {
		alert("商品id不存在");
	}
} 

function addshop(){
	var str1 = '<span class="succee" style="display: none">'
	+'<img src="/images/search/product_true.png" alt="" />' 
			+'<span>已移入购物车</span>'
		+'</span>';
}
	$(function(){
		limit(1);
		$(".tcdPageCode").pageLimit({
			 pageCount:pageCount,
			 current:1,
			 backFn:function(p){

			 }
			 }); 
	})	
</script>
	<script>
	$(".add_cart").click(function(){
		$(".modal").show();
		$(".modal .modal_information span").html("将您的宝贝加入购物车?");
	})
	$(".yes").click(function(){
	    $(".modal").hide();
	})
	$('.no').click(function(){
    	$('.modal').hide();
    	
    })
</script>
</body>
</html>