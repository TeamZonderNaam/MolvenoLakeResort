var id = 0; //Having an id of 0 means you are not currently logged in.
var mail = "";
var fName = "";
var lName = "";
var password = "";
var role = "";

var accountList;

// CRUD functions for the account class 
$(document).ready(function () {

    if (fName == ""){
        $("#myAccount").html("");
    }

    // table with values that correspond with backend fieldnames 
    let table = $("#accountTable").DataTable({
        columns: [
            {
                "className": 'details-control',
                "orderable": false,
                "data": function (data, type, row) {
                    return '<button class="btn btn-info"><i class="fas fa-info"></i></button>';
                },
                "defaultContent": ''
            },
            { "data": "firstName" },
            { "data": "lastName" },
            { "data": "email" },
            { "data": "password" },
            { "data": "role" },
            {
                data: function (data, type, row) {
                    return '<button id="editAccountBtn" class="btn btn-primary" type="button" data-toggle="modal" data-target="#editAccountModal" onclick="getSingleAccount(' + data.id + ')"><i class="fas fa-edit"></i></button><button id="deleteAccountBtn" class="btn btn-primary edit-btn" onclick="setIdForDelete(\'' + data.id + '\',\'' + data.firstName + '\')" type="button" data-toggle="modal" data-target="#deleteAccountModal"><i class="fas fa-trash-alt"></i></button>';
                }
            }
        ]
    });

    // dropdown in table
    $('#accountTable tbody').on('click', 'td.details-control', function () {
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

    // post new Account
    $("#newAccountForm").on('submit', function (e) {

        // Post the data from the form
        postData();

        // Reset form
        $("#firstName").val("");
        $("#lastName").val(""); 
        $("#email").val("");
        $("#password").val("");
        $("#role").val("");
    });

    $("#deleteAccountForm").on('submit', function (e) {

        // delete the Account of which the id was entered
        deleteData();
    });

    // get single Account
    $("#editAccountForm").on('submit', function (e) {

        // prefills form with Account info
        getSingleAccount();
    })

    // edit single Account
    $("#editAccountForm1").on('submit', function (e) {

        // edit single Account
        editAccount();
    })

    $("#NewLoginForm").on('submit', function (e) {

        logIn();
    })

    // Load data first.
    //generateDummyData(); 
    getData();
    getLoggedData();
    console.log(id);
});


// getdata
function getData() {
    // Get the data from endpoint.
    $.ajax({
        url: "/api/account/",
        type: "get",
        success: function (accounts) {
            // On successful get, reload the datatable with new data.
            $('#accountTable').DataTable().clear();
            $('#accountTable').DataTable().rows.add(accounts);
            $('#accountTable').DataTable().columns.adjust().draw();
            accountList = accounts;
        }
    });
}

// getdata
function getLoggedData() {
    // Get the data from endpoint.
    $.ajax({
        url: "/api/logged/1",
        type: "get",
        success: function (account) {
            // On successful get, reload the datatable with new data.
            if (id == 0){
                id = 1;
            }
            mail = account.email;
            fName = account.firstName;
            lName = account.lastName;
            password = account.password;
            role = account.role;
            $("#myAccount").html("Welcome, " + fName + " " + lName);
        }
    });
}

//postdata
function postData() {

    // Get values from html.
    let firstName = $("#firstName").val();
    let lastName = $("#lastName").val();
    let email = $("#email").val();
    let password = $("#password").val();
    let role;

    if (document.getElementById('r1').checked) {
        role = document.getElementById('r1').value;
        console.log("First radio is checked.");
      }
    else {
        role = document.getElementById('r2').value;
        console.log("Second radio is checked.");
      }

    var duplicate = false;
    var i;
    for (i = 0; i < accountList.length; i++){
        if(email == accountList[i].email){
            duplicate = true;
        }
    }

    if (duplicate){
        alert("An account with this email already exists");
    } else {

    // Create JS object with data.
    let newAccount = {
        firstName: firstName,
        lastName: lastName, 
        email: email,
        password: password,
        role: role,
    }

    // Convert JS object to JSON.
    let validJsonAccount = JSON.stringify(newAccount);

    // Post JSON to endpoint.
    $.ajax({
        url: "/api/account/",
        type: "post",
        data: validJsonAccount,
        contentType: "application/json",
        success: function (result) {
            // On successful post, reload data to get the added one as well.
            getData();

        }
    });
    // close modal
    $('#newAccountBtn').click();
}
}



// Set id for deletion in the modal
function setIdForDelete(id, firstName, lastName) {
    $("#deleteAccountId").val(id);
    $("#accountToBeDeleted").html(firstName, lastName); ////Uitprobeersel. Was 'name'
    // $("#accountToBeDeleted").html(); 
}

// DELETE data
function deleteData() {

    // get values from html
    let accountId = $('#deleteAccountId').val();

    if (id != $('#deleteAccountId').val()){

    // DELETE request to backend 
    $.ajax({
        url: "/api/account/" + accountId,
        type: "delete",
        data: accountId,
        contentType: "application/json",
        success: function () {
            getData();
        }
    });
    // close modal
    $("#deleteAccountBtn").click(); 
    }

    else {
        alert("You are currently logged in to that account. Log out first and try again.");
    }
}




// GET single Account
function getSingleAccount(id) {

    // GET data from backend
    $.ajax({
        url: "/api/account/" + id,
        type: "get",
        data: id,
        contentType: "application/json",
        success: function (account) {
            // prefill form with data from backend
            $("#id1").val(id);
            $("#firstName1").val(account.firstName);
            $("#lastName1").val(account.lastName);
            $("#email1").val(account.email);
            $("#password1").val(account.password);

            if (account.role == "Guest"){
                document.getElementById("r3").checked = true;
            }
            else {
                document.getElementById("r4").checked = true;
            }
        }
    });
}


// EDIT data
function editAccount() {
    // Get values from Html
    let id = $("#id1").val();
    let firstName = $("#firstName1").val();
    let lastName = $("#lastName1").val();
    let email = $("#email1").val();
    let password = $("#password1").val();
    let role;

    if (document.getElementById('r3').checked) {
        role = document.getElementById('r1').value;
        console.log("First radio is checked.");
      }
    else {
        role = document.getElementById('r4').value;
        console.log("Second radio is checked.");
      }

    // create JS object
    let editedAccount = {
        id: id,
        firstName: firstName,
        lastName: lastName, 
        email: email,
        password: password,
        role: role
    }

    // create JSON object
    let validJsonEditedAccount = JSON.stringify(editedAccount);

    // PUT request to endpoint
    $.ajax({
        url: "/api/account/" + id,
        type: "put",
        data: validJsonEditedAccount,
        contentType: "application/json",
        success: function () {
            // update table with new data
            getData();
        }
    })
    // close modal
    $("#editAccountBtn").click();
}


// // Mini table for dropdown
// function format ( d ) {
//     // `d` is the original data object for the row
//     return '<table class="table table-striped table-bordered nowrap" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
//         '<tr>'+
//             '<td><strong>Password:</strong></td>'+
//             '<td>'+d.streetName + " " +  d.houseNumber + " " + d.additionToHouseNumber + " " + '</td>'+
//         '</tr>'+
//         '<tr>'+
//             '<td><strong>Address 2:</strong></td>'+
//             '<td>'+d.postcode+ ' ' + d.city+'</td>'+
//         '</tr>'+
//         '<tr>'+
//             '<td><strong>Country:</strong></td>'+
//             '<td>'+d.country+'</td>'+
//         '</tr>'+
//         '<tr>'+
//             '<td><strong>State or province:</strong></td>'+
//             '<td>'+d.stateOrProvince+'</td>'+
//         '</tr>'+
//         '<tr>'+
//             '<td><strong>VAT number:</strong></td>'+
//             '<td>'+d.vatNumber+'</td>'+
//         '</tr>'+
//         '<tr>'+
//             '<td><strong>Chamber of commer no:</strong></td>'+
//             '<td>'+d.kvkNumber+'</td>'+
//         '</tr>'+
//     '</table>';
// }

// Generate dummy data in database
function generateDummyData() {
    let dummyAccount = {
        "firstName": "David",
        "lastName": "Mijdam", 
        "email": "david.mijdam@capgemini.com",
        "password": "password",
        "role": "Guest",
    }

        // Convert JS object to JSON.
        let validJsonAccount = JSON.stringify(dummyAccount);

        // Post JSON to endpoint.
        $.ajax({
            url: "/api/account/",
            type: "post",
            data: validJsonAccount,
            contentType: "application/json",
            success: function (result) {
                // On successful post, reload data to get the added one as well.
                getData();
            }
        });


}

function logIn() {
    // Get the data from endpoint.
    $.ajax({
        url: "/api/account/",
        type: "get",
        success: function (accounts) {
            //$('#accountTable').DataTable().clear();
            var iEmail = "";
            var iPassword = "";
            var i;
            for (i = 0; i < accounts.length; i++){
                if ($("#email2").val() == accounts[i].email) {
                    iEmail = accounts[i].mail;
                    mail = accounts[i].mail;
                    if ($("#password2").val() == accounts[i].password){
                        id = accounts[i].id;
                        fName = accounts[i].firstName;
                        lName = accounts[i].lastName;
                        password = accounts[i].password;
                        role = accounts[i].role;
                        iPassword = accounts[i].password
                        alert("Log in succesful.");
                    }
                }
            }

            if (iEmail == ""){
                alert("This email is not registered.");
            } else if (iPassword == ""){
                alert("Invalid password.")
            } else {
                changeAccount();
            }
        }
    });
}

function logOut(){

    var r = confirm("You are about to log out. Continue?");

    if (r) {

    let editedAccount = {
        id: 0,
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        role: "",
    }

    let validJsonEditedAccount = JSON.stringify(editedAccount);

    // PUT request to endpoint
    $.ajax({
        url: "/api/logged/1",
        type: "put",
        data: validJsonEditedAccount,
        contentType: "application/json",
        success: function () {
            // update table with new data
            getData();
            //getLoggedData();
            $("#myAccount").html("");
            id = 0;
        }
    })
    window.location.href = 'http://localhost:8080/loginpage/';
}
}

function changeAccount(){
    // create JS object
    let editedAccount = {
        id: 1,
        firstName: fName,
        lastName: lName, 
        email: mail,
        password: password,
        role: role,
    }

    // create JSON object
    let validJsonEditedAccount = JSON.stringify(editedAccount);

    // PUT request to endpoint
    $.ajax({
        url: "/api/logged/1",
        type: "put",
        data: validJsonEditedAccount,
        contentType: "application/json",
        success: function () {
            // update table with new data
            getData();
            getLoggedData();
            if (role == "Staff"){
                window.location.href = 'http://localhost:8080/dashboard/';
            }
            else {
                window.location.href = 'http://localhost:8080/guest/menu/';
            }
        }
    })
}