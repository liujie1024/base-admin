<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>直播列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
<table id="dg"></table>
<div id="tb" style="height:auto; padding: 10px">
    <div style="text-align: left;height:30px;line-height: 25px; float: left;">
        进度：<input id="progressSearch" name="progressSearch" style="width:150px;"/>
    </div>
    <div style="text-align: right;">
        <a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
        <a href="javascript:addDialog();" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    </div>
</div>
<div id="dd"></div>
</body>
<script type="text/javascript">

    $(function () {
        initGrid();//初始化grid
        getInitPageData();//初始化页面数据
    });

    //新增
    function addDialog() {
        $('#dd').dialog({
            href: path + "broadcast/gotoAddPage.do",
            title: '直播信息新增',
            width: '80%',
            height: '90%',
            closed: false,
            cache: false,
            modal: true
        });
    }

    //修改
    function updateDialog(id) {
        $('#dd').dialog({
            href: path + "broadcast/gotoUpdatePage/" + id,
            title: '直播信息修改',
            width: '80%',
            height: '90%',
            closed: false,
            cache: false,
            modal: true
        });
    }

    //初始化页面数据
    function getInitPageData() {
        $.ajax({
            type: "POST",
            url: path + "broadcast/initPageData.do?random=" + Math.random(),
            dataType: "json",
            success: function (data) {
                initPageData("progressSearch", data.progress);//进度
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

    //搜索
    function doSearch() {
        var progressSearch = $('#progressSearch').combobox('getValue');
        $('#dg').datagrid('load', {
            progress: progressSearch
        });
    }

    //初始化grid
    function initGrid() {
        $('#dg').datagrid({
            url: path + "broadcast/list.do",
            method: 'POST', // 数据请求方式
            iconCls: 'icon-tip', // 表格头图标
            fit: true, // 表格大小自适应
            fitColumns: true, // 列大小自适应
            rownumbers: true, // 显示序号
            singleSelect: true, // 单选行
            pagination: true, // 分页组件
            pageSize: 10, // 表格默认行数
            striped: true, // 奇偶行使用不同背景色
            nowrap: false, // 是否在一行显示数据
            toolbar: $("#tb"), // 加载工具栏
            remoteSort: false, // 是否从服务器给数据排序
            multiSort: false, // 在第一列排序的基础上对第二列排序
            loadMsg: '数据加载中，请稍等...', // 加载数据时提示语
            columns: [[
                {field: 'id', hidden: true},
                {field: 'progress', title: '进度', width: '8%', align: 'center'},
                {field: 'region', title: '区域', width: '8%', align: 'center'},
                {field: 'company', title: '单位', width: '8%', align: 'center'},
                {field: 'broadcastPlatform', title: '直播平台', width: '10%', align: 'center'},
                {field: 'broadcastOnlineshop', title: '网店名称', width: '10%', align: 'center'},
                {
                    field: 'broadcastDate', title: '直播日期', width: '10%', align: 'center',
                    formatter: function (value, row, index) {
                        return formatDate1(value);
                    }
                },
                {
                    field: 'broadcastBeginTime', title: '直播开始时间', width: '10%', align: 'center',
                    formatter: function (value, row, index) {
                        return formatDate(value);
                    }
                },
                {
                    field: 'broadcastEndTime', title: '直播结束时间', width: '10%', align: 'center',
                    formatter: function (value, row, index) {
                        return formatDate(value);
                    }
                },
                {field: 'broadcastNickname', title: '主播昵称', width: '10%', align: 'center'},
                {field: 'broadcastId', title: '主播ID', width: '10%', align: 'center'},
                {field: 'broadcastNums', title: '主播粉丝数', width: '8%', align: 'center'},
                {field: 'broadcastCount', title: '直播室人数', width: '8%', align: 'center'},
                {field: 'estimateSales', title: '预计销售', width: '8%', align: 'center'},
                {field: 'commission', title: '佣金', width: '8%', align: 'center'},
                {field: 'mcnOrg', title: 'MCN机构', width: '10%', align: 'center'},
                {field: 'orderPlatform', title: '订单平台', width: '10%', align: 'center'},
                {field: 'broadcastShopName', title: '直播门店名称', width: '10%', align: 'center'},
                {field: 'broadcastShopId', title: '门店ID', width: '10%', align: 'center'},
                {field: 'projectLeader', title: '负责人（招商）', width: '10%', align: 'center'},
                {field: 'projectOperation', title: '负责人（运营）', width: '10%', align: 'center'},
                {field: 'projectGoods', title: '负责人（商品）', width: '10%', align: 'center'},
                {field: 'projectPrice', title: '负责人（核款核价）', width: '10%', align: 'center'},
                {field: 'projectHead', title: '负责人（总部营运）', width: '10%', align: 'center'},
                {field: 'salesGross', title: '毛销售', width: '8%', align: 'center'},
                {field: 'salesNet', title: '净销售', width: '8%', align: 'center'},
                {field: 'returnRate', title: '退货率', width: '8%', align: 'center'},
                {field: 'score', title: '综合评分', width: '8%', align: 'center'},
                {field: 'changePrice', title: '是否支持改价', width: '8%', align: 'center'},
                {
                    field: 'createDate', title: '创建时间', width: '10%', align: 'center',
                    formatter: function (value, row, index) {
                        return formatDate(value);
                    }
                },
                {
                    field: 'updateDate', title: '更新日期', width: '10%', align: 'center',
                    formatter: function (value, row, index) {
                        return formatDate(value);
                    }
                },
                {
                    field: 'optype', title: '操作', width: '10%', align: 'center',
                    resizable: true,
                    formatter: function (value, rowData, rowIndex) { // 个性化列
                        var a = "<a href=\"javascript:updateDialog('" + rowData.id + "')\" class=\"ace_button\" >编辑</a>&nbsp;";
                        a += "<a href=\"javascript:doDelete()\" class=\"ace_button\" >删除</a>";
                        return a;
                    },
                    sortable: true
                }
            ]]
        });
    }

    //删除
    function doDelete() {
        var selectRow = $('#dg').datagrid('getSelected'); // 选择的行
        var id = selectRow.id;
        $.messager.confirm('确认对话框', '您确定删除该条记录吗？',
            function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: path + 'broadcast/deleteById.do?random=' + Math.random(),
                        data: {
                            id: id
                        },
                        success: function (data) {
                            if (data.success) {
                                $.messager.alert('提示', data.msg, 'info',
                                    function () {
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

</script>
</html>
