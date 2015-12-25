$(function(){
	
	$("#deposit_table_list").datagrid({
		title:message("yly.deposit.charge.record"),
		fitColumns:true,
		//toolbar:"#consultation_manager_tool",
		url:'../deposit/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#depositDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../deposit/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#depositDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      //老人姓名
		      {title:message("yly.common.elderlyname"),field:"elderlyInfoName",width:30,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.name;
		      }},
		      //老人编号
		      {title:message("yly.elderlyinfo.identifier"),field:"elderlyInfoIdentifier",width:40,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.identifier;
		      }},
		      //房间
		      {title:message("yly.common.bedRoom"),field:"elderlyInfoBedRoom",width:50,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.bedLocation;
		      }},
		      //护理等级
		      {title:message("yly.common.nurseLevel"),field:"elderlyInfoNurseLevel",width:30,align:'center',formatter:function(value,row,index){
		    	  if(row.elderlyInfo.nursingLevel != null){
		    		  return row.elderlyInfo.nursingLevel.configValue;
		    	  }else{
		    		  return null;
		    	  }
		      }},
		      //押金
		      {title:message("yly.charge.record.depositAmount"),field:"depositAmount",width:25,align:'center',sortable:true},
		      //收款人
		      {title:message("yly.charge.record.operator"),field:"operator",width:25,align:'center',sortable:true},
		      //缴费时间
		      {title:message("yly.charge.record.payTime"),field:"payTime",width:35,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd hh:mm");
		    	  	}
				}},
		      //状态
		      {title:message("yly.charge.record.status"),field:"chargeStatus",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "PAID"){
		    	  		return "<font color=#7CCD7C>"+message("yly.charge.status.paid")+"</font>";
		    	  	}
		    	  	if(value == "REFUNDED"){
		    	  		return "<font color=#FF0000>"+message("yly.charge.status.refunded")+"</font>";
		    	  	}
		      	}}
		   ]
		]

	});
	
	$("#deposit_search_btn").click(function(){
	  var _queryParams = $("#deposit_search_form").serializeJSON();
	  $('#deposit_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#deposit_table_list").datagrid('reload');
	})
	
	 
	 
})