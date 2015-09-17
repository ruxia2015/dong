$(function(){
		datagrid=$("#dg").datagrid({
			url:ajaxServlet+"/list.action",//加载的URL
		    isField:"id",
			pagination:true,//显示分页
			pageSize:10,//分页大小
			pageList:[10,20,50],//每页的个数
			fit:true,//自动补全
			fitColumns:true,
			singleSelect:true,
			striped:true,
			idField:"id",
			rownumbers:true,
			showFooter:true,
			
			toolbar:[
            //工具条
	        {text:"增加",iconCls:"icon-add",handler:function(){//回调函数
	        	if(rowEditor==undefined)
				{
	        		datagrid.datagrid('insertRow',{//如果处于未被点击状态，在第一行开启编辑
		        		index: 0,	
		        		row: {
		        		}
		        	});
	        		rowEditor=0;
		        	datagrid.datagrid('beginEdit',rowEditor);//没有这行，即使开启了也不编辑
		        	
				}
	        

	        }},
	        {text:"删除",iconCls:"icon-remove",handler:function(){
	        	var rows=datagrid.datagrid('getSelections');
	  
	        	if(rows.length<=0)
	        	{
	        		$.messager.alert('警告','您没有选择','error');
	        	}
	        	else if(rows.length>1)
	        	{
	        		$.messager.alert('警告','不支持批量删除','error');
	        	}
	        	else
	        	{
	        		$.messager.confirm('确定','您确定要删除吗',function(t){
	        			if(t)
	        			{
	        				
	        				$.ajax({
	        					url : ajaxServlet+'/delete.action',
	        					data : {"id":rows[0].id},
	        					dataType : 'json',
	        					success : function(r) {
	        						if (r.successCode==0) {
	        							datagrid.datagrid('acceptChanges');
	        							$.messager.show({
	        								msg : r.msg,
	        								title : '成功'
	        							});
	        							rowEditor = undefined;
	        							datagrid.datagrid('reload');
	        						} else {
	        							/*datagrid.datagrid('rejectChanges');*/
	        							datagrid.datagrid('beginEdit', rowEditor);
	        							$.messager.alert('错误', r.msg, 'error');
	        						}
	        						datagrid.datagrid('unselectAll');
	        					}
	        				});
	        			
	        			}
	        		});
	        	}
	        	
	        	
	        }},
	        {text:"修改",iconCls:"icon-edit",handler:function(){
	        	var rows=datagrid.datagrid('getSelections');
	        	if(rows.length==1)
	        	{
	        		if(rowEditor==undefined)
					{
	        			var index=datagrid.datagrid('getRowIndex',rows[0]);
	        			 rowEditor=index;
	        			datagrid.datagrid('unselectAll');
			        	datagrid.datagrid('beginEdit',index);
			        	
					}
	        	}
	        }},
	        {text:"保存",iconCls:"icon-save",handler:function(){
	        	
	        	datagrid.datagrid('endEdit',rowEditor);
	        	rowEditor=undefined;
	        }},
	        {text:"取消编辑",iconCls:"icon-redo",handler:function(){
	        	rowEditor=undefined;
	        	datagrid.datagrid('rejectChanges');
	        }}
	        ],
	        onAfterEdit:function(rowIndex, rowData, changes){
				var inserted = datagrid.datagrid('getChanges', 'inserted');
				var updated = datagrid.datagrid('getChanges', 'updated');
				if (inserted.length < 1 && updated.length < 1) {
					rowEditor = undefined;
					datagrid.datagrid('unselectAll');
					return;
				}

				var url = '';
				var messageStart = "";
				if (inserted.length > 0) {
					url = ajaxServlet+'/add.action';
					messageStart = "添加";
				}
				if (updated.length > 0) {
					url = ajaxServlet+'/update.action';
					messageStart = "修改";
				}
				alert(JSON.stringify(rowData));

				$.ajax({
					url : url,
					data :{"bean": JSON.stringify(rowData),"id":rowData.id},
					dataType : 'json',
					type: 'POST',
					success : function(r) {
						if (r.successCode==0) {
							datagrid.datagrid('acceptChanges');
							$.messager.show({
								msg : messageStart+ "成功！",
								title : '成功'
							});
							rowEditor = undefined;
							datagrid.datagrid('reload');
						} else {
							datagrid.datagrid('rejectChanges');
							//datagrid.datagrid('beginEdit', rowEditor);
							$.messager.alert('错误', r.msg, 'error');
						}
						datagrid.datagrid('unselectAll');
					}
				});
				
			},
			onDblClickCell:function(rowIndex, field, value){
				if (rowEditor != rowIndex){
					if (endEditing()){
						datagrid.datagrid('selectRow', rowIndex)
								.datagrid('beginEdit', rowIndex);
						rowEditor=rowIndex;
					} else {
						$('#dg').datagrid('selectRow', rowIndex);
					}
				}			
			}
		});
		function pagerFilter(data){
			if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
				data = {
					total: data.length,
					rows: data
				};
			}
			var dg = $(this);
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			pager.pagination({
		        beforePageText: '第',//页数文本框前显示的汉字 
		        afterPageText: '页    共 {pages} 页', 
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
				onSelectPage:function(pageNum, pageSize){
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh',{
						pageNumber:pageNum,
						pageSize:pageSize
					});
					dg.datagrid('loadData',data);
				}
			});
			if (!data.originalRows){
				data.originalRows = (data.rows);
			}
			var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = (data.originalRows.slice(start, end));
			return data;
		}
		
		function endEditing(){
			if (rowEditor == undefined){return true;}
			if ($('#dg').datagrid('validateRow', rowEditor)){
				$('#dg').datagrid('endEdit', rowEditor);
				rowEditor = undefined;
				return true;
			} else {
				return false;
			}
		}
		
		$(function(){
			$('#dg').datagrid({loadFilter:pagerFilter})	;
		});
		
});