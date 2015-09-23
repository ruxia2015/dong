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


    <script type="application/javascript">

        $(document).ready(function () {
            $("searcherType").val("${searcherType}");
            $("dataType").val("${dataType}");
        })


    </script>

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
                                搜索信息 / ${genPath}/
                            </li>
                        </ul>


                        <form class="form-horizontal" action="<%=request.getContextPath()%>/fetch/startFetch.action" method="post">
                            <c:if test="${not empty genPath}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">输出文件路径</label>

                                    <div class=" col-sm-10">
                                            ${genPath}
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="searcherType" class="col-sm-2 control-label">搜索引擎类型</label>

                                <div class="col-sm-10">
                                    <select id="searcherType" name="searcherType">
                                        <option value="">请选择</option>
                                        <option value="yahoojp" selected>雅虎日本</option>
                                        <option value="google">谷歌</option>
                                        <option value="baidu">百度</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="keywords" class="col-sm-2 control-label">搜索的关键词</label>

                                <div class=" col-sm-10">
                                    <input name="keywords" id="keywords" value="${keywords}"/>
                                    <span id="helpBlock" class="help-block"> 多个之间用逗号[,]分隔</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="dataType" class="col-sm-2 control-label">搜索的数据类型</label>

                                <div class="col-sm-10">
                                    <select id="dataType" name="dataType">
                                        <option value="">请选择</option>
                                        <option value="email">邮箱</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="regex" class="col-sm-2 control-label">搜索数据的正则表达式</label>

                                <div class=" col-sm-10">
                                    <input name="regex" id="regex" value="${regex}"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">开始执行</button>
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

