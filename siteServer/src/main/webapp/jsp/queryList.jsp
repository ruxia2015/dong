<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源管理</title>

<jsp:include page="common/common.jsp" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/userjs/${userJs}" charset="utf-8"></script>
</head>
<body>
<jsp:include page="top.jsp"/>

<div id="content-wrapper" class="clearfix row">
	<div class=" twelve columns">
		<div id="content" style="align-content: center">

			<table id="dg" class="easyui-datagrid" title="管理" style="width:900px;height:500px;">
			</table>


		</div>
	</div>
</div>







</body>
</html>