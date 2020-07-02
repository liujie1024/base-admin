<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>直播信息修改</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
<div style=" width: 100%; margin: 0 auto;">
    <form id="ff" method="post">
        <fieldset style="padding: 10px; margin: 10px; width: 92%; color: #333; border: #06c dashed 1px;">
            <legend style="color: firebrick; font-weight: bold; background: #fff; border: #b6b6b6 solid 1px; padding: 3px 6px; font-style: italic;">
                基础信息
            </legend>
            <table class="formTable" width="100%">
                <tr>
                    <td width="12%" align="right">进度：</td>
                    <td>
                        <input id="id" name="id" type="hidden" value="${broadcast.id }"/>
                        <input id="progress" name="progress" value="${broadcast.progress }"/>
                    </td>
                    <td width="12%" align="right">区域：</td>
                    <td>
                        <input id="region" name="region" value="${broadcast.region }"/>
                    </td>
                    <td width="12%" align="right">单位：</td>
                    <td>
                        <input id="company" name="company" value="${broadcast.company }"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset style="padding: 10px; margin: 10px; width: 92%; color: #333; border: #06c dashed 1px;">
            <legend style="color: firebrick; font-weight: bold; background: #fff; border: #b6b6b6 solid 1px; padding: 3px 6px; font-style: italic;">
                直播信息
            </legend>
            <table class="formTable" width="100%">
                <tr>
                    <td width="12%" align="right">直播平台：</td>
                    <td width="21%">
                        <input id="broadcastPlatform" name="broadcastPlatform" value="${broadcast.broadcastPlatform }"
                               style="width:180px"/>
                    </td>
                    <td width="12%" align="right">网店名称：</td>
                    <td width="21%">
                        <input id="broadcastOnlineshop" name="broadcastOnlineshop"
                               value="${broadcast.broadcastOnlineshop }" style="width:180px"/>
                    </td>
                    <td width="12%" align="right">订单平台：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="orderPlatform" id="orderPlatform"
                               value="${broadcast.orderPlatform }" style="width:180px"/>
                    </td>
                </tr>
                <tr>
                    <td width="12%" align="right">主播ID：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="broadcastId" id="broadcastId"
                               value="${broadcast.broadcastId }" style="width:180px"/>
                    </td>
                    <td width="12%" align="right">主播昵称：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="broadcastNickname" id="broadcastNickname"
                               value="${broadcast.broadcastNickname }"
                               style="width:180px"/>
                    </td>
                    <td width="12%" align="right">主播粉丝数：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="broadcastNums" id="broadcastNums"
                               value="${broadcast.broadcastNums }" style="width:180px"/>
                    </td>

                </tr>
                <tr>
                    <td width="12%" align="right">直播日期：</td>
                    <td width="21%">
                        <input id="broadcastDate" name="broadcastDate" type="text" class="easyui-datebox"
                               value="${broadcast.broadcastDateStr }"
                               style="width:180px"/>
                    </td>
                    <td width="12%" align="right">直播开始时间：</td>
                    <td width="21%">
                        <input class="easyui-datetimebox" name="broadcastBeginTime" id="broadcastBeginTime"
                               value="${broadcast.broadcastBeginTimeStr }"
                               data-options="showSeconds:true" style="width:180px">
                    </td>
                    <td width="12%" align="right">直播结束时间：</td>
                    <td width="21%">
                        <input class="easyui-datetimebox" name="broadcastEndTime" id="broadcastEndTime"
                               value="${broadcast.broadcastEndTimeStr }"
                               data-options="showSeconds:true" style="width:180px">
                    </td>
                </tr>
                <tr>
                    <td width="12%" align="right">MCN机构：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="mcnOrg" id="mcnOrg" value="${broadcast.mcnOrg }"
                               style="width:180px"/>
                    </td>
                    <td width="12%" align="right">预计销额：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="estimateSales" id="estimateSales"
                               value="${broadcast.estimateSales }" style="width:180px"/>
                    </td>
                    <td width="12%" align="right">佣金：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="commission" id="commission" value="${broadcast.commission }"
                               style="width:180px"/>
                    </td>
                </tr>
                <tr>
                    <td width="12%" align="right">直播室人数：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="broadcastCount" id="broadcastCount"
                               value="${broadcast.broadcastCount }" style="width:180px"/>
                    </td>
                    <td width="12%" align="right">直播门店ID：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="broadcastShopId" id="broadcastShopId"
                               value="${broadcast.broadcastShopId }" style="width:180px"/>
                    </td>
                    <td width="12%" align="right">直播门店名称：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="broadcastShopName" id="broadcastShopName"
                               value="${broadcast.broadcastShopName }"
                               style="width:180px"/>
                    </td>
                    <td width="12%" align="right"></td>
                    <td width="21%"></td>
                </tr>
            </table>
        </fieldset>
        <fieldset style="padding: 10px; margin: 10px; width: 92%; color: #333; border: #06c dashed 1px;">
            <legend style="color: firebrick; font-weight: bold; background: #fff; border: #b6b6b6 solid 1px; padding: 3px 6px; font-style: italic;">
                业绩
            </legend>
            <table class="formTable" width="100%">
                <tr>
                    <td width="12%" align="right">毛销售 ：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="salesGross" id="salesGross" value="${broadcast.salesGross }"
                               style="width:180px"/>
                    </td>
                    <td width="12%" align="right">净销售 ：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="salesNet" id="salesNet" value="${broadcast.salesNet }"
                               style="width:180px"/>
                    </td>
                    <td width="12%" align="right">退货率 ：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="returnRate" id="returnRate" value="${broadcast.returnRate }"
                               style="width:180px"/>
                    </td>
                </tr>
                <tr>
                    <td width="12%" align="right">综合评分 ：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="score" id="score" value="${broadcast.score }"
                               style="width:180px"/>
                    </td>
                    <td width="12%" align="right"></td>
                    <td width="21%">
                    </td>
                    <td width="12%" align="right"></td>
                    <td width="21%">
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset style="padding: 10px; margin: 10px; width: 92%; color: #333; border: #06c dashed 1px;">
            <legend style="color: firebrick; font-weight: bold; background: #fff; border: #b6b6b6 solid 1px; padding: 3px 6px; font-style: italic;">
                负责人
            </legend>
            <table class="formTable" width="100%">
                <tr>
                    <td width="12%" align="right">招商：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="projectLeader" id="projectLeader"
                               value="${broadcast.projectLeader }" style="width:180px"/>
                    </td>
                    <td width="12%" align="right">项目运营，策划：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="projectOperation" id="projectOperation"
                               value="${broadcast.projectOperation }"
                               style="width:180px"/>
                    </td>
                    <td width="12%" align="right">项目商品：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="projectGoods" id="projectGoods"
                               value="${broadcast.projectGoods }" style="width:180px"/>
                    </td>
                </tr>
                <tr>
                    <td width="12%" align="right">商品核款核价：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="projectPrice" id="projectPrice"
                               value="${broadcast.projectPrice }" style="width:180px"/>
                    </td>
                    <td width="12%" align="right">总部平台运营：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="projectHead" id="projectHead"
                               value="${broadcast.projectHead }" style="width:180px"/>
                    </td>
                    <td width="12%" align="right"></td>
                    <td width="21%">
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset style="padding: 10px; margin: 10px; width: 92%; color: #333; border: #06c dashed 1px;">
            <legend style="color: firebrick; font-weight: bold; background: #fff; border: #b6b6b6 solid 1px; padding: 3px 6px; font-style: italic;">
                其他
            </legend>
            <table class="formTable" width="100%">
                <tr>
                    <td width="12%" align="right">是否支持现场改价：</td>
                    <td width="21%">
                        <input class="easyui-textbox" name="changePrice" id="changePrice"
                               value="${broadcast.changePrice }" style="width:180px"/>
                    </td>
                    <td width="12%" align="right">直播尺码及备注：</td>
                    <td colspan="3">
                        <input class="easyui-textbox" name="note1" id="note1" data-options="multiline:true"
                               value="${broadcast.note1 }" style="height:60px;width:96%"/>
                    </td>
                    </td>
                </tr>
            </table>
        </fieldset>
        <table class="formTable" width="100%">
            <tr>
                <td colspan="8" height="80px" align="center">
                    <a href="javascript:doUpdate();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:doCancel();" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">
    $(function () {
        getInitPageData();//初始化页面数据
    });

    //初始化页面数据
    function getInitPageData() {
        $.ajax({
            type: "POST",
            url: path + "broadcast/initPageData.do?random=" + Math.random(),
            dataType: "json",
            success: function (data) {
                initPageData("progress", data.progress);//进度
                initPageData("region", data.region);//区域
                initPageData("company", data.company);//分公司
                initPageData("broadcastPlatform", data.platform);//直播平台
                initPageData("broadcastOnlineshop", data.onlineshop);//网店名称
            },
        });
    }

    //通用设置页面combox的方法
    function initPageData(id, jsonData) {
        $("#" + id).combobox({
            data: jsonData,
            valueField: 'id',
            textField: 'text'
        });
    }

    //取消，关闭dialog
    function doCancel() {
        $('#dd').dialog("close");
    }

    //保存
    function doUpdate() {
        $("#ff").form('submit', {
            url: path + 'broadcast/update.do?random=' + Math.random(),
            onSubmit: function () {

            },
            success: function (data) {
                var data = eval('(' + data + ')'); // data是返回的json对象，需要转换
                if (data.success) {
                    $.messager.alert('提示', data.msg, 'info',
                        function () {
                            $('#dd').dialog("close"); //重新加载
                            doSearch(); //重新加载
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