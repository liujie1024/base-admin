<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>数据字典</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
    <div data-options="region:'west',title:'数据字典'" style="width:50%;">
        <table id="dg_typeGroup"></table>
        <div id="tb_typeGroup" style="height:auto; padding: 10px">
            <div style="text-align: left;height:30px;line-height: 25px; float: left;">
                字典code：<input type="text" id="typeGroupCodeSearch" name="typeGroupCodeSearch" style="width:150px;"/>
                字典名称：<input type="text" id="typeGroupNameSearch" name="typeGroupNameSearch" style="width:150px;"/>
            </div>
            <div style="text-align: right;">
                <a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
                <a href="javascript:addTypeGroup();" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'字典分类'" style="padding:5px;">
        <table id="dg_type"></table>
        <div id="tb_type" style="height:auto; padding: 10px">
            <div style="text-align: left;height:30px;line-height: 25px; float: left;"></div>
            <div style="text-align: right;">
                <input type="hidden" name="typeGroupId" id="typeGroupId"/>
                <input type="hidden" name="typeGroupName" id="typeGroupName"/>
                <a href="javascript:addType();" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
            </div>
        </div>
    </div>
    <div id="dd"></div>
</div>
</body>
<script type="text/javascript">

    $(function () {
        initTypeGroupGrid();//数据字典列表加载
    });

    //搜索>>数据字典
    function doSearch() {
        var typeGroupCodeSearch = $('#typeGroupCodeSearch').val();
        var typeGroupNameSearch = $('#typeGroupNameSearch').val();
        $('#dg_typeGroup').datagrid('load', {
            typeGroupCode: typeGroupCodeSearch,
            typeGroupName: typeGroupNameSearch
        });
    }

    //初始化grid
    function initTypeGroupGrid() {
        $('#dg_typeGroup').datagrid({
            url: path + "baseTypeGroup/notPageList.do",
            method: 'POST', // 数据请求方式
            iconCls: 'icon-tip', // 表格头图标
            fit: true, // 表格大小自适应
            fitColumns: true, // 列大小自适应
            rownumbers: true, // 显示序号
            singleSelect: true, // 单选行
            pagination: false, // 分页组件
            pageSize: 10, // 表格默认行数
            striped: true, // 奇偶行使用不同背景色
            nowrap: false, // 是否在一行显示数据
            toolbar: $("#tb_typeGroup"), // 加载工具栏
            remoteSort: false, // 是否从服务器给数据排序
            multiSort: false, // 在第一列排序的基础上对第二列排序
            loadMsg: '数据加载中，请稍等...', // 加载数据时提示语
            columns: [[
                {field: 'id', hidden: true},
                {field: 'typeGroupCode', title: '字典code', width: 100, align: 'center'},
                {field: 'typeGroupName', title: '字典名称', width: 100, align: 'center'},
                {
                    field: 'optype', title: '操作', width: 80, align: 'center',
                    resizable: true,
                    formatter: function (value, rowData, rowIndex) { // 个性化列
                        var a = "<a href=\"javascript:updateTypeGroup('" + rowData.id + "')\" class=\"ace_button\" >编辑</a>&nbsp;";
                        a += "<a href=\"javascript:deleteTypeGroup()\" class=\"ace_button\" >删除</a>&nbsp;";
                        a += "<a href=\"javascript:initTypeGrid()\" class=\"ace_button\" >查看类型</a>&nbsp;";
                        return a;
                    },
                    sortable: true
                }
            ]], onLoadSuccess: function (data) {
                var rows = data.rows;
                if (rows.length > 0) {
                    $('#dg_typeGroup').datagrid("selectRow", 0);//默认选择第一行
                    initTypeGrid();
                }

            }
        });
    }

    //新增
    function addTypeGroup() {
        $('#dd').dialog({
            href: path + "baseTypeGroup/gotoAddPage.do",
            title: '数据字典新增',
            width: 450,
            height: 300,
            closed: false,
            cache: false,
            modal: true
        });
    }

    //修改
    function updateTypeGroup(id) {
        $('#dd').dialog({
            href: path + "baseTypeGroup/gotoUpdatePage/" + id,
            title: '数据字典修改',
            width: 450,
            height: 300,
            closed: false,
            cache: false,
            modal: true
        });
    }

    //删除数据
    function deleteTypeGroup() {
        var selectRow = $('#dg_typeGroup').datagrid('getSelected'); // 选择的行
        var id = selectRow.id;
        $.messager.confirm('确认对话框', '您确定删除该条记录，对应的字典分类也会删除吗？',
            function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: path + 'baseTypeGroup/deleteById.do?random=' + Math.random(),
                        data: {
                            id: id
                        },
                        success: function (data) {
                            if (data.success) {
                                $.messager.alert('提示', data.msg, 'info',
                                    function () {
                                        //******数据字典删除需要清除字典分类中的数据，设置为空***********************************
                                        $("#typeGroupId").val("");
                                        $("#typeGroupName").val("");
                                        //******重新加载数据******************************************************************
                                        $('#dg_typeGroup').datagrid('reload'); // 重载行。等同于'load'方法，但是它将保持在当前页。
                                        $('#dg_type').datagrid('reload'); // 重载行。等同于'load'方法，但是它将保持在当前页。
                                    });
                            } else {
                                $.messager.alert('提示', data.msg, 'error');
                            }
                        }
                    });
                }
            });
    }

    //搜索>>数据字典
    function doTypeSearch() {
        $('#dg_type').datagrid('load', {
            typeGroupId: $("#typeGroupId").val()
        });
    }

    //初始化grid
    function initTypeGrid() {
        var selectRow = $('#dg_typeGroup').datagrid('getSelected'); // 选择的行
        var groupId = selectRow.id;
        $("#typeGroupId").val(groupId);
        $("#typeGroupName").val(selectRow.typeGroupName);
        //********************************************************************
        $('#dg_type').datagrid({
            url: path + "baseType/notPageList.do",
            queryParams: {
                typeGroupId: groupId
            },
            method: 'POST', // 数据请求方式
            iconCls: 'icon-tip', // 表格头图标
            fit: true, // 表格大小自适应
            fitColumns: true, // 列大小自适应
            rownumbers: true, // 显示序号
            singleSelect: true, // 单选行
            pagination: false, // 分页组件
            pageSize: 10, // 表格默认行数
            striped: true, // 奇偶行使用不同背景色
            nowrap: false, // 是否在一行显示数据
            toolbar: $("#tb_type"), // 加载工具栏
            remoteSort: false, // 是否从服务器给数据排序
            multiSort: false, // 在第一列排序的基础上对第二列排序
            loadMsg: '数据加载中，请稍等...', // 加载数据时提示语
            columns: [[
                {field: 'id', hidden: true},
                {field: 'typeCode', title: '类型编码', width: 100, align: 'center'},
                {field: 'typeName', title: '类型名称', width: 100, align: 'center'},
                {
                    field: 'optype', title: '操作', width: 80, align: 'center',
                    resizable: true,
                    formatter: function (value, rowData, rowIndex) { // 个性化列
                        var a = "<a href=\"javascript:updateType('" + rowData.id + "')\" class=\"ace_button\" >编辑</a>&nbsp;";
                        a += "<a href=\"javascript:deleteType()\" class=\"ace_button\" >删除</a>&nbsp;";
                        return a;
                    },
                    sortable: true
                }
            ]]
        });
    }

    //新增
    function addType() {
        var typeGroupId = $("#typeGroupId").val();
        var typeGroupName = $("#typeGroupName").val();
        if (null != typeGroupId && typeGroupId != "") {
            $('#dd').dialog({
                href: path + "baseType/gotoAddPage.do",
                method: "post",
                queryParams: {
                    id: typeGroupId,
                    typeGroupName: typeGroupName
                },
                title: '字典分类新增',
                width: 450,
                height: 300,
                closed: false,
                cache: false,
                modal: true
            });
        } else {
            $.messager.alert('提示', "请先选择数据字典", 'info');
        }
    }

    //修改
    function updateType(id) {
        var typeGroupName = $("#typeGroupName").val();
        $('#dd').dialog({
            href: path + "baseType/gotoUpdatePage/" + id,
            method: "post",
            queryParams: {
                typeGroupName: typeGroupName
            },
            title: '字典分类修改',
            width: 450,
            height: 300,
            closed: false,
            cache: false,
            modal: true
        });
    }

    //删除数据
    function deleteType() {
        var selectRow = $('#dg_type').datagrid('getSelected'); // 选择的行
        var id = selectRow.id;
        $.messager.confirm('确认对话框', '您确定删除该条记录吗？',
            function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: path + 'baseType/deleteById.do?random=' + Math.random(),
                        data: {
                            id: id
                        },
                        success: function (data) {
                            if (data.success) {
                                $.messager.alert('提示', data.msg, 'info',
                                    function () {
                                        $('#dg_type').datagrid('reload'); // 重载行。等同于'load'方法，但是它将保持在当前页。
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
