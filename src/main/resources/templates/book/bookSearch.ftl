<#include "/layout/layout.ftl"/>

<#macro pageCSS>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dataTables.bootstrap.css" rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</#macro>

<#macro pageJS>
    <script src="/js/book/book.search.js"></script>
    <script src="/js/common/pagination.util.js"></script>
    <script src="/js/bootstrap/bootstrap.min.js"></script>
</#macro>

<#macro body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">책 검색</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    책 검색
                    <div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <span id="largeCategoryText">대분류</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" id="largeCategorySelect">
                                <li><a href="#" data-value="X">대분류</a>
                                <li class="divider"></li>
                                <li><a href="#" data-value="accuracy">정확도순</a>
                                </li>
                                <li><a href="#" data-value="recency">최신순</a>
                                </li>
                                <li><a href="#" data-value="sales">판매량순</a>
                                </li>
                            </ul>
                        </div>

                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <span id="">중분류</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" id="">
                                <li><a href="#" data-value="X">중분류</a>
                                <li class="divider"></li>
                                <li><a href="#" data-value="accuracy">정확도순</a>
                                </li>
                                <li><a href="#" data-value="recency">최신순</a>
                                </li>
                                <li><a href="#" data-value="sales">판매량순</a>
                                </li>
                            </ul>
                        </div>

                        <div class="btn-group">
                            <button style="width:72px;" type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <span id="sortText">정렬</span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu" id="sortSelect">
                                <li><a href="#" data-value="accuracy">정렬(정확도순)</a>
                                <li class="divider"></li>
                                <li><a href="#" data-value="accuracy">정확도순</a>
                                </li>
                                <li><a href="#" data-value="recency">최신순</a>
                                </li>
                                <li><a href="#" data-value="sales">판매량순</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="dataTables_length" id="dataTables-example_length">
                                <label>페이지 당
                                    <select id="size" aria-controls="dataTables-example" class="form-control input-sm">
                                        <option value="10">10</option>
                                        <option value="25">25</option>
                                        <option value="50">50</option>
                                    </select>
                                개씩 보기</label>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div id="dataTables-example_filter" class="dataTables_filter">
                                <label>Search:
                                    <input id="query" type="search" style="display: inline-block;width: auto;vertical-align: middle;" class="form-control input-sm" placeholder="Search" aria-controls="dataTables-example">
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>제목</th>
                                    <th>저자</th>
                                    <th>출판사</th>
                                    <th>출판 날짜</th>
                                    <th>상태</th>
                                </tr>
                                </thead>
                                <tbody id="tbody">
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <#include "/common/pagination.ftl"/>

                    <#include "/book/bookDetailModal.ftl"/>
                </div>
            </div>
        </div>
    </div>
</#macro>