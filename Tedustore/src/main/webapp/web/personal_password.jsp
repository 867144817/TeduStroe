<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 达内学子商城</title>
    <link href="../css/orders.css" rel="Stylesheet"/>
    <link href="../css/header.css" rel="Stylesheet"/>
    <link href="../css/footer.css" rel="Stylesheet"/>
    <link href="../css/personage.css" rel="stylesheet" />
</head>
<body>
<!-- 包含头部片段 -->
 <jsp:include page="header.jsp"></jsp:include>
<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="">首页<span>&gt;</span>个人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <!-- 左边栏-->
   <jsp:include page="left.jsp"></jsp:include>
    <!-- 右边栏-->
    <!--个人信息头部-->
 
    <div class="rightsidebar_box rt">
        <div class="rs_header">
            <span ><a href="../user/showPerson.do">我的信息</a></span>
            <span class="showPassword.do"><a href="personal_password.html">安全管理</a></span>
        </div>

        <!--安全管理 -->
        <div class="rs_content">
            <p class="change_password_title">更改密码</p>
            <div class="new_password">
                <span class="word">输入旧密码：</span><input type="password" name="oldPwd" id="oldPwd" onblur=""/><span class="change_hint"></span>
            </div>
            <div class="new_password">
                <span class="word">输入新密码：</span><input type="password" name="newPwd" id="newPwd"/><span class="change_hint"></span>
            </div>
            <div class="confirm_password">
                <span class="word">确认新密码：</span><input type="password" name="confirmPwd" id="confirmPwd"/><span class="confirm_hint"></span>
            </div>
            <div class="save_password">
                保存更改
            </div>
        </div>
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
function checkPasswordLength(t){
	//return pwd.length>=6&&pwd.length<=9;
	console.log();
	if(t.val().length>=6&&t.val().length<=14){
		t.next().css("color","green");
		t.next().html("密码格式正确");
	}else{
		t.next().css("color","red");
		t.next().html("密码格式不正确");
	}
}
//验证新密码和确认密码是否相同
function checkEqualsPwd(){
	//获取新密码框的值
	var newPwdValue = $("#newPwd").val();
	//获取确认新密码框的值
	var confirmPwdValue = $("#confirmPwd").val();
	return newPwdValue==confirmPwdValue;
}
//旧密码框长度的验证
$("#oldPwd").blur(function(){
	checkPasswordLength($(this));
});
//新密码框长度的验证
$("#newPwd").blur(function(){
	checkPasswordLength($(this));
});
//确认新密码框长度的验证
$("#confirmPwd").blur(function(){
	//checkPasswordLength($(this));
	if(checkEqualsPwd()){
		$(this).next().css("color","green");
		$(this).next().html("上下输入一致");
	}else{
		$(this).next().css("color","red");
		$(this).next().html("上下输入不一致");
	}
	
});

$(".save_password").click(function(){
	var oldpwd = $("#oldPwd").val();
	var newpwd = $("#newPwd").val();
	console.log(oldpwd);
	if(oldpwd==""){
		$("#oldPwd+.change_hint").html("旧密码不能为空");
	}else if(newpwd==""){
		$("#newPwd+.change_hint").html("新密码不能为空");
	}else if($("#confirmPwd").val()!=""){
		if(checkEqualsPwd()){
			console.log("开请请求");
			$.ajax({
				url:"../user/updatePwd.do",
				type:"post",
				data:"oldpwd="+oldpwd+"&password="+newpwd,
				dataType:"json",
				success:function(obj){
					console.log(obj.message);
					$("#oldpwd+.change_hint").html(obj.message);
					alert(obj.message);
					location='showPassword.do';
				}
				
			})
		}else{
			$("#confirmPwd").next().css("color","red");
			$("#confirmPwd").next().html("上下输入不一致");
		}
	}else{
		$("#confirmPwd").next().html("确认密码不能为空");
	}
})
</script>
<script type="text/javascript">
//处理侧边栏
$(function(){
	$("#leftsidebar_box dd").hide();
  //$("#leftsidebar_box .count_managment dd").show();
  //设置帐号管理的列表显示
  
  //设置所有图片为myOredr2.png
  
  //设置帐号管理图片为myOrder1.png
  
  
	$(".count_managment dt").click();
});

</script>
</html>