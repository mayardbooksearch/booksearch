var PaginationUtil = (function() {

    var init = function(pagination) {
        renderPagination(pagination);
    };

    var renderPagination = function(pagination) {

        $("#pages").empty();

        $("#totalCount").text(pagination["totalCount"]);
        $("#startEntry").text(pagination["startEntry"]);
        $("#endEntry").text(pagination["endEntry"]);

        var prevDisabled = "";
        var nextDisabled = "";

        var prevPage = pagination["startPageNo"] - 1;
        var nextPage = pagination["endPageNo"] + 1;

        if (Number(pagination["startPageNo"]) === 1) {
            prevDisabled = " disabled";
        }

        if (pagination["end"] == "true" || pagination["end"] === true) {
            nextDisabled = " disabled";
        }

        var innerHTML = '';

        innerHTML += '<li class="paginate_button previous' + prevDisabled + '" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous">';
        innerHTML += '<a href="#" data-page="' + prevPage + '">Previous</a>';
        innerHTML += '</li>';

        for (i = Number(pagination["startPageNo"]); i <= Number(pagination["endPageNo"]); i++ ) {

            if (i === Number(pagination["currPage"])) {
                var currPageCss = " active";
            } else {
                var currPageCss = "";
            }

            innerHTML += '<li class="paginate_button' + currPageCss + '" aria-controls="dataTables-example" tabindex="0">';
            innerHTML += '<a href="#" data-page="' + i + '">' + i + '</a>';
            innerHTML += '</li>';
        }

        innerHTML += '<li class="paginate_button next' + nextDisabled + '" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next">';
        innerHTML += '<a href="#" data-page="' + nextPage + '">Next</a>';
        innerHTML += '</li>';

        $("#pages").append(innerHTML);
        $("#paginationLayer").show();
    };

    return {
        init: init
    }
})();