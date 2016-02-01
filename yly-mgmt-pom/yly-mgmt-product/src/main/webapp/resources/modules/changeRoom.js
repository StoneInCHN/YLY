$(function(){
	
	$("#checkedInElderly-table-list").datagrid({
		title:message("yly.elderlyinfo.record"),
		fitColumns:true,
		toolbar:"#changeRoom_manager_tool",
		url:'../changeRoom/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		toolbar:[{
			text:message("yly.residential.changeRoom.action"),
			iconCls: 'icon-reload',
			handler:function(){
				var _selected_row = $('#checkedInElderly-table-list').datagrid('getSelected');
				if( _selected_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.residential.changeRoom.elderly"),'warning');
					return false;
				}else{
					console.log(_selected_row)
					selectRoom({
						type:2,
						elderlyId:_selected_row.id,
						elderlyName:_selected_row.name,
						bedNumber:_selected_row.bed.bedNumber,
						originalBedId:_selected_row.bed.id,
						tableId:"#checkedInElderly-table-list"
					});
				}
			}
		}],
		onDblClickRow : function (rowIndex, rowData){
			$('#checkedInElderlyDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 1200,    
			    height: 700, 
			    cache: false,   
			    href:'../changeRoom/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#checkedInElderlyDetail').dialog("close");
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
		    	  	if(value == "IN_NURSING_HOME"){
		    	  		return  message("yly.elderly.status.in_nursing_home");
		    	  	}
		    	  	if(value == "OUT_NURSING_HOME"){
		    	  		return  message("yly.elderly.status.out_nursing_home");
		    	  	}
		    	  	if(value == "IN_PROGRESS_CHECKIN"){
		    	  		return  message("yly.elderly.status.in_progress_checkin");
		    	  	}
		    	  	if(value == "IN_PROGRESS_CHECKOUT"){
		    	  		return  message("yly.elderly.status.in_progress_checkout");
		    	  	}
		    	  	if(value == "DEAD"){
		    	  		return  message("yly.elderly.status.dead");
		    	  	}
		    	  	if(value == "IN_PROGRESS_EVALUATING"){
		    	  		return  "通过入院评估";
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
	
	$("#checkedInElderly_search_btn").click(function(){
	 var _queryParams = $("#checkedInElderly_search_form").serializeJSON();
	  $('#checkedInElderly-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#checkedInElderly-table-list").datagrid('reload');
	})
})

