// CRUD functions for the room class

$(document).ready(function () {



    // table with values that correspond with backend fieldnames

    let table = $("#roomTable").DataTable({

        columns: [

            {

                "className": 'details-control',

                "orderable": false,

                "data": function (data, type, row) {

                    return '<button class="btn btn-info"><i class="fas fa-info"></i></button>';

                },

                "defaultContent": ''

            },

            { "data": "number" },

            { "data": "typeRoom" },

            { "data": "available" },

            { "data": "price" },

            {

                data: function (data, type, row) {

                    return '<button id="editRoomBtn" class="btn btn-primary" type="button" data-toggle="modal" data-target="#editRoomModal" onclick="getSingleRoom(' + data.id + ')"><i class="fas fa-edit"></i></button><button id="deleteRoomBtn" class="btn btn-primary edit-btn" onclick="setIdForDelete(\'' + data.id + '\',\'' + data.number + '\')" type="button" data-toggle="modal" data-target="#deleteRoomModal"><i class="fas fa-trash-alt"></i></button>';

                }

            }

        ]

    });



    // dropdown in table

    $('#roomTable tbody').on('click', 'td.details-control', function () {

        var tr = $(this).closest('tr');

        var row = table.row(tr);



        if (row.child.isShown()) {

            // This row is already open - close it

            row.child.hide();

            tr.removeClass('shown');

        }

        else {

            // Open this row

            row.child(format(row.data())).show();

            tr.addClass('shown');

        }

    });



    // post new room

    $("#newRoomForm").on('submit', function (e) {

        console.log("Posting data")

        // Post the data from the form

        postData();

        console.log("Data posted")

        // Reset form

        $("#roomNumber").val("");

        $("#roomType").val("");

        $("#available").val("");

        $("#price").val("");

    });



    $("#deleteRoomForm").on('submit', function (e) {

        console.log("Data delete request")

        // delete the room of which the id was entered

        deleteData();

        console.log("Data deleted")

    });



    // get single Room

    $("#editRoomForm").on('submit', function (e) {



        // prefills form with Room info

        getSingleRoom();

    });



    // edit single room

    $("#editRoomForm1").on('submit', function (e) {

        console.log("Data edit request")

        // edit single room

        editRoom();

        console.log("Data edited")

    });



    // Load data first.

    generateDummyData();

    getData();



});





// getdata

function getData() {

    // Get the data from endpoint.

    $.ajax({

        url: "/api/room/",

        type: "get",

        success: function (room) {

            // On successful get, reload the datatable with new data.

            $('#roomTable').DataTable().clear();

            $('#roomTable').DataTable().rows.add(room);

            $('#roomTable').DataTable().columns.adjust().draw();

        }

    });

}



//postdata

function postData() {



    // Get values from html.

    let number = $("#roomNumber").val();

    let typeRoom = $("#roomType").val();

    let available = $("#available").val();

    let price = $("#price").val();





    // Create JS object with data.

    let newRoom = {

        number: number,

        typeRoom: typeRoom,

        available: available,

        price: price

    }



    // Convert JS object to JSON.

    let validJsonRoom = JSON.stringify(newRoom);



    // Post JSON to endpoint.

    $.ajax({

        url: "/api/room/",

        type: "post",

        data: validJsonRoom,

        contentType: "application/json",

        success: function (result) {

            // On successful post, reload data to get the added one as well.

            console.log("Posting" + validJsonRoom)

            getData();

        }

    });

    $("#newRoomBtn").click();

}



// Set id for deletion in the modal

function setIdForDelete(id, number) {

    $("#deleteRoomId").val(id);

    $("#roomToBeDeleted").html(number);

}



// delete data

function deleteData() {



    // get values from html

    let id = $('#deleteRoomId').val();

    console.log(id)

    // DELETE request to backend

    $.ajax({

        url: "/api/room/" + id,

        type: "delete",

        data: id,

        contentType: "application/json",

        success: function () {

            getData();

        }

    });

    $("#deleteRoomBtn").click();

}





function getSingleRoom(id) {



    // GET data from backend

    $.ajax({

        url: "/api/room/" + id,

        type: "get",

        data: id,

        contentType: "application/json",

        success: function (room) {

            // prefill form with data from backend

            $("#id1").val(id);

            $("#roomNumber1").val(room.number);

            $("#roomType1").val(room.typeRoom);

            $("#available1").val(room.available);

            $("#price1").val(room.price);

        }

    });

}





// EDIT data

function editRoom() {

    // Get values from Html

    let id = $("#id1").val();

    let number = $("#roomNumber1").val();

    let typeRoom = $("#roomType1").val();

    let available = $("#available1").val();

    let price = $("#price1").val();





    // create JS object

    let editedRoom = {

        id: id,

        number: number,

        typeRoom: typeRoom,

        available: available,

        price: price

    }





    // create JSON object

    let validJsonEditedRoom = JSON.stringify(editedRoom);



    console.log(validJsonEditedRoom)

    // PUT request to endpoint

    $.ajax({

        url: "/api/room/" + id,

        type: "put",

        data: validJsonEditedRoom,

        contentType: "application/json",

        success: function () {

            // update table with new data

            getData();

        }

    });

    $("#editRoomBtn").click();

}



// Generate dummy data in database

function generateDummyData() {

    let dummyRoom = {

        "number": 199,

        "typeRoom": "STANDARD",

        "available": true,

        "price": 123,

    }



    // Convert JS object to JSON.

    let validJsonRoom = JSON.stringify(dummyRoom);



    // Post JSON to endpoint.

    $.ajax({

        url: "/api/room/",

        type: "post",

        data: validJsonRoom,

        contentType: "application/json",

        success: function (result) {

            // On successful post, reload data to get the added one as well.

            getData();

        }

    });

}