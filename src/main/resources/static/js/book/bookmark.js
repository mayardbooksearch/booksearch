$(function() {
    Bookmark.init();
});

var Bookmark = (function() {

    var init = function() {

        $("#side_menu").find("a[name^='menu']").removeClass("active");
        $("#side_menu").find("a[name='menu_bookmark']").addClass("active");

        if ($("#paginationLayer").length > 0) {
            initPaginationEvent();
            initModalEvent();
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
            $(this).attr("href", "/book/bookmark/list?page=" + (Number($(this).data("page")) - 1));

        });
    };

    var initModalEvent = function() {

        $("#btnRemoveBookmark").show().on("click", function() {
            removeBookmark($(this).data("bookmark-no"));
        });

        $.each($("#tbody").find("tr"), function() {
            var $tr = $(this);
            $(this).on("click", function() {
                $("#modalTitle").text($tr.find("input[name='title']").val());
                $("#modalCategory").text($tr.find("input[name='category']").val());
                $("#modalPublisher").text($tr.find("input[name='publisher']").val());
                $("#modalAuthors").text($tr.find("input[name='authors']").val());
                $("#modalTranslators").text($tr.find("input[name='translators']").val());
                $("#modalContents").text($tr.find("input[name='contents']").val());
                $("#modalDatetime").text($tr.find("input[name='datetime']").val());
                $("#modalIsbn").text($tr.find("input[name='isbn']").val());
                $("#modalPrice").text($tr.find("input[name='price']").val());
                $("#modalSalePrice").text($tr.find("input[name='sale_price']").val());
                $("#modalSaleYn").text($tr.find("input[name='sale_yn']").val());
                $("#modalStatus").text($tr.find("input[name='status']").val());
                $("#modalBarcode").text($tr.find("input[name='barcode']").val());
                $("#modalEbarcode").text($tr.find("input[name='ebook_barcode']").val());
                $("#modalUrl").attr("href", $tr.find("input[name='url']").val());
                $("#modalThumbnail").attr("src", $tr.find("input[name='thumbnail']").val());

                $("#btnRemoveBookmark").data("bookmark-no", $tr.find("input[name='bookmark_no']").val())
                $("#bookInfoModal").modal("toggle");
            });
        });
    };

    var removeBookmark = function(bookmarkNo) {

        $.ajax({
            url: "/book/bookmark/" + bookmarkNo,
            method: "DELETE",
            success: function(res) {
                alert(res);
                location.href = "/book/bookmark/list";
            },
            error: function(res, status, xhr) {
                alert(res["responseText"]);
            }
        });
    };


    return {
        init: init
    };

})();