<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  
  <body>
	<div style=" width: 100%; margin: 0 auto;">
		<form id="ff" method="post">   
			<table class="formTable" width="100%">
				<tr>
					<td colspan="2" height="20px"></td>
				</tr>
				<tr>
					<td width="30%" height="50px" align="right">用户名：</td>
					<td>
						<input type="hidden" name="id" id="id" value="${user.id }" />
						<input class="easyui-textbox" name="username" id="username" value="${user.username }" style="width:200px" /> 
					</td>
				</tr>
				<tr>
					<td colspan="2" height="80px" align="center">
						<a href="javascript:doUpdate();" class="easyui-linkbutton" iconCls="icon-save">修改</a> 
						&nbsp;
						<a href="javascript:doCancel();" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
					</td>
				</tr>
			</table>
		</form> 
	</div>
  
  <script type="text/javascript">
  $(function(){
	 
  });
  
  //取消，关闭dialog
  function doCancel() {
	  $('#dd').dialog("close");  
  }
  
  //更新
  function doUpdate() {
      $("#ff").form('submit', {
          url: path + 'user/update.do?random=' + Math.random(),
          onSubmit: function() {
              var username = $('#username').val();
              if (username == null || username == "") {
                  $.messager.alert('提示', "用户名不能为空", 'info');
                  return false;
              }
              var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
           	  if(reg.test(username)){     
                   $.messager.alert('提示', "用户名不能含有汉字", 'info');
                   return false;
              }  
          },
          success: function(data) {
              var data = eval('(' + data + ')'); // data是返回的json对象，需要转换
              if (data.success) {
                  $.messager.alert('提示', data.msg, 'info',
                  function() {
                      $('#dd').dialog("close"); //重新加载
                      doSearch(); //重新加载
                  });
              } else {
                  $.messager.alert('提示', data.msg, 'info');
              }
          }
      });
  }
  
  </script>
  </body>
</html>