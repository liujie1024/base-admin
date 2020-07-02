updateBaseTypeGroup.jsp
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>字典分类新增</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta charset="utf-8"/>
</head>

<body>
<div style=" width: 100%; margin: 0 auto;">
    <form id="ff" method="post">
        <table class="formTable" width="100%">
            <tr>
                <td colspan="2" height="20px"></td>
            </tr>
            <tr>
                <td width="30%" height="50px" align="right">字典名称：</td>
                <td>
                    ${entity.typeGroupName }
                    <input type="hidden" name="typeGroupId" id="typeGroupId" value="${entity.id }"/>
                </td>
            </tr>
            <tr>
                <td width="30%" height="50px" align="right">类型编码：</td>
                <td>
                    <input class="easyui-textbox" name="typeCode" id="typeCode" style="width:200px"/>
                </td>
            </tr>
            <tr>
                <td align="right" height="50px">类型名称：</td>
                <td>
                    <input class="easyui-textbox" name="typeName" id="typeName" style="width:200px"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" height="80px" align="center">
                    <a href="javascript:doSave();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
                    &nbsp;
                    <a href="javascript:doCancel();" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">
    $(function () {
    });

    //取消，关闭dialog
    function doCancel() {
        $('#dd').dialog("close");
    }

    //保存
    function doSave() {
        $("#ff").form('submit', {
            url: path + 'baseType/save.do?random=' + Math.random(),
            onSubmit: function () {

            },
            success: function (data) {
                var data = eval('(' + data + ')'); // data是返回的json对象，需要转换
                if (data.success) {
                    $.messager.alert('提示', data.msg, 'info',
                        function () {
                            $('#dd').dialog("close"); //重新加载
                            doTypeSearch(); //重新加载
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