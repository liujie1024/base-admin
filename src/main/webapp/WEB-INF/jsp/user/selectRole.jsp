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
	<div id="cc_selectRole" class="easyui-layout" style="width:630px;height:350px;">   
	    <div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
	    	<input type="hidden" id="userId" value="${userId }" />
	   		<table id="role_dg"></table>
	    </div>   
	    <div data-options="region:'south',title:'',split:true" style="height:50px; line-height:40px; text-align: center;">
	    	<a href="javascript:chooseRole4User();" class="easyui-linkbutton" iconCls="icon-ok">选择</a> 
	    	&nbsp;
			<a href="javascript:doCancel();" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
	    </div>
	</div> 

	<script type="text/javascript">
	
		$(function() {
			initGrid();//初始化grid
		});
		
		 //取消，关闭dialog
		 function doCancel() {
			 $('#dd').dialog("close");  
		 }
		 
		//选择角色
		 function chooseRole4User() {
			 var userId = $("#userId").val();
			 var checkedRoleJson = $('#role_dg').datagrid('getChecked');//所有被选择的数据
			 if(checkedRoleJson==null || checkedRoleJson.length==0){
				 $.messager.alert('提示', '请选择角色', 'info');
				 return;
			 }
			 var roleListStr = JSON.stringify(checkedRoleJson);//json对象转成json字符串
			 $.ajax({
                 type: "POST",
                 url: path + 'user/chooseRole4User.do?random=' + Math.random(),
                 data: {
                	 userId : userId,
                	 roleListStr : roleListStr
                 },
                 success: function(data) {
                     if (data.success) {
                         $.messager.alert('提示', data.msg, 'info',
                         function() {
                        	 $('#dd').dialog('close');//关闭当前窗体
                             $('#dg').datagrid('reload'); // 重载行。等同于'load'方法，但是它将保持在当前页。
                         });
                     } else {
                         $.messager.alert('提示', data.msg, 'error');
                     }
                 }
             });
		 }
		 
		//初始化grid
		function initGrid() {
			var userId = $("#userId").val();
			$('#role_dg').datagrid({
				url : path + "role/notPageList4Role.do?userId="+userId,
				method : 'POST', // 数据请求方式
				iconCls : 'icon-tip', // 表格头图标
				fit : true, // 表格大小自适应
				fitColumns : true, // 列大小自适应
				rownumbers : true, // 显示序号
				singleSelect : false, // 单选行
				pagination : false, // 分页组件
				pageSize : 10, // 表格默认行数
				striped : true, // 奇偶行使用不同背景色
				nowrap : false, // 是否在一行显示数据
				//	toolbar : $("#tb"), // 加载工具栏
				remoteSort : false, // 是否从服务器给数据排序
				multiSort : false, // 在第一列排序的基础上对第二列排序
				loadMsg : '数据加载中，请稍等...', // 加载数据时提示语
				columns : [ [ {
					field : '',
					checkbox : true,//如果为true，则显示复选框。该复选框列固定宽度。
				},{
					field : 'id',
					hidden : true
				}, {
					field : 'rolename',
					title : '角色名称',
					width : '33%',
					align : 'center'
				}, {
					field : 'createTime',
					title : '创建时间',
					width : '33%',
					align : 'center',
					formatter : function(value, row, index) {
						return formatDate(value);
					}
				}, {
					field : 'createUser',
					title : '创建人',
					width : '32%',
					align : 'center'
				} ] ],
				onLoadSuccess:function(data){ 
					var rowData = data.rows;
					var tempCheck = "";
					for (var i = 0; i < rowData.length; i++) {
						tempCheck = rowData[i].isCheck;
						if(tempCheck!=null && tempCheck!="" && tempCheck=="1"){
							$(this).datagrid("selectRow", i);//选中
						}
					}
				}
			});
		}
	</script>
</body>
</html>
