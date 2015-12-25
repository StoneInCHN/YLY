var leave_manager_tool = {
		add:function(){		
			$('#addLeave').dialog({    
			    title: "办理出院",    
			    width: 1200,    
			    height: 700,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addAdmission_form').form('validate');
						if(validate){
							
						};
					}
				},{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addLeave').dialog("close");
					}
			    }],
			    onOpen:function(){
			    	$('#addLeave_form').show();},
			    onClose:function(){
			    	
			    }
			});  
			 $('#addLeave_form').show();
		},
		edit:function(){},
		remove:function(){
			listRemove('leave-table-list','../leave/delete.jhtml');
		}
}


$(function(){
	
	$("#leave-table-list").datagrid({
		title:"出院老人",
		fitColumns:true,
		toolbar:"#leave_manager_tool",
		url:'../leave/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#admissionDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 1200,    
			    height: 700, 
			    cache: false,   
			    href:'../admission/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#admissionDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.elderlyinfo.identifier"),field:"identifier",width:12,align:'center',sortable:true},
		      {title:message("yly.common.elderlyname"),field:"name",width:20,align:'center',sortable:true},
		      {title:message("yly.common.age"),field:"age",width:10,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.elderlyPhoneNumber"),field:"elderlyPhoneNumber",width:20,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.beHospitalizedDate"),field:"beHospitalizedDate",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd");
		      	}
		      }},
		      {title:message("yly.elderlyInfo.outHospitalizedDate"),field:"outHospitalizedDate",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd");
		      	}
		      }},
		      {title:message("yly.common.gender"),field:"gender",width:10,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "MALE"){
		    	  		return  message("yly.common.male");
		    	  	}
		    	  	if(value == "FEMALE"){
		    	  		return  message("yly.common.female");
		    	  	}
		      	}},
		      {title:message("yly.common.idcard"),field:"idcard",width:40,align:'center',sortable:true},
		      {title:message("yly.common.bedRoom"),field:"bedLocation",width:30,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.elderlyConsigner.consignerPhoneNumber"),field:"consignerPhoneNumber",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  if(row !=  null){
		    		  return row.elderlyConsigner.consignerPhoneNumber;  
		    	  }
		      	}},
		      {title:message("yly.elderly.status"),field:"elderlyStatus",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "OUT_NURSING_HOME"){
		    	  		return "<font color=#FF0000>"+message("yly.elderly.status.out_nursing_home")+"</font>";
		    	  	}
		    	  	if(value == "IN_PROGRESS_CHECKOUT"){
		    	  		return  message("yly.elderly.status.in_progress_checkout");
		    	  	}
		      	}}		      
		   ]
		],
		onLoadSuccess:function(data){
	           $(".tips-span").tooltip({
	        	   position: 'top',
	               onShow: function(){
	                   $(this).tooltip('tip').css({ 
	                       width:'300'
	                   });
	               }
	           });
	        }

	});
	
	$("#leave_search_btn").click(function(){
	 var _queryParams = $("#leave_search_form").serializeJSON();
	  $('#leave-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#leave-table-list").datagrid('reload');
	})
})

