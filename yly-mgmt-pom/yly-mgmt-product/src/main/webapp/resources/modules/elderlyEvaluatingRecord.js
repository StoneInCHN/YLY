var elderlyEvaluating_manager_tool = {
			add:function(){
				$('#addElderlyEvent').dialog({    
				    title: message("yly.elderly.event.add"),    
				    width: 500,    
				    height: 350,
				    iconCls:'icon-mini-add',
				    modal:true,
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
				    	handler:function(){
							var validate = $('#addElderlyEvent_form').form('validate');
							if(validate){								
								$.ajax({
									url:"../elderlyEventRecord/add.jhtml",
									type:"post",
									data:$("#addElderlyEvent_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addElderlyEvent').dialog("close");
										$("#event-table-list").datagrid('reload');
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addElderlyEvent').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addElderlyEvent_form').show();
				    },
				    onClose:function(){
				    	 $('#addElderlyEvent_form').form('reset');
				    }
				});
			}
}
$(function(){
	$("#elderlyEvaluating-table-list").datagrid({
		title:"入院评估记录列表",
		fitColumns:true,
		toolbar:"#elderlyEvaluating_manager_tool",
		url:'../elderlyEvaluatingRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#editEvaluating').dialog({    
			    title: message("yly.common.detail"),    
			    width: 800,    
			    height: 800, 
			    cache: false,
			    modal: true,
			    href:'../elderlyEvaluatingRecord/detail.jhtml?id='+rowData.id+'&handle=view',
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editEvaluating').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:"被评估老人",field:"elderlyInfoName",width:30,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.name;
		      }},
		      {title:"评估人操作人",field:"operator",width:30,align:'center',formatter:function(value,row,index){
		    	 return formatLongString(value,8);
		      }},
		      {title:"评估原因",field:"evaluatingReason",width:30,align:'center',formatter:function(value,row,index){
					if(value=="CHECKIN_EVALUATING"){
						return "接受服务前初评";
					}
					if(value=="ROUTINE_EVALUATING"){
						return "接受服务后的常规评估";
					}
					if(value=="IMMEDIATE_EVALUATING"){
						return "状况发生变化后的即时评估";
					}
					if(value=="QUESTION_EVALUATING"){
						return "因评估结果有疑问进行的复评";
					}
			  }},
		      {title:"评估结果",field:"evaluatingResult",width:30,align:'center',formatter:function(value,row,index){
			    	 return formatLongString(value,8);
		      }},
		      {title:"评估表编号",field:"evaluatingFormName",width:30,align:'center',formatter:function(value,row,index){
		    	  var fullFormName = row.evaluatingForm.formName;//老年人能力评估(MZ/T 039-2013)
		    	  var codeOfFormName = fullFormName.substring(fullFormName.indexOf("(")+1,fullFormName.length-1);//MZ/T 039-2013
		    	  return codeOfFormName;
		      }},
		      {title:"评估基准时间",field:"createDate",width:30,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}},
		   ]
		]
	});
	$("#elderlyEvaluating_search_btn").click(function(){
		  var _queryParams = $("#elderlyEvaluating_search_form").serializeJSON();
		  $('#elderlyEvaluating-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#elderlyEvaluating-table-list").datagrid('reload');
		})
})
function formatLongString(str,len){
	if(str.length > len){
		return '<span title="'+str+'">'+str.substring(0,len)+"..."+'<span>'
	}else{
		return str;
	}	
}