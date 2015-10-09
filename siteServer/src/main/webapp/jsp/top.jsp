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
			<a class="navbar-brand" href="#">������</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<%=request.getContextPath() %>">��ҳ</a></li>
				<li><a href="#about">����</a></li>
				<li><a href="#contact">��ϵ��</a></li>
				<li class="dropdown">
					<a href="javascript:void(0)"   class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">�������� <span class="caret"></span></a>
					<ul class="dropdown-menu" id="dropdown_menu">
						<li><a href="<%=request.getContextPath() %>/setting.jsp">����</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">�������</li>
						<li><a href="<%=request.getContextPath() %>/resourceType/list.action">��Դ���͹���</a></li>
						<li><a href="<%=request.getContextPath() %>/category/list.action">վ�����</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">վ��</li>
						<li><a href="<%=request.getContextPath() %>/website/list.action">վ�����</a></li>
						<li><a href="<%=request.getContextPath() %>/website/toAdd.action">���վ��</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">��Դ</li>
						<li><a href="<%=request.getContextPath() %>/resource/list.action">��Դ����</a></li>
						<li><a href="<%=request.getContextPath() %>/resource/toAdd.action">�����Դ</a></li>
						<li><a href="<%=request.getContextPath() %>/searchResources.jsp">��Դ�Զ�����</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="<%=request.getContextPath() %>/tg/toTg.action">�ƹ�</a></li>
					</ul>
				</li>

				<li class="dropdown">
					<a href="javascript:void(0)"   class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">���� <span class="caret"></span></a>
					<ul class="dropdown-menu" >
						<li class="dropdown-header">��������</li>
						<li><a href="<%=request.getContextPath() %>/fetch/toSetting.action">������Ϣ</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">��Ϣ����</li>
						<li><a href="<%=request.getContextPath() %>/fetch/toFetch.action">������Ϣ-�򵥷�</a></li>
						<li><a href="<%=request.getContextPath() %>/fetch/toFetchFromSite.action">��ȡҳ����Ϣ</a></li>
						<li><a href="<%=request.getContextPath() %>/fetch/toDepthFetchFromSite.action">������ȡҳ����Ϣ</a></li>
					</ul>
				</li>

				<li class="dropdown">
					<a href="javascript:void(0)"   class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">���� <span class="caret"></span></a>
					<ul class="dropdown-menu" >

						<li><a href="<%=request.getContextPath() %>/email/toSendMail.action">�����ʼ�</a></li>

						<li role="separator" class="divider"></li>
						<li class="dropdown-header">���ù������</li>
						<li><a href="<%=request.getContextPath() %>/email/setiingSendMailInfo.action">���÷���������Ϣ</a></li>
						<li><a href="<%=request.getContextPath() %>/email/settingReceMailInfo	.action">�����ռ�������Ϣ</a></li>

					</ul>
				</li>

			</ul>
		</div><!--/.nav-collapse -->
	</div>
</nav>