<#include "/layout/layout.ftl"/>

<#macro pageCSS>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dataTables.bootstrap.css" rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</#macro>

<#macro pageJS>
    <script src="/js/book/bookmark.js"></script>
    <script src="/js/common/pagination.util.js"></script>
    <script src="/js/bootstrap/bootstrap.min.js"></script>
</#macro>

<#macro body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">북마크</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    북마크
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th style="width:60%">제목</th>
                                    <th style="width:40%">북마크한 시간</th>
                                </tr>
                                </thead>
                                <tbody id="tbody">

                                <#if data?? && data?has_content>
                                    <#list data as bookmark>
                                        <tr style="cursor:pointer;" >
                                            <td>${bookmark.title}</td>
                                            <td>${bookmark.bookmarkDate}</td>

                                            <input type="hidden" name="bookmark_no" value="${bookmark.bookmarkNo}">
                                            <input type="hidden" name="title" value="${bookmark.title}">
                                            <input type="hidden" name="contents" value="${bookmark.contents}">
                                            <input type="hidden" name="url" value="${bookmark.url}">
                                            <input type="hidden" name="isbn" value="${bookmark.isbn}">
                                            <input type="hidden" name="datetime" value="${bookmark.datetime}">
                                            <input type="hidden" name="authors" value="${bookmark.authors}">
                                            <input type="hidden" name="publisher" value="${bookmark.publisher}">
                                            <input type="hidden" name="translators" value="${bookmark.translators}">
                                            <input type="hidden" name="price" value="${bookmark.price}">
                                            <input type="hidden" name="sale_price" value="${bookmark.salePrice}">
                                            <input type="hidden" name="sale_yn" value="${bookmark.saleYn}">
                                            <input type="hidden" name="category" value="${bookmark.category}">
                                            <input type="hidden" name="thumbnail" value="${bookmark.thumbnail}">
                                            <input type="hidden" name="barcode" value="${bookmark.barcode}">
                                            <input type="hidden" name="ebook_barcode" value="${bookmark.ebookBarcode}">
                                            <input type="hidden" name="status" value="${bookmark.status}">
                                        </tr>
                                    </#list>
                                <#else>
                                    <tr>
                                        <td colspan="2">북마크가 없습니다.</td>
                                    </tr>
                                </#if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <#if data?? && data?has_content>
                        <input type="hidden" id="paginationTotalCount" value="${pagination.totalCount}"/>
                        <input type="hidden" id="paginationStartEntry" value="${pagination.startEntry}"/>
                        <input type="hidden" id="paginationEndEntry" value="${pagination.endEntry}"/>
                        <input type="hidden" id="paginationStartPageNo" value="${pagination.startPageNo}"/>
                        <input type="hidden" id="paginationEndPageNo" value="${pagination.endPageNo}"/>
                        <input type="hidden" id="paginationEnd" value="${pagination.end?c}"/>
                        <input type="hidden" id="paginationCurrPage" value="${pagination.currPage}"/>
                        <#include "/common/pagination.ftl"/>
                    </#if>

                    <#include "/book/bookDetailModal.ftl"/>
                </div>
            </div>
        </div>
    </div>
</#macro>