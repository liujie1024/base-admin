<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>报表检测</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  <body>
	<table id="dg"></table> 
	<div id="tb" style="height:auto; padding: 10px">
		<div style="text-align: left;height:30px;line-height: 25px; float: left;">
			 检测项：
			<select id="checkType" class="easyui-combobox" name="checkType" style="width:200px;">  
				<option value="">--请选择--</option> 
			    <option value="MB_ZY">MB直营</option>   
			    <option value="MB_HHR">MB合伙人</option>   
			    <option value="MM_HHR">MM品牌</option>  
			    <option value="JMSC">加盟市场</option>
			</select> 
		</div>
		<div style="text-align: left;">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-sum">检测</a> 
			&nbsp;&nbsp;
			<a href="javascript:doExport();" class="easyui-linkbutton" iconCls="icon-undo">导出</a> 
		</div>
	</div>
	
	<form id="ff" method="post"></form>
	
  </body>
  <script type="text/javascript">
  
  $(function(){
	  var checkType = $('#checkType').combobox('getValue');
	  initGrid(checkType);//初始化grid
  });
  
  //搜索
  function doSearch() {
	  var checkType = $('#checkType').combobox('getValue');
	  initGrid(checkType);//初始化grid
  }
  
  //初始化grid
  function initGrid(checkType){
	  $('#dg').datagrid({    
		    url: path + "checkreport/list.do",
		    method : 'POST', // 数据请求方式
			iconCls : 'icon-tip', // 表格头图标
			fit : true, // 表格大小自适应
			fitColumns : true, // 列大小自适应
			rownumbers : true, // 显示序号
			singleSelect : true, // 单选行
			pagination : true, // 分页组件
			pageSize : 50, // 表格默认行数
			striped : true, // 奇偶行使用不同背景色
			nowrap : false, // 是否在一行显示数据
			toolbar : $("#tb"), // 加载工具栏
			queryParams: {
				checkType: checkType
			},
			remoteSort : false, // 是否从服务器给数据排序
			multiSort : false, // 在第一列排序的基础上对第二列排序
			loadMsg : '数据加载中，请稍等...', // 加载数据时提示语
		    columns:[[    
				{field:'mb_dist_name',title:'片区',width:100,align:'center'},    
		        {field:'manage_unit_name',title:'分公司',width:100,align:'center'},    
		        {field:'check_msg',title:'上传结果',width:100,align:'center',
		        	styler: function(value, row, index) {
		        		if (value=="未上传") {
							return 'color:red;';
						}
		        		return 'color:green;';
					}
		        },
		        {field:'import_count',title:'上传条数',width:100,align:'center'}
		    ]]    
		});
  }
  
  //导出
  function doExport() {
	  var checkType = $('#checkType').combobox('getValue');
	  if(checkType==null || checkType==""){
		  $.messager.alert('提示', "请先选择检测项", 'info');
		  return false;
	  }
	  $('#ff').form('submit', {    
		    url : path + "checkreport/download.do",  
		    onSubmit: function(param){ 
		    	param.checkType = checkType; 
		    },
			success:function(data){    
		        
		    }  
	  });
  }
  
  </script>
</html>