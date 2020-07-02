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
					<td width="30%" height="50px" align="right">角色名称：</td>
					<td>
						<input type="hidden" name="id" id="id" value="${role.id }" />
						<input class="easyui-textbox" name="rolename" id="rolename" value="${role.rolename }" style="width:200px" /> 
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
          url: path + 'role/update.do?random=' + Math.random(),
          onSubmit: function() {
              var rolename = $('#rolename').val();
              if (rolename == null || rolename == "") {
                  $.messager.alert('提示', "角色名称不能为空", 'info');
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