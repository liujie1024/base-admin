<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户登录</title>
</head>

<body>
<div class="easyui-dialog" style="width: 400px; height: 220px; top:20%;" modal="true" closable="false" title="用户登入"
     buttons="#dlg-buttons" align="center">
    <form id="ff" method="post">
        <div style="height: 60px; line-height: 60px">
            <label for="name">用户名：</label>
            <input class="easyui-textbox" name="username" id="username" style="width:160px"/>
        </div>
        <div style="height: 40px; line-height: 40px">
            <label for="email">密　码：</label>
            <input class="easyui-textbox" type="password" name="password" id="password" style="width:160px"/>
        </div>
        <div id="errorShow" style="height: 20px; line-height: 20px; color: red;">

        </div>
        <div style="height: 40px; line-height: 40px">
            <a href="javascript:reset();" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">重置</a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:login();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">登录</a>
        </div>
    </form>
</div>
</body>

<script type="text/javascript">

    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) { // enter 键
            login();
        }
    };

    //登录方法
    function login() {
        $('#ff').form('submit', {
            url: path + 'login/login.do',
            onSubmit: function (param) {
                var username = $("#username").val();
                var password = $("#password").val();
                if (username == null || username == "") {
                    $('#username').textbox({
                        required: true
                    }); //提示必填
                    return false;
                }
                if (password == null || password == "") {
                    $('#password').textbox({
                        required: true
                    }); //提示必填
                    return false;
                }
                //提交参数
                param.username = username;
                param.password = password;
            },
            success: function (data) {
                var data = eval('(' + data + ')'); // change the JSON string to javascript object
                if (data.success) {
                    window.location.href = path + "login/main.do?r=" + Math.random();
                } else {
                    $("#errorShow").text(data.msg);
                }
            }
        });
    }

    //重置
    function reset() {
        $('#username').textbox('setValue', '');
        $('#password').textbox('setValue', '');
        $("#errorShow").text('');
    }

</script>
</html>
