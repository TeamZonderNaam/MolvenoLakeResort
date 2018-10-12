// The main file is meant to construct the dataTable and provide a global variable for other javascript files to use
var DATA_TABLE;
var BASE_URL = "/api/facility/";
var DATA_PAIRS = {
    name: ".name",
    description: ".description",
};


$(function() {
    DATA_TABLE = $("table").DataTable({
        autoWidth: false, // this is to fix an error

        columns: [
            {data: "name"},
            {data: "description"},
            {
                data: null,
                className: "center",
                defaultContent: '<a href="edit"><i class="fas fa-edit"></i></a> / <a href="delete"><i class="far fa-trash-alt"></i></a>'
            }
        ]
    });
});