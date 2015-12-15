$(document).ready(function(){
	
	$("form :input.required").each(function(){
		var $required=$("<font color='red'><strong class='high' color='red'>*</Strong></font>");
		$(this).parent().append($required);
	})
	
	$("form :input").blur(function(){
		var $parent=$(this).parent();
		$parent.find("#formtips").remove();
		if($(this).is('#username')){
			if(this.value==""||this.value.length<6){
				var errorMsg='请输入至少六位的用户名.';
				$parent.append("<span id='formtips' class='onError'>"+errorMsg+"</span");
			}
			else{
				var okMsg='输入正确.';
				$parent.append("<span id='formtips' class='onSuccess'>"+okMsg+"</span");
			}
		}
		
		if($(this).is('#email')){
			if(this.value==""||(this.value!=""&&!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/g.test(this.value))){
				var errorMsg='请输入正确的email.';
				$parent.append("<span id='formtips' class='onError'>"+errorMsg+"</span");
			}
			else{
				var okMsg='输入正确.';
				$parent.append("<span id='formtips' class='onSuccess'>"+okMsg+"</span");
			}
		}
		
	})
	$('#send').click(function(){
		$("form .required:input ").trigger('blur');
		if($('form .onError').length>0){
			return false;
		}
		alert("注册成功，密码已发送到您的邮箱，请查收.");
	})
});




$(document).ready(function(){
	$("tr.parent").click(function(){
		$(".parent")
		.addClass("selected")
		.siblings(".child_"+this.id).toggle();
	})
})




$(document).ready(function(){
	$("tbody>tr:odd").addClass("odd");
	$("tbody>tr:even").addClass("even");
	//$("tr:contains('重庆')").addClass("selected");
	$("tbody>tr").click(function(){
		$(this)
		.addClass("selected")
		.siblings().removeClass("selected")
		.end()
		.find(":radio").attr("checked",true);
	})
	$("table :radio:checked").parent().parent().addClass("selected");
	
})