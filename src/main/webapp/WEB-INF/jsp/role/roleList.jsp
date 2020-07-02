<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单选择</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  <body>
	<table id="dg"></table> 
	<div id="tb" style="height:auto; padding: 10px">
		<div style="text-align: left;height:30px;line-height: 25px; float: left;">
			 角色名称：<input type="text" id="rolenameSearch" name="rolenameSearch" style="width:150px;" />
		</div>
		<div style="text-align: right;">
			<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a> 
			<a href="javascript:addDialog();" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</div>
	</div>
	<div id="dd"></div> 
  </body>
  <script type="text/javascript">
  
  $(function(){
	  initGrid();//初始化grid
  });
  
  //新增
  function addDialog() {
	  $('#dd').dialog({  
		    href: path + "role/gotoAddPage.do",  
		    title: '角色新增',    
		    width: 450,    
		    height: 300,    
		    closed: false,    
		    cache: false,    
		    modal: true   
	  }); 
  }
  
  //修改
  function updateDialog(id) {
	  $('#dd').dialog({  
		    href: path + "role/gotoUpdatePage.do",  
		    queryParams: {
		    	id: id
		    },
		    title: '角色修改',    
		    width: 450,    
		    height: 300,    
		    closed: false,    
		    cache: false,    
		    modal: true   
	  }); 
  }
  
  //搜索
  function doSearch() {
	  var rolenameSearch = $('#rolenameSearch').val();
	  $('#dg').datagrid('load', {
		  rolename : rolenameSearch
	  });
  }
  
  //初始化grid
  function initGrid(){
	  $('#dg').datagrid({    
		    url: path + "role/list.do",
		    method : 'POST', // 数据请求方式
			iconCls : 'icon-tip', // 表格头图标
			fit : true, // 表格大小自适应
			fitColumns : true, // 列大小自适应
			rownumbers : true, // 显示序号
			singleSelect : true, // 单选行
			pagination : true, // 分页组件
			pageSize : 10, // 表格默认行数
			striped : true, // 奇偶行使用不同背景色
			nowrap : false, // 是否在一行显示数据
			toolbar : $("#tb"), // 加载工具栏
			remoteSort : false, // 是否从服务器给数据排序
			multiSort : false, // 在第一列排序的基础上对第二列排序
			loadMsg : '数据加载中，请稍等...', // 加载数据时提示语
		    columns:[[ 
				{field:'id',hidden:true}, 
		        {field:'rolename',title:'角色名称',width:100,align:'center'},    
		        {field:'createTime',title:'创建时间',width:100,align:'center',
		        	formatter: function(value,row,index){
		        		return formatDate(value);
					}
		        },   
		        {field:'createUser',title:'创建人',width:100,align:'center'}, 
		        {field:'optype',title:'操作',width:80,align:'center',
					resizable : true,
					formatter : function(value, rowData, rowIndex) { // 个性化列
						var a = "<a href=\"javascript:updateDialog('" + rowData.id + "')\" class=\"ace_button\" >编辑</a>&nbsp;";
						a += "<a href=\"javascript:doDelete()\" class=\"ace_button\" >删除</a>&nbsp;";
						a += "<a href=\"javascript:doFpMenu('" + rowData.id + "')\" class=\"ace_button\" >分配菜单</a>&nbsp;";
						return a;
					},
					sortable : true
				} 
		    ]]    
		});
  }
  
  //删除
  function doDelete() {
      var selectRow = $('#dg').datagrid('getSelected'); // 选择的行
      var id = selectRow.id;
      $.messager.confirm('确认对话框', '您确定删除该条记录吗？',
      function(r) {
          if (r) {
              $.ajax({
                  type: "POST",
                  url: path + '/role/deleteById.do?random=' + Math.random(),
                  data: {
                      id: id
                  },
                  success: function(data) {
                      if (data.success) {
                          $.messager.alert('提示', data.msg, 'info',
                          function() {
                              $('#dg').datagrid('reload'); // 重载行。等同于'load'方法，但是它将保持在当前页。
                          });
                      } else {
                          $.messager.alert('提示', data.msg, 'error');
                      }
                  }
              });
          }
      });
  }
  
  //分配菜单
  function doFpMenu(roleId){
	  $('#dd').dialog({  
		    href: path + "role/gotoRoleMenu.do?random=" + Math.random(),  
		    queryParams: {
		    	roleId: roleId
		    },
		    title: '菜单选择',    
		    width: 450,    
		    height: 300,    
		    closed: false,    
		    cache: false,    
		    modal: true   
	  }); 
  }
  
  </script>
</html>
