
	$(function(){
		
		var nurseId =$("#personalizedRecord-table-list").attr("data-nurseId") ;
		
		$("#personalizedRecord-table-list").datagrid({
			title:message("yly.personalizedRecord.list"),
			fitColumns:true,
			fit:true,
			url:'../personalizedRecord/list.jhtml?nurseId='+nurseId,  
			pagination:true,
			loadMsg:message("yly.common.loading"),
			striped:true,
			toolbar: [{
				text:message("yly.common.add"),
				iconCls: 'icon-add',
				handler: function(){
					
					$("#addPersonalizedRecord").show();
					
					$("#addPersonalizedRecord").dialog({    
					    title:message("yly.personalizedRecord.add"),   
					    width: 350,    
					    height: 450,    
					    closed: false,    
					    cache: false,
					    iconCls:'icon-mini-add',
					    modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#addPersonalizedRecord_form').form('validate');
								if(validate){
									$.ajax({
										url:"../personalizedRecord/add.jhtml",
										type:"post",
										data:$("#addPersonalizedRecord_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addPersonalizedRecord_form').form('reset');
											$('#addPersonalizedRecord').dialog("close");
											$("#personalizedRecord-table-list").datagrid('reload');
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
								$('#addPersonalizedRecord_form').form('reset');
								$('#addPersonalizedRecord').dialog("close");
								$("#addPersonalizedRecord").hide();
							}
						}],
						onClose:function(){
						    	$('#addPersonalizedRecord_form').form('reset');
						    	$("#addPersonalizedRecord").hide();
						}
					});
				}
			},'-',{
				text:message("yly.common.remove"),
				iconCls: 'icon-remove',
				handler: function(){
					var _rows = $("#personalizedRecord-table-list").treegrid('getSelections');
					if (_rows.length == 0) {
						$.messager.alert(message("yly.common.prompt"), message("yly.common.select.deleteRow"),'warning');
					} else {
						var _ids = [];
						for (var i = 0; i < _rows.length; i++) {
							_ids.push(_rows[i].id);
						}
						if (_ids.length > 0) {
							$.messager.confirm(message("yly.common.confirm"), message("yly.common.delete.confirm"), function(r) {
								if (r) {
									$.ajax({
										url : "../personalizedRecord/delete.jhtml",
										type : "post",
										traditional : true,
										data : {
											"ids" : _ids
										},
										beforeSend : function() {
											$.messager.progress({
												text : message("yly.common.progress")
											});
										},
										success : function(result, response, status) {
											$.messager.progress('close');
											var resultMsg = result.content;
											if (response == "success") {
												showSuccessMsg(resultMsg);
												$("#personalizedRecord-table-list").datagrid('reload');
											} else {
												alertErrorMsg();
											}
										}
									});
								}
							})
						}

					}
				}
			}],
			columns:[
			   [
			      {field:'ck',checkbox:true},
			      {title:message("yly.personalizedRecord.elderlyInfo"),field:"elderlyInfo",width:100,sortable:true,formatter: function(value,row,index){
						if(value && value.name){
							return value.name;
						}
						return ""
			      	}},
			       {title:message("yly.personalizedRecord.personalizedNurse"),field:"personalizedNurse",width:100,sortable:true,formatter: function(value,row,index){
			    	   if(value && value.personalized && value.personalized.chargeItem){
			    		   return value.personalized.chargeItem;
			    	   }
			    	   return "";
			      	}},
			      {title:message("yly.personalizedRecord.serviceTime"),field:"serviceTime",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.personalizedRecord.operator"),field:"operator",width:100,sortable:true},
			      {title:message("yly.personalizedRecord.nurseContent"),field:"nurseContent",width:100,sortable:true},
			      {title:message("yly.personalizedRecord.remark"),field:"remark",width:100,sortable:true},
			      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}}
			   ]
			]
	
		});
})
	