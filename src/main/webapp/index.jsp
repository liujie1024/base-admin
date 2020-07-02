<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  
  <body>
   	用户名：<input class="easyui-textbox" name="username" id="username" style="width:300px"> <br /><br />
  	密　码：<input class="easyui-textbox" name="password" id="password" style="width:300px"> <br /><br />
  	<a id="btn" href="javascript:login();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">登录</a> 
  </body>
  
  <script type="text/javascript">
  
  	//登录方法
  	function login() {
  		var username = $("#username").val();
  		var password = $("#password").val();
  		$.ajax({
			type : "POST",
			url : path + '/login/login.do?random=' + Math.random(),
			data : {
				username : username,
				password : password
			},
			success : function(data) {
				if(data.success){
					window.location.href = "<%=request.getContextPath() %>/login/main.do?r=" + Math.random();
				} else {
					alert(data.msg);
				}
			}
		});
  	}
  	
  </script>
</html>
