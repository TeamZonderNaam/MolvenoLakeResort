var dp;
var dpNewStart;
var dpNewEnd;
var newGuestField;
var newRoomField;

var lastDateStart;
var lastDateEnd;

$(document).ready(function ()
{
    dpNewStart = $("#datepickerNewStart")
    dpNewStart.datepicker({
        dateFormat: 'dd-mm-yy',
        autoPick: false,
        minDate: new Date(),
        changeMonth: false,
        changeYear: false,
        autoHide: true,
        onSelect: function(dateText, inst) {
            lastDateStart = dateText.split('-');
            if(lastDateStart != null && lastDateEnd != null)
                getAvailableRooms(lastDateStart[0], lastDateStart[1], lastDateStart[2], lastDateEnd[0], lastDateEnd[1], lastDateEnd[2]);
        }
    });
    dpNewEnd = $("#datepickerNewEnd")
    dpNewEnd.datepicker({
        dateFormat: 'dd-mm-yy',
        autoPick: false,
        minDate: new Date(),
        changeMonth: false,
        changeYear: false,
        autoHide: true,
        onSelect: function(dateText, inst) {
            lastDateEnd = dateText.split('-');
            if(lastDateStart != null && lastDateEnd != null)
                getAvailableRooms(lastDateStart[0], lastDateStart[1], lastDateStart[2], lastDateEnd[0], lastDateEnd[1], lastDateEnd[2]);
        }
    });

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
    OpenNewFormButton();
    newRoomField = $("#newRoomField");

    setupGuestField();

    showAllReservations();
    showAllFromTodayReservations();
    showAllOpenReservations();

    submitReservation();

    ConfirmDeletion();
    //editReservation();
});


function setupCalendarTable()
{
    $("#calendarTable").DataTable({
        columns: [
            { "data": "guest.firstName" },
            { "data": "guest.lastName" },
            { "data": "room.number" },
            { "data": "start"},
            { "data": "end"},
            {
                data: function (data, type, row) {
                    return '<button id="deleteReservationBtn" class="btn btn-primary edit-btn" onclick="setIdForDelete(' + data.id + ')" type="button" data-toggle="modal" data-target="#deleteReservationModal"><i class="fas fa-trash-alt">delete</i></button>';
                    // return '<button id="editReservationBtn" class="btn btn-primary" type="button" data-toggle="modal" data-target="#editReservationModal" onclick="updateReservation(' + data.id + ')"><i class="fas fa-edit">Edit</i></button><button id="deleteReservationBtn" class="btn btn-primary edit-btn" onclick="removeReservation(' + data.id + ')" type="button" data-toggle="modal" data-target="#deleteReservationModal"><i class="fas fa-trash-alt">delete</i></button>';
                }
            }
        ]
    });
}


function getActiveDate(day, month, year)
{
    // Get the data from endpoint.
    $.ajax({
        url: "hotel/calendar/ymdb/"+day+'-'+month+'-'+year,
        type: "get",
        success: function (data) {
            // On successful get, reload the datatable with new data.
            $('#calendarTable').DataTable().clear();
            $('#calendarTable').DataTable().rows.add(data);
            $('#calendarTable').DataTable().columns.adjust().draw();
        }
    });
    //dpNewEnd.datepicker( "option", "minDate", new Date(lastDateStart[0]+'-'+lastDateStart[1]+'-'+lastDateStart[2]) );
}

function OpenNewFormButton()
{

//// dropdown in table
    $('#newReservationModal').on('click', function () {
        $('#newReservationModal').toggleClass("fade-in");
    });

}

function showAllReservations()
{
    $("#allReservationBtn").click(function()
    {
        // Get the data from endpoint.
        $.ajax({
            url: "hotel/calendar/",
            type: "get",
            success: function (calendar) {
                // On successful get, reload the datatable with new data.
                $('#calendarTable').DataTable().clear();
                $('#calendarTable').DataTable().rows.add(calendar);
                $('#calendarTable').DataTable().columns.adjust().draw();
            }
        });
    });
}

function showAllFromTodayReservations()
{
    $("#allFromTodayReservationBtn").click(function()
    {
        // Get the data from endpoint.
        $.ajax({
            url: "hotel/calendar/fromToday",
            type: "get",
            success: function (calendar) {
                // On successful get, reload the datatable with new data.
                $('#calendarTable').DataTable().clear();
                $('#calendarTable').DataTable().rows.add(calendar);
                $('#calendarTable').DataTable().columns.adjust().draw();
            }
        });
    });
}
function showAllOpenReservations()
{
    $("#allOpenReservationBtn").click(function()
    {
        // Get the data from endpoint.
        $.ajax({
            url: "hotel/calendar/open",
            type: "get",
            success: function (calendar) {
                // On successful get, reload the datatable with new data.
                $('#calendarTable').DataTable().clear();
                $('#calendarTable').DataTable().rows.add(calendar);
                $('#calendarTable').DataTable().columns.adjust().draw();
            }
        });
    });
}


function submitReservation()
{
    $("#submitReservationBtn").click(function()
    {
        // Get values from html.
        let guest = $("#newGuestField").val();
        let room = $("#newRoomField").val();
        console.log("working?");
        if(lastDateStart != null && lastDateEnd != null)
        {
            var postUrl = "hotel/calendar/"+lastDateStart[0]+"-"+lastDateStart[1]+"-"+lastDateStart[2]+
                "/"+lastDateEnd[0]+"-"+lastDateEnd[1]+"-"+lastDateEnd[2]+"/"+guest+"_"+room;
            console.log(postUrl);
            // Post JSON to endpoint.
            $.ajax({
                url: postUrl,
                type: "post",
                success: function (result) {
                    // On successful post, reload data to get the added one as well.
                    //getData();

                }
            });
            // TODO: DO ERROR HERE OR HALT EXCEPTION
        }
    });
}

//new function
function setIdForDelete(id) {
    $("#deleteReservationId").val(id);
}

function ConfirmDeletion()
{
    $("#deleteReservationForm").on('submit', function (e) {
        console.log("Data delete request")
        // delete the room of which the id was entered
        removeReservation();
        console.log("Data deleted")
    });
}

function removeReservation()
{
    // get values from html
    let id = $('#deleteReservationId').val();
    console.log(id)
    $.ajax({
        url: "/hotel/calendar/" + id ,
        type: "delete",
        data: id,
        contentType: "application/json",
        success: function () {
        }
    });
    $("#deleteReservationBtn1").click();

    //$('#deleteReservationModal').on('click', function () {
    //    $('#deleteReservationModal').toggleClass("fade-in");
    //});
}

// EDIT
function getSingleReservation(id) {

    // GET data from backend
    $.ajax({
        url: "/hotel/calendar/" + id ,
        type: "get",
        data: id,
        contentType: "application/json",
        success: function (calendar) {
            // prefill form with data from backend
            $("#datepickerNewStart1").val(calendar.start);
            $("#datepickerNewEnd1").val(calendar.end);
            $("#newGuestField1").val(calendar.guest.number);
            $("#newRoomField1").val(calendar.room.number);
        }
    });
}

function setIdForEdit(id) {
    getSingleReservation(id);
    $("#editReservationId").val(id);
}

// EDIT data
function editReservation() {
    // Get values from Html
    let id = $("#editReservationId").val();
    let start = $("#datepickerNewStart1").val();
    let end = $("#datepickerNewEnd1").val();
    let guest = $("#newGuestField1").val();
    let room = $("#newRoomField1").val();
    //let open = $("#newRoomOpen").val();


    console.log("this id "+id);
    // PUT request to endpoint
    $.ajax({
        url: "/hotel/calendar/upd/"+id+"/"+guest+"_"+room,
        type: "put",
        success: function () {
            // update table with new data
        }
    });
    $("#editReservationBtn").click();
}




function getAvailableRooms(dayStart, monthStart, yearStart, dayEnd, monthEnd, yearEnd)
{

    // Get the data from endpoint.
    $.ajax({
        url: "hotel/calendar/freer/"+dayStart+'-'+monthStart+'-'+yearStart+'/'+dayEnd+'-'+monthEnd+'-'+yearEnd,
        type: "get",
        success: function (data)
        {

            newRoomField.empty();
            newRoomField.append('<option selected="true" disabled>Choose room</option>');
            newRoomField.prop('selectedIndex', 0);

            console.log(data.length);

            $.each(data, function (key, entry)
            {
                newRoomField.append($('<option></option>').attr('value', entry.number).text(entry.number));
            })

        }
    });
}



// TODO: Does not work... why? following doc and stackoverflow advice
var names = ["test1","test2"];

function setupGuestField()
{
    newGuestField = $("#newGuestField");

    newGuestField.autocomplete({ source: names });


    $.ajax({
        url: "hotel/guest/",
        type: "get",
        success: function (data)
        {
            console.log("got here! l="+ data.length );
            //names.length = 0;
            $.each(data, function (key, entry)
            {
                names.push(entry.lastName + ", " + entry.firstName)
                console.log(names[key]);
            })

            newGuestField.autocomplete(
                "option", { source: names }
            );

        }
    });


}