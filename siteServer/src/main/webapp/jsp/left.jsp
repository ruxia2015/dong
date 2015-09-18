<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>My JSP 'ztreedemo.jsp' starting page</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">


	  <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery.easyui.min.js"></script>
	

	
   <style type="text/css">
   	a{text-decoration:none;
   	  	color: blue;   	  
   	};
   	a:hover {
   		color:green;
   	}
	
	a:LINK {
		color:orange;
	}
   
   </style>
   
   
   <script type="text/javascript">
   
   $.ajax({
		url : "<%=request.getContextPath()%>/TgAjaxServlet",
		data :null,
		dataType : 'json',
		success : function(r) {
				var obj = 	r;
				if(obj!=null){
					for(var i=0;i<obj.length;i++){
						var con = ' <p> <a href="javascript:void(0)" onclick="openTgResource(\''+obj[i].domain+'\',\''+obj[i].id+'\')">'+obj[i].domain+'</a></p>';
						$("#tgs").append(con);
					}
				}


		}
	});
   
   
   	function openTgResource(tagName,tgId){   
   		if(document.getElementById("tgTabs")==null){
   			window.open("<%=request.getContextPath()%>/queryTgResourceMain.jsp?tgId="+tgId+"&tagName="+tagName ); 
   		}else{
   			var url ="<%=request.getContextPath()%>/ResourceTgServlet?tgId="+tgId;  
   			var name = '推广_'+tagName;
   			if($('#tgTabs').tabs("exists",name)){
   				//$("#tagTabs").tabs("select",name);
   			}else{
   		    	$('#tgTabs').tabs('add',{
   					title: name,
   					content: '<iframe width="97%" height="95%" src="'+url+'"/>',
   					closable: true
   				});
   			}
	    	
   		}
   	}
   	
   	

   
   </script>

</html>  