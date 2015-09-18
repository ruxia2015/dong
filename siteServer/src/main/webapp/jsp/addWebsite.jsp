<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>资源管理</title>

    <jsp:include page="common/common.jsp"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/userjs/resource.js"></script>
</head>
<body>
<jsp:include page="top.jsp"/>

<div id="content-wrapper" class="clearfix row">
    <div class=" twelve columns">
        <div id="content" style="align-content: center">

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span12">
                        <ul class="breadcrumb">
                            <li>
                                <a href="#">站点管理</a>
                            </li>
                            <li class="active">
                                添加站点
                            </li>
                        </ul>

                        <form class="form-horizontal" method="post"
                              action="<%=request.getContextPath()%>/website/add.action">
                            <div class="form-group">
                                <label for="default_category" class="col-sm-2 control-label">网站分类</label>

                                <div class=" col-sm-10">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="categoryId" id="default_category"> 不分类
                                        </label>
                                        <c:forEach items="${categoryList}" var="item">
                                            <label>
                                                <input type="radio" name="categoryId"
                                                       value="${item.id }">${item.name}
                                            </label>
                                        </c:forEach>

                                    </div>

                                </div>
                            </div>

                            <div class="form-group">
                                <label for="sites" class="col-sm-2 control-label">推广网站</label>

                                <div class="col-sm-10">
                        <textarea class="form-control" rows="10" name="sites" id="sites" cols="10"
                                  style="width: 60%"></textarea>
                                    <span id="helpBlock" class="help-block"> 一行代表一个网站.</span>

                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default" onclick="submitForm()"> 提交</button>
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