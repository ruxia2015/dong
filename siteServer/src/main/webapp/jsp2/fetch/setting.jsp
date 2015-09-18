<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源管理</title>

<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="<%=request.getContextPath()%>/userjs/resource.js"></script>
</head>
<body>
<jsp:include page="../top.jsp"/>
<div style="float:left;width:260px;">
	<jsp:include page="../left.jsp"/>
</div>
<body>


	<div class="easyui-panel" title="基本操作" style="width:1000px">

		<form action="<%=request.getContextPath()%>/fetch/startFetch.action">
		<table >

			    <tr height="42px">
					<td>搜索的关键词：</td>
					<td>
						<input type="text" value=""  name="keywords">
					</td>
			   </tr>

			<tr height="42px">
				<td>搜索的数据类型：</td>
				<td>
					<select id="searchType">
						<option type="">请选择</option>
						<option type="email">邮箱</option>
					</select>
				</td>
			</tr>

			<tr height="42px">
				<td>搜索数据的正则表达式：</td>
				<td>
					<input type="text" id="regex" value="" name="regex">
				</td>
			</tr>

	    		<tr height="42px">
	    			<td>搜索的关键词：</td>
	    			<td>
	    				<input type="submit" value="开始执行" >
	    			</td>
	    		</tr>
	    		
	    		</table>

		</form>
	
	</div>

</body>


</body>
</html>