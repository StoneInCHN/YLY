
<form id="viewEvent_form" method="post">   
	    <table class="table table-striped">
	    	<tr>
	    		<th style="width:110px;">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			  ${elderlyEventRecord.elderlyInfo.name}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th style="width:110px;">${message("yly.common.elderly.operator")}:</th>
	    		<td>
	    			 ${elderlyEventRecord.operator}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th style="width:110px;">${message("yly.elderlyInfo.event.eventDate")}:</th>
	    		<td>
	    			 ${(elderlyEventRecord.eventDate?string("yyyy-MM-dd"))!}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th style="width:110px;">${message("yly.elderlyInfo.event.content")}:</th>
	    		<td>
	    			 ${elderlyEventRecord.eventContent}
	    		</td>
	    	</tr>
	    </table>
</form>
	

