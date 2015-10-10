<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>资源管理</title>

    <jsp:include page="../common/common.jsp"/>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#type").val("${type}");
        });

        function selectAll() {
            var t = document.getElementById("resultHtml");
            t.select();
        }


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
                                提取邮箱信息(只支持网页)
                            </li>
                        </ul>


                        <form class="form-horizontal"
                              action="<%=request.getContextPath()%>/fetch/depthFetchFromSite.action"
                              method="post">


                            <div class="form-group">
                                <label for="onlySelfDomain" class="col-sm-2 control-label">本网站</label>
                                <div class="col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="onlySelfDomain" id="onlySelfDomain">
                                </label>
                            </div>
                                </div>
                                </div>


                            <div class="form-group">
                                <label for="type" class="col-sm-2 control-label">搜索引擎类型</label>


                                <div class="col-sm-10">
                                    <select id="type" name="type">
                                        <option value="">请选择</option>
                                        <option value="http" selected>网址(不包含页面中的pdf等)</option>
                                        <option value="str">内容</option>
                                        <option value="file">本地文件地址</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="sitePages" class="col-sm-2 control-label">页面</label>

                                <div class=" col-sm-10">
                                    <textarea name="sitePages" id="sitePages" cols="60" rows="5">${sitePages}</textarea>
                                    <span id="helpBlock" class="help-block"> 一行一个(网址/本地地址)</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">开始执行</button>
                                </div>
                            </div>
                        </form>


                        <div class="panel panel-success">
                            <div class="panel-heading">提示信息</div>
                            <div class="panel-body">
                                文件路径：${filepath} <br/>
                                处理状况：${thread} <br/>

                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


</body>


</body>
</html>

