<%@ page language="java" contentType="text/html; charset=GBK"
		 pageEncoding="GBK"%>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">咚咚咚</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<%=request.getContextPath() %>">主页</a></li>
				<li><a href="#about">关于</a></li>
				<li><a href="#contact">联系人</a></li>
				<li class="dropdown">
					<a href="javascript:void(0)"   class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">基础功能 <span class="caret"></span></a>
					<ul class="dropdown-menu" id="dropdown_menu">
						<li><a href="<%=request.getContextPath() %>/setting.jsp">配置</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">类别配置</li>
						<li><a href="<%=request.getContextPath() %>/resourceType/list.action">资源类型管理</a></li>
						<li><a href="<%=request.getContextPath() %>/category/list.action">站点分类</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">站点</li>
						<li><a href="<%=request.getContextPath() %>/website/list.action">站点管理</a></li>
						<li><a href="<%=request.getContextPath() %>/website/toAdd.action">添加站点</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">资源</li>
						<li><a href="<%=request.getContextPath() %>/resource/list.action">资源管理</a></li>
						<li><a href="<%=request.getContextPath() %>/resource/toAdd.action">添加资源</a></li>
						<li><a href="<%=request.getContextPath() %>/searchResources.jsp">资源自动搜索</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="<%=request.getContextPath() %>/tg/toTg.action">推广</a></li>
					</ul>
				</li>

				<li class="dropdown">
					<a href="javascript:void(0)"   class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">搜索 <span class="caret"></span></a>
					<ul class="dropdown-menu" >
						<li class="dropdown-header">邮箱搜索</li>
						<li><a href="<%=request.getContextPath() %>/fetch/toSetting.action">搜索信息</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">信息管理</li>
						<li><a href="<%=request.getContextPath() %>/fetch/toFetch.action">搜索信息-简单法</a></li>
						<li><a href="<%=request.getContextPath() %>/fetch/toFetchFromSite.action">提取页面信息</a></li>
						<li><a href="<%=request.getContextPath() %>/fetch/toDepthFetchFromSite.action">深入提取页面信息</a></li>
					</ul>
				</li>

				<li class="dropdown">
					<a href="javascript:void(0)"   class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">服务 <span class="caret"></span></a>
					<ul class="dropdown-menu" >

						<li><a href="<%=request.getContextPath() %>/email/toSendMail.action">发送邮件</a></li>

						<li role="separator" class="divider"></li>
						<li class="dropdown-header">配置管理管理</li>
						<li><a href="<%=request.getContextPath() %>/email/setiingSendMailInfo.action">配置发送邮箱信息</a></li>
						<li><a href="<%=request.getContextPath() %>/email/settingReceMailInfo	.action">配置收件邮箱信息</a></li>

					</ul>
				</li>

			</ul>
		</div><!--/.nav-collapse -->
	</div>
</nav>