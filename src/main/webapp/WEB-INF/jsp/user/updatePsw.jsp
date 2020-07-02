<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>密码修改</title>
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
					<td width="30%" height="35px" align="right">旧密码：</td>
					<td>
						<input class="easyui-textbox" type="password" name="oldPsw" id="oldPsw" style="width:200px" /> 
					</td>
				</tr>
				<tr>
					<td align="right" height="35px">新密码：</td>
					<td>
						<input class="easyui-textbox" type="password" name="newPsw" id="newPsw" style="width:200px" /> 
					</td>
				</tr>
				<tr>
					<td align="right" height="35px">确认新密码：</td>
					<td>
						<input class="easyui-textbox" type="password" name="confireNewPsw" id="confireNewPsw" style="width:200px" /> 
					</td>
				</tr>
				<tr>
					<td colspan="2" height="80px" align="center">
						<a href="javascript:updatePsw();" class="easyui-linkbutton" iconCls="icon-save">修改</a> 
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
	  parent.doClose();
  }
  
  //保存
  function updatePsw() {
      $("#ff").form('submit', {
          url: path + 'user/updatePsw.do?random=' + Math.random(),
          onSubmit: function(param) {
              var oldPsw = $('#oldPsw').val();//旧密码
              var newPsw = $('#newPsw').val();//新密码
              var confireNewPsw = $('#confireNewPsw').val();//确认新密码
              if (oldPsw == null || oldPsw == "") {
            	  $('#oldPsw').textbox({ required: true }); //提示必填
                  return false;
              }
              if (newPsw == null || newPsw == "") {
            	  $('#newPsw').textbox({ required: true }); //提示必填
                  return false;
              }
			  if (confireNewPsw == null || confireNewPsw == "") {
				  $('#confireNewPsw').textbox({ required: true }); //提示必填
                  return false;
              }
			  if(confireNewPsw.trim() != newPsw.trim()) {
				  $.messager.alert('提示', '2次密码输入不一致', 'info')
				  return false;
			  }
			  //传到后台的参数
			  param.oldPsw = oldPsw;
			  param.newPsw = newPsw;
			  param.confireNewPsw = confireNewPsw;
          },
          success: function(data) {
              var data = eval('(' + data + ')'); // data是返回的json对象，需要转换
              if (data.success) {
                  $.messager.alert('提示', data.msg, 'info',
                  function() {
                	  doCancel(); //关闭窗体
                	  parent.logout(); //重新登录
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