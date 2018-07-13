<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">회원가입</h4>
            </div>
            <div class="modal-body">

                <form role="form">
                    <div class="form-group">
                        <label class="control-label" for="modalId">아이디</label>
                        <input type="text" class="form-control" id="modalId" data-toggle="popover" data-placement="right" data-content="">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="modalPassword">비밀번호</label>
                        <input type="password" class="form-control" id="modalPassword">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="modalPasswordConfirm">비밀번호 재입력</label>
                        <input type="password" class="form-control" id="modalPasswordConfirm">
                    </div>
                </form>

                <div id="resultMessage" class="alert alert-warning" style="display:none;">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                <button id="modalBtnSignUp" type="button" class="btn btn-primary">회원 가입</button>
            </div>
        </div>
    </div>
</div>