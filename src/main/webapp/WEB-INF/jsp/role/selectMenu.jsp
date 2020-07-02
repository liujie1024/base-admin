<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<div id="cc_selectMenu" class="easyui-layout" style="width:435px;height:260px;">   
	    <div data-options="region:'center',title:''" style="padding:5px;">
	    	<input type="hidden" id="roleId" value="${roleId }" />
	    	<ul id="tt"></ul>
	    </div>   
	    <div data-options="region:'south',title:'',split:true" style="height:50px; line-height:40px; text-align: center;">
	    	<a href="javascript:chooseMenu4Role();" class="easyui-linkbutton" iconCls="icon-ok">确定</a> 
	    	&nbsp;
			<a href="javascript:doCancel();" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
	    </div>
	</div> 

	<script type="text/javascript">
	$(function() {
		initMenuTree();//初始化grid
	});
	
	 //取消，关闭dialog
	 function doCancel() {
		 $('#dd').dialog("close");  
	 }
	
	//tree控件的加载
	 function initMenuTree() {
		 var roleId = $("#roleId").val();
	     $('#tt').tree({
	         url: path + "menu/initMenuTree.do?random=" + Math.random(),
	         checkbox: true,
	         queryParams:{
	        	 roleId: roleId
	         },
	         onClick: function(node) {

	 		 }
	     });
	 }
	
	//为角色选择对应的菜单
	  function chooseMenu4Role() {
	      var roleId = $("#roleId").val();
	      var menuChecked = $('#tt').tree('getChecked'); // get checked nodes
	      var parentChecked = $('#tt').tree('getChecked', 'indeterminate'); // 获取不确定的节点,相当于子菜单未全选的时候，父菜单是不确定的状态
	      //子菜单和父菜单合并一起，放到数组中
	      var menuIdsArray = new Array();
	      for (var i = 0; i < menuChecked.length; i++) {
	          menuIdsArray[i] = menuChecked[i].id; //选中的菜单放到数组中
	      }
	      var len = menuIdsArray.length; //父菜单集合的起点是放在子菜单集合的最后
	      for (var j = 0; j < parentChecked.length; j++) {
	          menuIdsArray[len + j] = parentChecked[j].id; //选中的不确定菜单放到数组中
	      }

	      var menuIdsStr = menuIdsArray.join(",");
	      $.ajax({
	          type: "POST",
	          url: path + 'role/chooseMenu4Role.do?random=' + Math.random(),
	          data: {
	              roleId: roleId,
	              menuIdsStr: menuIdsStr
	          },
	          success: function(data) {
	              if (data.success) {
	                  $.messager.alert('提示', data.msg, 'info',
	                  function() {
	                      $('#dd').dialog('close'); //关闭当前窗体
	                      $('#dg').datagrid('reload'); // 重载行。等同于'load'方法，但是它将保持在当前页。
	                  });
	              } else {
	                  $.messager.alert('提示', data.msg, 'error');
	              }
	          }
	      });
	  }
	
	</script>
</body>
</html>
