function check_m(){
	if (myform.username.value==""){
		alert("用户名不能为空");myform.username.focus();return;
	}
	if (myform.password.value==""){
		alert("密码不能为空");myform.password.focus();return;
	}	
	if (myform.confpassword.value==""){
		alert("确认密码不能为空");myform.new_password.focus();return;
	}

	if (myform.password.value!=myform.confpassword.value){
		alert("两次输入的密码不一致");myform.password.focus();return;
	}
	
	myform.submit();		
}