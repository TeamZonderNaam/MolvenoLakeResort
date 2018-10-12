

// CRUD functions for the supplier class 
$(document).ready(function () {

    // table with values that correspond with backend fieldnames 
    let table = $("#supplierTable").DataTable({
        columns: [
            {
                "className": 'details-control',
                "orderable": false,
                "data": function (data, type, row) {
                    return '<button class="btn btn-info"><i class="fas fa-info"></i></button>';
                },
                "defaultContent": ''
            },
            { "data": "contact" },
            { "data": "companyName" },
            { "data": "email" },
            { "data": "phoneNumber" },
            {
                data: function (data, type, row) {
                    return '<button id="editSupplierBtn" class="btn btn-primary" type="button" data-toggle="modal" data-target="#editSupplierModal" onclick="getSingleSupplier(' + data.id + ')"><i class="fas fa-edit"></i></button><button id="deleteSupplierBtn" class="btn btn-primary edit-btn" onclick="setIdForDelete(\'' + data.id + '\',\'' + data.companyName + '\')" type="button" data-toggle="modal" data-target="#deleteSupplierModal"><i class="fas fa-trash-alt"></i></button>';
                }
            }
        ]
    });

    // dropdown in table
    $('#supplierTable tbody').on('click', 'td.details-control', function () {
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

    // post new supplier
    $("#newSupplierForm").on('submit', function (e) {

        // Post the data from the form
        postData();

        // Reset form
        $("#companyName").val("");
        $("#contact").val(""); 
        $("#email").val("");
        $("#phone").val("");
        $("#streetName").val("");
        $("#houseNumber").val("");
        $("#additionToHouseNumber").val("");
        $("#postcode").val("");
        $("#city").val(""); 
        $("#country").val("");
        $("#stateOrProvince").val("");
        $("#vatNumber").val("");
        $("#kvkNumber").val("");
    });

    $("#deleteSupplierForm").on('submit', function (e) {

        // delete the supplier of which the id was entered
        deleteData();
    });

    // get single supplier
    $("#editSupplierForm").on('submit', function (e) {

        // prefills form with supplier info
        getSingleSupplier();
    })

    // edit single supplier
    $("#editSupplierForm1").on('submit', function (e) {

        // edit single supplier
        editSupplier();

    })

    // Load data first.
    getData();
});


// getdata
function getData() {
    // Get the data from endpoint.
    $.ajax({
        url: "/api/supplier/",
        type: "get",
        success: function (suppliers) {
            // On successful get, reload the datatable with new data.
            $('#supplierTable').DataTable().clear();
            $('#supplierTable').DataTable().rows.add(suppliers);
            $('#supplierTable').DataTable().columns.adjust().draw();
        }
    });
}

//postdata
function postData() {

    // Get values from html.
    let companyName = $("#companyName").val();
    let contact = $("#contact").val();
    let email = $("#email").val();
    let phoneNumber = $("#phone").val();
    let streetName = $("#streetName").val();
    let houseNumber = $("#houseNumber").val();
    let additionToHouseNumber = $("#additionToHouseNumber").val();
    let postcode = $("#postcode").val();
    let city = $("#city").val(); 
    let country = $("#country").val();
    let stateOrProvince = $("#stateOrProvince").val();
    let vatNumber = $("#vatNumber").val();
    let kvkNumber = $("#kvkNumber").val();

    // Create JS object with data.
    let newSupplier = {
        companyName: companyName,
        contact: contact, 
        email: email,
        phoneNumber: phoneNumber,
        streetName: streetName,
        houseNumber: houseNumber,
        additionToHouseNumber: additionToHouseNumber,
        postcode: postcode,
        city: city, 
        country: country,
        stateOrProvince: stateOrProvince,
        vatNumber: vatNumber,
        kvkNumber: kvkNumber,
    }

    // Convert JS object to JSON.
    let validJsonSupplier = JSON.stringify(newSupplier);

    // Post JSON to endpoint.
    $.ajax({
        url: "/api/supplier/",
        type: "post",
        data: validJsonSupplier,
        contentType: "application/json",
        success: function (result) {
            // On successful post, reload data to get the added one as well.
            getData();

        }
    });
    // close modal
    $('#newSupplierBtn').click();
}



// Set id for deletion in the modal
function setIdForDelete(id, name) {
    $("#deleteSupplierId").val(id);
    $("#supplierToBeDeleted").html(name); 
    // $("#supplierToBeDeleted").html(); 
}

// DELETE data
function deleteData() {

    // get values from html
    let companyId = $('#deleteSupplierId').val();

    // DELETE request to backend 
    $.ajax({
        url: "/api/supplier/" + companyId,
        type: "delete",
        data: companyId,
        contentType: "application/json",
        success: function () {
            getData();
        }
    });
    // close modal
    $("#deleteSupplierBtn").click(); 
}




// GET single supplier
function getSingleSupplier(id) {

    // GET data from backend
    $.ajax({
        url: "/api/supplier/" + id,
        type: "get",
        data: id,
        contentType: "application/json",
        success: function (supplier) {
            // prefill form with data from backend
            $("#id1").val(id);
            $("#companyName1").val(supplier.companyName);
            $("#contact1").val(supplier.contact);
            $("#email1").val(supplier.email);
            $("#phone1").val(supplier.phoneNumber);
            $("#streetName1").val(supplier.streetName);
            $("#houseNumber1").val(supplier.houseNumber);
            $("#additionToHouseNumber1").val(supplier.additionToHouseNumber);
            $("#postcode1").val(supplier.postcode);
            $("#city1").val(supplier.city); 
            $("#country1").val(supplier.country);
            $("#stateOrProvince1").val(supplier.stateOrProvince);
            $("#vatNumber1").val(supplier.vatNumber);
            $("#kvkNumber1").val(supplier.kvkNumber);
        }
    });
}


// EDIT data
function editSupplier() {
    // Get values from Html
    let id = $("#id1").val();
    let companyName = $("#companyName1").val();
    let contact = $("#contact1").val();
    let email = $("#email1").val();
    let phoneNumber = $("#phone1").val();
    let streetName = $("#streetName1").val();
    let houseNumber = $("#houseNumber1").val();
    let additionToHouseNumber = $("#additionToHouseNumber1").val();
    let postcode = $("#postcode1").val();
    let city = $("#city1").val(); 
    let country = $("#country1").val();
    let stateOrProvince = $("#stateOrProvince1").val();
    let vatNumber = $("#vatNumber1").val();
    let kvkNumber = $("#kvkNumber1").val();

    // create JS object
    let editedSupplier = {
        id: id,
        companyName: companyName,
        contact: contact, 
        email: email,
        phoneNumber: phoneNumber,
        streetName: streetName,
        houseNumber: houseNumber,
        additionToHouseNumber: additionToHouseNumber,
        postcode: postcode,
        city: city, 
        country: country,
        stateOrProvince: stateOrProvince,
        vatNumber: vatNumber,
        kvkNumber: kvkNumber,
    }

    // create JSON object
    let validJsonEditedSupplier = JSON.stringify(editedSupplier);

    // PUT request to endpoint
    $.ajax({
        url: "/api/supplier/" + id,
        type: "put",
        data: validJsonEditedSupplier,
        contentType: "application/json",
        success: function () {
            // update table with new data
            getData();
        }
    })
    // close modal
    $("#editSupplierBtn").click();
}


// Mini table for dropdown
function format ( d ) {
    // `d` is the original data object for the row
    return '<table class="table table-striped table-bordered nowrap" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td><strong>Address:</strong></td>'+
            '<td>'+d.streetName + " " +  d.houseNumber + " " + d.additionToHouseNumber + " " + '</td>'+
        '</tr>'+
        '<tr>'+
            '<td><strong>Address 2:</strong></td>'+
            '<td>'+d.postcode+ ' ' + d.city+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td><strong>Country:</strong></td>'+
            '<td>'+d.country+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td><strong>State or province:</strong></td>'+
            '<td>'+d.stateOrProvince+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td><strong>VAT number:</strong></td>'+
            '<td>'+d.vatNumber+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td><strong>Chamber of commer no:</strong></td>'+
            '<td>'+d.kvkNumber+'</td>'+
        '</tr>'+
    '</table>';
}