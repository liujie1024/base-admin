<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  <body>
	 <div id="cc" class="easyui-layout" style="width:100%;height:100%;">   
	    <div data-options="region:'west',title:'',split:true" style="width:30%;">
	    	<ul id="tt"></ul> 
	    </div>   
	    <div data-options="region:'center',title:''" style="padding:5px;">
	    	<form id="ff" method="post">   
				<table class="formTable" width="100%">
					<tr>
						<td width="35%" height="50px" align="right">菜单级别：</td>
						<td>
							<input id="grade" name="grade" style="width:260px" /> 
						</td>
					</tr>
					<tr>
						<td width="35%" height="50px" align="right">ID：</td>
						<td height="20px">
							<input class="easyui-textbox" type="text" name="id" id="id" style="width:260px" />
							<span style="color: red;">*不可修改，为空表示新增</span>
						</td>
					</tr>
					<tr>
						<td width="35%" height="50px" align="right">菜单名：</td>
						<td>
							<input class="easyui-textbox" name="menunama" id="menunama" style="width:260px" /> 
						</td>
					</tr>
					<tr id="menuurl_tr">
						<td align="right" height="50px">菜单URL：</td>
						<td>
							<input class="easyui-textbox" name="menuurl" id="menuurl" style="width:260px" /> 
						</td>
					</tr>
					<tr id="parentId_tr">
						<td align="right" height="50px">上级菜单：</td>
						<td>
							<input name="parentId" id="parentId" style="width:260px" /> 
						</td>
					</tr>
					<tr>
						<td colspan="2" height="80px" align="center">
							<a href="javascript:doSaveOrUpdate();" class="easyui-linkbutton" iconCls="icon-save">确认</a> 
							&nbsp;
							<a href="javascript:doDel();" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
							&nbsp;
							<a href="javascript:doReset();" class="easyui-linkbutton" iconCls="icon-cancel">重置</a>
						</td>
					</tr>
				</table>
			</form> 
	    </div>   
	</div>
  </body>
  <script type="text/javascript">
  
  $(function(){
	  $('#id').textbox('readonly');//id只读
	  initPage();//初始化数据
  });
  
  //初始化数据
  function initPage(){
	  initMenuTree();//tree控件的加载
	  initParentMenu();//初始化下拉上级菜单
	  setGrade();//设置初始化，菜单级别
  }
  
  //设置初始化，菜单级别
  function setGrade(){
	  var jsonData = [{id:'1',text:"一级菜单", "selected":true   }, {id:'2',text:"二级菜单"}]; 
	  $('#grade').combobox({ 
		    data:jsonData,
		    valueField:'id',    
		    textField:'text',
		    panelHeight:100,
		    onSelect: function(rec){    
		    	if(rec.id == "1"){
		    		$('#menuurl_tr').hide();
		 			$('#parentId_tr').hide();
		    	} else if(rec.id == "2"){
		    		$('#menuurl_tr').show();
		 			$('#parentId_tr').show();
		    	}
	        }
      });  
  }
  
  //tree控件的加载
  function initMenuTree(){
	  $('#tt').tree({    
	     url: path + "menu/initMenuTree.do?random=" + Math.random(),  
	     onClick: function(node){
	 		// alert(node.id); 
	 		var text = node.text;
	 		if(text=="其他"){
	 			$('#menunama').textbox('disable');
	 			$('#menuurl').textbox('disable');
	 			$('#parentId').textbox('disable');
	 		} else {
	 			$('#menunama').textbox('enable');
	 			$('#menuurl').textbox('enable');
	 			$('#parentId').textbox('enable');
	 		}
	    	$('#id').textbox('setValue', node.id);
	 		$('#menunama').textbox('setValue', node.text);
	 		$('#menuurl').textbox('setValue', node.attributes.menuurl);
	 		$('#parentId').combobox('setValue', node.attributes.parentId);
	 		$('#grade').combobox('setValue', node.attributes.grade);
	 	}
	  }); 
  }
  
  //初始化下拉上级菜单
  function initParentMenu(){
	  $('#parentId').combobox({    
		  url: path + "menu/initParentMenu.do?random=" + Math.random(), 
		  valueField:'id',    
		  textField:'text'   
	  });
  }
  
  //新增或者修改
  function doSaveOrUpdate(){
	  var url = path + "menu/save.do?random=" + Math.random();//新增
	  var menuId = $('#id').val();
	  if(menuId!=null && menuId!=""){
		  url = path + "menu/update.do?random=" + Math.random();//修改
	  }
	  
	  $("#ff").form('submit', {
          url: url,
          onSubmit: function() {
              var menunama = $('#menunama').val();
              if (menunama == null || menunama == "") {
                  $.messager.alert('提示', "菜单名不能为空", 'info');
                  return false;
              }
          },
          success: function(data) {
              var data = eval('(' + data + ')'); // data是返回的json对象，需要转换
              if (data.success) {
                  $.messager.alert('提示', data.msg, 'info',
                  function() {
                	 initPage();//初始化数据
                  });
              } else {
                  $.messager.alert('提示', data.msg, 'info');
              }
          }
      });
  }
  
  //重置
  function doReset(){
	  $('#id').textbox('setValue', '');
	  $('#menunama').textbox('setValue', '');
	  $('#menuurl').textbox('setValue', '');
	  $('#parentId').combobox('setValue', '');
  }
  
  //删除
  function doDel() {
	  var menuId = $('#id').val();
	  if (menuId == null || menuId == "") {
          $.messager.alert('提示', "请先选择需要删除的菜单", 'info');
          return false;
      }
	  
      $.messager.confirm('确认对话框', '您确定删除该条记录吗？',
      function(r) {
          if (r) {
              $.ajax({
                  type: "POST",
                  url: path + 'menu/deleteById.do?random=' + Math.random(),
                  data: {
                      id: menuId
                  },
                  success: function(data) {
                      if (data.success) {
                          $.messager.alert('提示', data.msg, 'info',
                          function() {
                        	  initPage();//初始化数据
                          });
                      } else {
                          $.messager.alert('提示', data.msg, 'error');
                      }
                  }
              });
          }
      });
  }
  
  function test(){
	  alert(111);
  }

  </script>
</html>
