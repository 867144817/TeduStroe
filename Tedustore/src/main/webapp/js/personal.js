
/*****************************************************地址管理页面js********************************************************/
/**
 * 地址页面添加名称至输入框
 */
$(".sp").click(function(){
	var value = $(this).html();
	$("#addressName").val(value);
})

/**
 * 手机号码中间4位以*号代替转换
 * @param {Object} phone
 */
function changePhone(phone){
	var dh=phone.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');
	return dh;
}
/**
 * 校验手机号码格式是否正确
 */
$("#receiverMobile").blur(function(){
	
	var regex = /^1[3|4|5|8][0-9]\d{4,8}$/; 
	var value = $(this).val();
	console.log(value);
	if(!regex.test(value)){
		console.log("格式不对");
		//alert("格式不对");
		$(this).css("border-color","red");
	}else{
		$(this).css("border-color","#808080");
	}
})

/**
 * 提交表单时，校验必填项是否填写完整
 */
/*$(".save_recipient").click(function(){
	var receiverName = $("#receiverName").val();// 收件人
	var receiverState = $("#receiverState").val();// 省
	var receiverCity = $("#receiverCity").val();// 市
	var receiverDistrict = $("#receiverDistrict").val();// 区/县
	var receiverAddress = $("#receiverAddress").val();// 
	var receiverMobile = $("#receiverMobile").val();
	if(receiverName && receiverState && receiverCity && receiverDistrict && receiverAddress && receiverMobile){
		$("form").submit();
	}else{
		alert("请将必填信息填写完整");
	}
})*/

	
	function submit(url,id){
		console.log(id);
		var nameVal = $("#receiverName").val();
		var provinceVal = $("#receiverState").val();
		var cityVal = $("#receiverCity").val();
		var areaVal = $("#receiverDistrict").val();
		var provinceText = $("#receiverState").find("option:selected")
				.text();
		var cityText = $("#receiverCity").find("option:selected").text();
		var areaText = $("#receiverDistrict").find("option:selected")
				.text();
		var address = $("#receiverAddress").val();
		var phone = $("#receiverMobile").val();
		var tel = $("#receiverPhone").val();
		var zip = $("#receiverZip").val();
		var addressName = $("#addressName").val();
		//var falg = true;
		console.log(address);
		if (nameVal == "" || phone == ""
				|| address == ""||provinceVal==0||cityVal==0||areaVal==0) {
			alert("请将必填信息填写完整");
			//falg = false;
		}else {
			$.ajax({
				url : url,
				data : {
					id:id,
					recvUsername : nameVal,
					recvProvinceCode : provinceVal,
					recvCityCode : cityVal,
					recvAreaCode : areaVal,
					recvAddress : address,
					recvDistrict : provinceText+cityText+areaText+address,
					recvPhone : phone,
					recvTel : tel,
					recvZip: zip,
					recvTag:addressName
				},
				type : "post",
				dataType : "json",
				success : function(obj) {
					if (obj.state == 1) {
						console.log(obj);
						append_address("1");
//						location='../address/showAddress.do';
						link_href();
						$("#receiverName").val("");
						$("#receiverAddress").val("");
						$("#receiverMobile").val("");
						$("#receiverPhone").val("");
						 $("#addressName").val("");
						
					}else{
						console.log(obj.message);
						alert("修改失败");
						//location='../address/showAddress.do';
					}
				}
			})
		}
	}

	function append_address(str3){
		$.ajax({
			url : "../address/addressmsg.do",
			data : "str="+str3,
			type : "post",
			datatype : "json",
			success : function(obj) {
				var str="";
				$("#append_address").html("");
				var list = obj.data;
//				console.log(list);
				for (var i = 0; i < list.length; i++) {
					if(list[i].isDefault==0){
						if(str3=="1"){
							var city="";
							if(list[i].recvCityCode!="市辖区"){
								city=list[i].recvCityCode;
							}
							str = '<div id="" class="base">'
								+'<i class="address_name">'+list[i].recvUsername+list[i].recvProvinceCode+'</i>'
								+'<i class="user_address"> '+list[i].recvProvinceCode+' '+city+' '+list[i].recvAreaCode+list[i].recvAddress+' '+list[i].recvPhone+'</i>'
								+'<i class="user_site rt" onclick="setDefault(this);user_site(this);">设为默认地址</i>'
								+'<input type="hidden" value="'+list[i].id+'"/>'
								+'</div>';
						}else{
							 str = '<div class="aim_content_one ">'
								+'<span class="dzmc  dzmc_normal'
								+'">'+list[i].recvTag+'</span> '
								+'<span class="dzxm dzxm_normal">'+list[i].recvUsername+'</span> '
								+'<span class="dzxq dzxq_normal">'+list[i].recvDistrict+'</span> '
								+'<span class="lxdh lxdh_normal">'+list[i].recvPhone+'</span> '
								+'<span class="operation operation_normal"> '
								+'    <span class="aco_change" onclick="aco_change('+list[i].id+');">修改</span> | '
								+'    <span class="aco_delete">'
								+'        <a href="###" onclick="address_del('+list[i].id+')">删除</a>'
								+'    </span>'
								+'</span>'
								+'<span class="swmr swmr_normal" onclick="setDefault(this)">'
								+'设为默认'
								+'</span>'
								+'<input type="hidden" value="'+list[i].id+'"/>'
								+'</div>';
						}
					
					}else{
						if(str3=="1"){
							var city="";
							if(list[i].recvCityCode!="市辖区"){
								city=list[i].recvCityCode;
							}
							 str = '<div id="" class="base_select">'
									+'<i class="address_name">'+list[i].recvUsername+list[i].recvProvinceCode+'</i>'
									+'<i class="user_address"> '+list[i].recvProvinceCode+' '+city+' '+list[i].recvAreaCode+list[i].recvAddress+' '+list[i].recvPhone+'</i>'
									+'<i class="user_site rt" onclick="setDefault(this);user_site(this);">设为默认地址</i>'
									+'<input type="hidden" value="'+list[i].id+'"/>'
									+'</div>';
						}else{
							 str = '<div class="aim_content_one aim_active">'
								+'<span class="dzmc dzmc_active'
								+'">'+list[i].recvTag+'</span> '
								+'<span class="dzxm dzxm_normal">'+list[i].recvUsername+'</span> '
								+'<span class="dzxq dzxq_normal">'+list[i].recvDistrict+'</span> '
								+'<span class="lxdh lxdh_normal">'+list[i].recvPhone+'</span> '
								+'<span class="operation operation_normal"> '
								+'    <span class="aco_change" onclick="aco_change('+list[i].id+');">修改</span> | '
								+'    <span class="aco_delete">'
								+'        <a href="###" onclick="address_del('+list[i].id+')">删除</a>'
								+'    </span>'
								+'</span>'
								+'<span class="swmr swmr_normal" onclick="setDefault(this)"></span>'
								+'<input type="hidden" value="'+list[i].id+'"/>'
								+'</div>';
						}
					}
//					console.log(str);
					$("#append_address").append(str);
					if(str3=="1"){
						console.log(!$("#more").hasClass("upup"));
						if(!$("#more").hasClass("upup")){
							console.log($(".base"));
							$(".base").hide();
						}else{
							
						}
					}
				}
			},
			error : function() {

			}
			
		})
		
	}
/**
 * 地址设为默认点击事件
 */


/**
 * 设置默认方法
 * @param {Object} e
 */
function setDefault(e){
	var parent = $(e).parent();
	if($(parent).siblings().hasClass("aim_active")){
		$(parent).siblings().removeClass("aim_active");
		$(parent).siblings().children(".dzmc_active").removeClass("dzmc_active").addClass("dzmc_normal");
		$(parent).siblings().children(".swmr_normal").html("设为默认")
	}
	$(parent).addClass("aim_active");
	$(parent).children(".dzmc_normal").removeClass("dzmc_normal").addClass("dzmc_active");
	$(e).html("");
	var id = $(e).next().val();
	$.ajax({
		url:"../address/update.do",
		data:"id="+id,
		type:"post",
		dataType:"json",
		success:function(obj){
			console.log(obj.message);
		}
	})
}

/************************删除地址功能************************/

function address_del(param){
	var falg = confirm("确定删除吗？");
	//console.log($(this).find("a").data("id"));
	if(falg){
		var url = "../address/del.do";
//		var param = $(this).find("a").data("id");
		console.log(url);
		$.post(
				url,{id:param},
				function(data){
				
		},"json");
		append_address();
//		location.href = '../address/showAddress.do';
	}
}

/*****************************************************个人信息管理页面js********************************************************/

/**
 * 这是个人信息页面js
 */
// 跳页面
function toPage(page){
	window.location.href=page;
}


/**
 * 性别选择男
 */
$(".man").click(function(){
	if(!$(".man").hasClass("selected")){
		$(".man").addClass("selected");
		$(".man img").attr("src","../images/personage/select.png");
		$(".women").removeClass("selected");
		$(".women img").attr("src","../images/personage/un_select.png");
	}
})

/**
 * 性别选择女
 */
$(".women").click(function(){
	if(!$(".women").hasClass("selected")){
		$(".women").addClass("selected");
		$(".women img").attr("src","../images/personage/select.png");
		$(".man").removeClass("selected");
		$(".man img").attr("src","../images/personage/un_select.png");
	}
})
/**
 * 更改用户名，变成可编辑状态
 */
$(".change_username").click(function(){
	var parent = $(".change_username").parent();
	$(parent).children(".rs_username").hide();
	var name = $(parent).children(".rs_username").html();
	$(parent).children(".ed_username").val(name);
	$(parent).children(".ed_username").show();
})

/**
 * 更改邮箱，变成可编辑状态
 */
$(".change_mail").click(function(){
	var parent = $(".change_mail").parent();
	$(parent).children(".rs_mail").hide();
	var email = $(parent).children(".rs_mail").html();
	console.log(email)
	$(parent).children(".ed_email").val(email);
	$(parent).children(".ed_email").show();
})
/**
 * 三级联动
 */
	
	//查找省
	function province(provinceCode,cityCode,areaCode){
			$.ajax({
				url : "../dict/province.do",
				data : "",
				type : "post",
				dataType : "json",
				success : function(obj) {
					//console.log(obj.data);
					if (obj.state == 1) {
						var province = obj.data;
						$("#receiverState").html("<option value='0'>---- 选择省 ----</option>");
						//$("#receiverState").html("");
						//遍历省
						for (var i = 0; i < province.length; i++) {
							$("#receiverState").append(
									"<option value='"+province[i].provinceCode+"'>"
											+ province[i].provinceName
											+ "</option>");
						}
						console.log("省份遍历完成")
						if(provinceCode!=-1){
							//console.log(provinceCode);
							$("#receiverState").val(provinceCode);
						}
					}
				}
			})
			city(provinceCode,cityCode,areaCode);
		}
//查找城市
		function city(provinceCode,cityCode,areaCode) {
			$.ajax({
				url : "../dict/getCity.do",
				data : {
					provinceCode:provinceCode
					/* cityCode:$("#receiverCity").val(),
					areaCode:$("#receiverDistrict").val() */
				},
				type : "post",
				dataType : "json",
				success : function(obj) {
					//console.log(obj.data);
					if (obj.state == 1) {
						var city = obj.data;
						$("#receiverCity").html("<option value='0'>---- 选择市 ----</option>");
						//$("#receiverCity").html("");
						//遍历城市
						for (var i = 0; i < city.length; i++) {
							$("#receiverCity").append(
									"<option value='"+city[i].cityCode+"'>"
											+ city[i].cityName + "</option>");
						}
						console.log("城市遍历成功");
						if(cityCode!=-1){
							//console.log(cityCode);
							$("#receiverCity").val(cityCode);
						}
					}
				}
			})
			area(cityCode,areaCode);
		}

	//查找地区
		function area(cityCode,areaCode) {
			$.ajax({
				url : "../dict/getArea.do",
				data : {
					/* provinceCode:$("#receiverState").val(), */
					cityCode:cityCode
					/* areaCode:$("#receiverDistrict").val() */
				},
				type : "post",
				dataType : "json",
				success : function(obj) {
					//console.log(obj.data);
					if (obj.state == 1) {
						var area = obj.data;
						$("#receiverDistrict").html("<option value='0'>---- 选择区 ----</option>");
						//$("#receiverDistrict").html("");
						//遍历地区
						for (var i = 0; i < area.length; i++) {
							$("#receiverDistrict").append(
									"<option value='"+area[i].areaCode+"'>"
											+ area[i].areaName + "</option>");
						}
						console.log("地区遍历成功");
						if(areaCode!=-1){
							//console.log(areaCode);
							$("#receiverDistrict").val(areaCode);
						}
					}
				}
			})
			
		}
	
	
	//选择省onchange事件
	$("#receiverState").change(function() {
		if ($(this).val() != null) {
			//调用查找城市方法
			city($(this).val(),-1,-1);
		} else {

		}
	})
		//选择市onchange事件
		$("#receiverCity").change(function() {
			if ($(this).val() != null) {
				area($(this).val(),-1);
			} else {
				$("#receiverDistrict").html("<option value='0'>---- 选择区 ----</option>");
			}
		});
