<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>推广资源管理</title>

<jsp:include page="common/common.jsp" />

<script type="text/javascript">
	
	function init(){
		var tagName  = "${tagName}";
		var tgId = "${tgId}";		
		var url ="<%=request.getContextPath()%>/ResourceTgServlet?tgId="+tgId;  
		var name = '推广_'+tagName;
		$('#tgTabs').tabs('add',{
				title: name,
				content: '<iframe width="97%" height="95%" src="'+url+'"/>',
				closable: true
			});
	}

</script>

</head>
<body onload="init();">
<jsp:include page="top.jsp"/>
<div style="float:left;width:260px;">
	<jsp:include page="left.jsp"/>
</div>


<div class="easyui-tabs" style="height:800px;width:1080px;overflow:hidden;float:left" id="tgTabs"></div>

</body>
</html>