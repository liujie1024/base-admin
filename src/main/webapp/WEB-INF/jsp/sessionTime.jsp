<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">
	function countDown(secs, surl) {
		var jumpTo = document.getElementById('jumpTo');
		jumpTo.innerHTML = secs;
		if (--secs > 0) {
			setTimeout("countDown(" + secs + ",'" + surl + "')", 1000);
		} else {
			window.parent.location.href = surl;
			-ma;
		}
	}
</script>

</head>
<body>
	<div style="width: 100%; text-align: center; margin-top: 30px;">
		<h2>登录超时...</h2>
		<span id="jumpTo" style="color: red;">3</span> 秒后系统会自动跳转
	</div>
	
	<script type="text/javascript">
		countDown(3, path + 'login.jsp');
	</script>
</body>
</html>
