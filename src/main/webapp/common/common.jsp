<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" info="123123" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/uns.tld" prefix="uns" %>

<!-- 基础js,css -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>

<!-- easyui插件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/js/easyui-lang-zh_CN.js"></script>

<!-- 自定义样式 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String webPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<script type="text/javascript">
    /* *****公共路径参数***** */
    var path = '<%out.print(basePath);%>';
    var basePath = '<%out.print(basePath);%>';
    var webPath = '<%out.print(webPath);%>';

    //日期格式化转换
    function formatDate(dateStr) {
        var rtnStr = "";
        if (dateStr != null && dateStr != "") {
            var date = new Date(dateStr);
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            if (month < 10) {
                month = "0" + month;
            }
            var day = date.getDate();
            if (day < 10) {
                day = "0" + day;
            }
            var hour = date.getHours();
            if (hour < 10) {
                hour = "0" + hour;
            }
            var minute = date.getMinutes();
            if (minute < 10) {
                minute = "0" + minute;
            }
            var second = date.getSeconds();
            if (second < 10) {
                second = "0" + second;
            }
            rtnStr = year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
        }
        return rtnStr;
    }

    //日期格式化转换
    function formatDate1(dateStr) {
        if (dateStr != null && dateStr != "") {
            return formatDate(dateStr).substr(0, 10);//只取时间
        }
        return "";
    }
</script>






