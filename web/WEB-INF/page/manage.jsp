<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <style>
        #navigation{float: left;width: 10%;height: 100%;}
        #work{float: left;width: 90%;height: 100%;}
    </style>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<%--新的文章--%>
<%@ include file="editor.jsp" %>

<%--模态框　协助提交文章--%>
<%@ include file="articleInfo.jsp"%>


</body>
</html>