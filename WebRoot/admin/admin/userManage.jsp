<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息管理</title>
<!-- 判断权限，是否登陆 -->
<!-- 判断权限，是否登陆 -->

<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.4.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="../../jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="../../jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
//查询的javascript方法
function userSearch(){
	$('#dg').datagrid('load',{
		s_phone: $("#s_phone").val(),       //手机号
		s_type: $("#s_type").val(), //类型
	});
}
//删除的javascript方法
function userDelDialog(){
	var selectedRows=$('#dg').datagrid('getSelections');
	//判断是否选择了要删除的数据
	if(selectedRows.length==0){
		$.messager.alert("温馨提示","请选择您要删除的数据！","warning");
		return;
	}
	//获取选择的所有id
	var strIds=[];
	for(var i=0;i<selectedRows.length;i++){
		strIds.push(selectedRows[i].id);
	}
	var ids=strIds.join(",");
	$.messager.confirm("温馨提示", "您确定要删除这<font color=red size=4>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("../../zhuhu?op=del",{delIds:ids},function(result){
				if(result.success){
					$.messager.alert("温馨提示","您已成功删除<font color=red size=4>"+result.delNums+"</font>条数据！","info");
					$("#dg").datagrid("reload");//删除成功后刷新数据
				}
				else{
					$.messager.alert("温馨提示",result.errorMsg,"error");
				}
			},"json");
		}
	});
}
//添加按钮事件，打开添加数据对话框
var url
function userAddDialog(){
	$("#dlg").dialog("open").dialog("setTitle","添加用户");
	url="../../zhuhu?op=add";
}
//修改按钮事件，打开修改数据对话框
function userUpdateDialog(){
	var selectedRows = $('#dg').datagrid('getSelections');
	if(selectedRows.length==0){
		$.messager.alert("温馨提示","请选择要修改的数据！","warning");
		return;
	}
	if(selectedRows.length>1){
		$.messager.alert("温馨提示","请选择一条数据进行修改！","warning");
		return;
	}
	var row = selectedRows[0];
	$("#dlg").dialog("open").dialog("setTitle","用户信息修改");
	$("#form").form("load",row);
	url="../../zhuhu?op=update&id="+row.id;
}

//关闭对话框后要清除文本框里面的数据
function resetDialogValue(){
	$("#form").form('clear');
	$("#nickName").val("");
	$("#pwd").val("");
	$("#phone").val("");
	$("#type").val("");
	$("#tel").val("");
	$("#home_location").val("");
	$("#cur_location").val("");
}

//对话框上面的取消按钮
function closeDialog(){
	$("#dlg").dialog("close");
	resetDialogValue();
}

//对话框上面的保存按钮事件
function saveUser() {
	
	
	$("#form").form("submit",{
		url:url,
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("温馨提示",result.errorMsg,"error");
				return;
			}
			
			else{
				$.messager.alert("温馨提示","保存数据成功！","info");
				$("#dlg").dialog("close");//同时关闭对话框
				$("#dg").datagrid("reload");//刷新数据
				resetDialogValue();//保存成功之后要清除对话框里面的文本框里面的数据
			}
		},
	});
}



</script>
</head>
<body style="margin: 5px">
	<table id="dg" title="用户信息" class="easyui-datagrid" fitColumns="true"
	  pagination="true" rownumbers="true" url="../../zhuhu?op=list" toolbar="#tb" fit="true">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th align="center" field="id" width="10">用户ID</th>
				<th align="center" field="nickName"      width="10">昵称</th>
				<th align="center" field="phone"  width="15">手机号</th>
				<th align="center" field="pwd"       width="15">密码</th>
				<th align="center" field="type"       width="6">类型</th>
				<th align="center" field=money       width="18">余额</th>
				
			</tr>
		</thead>
	</table>
	<!-- 工具条，基本操作 -->
	<div id="tb" style="padding-top: 10px;padding-bottom: 10px;padding-left: 10px">
		<div title="您的位置">您的位置：导航菜单>>用户管理</div><hr>
		
		<div style="color: black"></div>
		 <div title="">
		   <a title="添加" href="javascript:userAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="">   新增</a> 
			<a title="删除" href="javascript:userDelDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="">删除</a>
		    <a title="修改" href="javascript:userUpdateDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="">修改</a> 
			
		</div> 
		<hr> 
		<div title="查询条件" style="padding-top: 5px">
			
			&nbsp;手机号：&nbsp;<input type="text" name="s_phone" id="s_phone" size=15/>&nbsp;
			&nbsp;类型：&nbsp;<input type="text" name="s_type" id="s_type" size=10/>&nbsp;
			
			<a title="查询" href="javascript:userSearch()" class="easyui-linkbutton" iconCls="icon-search" plain="">查询</a>
		</div>
	</div>
	<!-- 对话框，添加，修改时弹出的对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 450px;height: 400px" buttons="#dlg-button" title="操作对话框" closed="true">
		<form method="post" id="form" name="form">
			<table align="center" style="margin-top: 48px">
				
				<tr>
					<td>昵称：</td>
					<td><input type="text" name="nickName" id="nickName" class="easyui-validatebox" size=20 required="true"/></td>
					<td><font color="red">***此项为必填项***</font></td>
				</tr>
				<tr height="5px"></tr>
				<tr>
					<td >手机号：</td>
					<td><input type="text" name="phone" id="phone" class="easyui-validatebox" size=20 required="true"/></td>
					<td><font color="red">***此项为必填项***</font></td>
				</tr>
			
				<tr height="5px"></tr>
				<tr>
					<td >密码：</td>
					<td><input type="text" name="pwd" id="pwd" class="easyui-validatebox" size=20 required="true"/></td>
					<td><font color="red">***此项为必填项***</font></td>
				</tr>
				<tr height="5px"></tr>
				
				<tr>
					<td >余额：</td>
					<td><input type="text" name="money" id="money" class="easyui-validatebox" size=20 required="true"/></td>
					<td><font color="red">***形如  10000 ***</font></td>
				</tr>
				
				
			</table>
		</form>
	</div>
	
	
	
	<!-- 对话框的按钮，确定和取消 -->
	<div id="dlg-button">
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
		<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	</div>
</body>
</html>