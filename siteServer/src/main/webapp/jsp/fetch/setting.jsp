<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>资源管理</title>

    <jsp:include page="../common/common.jsp"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/userjs/resource.js"></script>
</head>
<body>
<jsp:include page="../top.jsp"/>
<div id="content-wrapper" class="clearfix row">
    <div class=" twelve columns">
        <div id="content" style="align-content: center">

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span12">
                        <ul class="breadcrumb">
                            <li>
                                <a href="#">搜索</a>
                            </li>
                            <li class="active">
                                搜索信息
                            </li>
                        </ul>

                        <form class="form-horizontal" action="<%=request.getContextPath()%>/fetch/startFetch.action">
                            <div class="form-group">
                                <label for="keyword" class="col-sm-2 control-label">搜索的关键词</label>
                                <div class=" col-sm-10">
                                    <input name="keyword" id="keyword"/>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="searchType" class="col-sm-2 control-label">搜索的数据类型</label>
                                <div class="col-sm-10">
                                    <select id="searchType">
                                        <option type="">请选择</option>
                                        <option type="email">邮箱</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="regex" class="col-sm-2 control-label">搜索数据的正则表达式</label>

                                <div class=" col-sm-10">
                                    <input name="regex" id="regex"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default" >开始执行</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>


</body>
</html>

