<#include "/layout/layout.ftl"/>

<#macro pageCSS>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dataTables.bootstrap.css" rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</#macro>

<#macro pageJS>
    <script src="/js/book/search.history.js"></script>
    <script src="/js/common/pagination.util.js"></script>
    <script src="/js/bootstrap/bootstrap.min.js"></script>
</#macro>

<#macro body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">최근 검색 히스토리</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    최근 검색 히스토리
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th style="width:20%;">검색어</th>
                                    <th style="width:10%;">검색 대상</th>
                                    <th style="width:15%;">대분류</th>
                                    <th style="width:15%;">소분류</th>
                                    <th style="width:10%;">정렬</th>
                                    <th style="width:10%;">찾은 갯수</th>
                                    <th style="width:20%;">검색 시간</th>
                                </tr>
                                </thead>
                                <tbody id="tbody">

                                <#if data?? && data?has_content>
                                    <#list data as searchHistory>
                                        <tr>
                                            <td>${searchHistory.query}</td>
                                            <td>${searchHistory.target}</td>
                                            <td>${searchHistory.largeCategory}</td>
                                            <td>${searchHistory.smallCategory}</td>
                                            <td>${searchHistory.sort}</td>
                                            <td>${searchHistory.searchCount}</td>
                                            <td>${searchHistory.searchDate}</td>
                                        </tr>
                                    </#list>
                                <#else>
                                    <tr>
                                        <td colspan="7">최근 검색 히스토리가 없습니다.</td>
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
                </div>
            </div>
        </div>
    </div>
</#macro>