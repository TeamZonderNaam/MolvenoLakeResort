// The main file is meant to construct the dataTable and provide a global variable for other javascript files to use
var DATA_TABLE;
var BASE_URL = "/api/guest/";
var DATA_PAIRS = {
    fullName: ".firstName",
    firstName: ".firstName",
    lastName: ".lastName",
    streetName: ".streetName",
    houseNumber: ".houseNumber",
    additionHouseNumber: ".additionHouseNumber",
    postcode: ".postcode",
    country: ".country",
    strateOrProvince: ".stateOrProvince",
    //creditCardDetails: ".creditCardDetails",
};

$(function () {
    DATA_TABLE = $("#guestTable").DataTable({
        columns: [
            {
                "className": 'details-control',
                "orderable": false,
                "data": function (data, type, row) {
                    return '<i class="fas fa-info"></i>';
                },
            },
            {
                "data": function (data, type, row) {
                    return '<button onclick="createOrderTable(' + data.id + ')" id="ordersBtn" class="btn btn-primary" type="text" data-toggle="modal" data-target="#ordersModal"><iclass="fas fa-plus"></i>Bills</button>';
                },
            },
            { data: "firstName" },
            { data: "lastName" },
            {
                data: null,
                className: "center",
                defaultContent: '<a href="edit"><i class="fas fa-edit"></i></a> / <a href="delete"><i class="far fa-trash-alt"></i></a>'
            },
        ]
    });

    $('#guestTable tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = DATA_TABLE.row(tr);

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
});

// Mini table for dropdown
function format(d) {
    // `d` is the original data object for the row
    return '<table class="table table-striped table-bordered nowrap" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
        '<tr>' +
        '<td><strong>Address: </strong></td>' +
        '<td>' + " " + d.streetName + " " + d.houseNumber + " " + d.additionHouseNumber + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td><strong>Postcode:</strong></td>' +
        '<td>' + " " + d.postcode + " " + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td><strong>State or province:</strong></td>' +
        '<td>' + " " + d.stateOrProvince + " " + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td><strong>Country:</strong></td>' +
        '<td>' + " " + d.country + " " + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td><strong>Creditcard details:</strong></td>' +
        '<td>' + " " + "**** **** **** ****" + " " + '</td>' +
        '</tr>' +
        '</table>';
}

function createOrderTable(id) {
    $.ajax({
        url: "http://localhost:8080/api/orderbill/" + id,
        type: "get",
        data: id,
        success: function (orders) {
            $("#orderTable tbody").html("")

            let totalInvoice = 0.0;
            for (i = 0; i < orders.length; i++) {
                if (orders[i].paid) {
                    $("#orderTable tbody").append(
                        '<tr class="paidBills">' +
                        '<td><i style="color:green" class="fas fa-check"></i></td>' +
                        '<td>' + orders[i].product + '</td>' +
                        '<td>' + orders[i].quantity + '</td>' +
                        '<td>' + '¥ ' + orders[i].price + '</td>' +
                        '<td class="paidBills">' + '¥ ' + orders[i].total + '</td>' +
                        '</tr>'
                    );
                } else {
                    totalInvoice += orders[i].total;
                    $("#orderTable tbody").append(
                        '<tr>' +
                        '<td><i style="color:red" class="fas fa-times"></i></td>' +
                        '<td>' + orders[i].product + '</td>' +
                        '<td>' + orders[i].quantity + '</td>' +
                        '<td>' + '¥ ' + orders[i].price + '</td>' +
                        '<td>' + '¥ ' + orders[i].total + '</td>' +
                        '</tr>'
                    );
                }
            }
            $("#orderTable tbody").append(
                '<tr>' +
                '<td><strong>Total to be paid:</td>' +
                '<td></td>' +
                '<td></td>' +
                '<td></td>' +
                '<td><strong>' + '¥ ' + totalInvoice + '</strong></td>' +
                '</tr>'
            );
        }
    });
}




