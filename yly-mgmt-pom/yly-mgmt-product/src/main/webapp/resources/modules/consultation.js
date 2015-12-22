$(function(){
	
	$("#consultation-table-list").datagrid({
		title:message("yly.consultation.record"),
		fitColumns:true,
		toolbar:"#consultation_manager_tool",
		url:'../consultation/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#consultationDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 700,    
			    height: 500, 
			    cache: false,   
			    href:'../consultation/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#consultationDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.consultation.vistor"),field:"visitor",width:40,align:'center',sortable:true},
		      {title:message("yly.common.phonenumber"),field:"phoneNumber",width:50,align:'center',sortable:true},
		      {title:message("yly.common.elderlyname"),field:"elderlyName",width:40,align:'center',sortable:true},
		      {title:message("yly.common.gender"),field:"gender",width:30,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "MALE"){
		    	  		return  message("yly.common.male");
		    	  	}
		    	  	if(value == "FEMALE"){
		    	  		return  message("yly.common.female");
		    	  	}
		      	}},
		      {title:message("yly.consultation.checkinintention"),field:"checkinIntention",width:80,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "CONFIRMED"){
		    	  		return  message("yly.consultation.checkinIntention.confirmed");
		    	  	}
		    	  	if(value == "WILL_TO_CHECKIN_STRONGLY"){
		    	  		return  message("yly.consultation.checkinIntention.will_to_checkin_strongly");
		    	  	}
		    		if(value == "WILL_TO_CHECKIN_NOT_STRONGLY"){
		    	  		return  message("yly.consultation.checkinIntention.will_to_checkin_not_strongly");
		    	  	}
		    		if(value == "WILL_NOT_CHECKIN"){
		    	  		return  message("yly.consultation.checkinIntention.will_not_checkin");
		    	  	}
		      	}},
		      {title:message("yly.common.relation"),field:"relation",width:75,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "SELF"){
		    	  		return  message("yly.common.relation.self");
		    	  	}
		    	  	if(value == "CHILDREN"){
		    	  		return  message("yly.common.relation.children");
		    	  	}
		    		if(value == "MARRIAGE_RELATIONSHIP"){
		    	  		return  message("yly.common.relation.marriage_relationship");
		    	  	}
		    		if(value == "GRANDPARENTS_AND_GRANDCHILDREN"){
		    	  		return  message("yly.common.relation.grandparents_and_grandchildren");
		    	  	}
		    		if(value == "BROTHERS_OR_SISTERS"){
		    	  		return  message("yly.common.relation.brothers_or_sisters");
		    	  	}
		    		if(value == "DAUGHTERINLAW_OR_SONINLAW"){
		    	  		return  message("yly.common.relation.daughterinlaw_or_soninlaw");
		    	  	}
		    		if(value == "FRIEND"){
		    	  		return  message("yly.common.relation.friend");
		    	  	}
		    		if(value == "OTHER"){
		    	  		return  message("yly.common.other");
		    	  	}
		      	}},
		      {title:message("yly.common.infoChannel"),field:"infoChannel",width:30,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "NETWORK"){
		    	  		return  message("yly.common.relation.infochannel.network");
		    	  	}
		    	  	if(value == "COMMUNITY"){
		    	  		return  message("yly.common.relation.infochannel.community");
		    	  	}
		    	 	if(value == "OHTER_INTRODUCT"){
		    	  		return  message("yly.common.relation.infochannel.ohter_introduct");
		    	  	}
		    	 	if(value == "ADVERTISEMENT"){
		    	  		return  message("yly.common.relation.infochannel.advertisement");
		    	  	}
		    	 	if(value == "OTHER"){
		    	  		return  message("yly.common.other");
		    	  	}
		      	}},
		      {title:message("yly.consultation.returnVisit"),field:"returnVisit",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == true){
		    	  		return  message("yly.common.yes");
		    	  	}else{
		    	  		return  message("yly.common.no");
		    	  	}
		      	}},
		      {title:message("yly.consultation.returnVisitDate"),field:"returnVisitDate",width:60,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd");
		    	  	}
				}}
		   ]
		]

	});
	
	consultation_manager_tool = {
			add:function(){		
				$('#addConsultation').dialog({    
				    title: message("yly.consultation.add"),    
				    width: 700,    
				    height: 500,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addConsultation_form').form('validate');
							if(validate){
								$.ajax({
									url:"../consultation/add.jhtml",
									type:"post",
									data:$("#addConsultation_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addConsultation_form').form('reset');
										$('#addConsultation').dialog("close");
										$("#consultation-table-list").datagrid('reload');
										
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
							 $('#addConsultation').dialog("close");
						}
				    }]
				});  
				 $('#addConsultation_form').show();
			},
			edit:function(){
				var _edit_row = $('#consultation-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editConsultation').dialog({    
				    title: message("yly.common.edit"),     
				    width: 700,    
				    height: 500,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../consultation/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editConsultation_form').form('validate');
							if(validate){
								$.ajax({
									url:"../consultation/update.jhtml",
									type:"post",
									data:$("#editConsultation_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editConsultation').dialog("close");
										$("#consultation-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editConsultation').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('consultation-table-list','../consultation/delete.jhtml');
			}
	}
	$("#consultation_search_btn").click(function(){
//	  var _queryParams = {
//			  vistor:$("#search-vistor").val(),
//			  elderlyName:$("#search-elderlyName").val(),
//			  checkinIntention:$("#search-checkinIntention").val(),
//			  infoChannel:$("#search-infoChannel").val(),
//			  returnVisitDateBeginDate:$("#returnVisitDateBeginDate").val(),
//			  returnVisitDateEndDate:$("#returnVisitDateEndDate").val()
//	  }
		var _queryParams = $("#consultation_search_form").serializeJSON();
	  $('#consultation-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#consultation-table-list").datagrid('reload');
	//隐藏域用于标记上次使用过的查询条件
	  $("#visitorHidden").val($("#search-vistor").val());
	  $("#elderlyNameHidden").val($("#search-elderlyName").val());
	  $("#checkinIntentionHidden").val($("#search-checkinIntention").val());
	  $("#infoChannelHidden").val($("#search-infoChannel").val());
	  $("#returnVisitDateBeginDateHidden").val($("#returnVisitDateBeginDate").val());
	  $("#returnVisitDateEndDateHidden").val($("#returnVisitDateEndDate").val());
	     
	})
	
	 
	 
})