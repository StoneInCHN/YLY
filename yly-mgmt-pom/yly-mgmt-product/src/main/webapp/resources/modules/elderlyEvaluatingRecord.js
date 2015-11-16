var elderlyEvaluating_manager_tool = {
			add:function(){
				$('#addEvaluating').dialog({    
				    title: "添加入院评估",    
				    width: 1250,    
				    height: 850,
				    iconCls:'icon-mini-add',
				    modal:true,
				    href:'../elderlyEvaluatingRecord/addEvaluating.jhtml',
				    onOpen:function(){
				    	//$('#addElderlyEvent_form').show();
				    },
				    onClose:function(){
				    	 $('#addElderlyEvent_form').form('reset');
				    }
				});
			},
			remove:function(){
					listRemove('elderlyEvaluating-table-list','../elderlyEvaluatingRecord/delete.jhtml');
			},
			edit:function(){

				var _edit_row = $('#elderlyEvaluating-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');  
					return false;
				}
				var _dialog = $('#editEvaluating').dialog({    
				    title: "编辑入院评估记录",     
				    width: 1250,    
				    height: 850,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../elderlyEvaluatingRecord/edit.jhtml?id='+_edit_row.id
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
			$('#listEvaluating').dialog({    
			    title: message("yly.common.detail"),    
			    width: 800,    
			    height: 800, 
			    cache: false,
			    modal: true,
			    href:'../elderlyEvaluatingRecord/view.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#listEvaluating').dialog("close");
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
	if(str != null && str!=""&& len > 0){
		if(str.length > len){
			return '<span title="'+str+'">'+str.substring(0,len)+"..."+'<span>'
		}else{
			return str;
		}	
	}
	return "";
}

