var dp;

$(document).ready(function () 
{
    dp = $('#datepicker');
    dp.datepicker({
        dateFormat: 'dd-mm-yy',
        autoPick: false,
        startDate: new Date(),
        changeMonth: false,
        changeYear: false,
        autoHide: true,
        onSelect: function(dateText, inst) { 
            var date = dateText.split('-');
            getActiveDate( date[0], date[1], date[2] );
        }
    });
    setupCalendarTable();
});


function setupCalendarTable()
{
    $("#calendarTable").DataTable({
       columns: [
            { "data": "guest.firstName" },
            { "data": "guest.lastName" },
            { "data": "room.number" },
            { "data": "start"},
            { "data": "end"}
        ]
    });
}


// getdata
function getDataOnDate(day, month, year) 
{
    // Get the data from endpoint.
    $.ajax({
        url: "/api/calendar/"+day+'-'+month+'-'+year,
        type: "get",
        success: function (data) {
            // On successful get, reload the datatable with new data.
            $('#calendarTable').DataTable().clear();
            $('#calendarTable').DataTable().rows.add(data);
            $('#calendarTable').DataTable().columns.adjust().draw();
        }
    });
}

function getActiveDate(day, month, year) 
{
    // Get the data from endpoint.
    $.ajax({
        url: "/api/calendar/ymdb/"+day+'-'+month+'-'+year,
        type: "get",
        success: function (data) {
            // On successful get, reload the datatable with new data.
            $('#calendarTable').DataTable().clear();
            $('#calendarTable').DataTable().rows.add(data);
            $('#calendarTable').DataTable().columns.adjust().draw();
        }
    });
}