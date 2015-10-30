$(function(){
	
	
	/**
	 *初始化右侧的选项卡
	 */
	$("#manager-tabs").tabs({
		fit:true,
		border:false
	});
	
	
	$("#nav-switcher").mouseover(function(){
		$(".nav-wrap").addClass("nav-silde");
		$("#nav-switcherset").show();
	})
	$("#nav-wrap ul").mouseleave(function(){
		$("#nav-wrap").removeClass("nav-silde");
		$("#nav-switcherset").hide();
	})
	
	$("#dropdownMenu1").dropdown();
	 
	$("#nav-wrap > ul >li >a").click(function(){
		var $this = $(this);
		$(".left-content > ul").hide();
		$($this.attr("href")).show();
	})
	
	/**
	 *绑定左侧导航条的点击事件 
	 */
	$(".left-content a").click(function(){
		var _this = $(this);
		var _url = _this.attr("data-url");
		if(_this.text()){
			if($('#manager-tabs').tabs("exists",_this.text())){
				$('#manager-tabs').tabs("select",_this.text())
			}else{
				$('#manager-tabs').tabs('add',{    
				    title:_this.text(),    
				    href:_url,    
				    closable:true      
				}); 
			}
		}
	})
	
	$("#selectRoom").click(function(){
		$("#searchElderlyInfo").dialog({    
		    title: '选床',    
		    width: 1000,    
		    height: 500,    
		    closed: false,    
		    cache: true,    
		    href: '../room/changeRoom.jhtml',    
		    modal: true,
		    onClose:function(){
		    	$('#searchElderlyInfo').empty;  
		    }
		}); 
		
	});
	
	
})