<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>导入数据</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  
  <body>
	<div style=" width: 100%; margin: 0 auto;">
		<form id="ff" method="post" enctype="multipart/form-data">  
			<table width="100%" style="text-align: center;">
				<tr>
					<td height="50px">
						<strong>直营&合伙分公司目标数据导入</strong>
					</td>
				</tr>
				<tr>
					<td height="80px">
						<input id="fb" type="text" name="file" style="width:390px" />
					</td>
				</tr>
				<tr>
					<td>
						<a id="btn" href="javascript:onClear();" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">重置</a>&nbsp;
						<a id="btn" href="javascript:onSubmit();" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">导入</a>
					</td>
				</tr>
			</table>
		</form> 
	</div>
	
	<!-- 显示导入结果 -->
	<div>
		<div id="show_count">
			
		</div>
		<div id="show_fail" style="height: 230px; overflow: auto;">
		
		</div>
	</div>
  
  <script type="text/javascript">
  
  $(function(){
	  initFile();
  });
  
  //显示文件框
  function initFile() {
	  $('#fb').filebox({    
		  buttonText: '选择文件', 
		  buttonAlign: 'right' 
	  })
  }
  
    //提交方法
	function onSubmit() {
	    var url = path + "importbi/upload.do?random=" + Math.random();
	    $('#ff').form({
	        url: url,
	        onSubmit: function() {
	            var fileValue = $('#fb').filebox('getValue');
	            if (fileValue == null || fileValue == "") {
	                $.messager.alert('提示', '请先选择execl文件', 'info');
	                return false;
	            }
	            //打开遮罩
	            openMask();
	        },
	        success: function(data) {
	            if (null != data) {
	                var data = eval('(' + data + ')'); // data是返回的json对象，需要转换
	                var successListSize = data.successRowListSize; // 成功数据的个数
	                var failList = data.failRowList; // 失败数据
	                if (null != successListSize && null != failList) {
	                    var succLen = successListSize;
	                    var failLen = failList.length;
	                    var msg = "成功" + succLen + "条，失败" + failLen + "条";
	                    $.messager.alert('提示', msg, 'info');
	
	                    //显示个数
	                    if (failLen > 0) {
	                        $("#show_count").html("<h4>数据处理结果：失败：" + failLen + "条，详情如下：</h4>");
	                    } else if (failLen == 0) {
	                        $("#show_count").html("<h4>数据导入结果：" + msg + "</h4>");
	                    }
	                    //显示错误的数据
	                    setFailListData(failList);
	                } else {
	                    $.messager.alert('提示', '导入异常', 'info');
	                }
	            }
	            //关闭遮罩
	            closeMask();
	        },
	        onLoadError: function() {
	            $.messager.alert('提示', '导入异常', 'info');
	            //关闭遮罩
	            closeMask();
	        }
	    });
	    $('#ff').submit();
	}
  
  //设置失败的数据
  function setFailListData(failList){
	  var failLen = failList.length;
	  if(failLen > 0) {
		  var html = "<table class='errorShow'>";
	  	  html += "<thead><tr>";
	  	  html += "<th width='9%'>EXECL行号</th>";
	  	  html += "<th width='45%'>具体内容</th>";
	  	  html += "<th width='45%'>错误说明</th>";
	  	  html += "</tr><thead>";
	  	  for (var i = 0; i < failLen; i++) {
	  		  if(i%2==0){
	  			  html += "<tr>";
	  		  } else {
	  			  html += "<tr id='tdsty'>";
	  		  }
	  	  	  html += "<td>"+failList[i].cellId+"</td>";
	  	      html += "<td>"+failList[i].object+"</td>";
	  	      html += "<td>"+failList[i].msg+"</td>";
	  		  html += "</tr>";
		  }
	  	  html += "</table>";
	  	  $("#show_fail").html(html);
	  } else {
		  $("#show_fail").html("");
	  }
  }
  
  //重置方法
  function onClear(){
	  $('#fb').filebox('clear');
  }
  
  //打开遮罩
  function openMask() {
	  $.messager.progress({ 
          title: '提示', 
          msg: '数据处理中，可能需要几分钟，请稍候……', 
          text: '' 
       });
  }
  
  //关闭遮罩
  function closeMask() {
	  $.messager.progress('close');
  }
  
  </script>
  </body>
</html>