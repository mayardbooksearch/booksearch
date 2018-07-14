$(function() {
    BookSearch.init();
});

var BookSearch = (function() {

    var init = function() {

        $("#side_menu").find("a[name^='menu']").removeClass("active");
        $("#side_menu").find("a[name='menu_search']").addClass("active");

        initBinding();
    };

    var initBinding = function() {

        $("#query").on("keydown", function(e) {
            var _this = $(this);

            if (e.keyCode === 13 && _this.val() !== "") {
                searchBook(1);
            }
        });

        $("#size").on("change", function() {
            if ($("#query").val() !== "") {
                searchBook(1);
            }
        });

        // 정렬 select event
        $("#sortSelect").find("a").on("click", function(e) {
            e.preventDefault();
            $("#sortText").text($(this).text());
            $("#sortText").data("value", $(this).data("value"));
        });

        // 검색어 타겟 select event
        $("#targetSelect").find("a").on("click", function(e) {
            e.preventDefault();
            $("#targetText").text($(this).text());
            $("#targetText").data("value", $(this).data("value"));
        });
        
        $("#btnBookmark").show().on("click", function() {
            addBookmark();
        });

        $("#btnRemoveBookmark").hide().on("click", function() {
            removeBookmark();
        });

        // 카테고리 이벤트
        if ($("#largeCategorySelect").length > 0) {
            initCategoryEvent();
        }
    };

    var initCategoryEvent = function() {
        $("#largeCategorySelect").find("a").on("click", function(e) {
            e.preventDefault();
            $("#largeCategoryText").text($(this).text());
            $("#largeCategoryText").data("value", $(this).data("value"));

            getSmallCategory($(this).data("value"));
        });
    };

    var getSmallCategory = function(largeCategoryId) {

        $("#smallCategorySelect").empty();
        $("#smallCategoryText").text("중분류");
        $("#smallCategoryText").data("value", "-1");

        $.ajax({
            url: "/book/category/small/" + largeCategoryId,
            method: "GET",
            success: function(res) {
                renderSmallCategory(res["smallCategoryList"]);
            },
            error: function(res, status, xhr) {
                alert(res["responseText"]);
            }
        });
    };

    var renderSmallCategory = function(smallCategoryList) {

        if (smallCategoryList === null || smallCategoryList.length === 0) {
            $("#btnSmallCategory").addClass("disabled");
            return;
        }

        var innerHTML = '<li><a href="#" data-value="-1">중분류</a></li>';
        innerHTML += '<li class="divider"></li>';

        $.each(smallCategoryList, function(k, v) {
            innerHTML += '<li>';
            innerHTML += '<a href="#" data-value="' + v["id"] + '">' + v["name"] + '</a>'
            innerHTML += '</li>';
        });

        $("#smallCategorySelect").append(innerHTML);
        $("#btnSmallCategory").removeClass("disabled");

        initSmallCategoryEvent();
    };

    var initSmallCategoryEvent = function() {

        $("#smallCategorySelect").find("a").on("click", function(e) {
            e.preventDefault();
            $("#smallCategoryText").text($(this).text());
            $("#smallCategoryText").data("value", $(this).data("value"));
        });
    };

    var searchBook = function(page) {

        // 중분류 카테고리가 disabled 상태가 아닌데 선택되지 않았을 경우 alert 노출하며 검색 진행하지 않는다.
        if (!$("#btnSmallCategory").hasClass("disabled") && $("#smallCategoryText").data("value") == -1) {
            alert("중분류를 선택해주세요.");
            return false;
        }

        $.ajax({
            url: "/book/search",
            type: "POST",
            data: {
                "query": $("#query").val(),
                "page": page,
                "size": $("#size").val(),
                "sort": $("#sortText").data("value"),
                "target": $("#targetText").data("value"),
                "largeCategory": $("#largeCategoryText").data("value"),
                "largeCategoryText": $("#largeCategoryText").text(),
                "smallCategory": $("#smallCategoryText").data("value"),
                "smallCategoryText": $("#smallCategoryText").text()
            },
            success: function(result) {
                renderingResult(result);
            },
            error: function(res, status, xhr) {
                alert(res["responseText"]);
            }
        });
    };

    var renderingResult = function(result) {
        $("#tbody").empty();

        var innerHTML = '';
        if (result["data"].length === 0) {
            innerHTML += '<tr class="gradeX odd">';
            innerHTML += '<td colspan="5">검색 결과가 없습니다.</td>';
            innerHTML += '</tr>';
        } else {
            $.each(result["data"], function(k, v) {
                var rowClass = " odd";
                if (k % 2 === 0) {
                    rowClass = "";
                }
                innerHTML += '<tr style="cursor:pointer;" class="gradeX"' + rowClass + '>';
                innerHTML += '<td>' + v["title"] + '</td>';
                innerHTML += '<td>' + v["authors"] + '</td>';
                innerHTML += '<td>' + v["publisher"] + '</td>';
                innerHTML += '<td>' + v["datetime"] + '</td>';
                innerHTML += '<td>' + v["status"] + '</td>';
                innerHTML += '<input type="hidden" name="title" value="' + v["title"] + '">';
                innerHTML += '<input type="hidden" name="contents" value="' + v["contents"] + '">';
                innerHTML += '<input type="hidden" name="url" value="' + v["url"] + '">';
                innerHTML += '<input type="hidden" name="isbn" value="' + v["isbn"] + '">';
                innerHTML += '<input type="hidden" name="datetime" value="' + v["datetime"] + '">';
                innerHTML += '<input type="hidden" name="authors" value="' + v["authors"] + '">';
                innerHTML += '<input type="hidden" name="publisher" value="' + v["publisher"] + '">';
                innerHTML += '<input type="hidden" name="translators" value="' + v["translators"] + '">';
                innerHTML += '<input type="hidden" name="price" value="' + v["price"] + '">';
                innerHTML += '<input type="hidden" name="sale_price" value="' + v["sale_price"] + '">';
                innerHTML += '<input type="hidden" name="sale_yn" value="' + v["sale_yn"] + '">';
                innerHTML += '<input type="hidden" name="category" value="' + v["category"] + '">';
                innerHTML += '<input type="hidden" name="thumbnail" value="' + v["thumbnail"] + '">';
                innerHTML += '<input type="hidden" name="barcode" value="' + v["barcode"] + '">';
                innerHTML += '<input type="hidden" name="ebook_barcode" value="' + v["ebook_barcode"] + '">';
                innerHTML += '<input type="hidden" name="status" value="' + v["status"] + '">';
                innerHTML += '</tr>';
            });
        }

        $("#tbody").append(innerHTML);

        initModalEvent();
        PaginationUtil.init(result["pagination"]);
        initPagingEvent();
    };

    var initPagingEvent = function() {

        $.each($('#pages').find("a"), function() {

            $(this).on("click", function(e) {
                e.preventDefault();
                if ($(this).parents("li").hasClass("disabled") || $(this).parents("li").hasClass("active")) {
                    return;
                }
                searchBook($(this).data("page"));
            })
        });
    };

    var initModalEvent = function() {

        $.each($("#tbody").find("tr"), function() {

            var $tr = $(this);

            $(this).on("click", function() {

                checkDuplicatedBookmark($tr);
            });
        });
    };

    // 이미 북마크한 책인지 확인
    var checkDuplicatedBookmark = function($tr) {

        var params = {
            "title": $tr.find("input[name='title']").val(),
            "barcode" : $tr.find("input[name='barcode']").val(),
            "ebookBarcode": $tr.find("input[name='ebook_barcode']").val(),
            "isbn": $tr.find("input[name='isbn']").val()
        };

        $.ajax({
            url: "/book/bookmark/duplicated",
            method: "POST",
            data: params,
            success: function(res) {
               renderModalDetail($tr, res["duplicatedBookmark"]);
            },
            error: function(res, status, xhr) {
                alert(res["responseText"]);
                return false;
            }
        });
    };

    var renderModalDetail = function($tr, duplicatedBookmark) {

        $("#btnBookmark").show();
        $("#btnRemoveBookmark").hide().data("bookmark-no", "");

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

        $("#bookInfoModal").modal("toggle");

        if (duplicatedBookmark !== null) {
            $("#btnRemoveBookmark").data("bookmark-no", duplicatedBookmark["bookmarkNo"]);
            bookmarkBtnToggle();
        }
    };
    
    var addBookmark = function() {
        
        var params = {
            "title": $("#modalTitle").text(),
            "category": $("#modalCategory").text(),
            "publisher": $("#modalPublisher").text(),
            "authors": $("#modalAuthors").text(),
            "translators": $("#modalTranslators").text(),
            "contents": $("#modalContents").text(),
            "datetime": $("#modalDatetime").text(),
            "isbn": $("#modalIsbn").text(),
            "price": $("#modalPrice").text(),
            "salePrice": $("#modalSalePrice").text(),
            "saleYn": $("#modalSaleYn").text(),
            "status": $("#modalStatus").text(),
            "barcode": $("#modalBarcode").text(),
            "ebookBarcode": $("#modalEbarcode").text(),
            "url": $("#modalUrl").attr("href"),
            "thumbnail": $("#modalThumbnail").attr("src")
        };

        $.ajax({
            url: "/book/bookmark/add",
            method: "POST",
            data: params,
            success: function(res) {
                alert(res["message"]);
                $("#btnRemoveBookmark").data("bookmark-no", res["bookmark"]["bookmarkNo"]);
                bookmarkBtnToggle();
            },
            error: function(res, status, xhr) {
                alert(res["responseText"]);
            }
        })
    };

    var removeBookmark = function() {
        var bookmarkNo = $("#btnRemoveBookmark").data("bookmark-no");

        $.ajax({
            url: "/book/bookmark/remove",
            method: "POST",
            data: {
                "bookmarkNo": bookmarkNo
            },
            success: function(res) {
                alert(res);
                bookmarkBtnToggle();
            },
            error: function(res, status, xhr) {
                alert(res["responseText"]);
            }
        });
    };

    var bookmarkBtnToggle = function() {
        $("#btnBookmark").toggle();
        $("#btnRemoveBookmark").toggle();
    };

    return {
        init: init,
        searchBook: searchBook
    }
})();

