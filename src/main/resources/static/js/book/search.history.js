$(function() {
    SearchHistory.init();
});

var SearchHistory = (function() {

    var init = function() {

        $("#side_menu").find("a[name^='menu']").removeClass("active");
        $("#side_menu").find("a[name='menu_history']").addClass("active");

        if ($("#paginationLayer").length > 0) {
            initPaginationEvent();
        }
    };

    var initPaginationEvent = function() {

        PaginationUtil.init({
            "totalCount": $("#paginationTotalCount").val(),
            "startEntry": $("#paginationStartEntry").val(),
            "endEntry": $("#paginationEndEntry").val(),
            "startPageNo": $("#paginationStartPageNo").val(),
            "endPageNo": $("#paginationEndPageNo").val(),
            "end": $("#paginationEnd").val(),
            "currPage": $("#paginationCurrPage").val()
        });

        initPagingEvent();
    };

    var initPagingEvent = function() {

        $.each($('#pages').find("a"), function() {
            if ($(this).parents("li").hasClass("disabled") || $(this).parents("li").hasClass("active")) {
                return;
            }
            $(this).attr("href", "/book/history?page=" + (Number($(this).data("page")) - 1));

        });
    };

    return {
        init: init
    };

})();