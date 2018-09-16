<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        #login{
            margin: auto;
            text-align: center;
            width: 30%;
            height: 35%;
            border-style:solid;
            border-width:1px;
            border-radius:20px;
        }
        #welcome{
        }
    </style>
    <title>管理员登入</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
</head>
<%
    String path=request.getContextPath();
%>
<body background= "${path}/source/img/loginPageBackground.jpg">

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<div class="page-header" id="welcome">
    <h1 style="text-align: center">欢迎登入Blog后台</h1>
</div>

<div>

    <!--登入框-->
    <div id="login">
        <div style="margin: 25px;">
            <form role="form" action="/admin.action" method="post">
                <div id="warning"></div>
                <div class="form-group">
                    <label for="name">用户名</label>
                    <input type="email" class="form-control" id="name" name="name"
                           placeholder="请输入您的邮箱地址">
                </div>
                <div class="form-group">
                    <label for="name">密码</label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="请输入您的密码">
                </div>

                <button type="submit" class="btn btn-default" onsubmit="checkAccount()">登入</button>
                <button type="reset" class="btn btn-default">取消</button>
            </form>
        </div>
    </div>

</div>

</body>
</html>
