<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <title>로그인</title>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">로그인</h3>
                </div>
                <div class="panel-body">
                    <form id="loginForm" role="form" method="post" action="/user/login">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="아이디" name="userId" id="userId" type="id" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="비밀번호" id="password" type="password">
                                <input class="form-control" name="password" type="hidden">
                            </div>
                            <div>
                                <a id="btnLogin" href="#" class="btn btn-lg btn-success btn-block">로그인</a>
                                <a id="btnSignUp" href="#" class="btn btn-lg btn-primary btn-block">회원가입</a>
                            </div>
                        </fieldset>

                        <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
                            <p style="margin-top:10px; margin-bottom:0px;" class="text-center text-danger">${Session.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
                        </#if>

                        <input type="hidden" id="RSAModulus" value="${RSAModulus}"/>
                        <input type="hidden" id="RSAExponent" value="${RSAExponent}"/>
                    </form>
                </div>
            </div>
        </div>

        <#include "/user/signUpModal.ftl"/>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="/js/bootstrap/bootstrap.min.js"></script>

<script src="/js/user/login.js"></script>

<script src="/js/rsa/jsbn.js"></script>
<script src="/js/rsa/prng4.js"></script>
<script src="/js/rsa/rng.js"></script>
<script src="/js/rsa/rsa.js"></script>

</body>
</html>