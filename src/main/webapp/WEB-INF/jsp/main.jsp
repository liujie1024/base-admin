<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>系统管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',title:'',split:true" style="height: 80px;">
			<div style="float: left;">
				<img alt="" src="../image/logo.png" style="padding-left: 20px; padding-top: 20px;" />
			</div>
			<div style="float: right;">
				<div style="padding-right: 20px; padding-top: 30px;">
					<span>欢迎您：${userInfo.username }</span>
					<a href="javascript:void(0);" onclick="updatePsw()" style="color: gray">修改密码</a>
					<a href="javascript:tuichu();" style="color: gray">退出</a>
				</div>
			</div>
		</div>
		<div data-options="region:'south',title:'',split:true" style="height: 50px;">
			<div style="text-align: center;width: 100%; height: 40px; line-height: 40px;">
				Copyright 美特斯邦威 2019-2025，All Rights Reserved
			</div>
		</div>
		<div data-options="region:'west',title:'',split:true" style="width: 180px;">
			<div class="easyui-accordion">
				<c:forEach items="${menuClassifyList }" var="menuClassify" varStatus="oneStatus">
					<div title="${menuClassify.oneMenu.menunama }" class="menu1" data-options="iconCls:'icon-filter',selected:true">   
				    	<ul>
				    		<c:forEach items="${menuClassify.twoMenuList }" var="twoMenu" varStatus="twoStatus">
				    			<li><a href="javascript:addTabsPage('${twoMenu.menunama }', '${twoMenu.menuurl }')">${twoMenu.menunama }</a></li> 
				    		</c:forEach>
				    	</ul>
				    </div>
				</c:forEach>
			</div>  
		</div>
		<div data-options="region:'center',title:''" style="padding: 5px; background: #eee;">
			<div id="tabs"></div>
		</div>
		
		<!-- 密码修改 -->
		<div id="updatePswDiv" class="easyui-dialog" data-options="iconCls:'icon-save'" style="width:800px;height:600px;padding:10px">
			<iframe scrolling="no" id="updatePswFrame" name="updatePswFrame" frameborder="0" src="<%=request.getContextPath()%>/user/gotoPswPage.do" style="width:100%;height:100%;"></iframe>
		</div>
	</body>
	<script type="text/javascript">
	
		$(function(){
			initTabs();
			initUpdatePsw();//修改密码初始化
		});
	
		//初始化tabs面板
		function initTabs(){
			$("#tabs").tabs({ 
				width:'100%',
				height:'auto',
			    fit:true
			}); 
			initWelcomePage();
		}
		
		//初始化tabs页面，我的中心
		function initWelcomePage(){
			var src = path + "login/welcome.do";
			// 设置欢迎页面
			$('#tabs').tabs('add', {
			    title: "我的中心",
			    tabWidth: "100px",
			    closable: false,
			    content: "<iframe scrolling='no' frameborder='0' src='" + src + "' style='width:100%;height:100%;'></iframe>"
			});
		}
		
		//新增tabs
		function addTabsPage(menuname, url) {
		    var src = path + url;
		    if ($('#tabs').tabs('exists', menuname)) {
		        $('#tabs').tabs('select', menuname);
		    } else {
		        $('#tabs').tabs('add', {
		            title: menuname,
		            tabWidth: "100px",
		            closable: true,
		            content: "<iframe scrolling='no' frameborder='0' src='" + src + "' style='width:100%;height:100%;'></iframe>"
		        });
		    }
		}
		
		//退出方法
		function tuichu() {
			$.messager.confirm('确认对话框', '您确定退出？',
		      function(r) {
		          if (r) {
		        	  logout();//后台请求退出
		          }
		      });
		}
		
		//后台请求退出
		function logout() {
			$.ajax({
                type: "POST",
                url: path + '/login/logout.do?random=' + Math.random(),
                success: function(data) {
					window.location.href = path + "login.jsp";//首页
                }
            });
		}
		
		//修改密码页面打开
		function updatePsw() {
			$('#updatePswDiv').dialog('open');
		}
		
		//修改密码页面关闭
		function doClose() {
			$('#updatePswDiv').dialog('close');
		}
		
		//初始化修改密码页面
		function initUpdatePsw() {
			var dlg = $('#updatePswDiv').dialog({
				title : "修改密码",
				width : 450,
				height : 300,
				closed : true,
				cache : false,
				modal : true
			});
			$('#updatePswDiv').dialog('center');//将窗口绝对居中
		}
	
	</script>
</html>
