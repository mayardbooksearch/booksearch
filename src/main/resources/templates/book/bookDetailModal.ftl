<div class="modal fade" id="bookInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle"></h4>
            </div>
            <div class="modal-body">

                <table width="100%" class="table table-bordered">
                    <tbody>
                    <tr>
                        <th width="15%;">카테고리</th>
                        <td width="35%;" id="modalCategory"></td>
                        <th width="15%;">출판사</th>
                        <td width="35%;" id="modalPublisher"></td>
                    </tr>
                    <tr>
                        <th width="15%;">저자</th>
                        <td width="35%;" id="modalAuthors"></td>
                        <th width="15%;">번역</th>
                        <td width="35%;" id="modalTranslators"></td>
                    </tr>
                    <tr>
                        <th width="15%;">도서 소개</th>
                        <td width="85%;" colspan="3" id="modalContents"></td>
                    </tr>
                    <tr>
                        <th width="15%;">출판날짜</th>
                        <td width="85%;" colspan="3" id="modalDatetime"></td>
                    </tr>
                        <th width="15%;">ISBN</th>
                        <td width="85%;" colspan="3" id="modalIsbn"></td>
                    </tr>
                    <tr>
                        <th width="15%;">정가</th>
                        <td width="35%;" id="modalPrice"></td>
                        <th width="15%;">판매가</th>
                        <td width="35%;" id="modalSalePrice"></td>
                    </tr>
                    <tr>
                        <th width="15%;">판매여부</th>
                        <td width="35%;" id="modalSaleYn"></td>
                        <th width="15%;">도서상태</th>
                        <td width="35%;" id="modalStatus"></td>
                    </tr>
                    <tr>
                        <th width="15%;">바코드</th>
                        <td width="35%;" id="modalBarcode"></td>
                        <th width="15%;">e바코드</th>
                        <td width="35%;" id="modalEbarcode"></td>
                    </tr>
                    </tbody>
                </table>

                <div class="col-lg-pull-0" style="margin-bottom: 10px;">
                    <a id="modalUrl" href="" target="_blank">책 링크</a>
                </div>
                <div class="col-lg-offset-0">
                    <img id="modalThumbnail" src=""/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="btnBookmark" class="btn btn-primary" style="display:none;">북마크</button>
                <button type="button" id="btnRemoveBookmark" class="btn btn-danger" style="display:none;">북마크 삭제</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>