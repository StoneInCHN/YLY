<link href='${base}/resources/css/fullcalendar.css' rel='stylesheet' />
<link href='${base}/resources/css/nurseDutyType.css' rel='stylesheet' />
<script src='${base}/resources/js/moment.min.js'></script>
<script src='${base}/resources/js/jquery-ui.custom.min.js'></script>
<script src='${base}/resources/js/fullcalendar.min.js'></script>
<script src='${base}/resources/js/fullcalendar/lang/zh-cn.js'></script>
<div id='nursePlanWrap'>
    <div id='nursePlan-events'>
        <h4>排班类型</h4>
        [#list nurseDutyTypes as nurseDutyType] 
				<div class='fc-event'>${nurseDutyType.dutyName}</div>
		[/#list]
        <p>
            <input type='checkbox' id='drop-remove' />
            <label for='drop-remove'>拖拽后移除</label>
        </p>
    </div>

    <div id='nursePlanCalendar'></div>

    <div style='clear:both'></div>

</div>
  <script>

        $(document).ready(function() {


            /* initialize the external events
             -----------------------------------------------------------------*/

            $('#nursePlan-events .fc-event').each(function() {

                // store data so the calendar knows to render an event upon drop
                $(this).data('event', {
                    title: $.trim($(this).text()), // use the element's text as the event title
                    stick: true // maintain when user navigates (see docs on the renderEvent method)
                });
                $(this).dragable({
                    zIndex: 999,
                    revert: true,      // will cause the event to go back to its
                    revertDuration: 0  //  original position after the drag
                });

            });


            /* initialize the calendar
             -----------------------------------------------------------------*/

            $('#nursePlanCalendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                editable: true,
                droppable: true, // this allows things to be dropped onto the calendar
                drop: function() {
                    // is the "remove after drop" checkbox checked?
                    if ($('#drop-remove').is(':checked')) {
                        // if so, remove the element from the "Draggable Events" list
                        $(this).remove();
                    }
                }
            });


        });

    </script>