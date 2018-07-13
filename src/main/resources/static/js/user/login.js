$(function() {
    Login.init();
});

var Login = (function() {

    var init = function() {

        $("#btnSignUp").on("click", function(e) {
            e.preventDefault();
            $("#signUpModal").modal("toggle");
        });

        $("#btnLogin").on("click", function(e) {
            e.preventDefault();
            login();
        });

        $("#password").on("keydown", function(e) {
            if (e.keyCode === 13) {
                login();
            }
        });

        initModalEvent();
    };

    var initModalEvent = function() {

        $("#modalBtnSignUp").on("click", function() {
            signUp();
        });

        $("#modalPasswordConfirm").on("keydown", function(e) {
            if (e.keyCode === 13) {
                signUp();
            }
        });
    };

    var login = function() {

        // rsa 암호화
        var rsa = new RSAKey();
        rsa.setPublic($('#RSAModulus').val(),$('#RSAExponent').val());

        var password = $("#password").val();
        password = rsa.encrypt(password);

        $("input[name='password']").val(password);

        $("#loginForm").submit();
    };


    var signUp = function() {

        var userId = $("#modalId").val();
        var password = $("#modalPassword").val();
        var passwordConfirm = $("#modalPasswordConfirm").val();

        if (userId === undefined || userId === "") {
            alert("아이디를 입력해주세요.");
            $("#modalId").focus();
            return;
        }

        if (password === undefined || password === "") {
            alert("비밀번호를 입력해주세요.");
            $("#modalPassword").focus();
            return;
        }

        if (passwordConfirm === undefined || passwordConfirm === "") {
            alert("비밀번호 재입력을 입력해주세요.");
            $("#modalPasswordConfirm").focus();
            return;
        }

        if (password !== passwordConfirm) {
            alert("비밀번호가 일치하지 않습니다.");
            $("#modalPassword").focus();
            return;
        }

        $("#resultMessage").empty().hide();

        // rsa 암호화
        var rsa = new RSAKey();
        rsa.setPublic($('#RSAModulus').val(),$('#RSAExponent').val());
        password = rsa.encrypt(password);

        $.ajax({
            url: "/user/signup",
            method: "POST",
            data: {
                "userId": userId,
                "password": password
            },
            success: function(res) {
                $("#resultMessage").show().text(res);
            },
            error: function(res, status, xhr) {
                $("#resultMessage").show().text(res["responseText"]);
            }
        });
    };


    return {
        init: init
    };
})();