$.ajax({
	url:"../user/session.do",
	type:"post",
	data:"",
	dataType:"json",
	success:function(obj){
		if(obj.state==0){
			$("img.shopcar").parent().parent().next().next().html('<a href="personage.html">'+obj.message+'</a>');
		}
		
	}
})