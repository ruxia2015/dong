<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>资源管理</title>
    <jsp:include page="../common/common.jsp"/>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/appreciate/3.css">
    <script type="application/javascript" src="<%=request.getContextPath() %>/js/appreciate/1.js"></script>


</head>
<body onload="pageLoad();">
<jsp:include page="../top.jsp"/>


<canvas id="cnvMain" width="400px" height="400px"></canvas>


</body></html>