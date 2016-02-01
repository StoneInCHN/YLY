<div class="panel-group">

	[#list buildings as building]	
	<div class="panel panel-info">
			 <div class="panel-heading">
			      <h4 class="panel-title">
			        	<div class="pull-left"><i class="fa fa-building-o"></i>${building.buildingName}</div>
						<div class="widget-icons pull-right">
							 <a class="wminimize"><i class="fa fa-chevron-up"></i></a>
						 </div>  
			      </h4>
			  </div>
			  <div class="panel-body widget-content">
			      	<ul class="room-item">
			      		 [#list building.rooms as room]	
				   			<li>
				   				<h4>${roomNumber}房间</h4>
				   				[#list room.beds as bed]
					   				<div [#if bed.elderlyInfo]class="bed-item-somebody" data-elderlyId="${bed.elderlyInfo.id}" [#else] class="bed-item-nobody"  data-bedDescription="${building.buildingName} ${room.roomName} (${bed.bedNumber})"[/#if]  data-bed-id="${bed.id}" data-bed-number="${bed.bedNumber}">
					   					<a href="javascript:;"><img [#if bed.elderlyInfo]src="${base}/resources/images/somebody-48.png"[#else] src="${base}/resources/images/nobody-48.png"[/#if] style="width:48px;height:48px" >
				   					<h7>${bed.bedNumber}</h7>
				   					<p>[#if bed.elderlyInfo]${bed.elderlyInfo.name}[#else]未占用[/#if]</p></a>
					   				</div>
								[/#list]
				   			</li>
				   			[/#list]
				   			<li style="clear:both;height:0"></li>
				   	 </ul>
			   </div>
	</div>
	[/#list]
</div>
