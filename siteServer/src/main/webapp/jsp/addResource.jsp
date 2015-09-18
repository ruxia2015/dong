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
            <div style="text-align: center;color:#00B83F">
                <h2>资源管理 <small>添加资源</small></h2>
            </div>

            <form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/resource/add.action">

                <div class="form-group">
                    <label for="resourceType" class="col-sm-2 control-label">资源类型</label>

                    <div class="col-sm-10">
                        <select id="resourceType" name="resourceType">
                            <option value=""> ----- 请选择 -----</option>
                            <c:forEach items="${resourceTypes }" var="item">
                                <option value="${item.id }">${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <label for="default_category" class="col-sm-2 control-label">所属站点分类</label>

                    <div class=" col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="categoryIds" id="default_category"> 不区分
                            </label>
                            <c:forEach items="${categoryList}" var="item">
                                <label>
                                    <input type="checkbox" name="categoryIds"
                                           value="${item.id }">${item.name}
                                </label>
                            </c:forEach>

                        </div>

                    </div>
                </div>

                <div class="form-group">
                    <label for="override" class="col-sm-2 control-label">是否覆盖</label>

                    <div class=" col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="override" id="override"> 是
                            </label>


                        </div>

                    </div>
                </div>

                <div class="form-group">
                    <label for="resources" class="col-sm-2 control-label">资源</label>

                    <div class="col-sm-10">
                        <textarea class="form-control" rows="10" name="resources" id="resources" cols="10"
                                  style="width: 60%"></textarea>
                        <span id="helpBlock" class="help-block"> 一行代表一个资源.</span>

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

</body>


</body>
</html>