$(function(){
	
	$("#checkedInElderly-table-list").datagrid({
		title:message("yly.elderlyinfo.record"),
		fitColumns:true,
		url:'../elderlyInfo/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#checkedInElderlyDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 1200,    
			    height: 700, 
			    cache: false,   
			    href:'../elderlyInfo/details.jhtml?id='+rowData.id,
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
		    	  	if(value == "IN_PROGRESS_CHECKIN_BILL"){
		    	  		return  "入院办理(已出账单未交费)";
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
	checkedInElderly_manager_tool = {
			exportData:function(){
				$.ajax({
					url:"../elderlyInfo/count.jhtml",
					type:"post",
					data:$("#checkedInElderly_search_form").serialize(),
					success:function(result,response,status){
						if(result.count != null){
							var text = "";
							if(result.count == 0){
								text = "当前条件无可导出的数据。";
								$.messager.alert(message("yly.common.notice"), text,'warning');
							}else if(result.count <= 500){
								text = "确定导出 "+result.count+" 条记录？";
								$.messager.confirm(message("yly.common.confirm"), text, function(r) {
									if(r){
										$("#checkedInElderly_search_form").attr("action","../elderlyInfo/exportData.jhtml");
										$("#checkedInElderly_search_form").attr("target","_blank");
										$("#checkedInElderly_search_form").submit();
									}
								});
							}else{
								text = "导出数据超过500条数据，建议搜索查询条件以缩小查询范围，再导出。";
								$.messager.confirm(message("yly.common.notice"), text, function(r) {
									if (!r) {
										text = "导出共有"+ result.count +"条数据，导出超过500条数据可能需要您耐心等待，仍需操作请确定继续。";
										$.messager.confirm(message("yly.common.confirm"), text, function(yes) {
											if(yes){
												$("#checkedInElderly_search_form").attr("action","../elderlyInfo/exportData.jhtml");
												$("#checkedInElderly_search_form").attr("target","_blank");
												$("#checkedInElderly_search_form").submit();
											}
										});
									}
								})
							}
						}
						$("#checkedInElderly-table-list").datagrid('reload');
					},
					error:function (XMLHttpRequest, textStatus, errorThrown) {
						alert("error");
						$.messager.progress('close');
						alertErrorMsg();
					}
				});
			}	
	}	
	
	$("#checkedInElderly_search_btn").click(function(){
	 var _queryParams = $("#checkedInElderly_search_form").serializeJSON();
	  $('#checkedInElderly-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#checkedInElderly-table-list").datagrid('reload');
		//隐藏域用于标记上次使用过的查询条件 
		$("#identifierHidden").val($("#identifier").val());
		$("#nameHidden").val($("#name").val());
		$("#elderlyStatusHidden").val($("#elderlyStatus").combobox('getValue'));
		$("#beHospitalizedBeginDateHiden").val($("#beHospitalizedBeginDate").val());
		$("#beHospitalizedEndDateHidden").val($("#beHospitalizedEndDate").val());	
	})
})

