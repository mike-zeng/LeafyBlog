<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>留言板</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<!--留言框-->
<div style="margin-left: 30%;margin-right: 30%">

    <h1 style="text-align: center">欢迎给我留言</h1>
    <form method="post" action="/addLeaveMessage">

        <div class="form-group">
            <label >邮箱</label>
            <input type="email" class="form-control" placeholder="请输入你的邮箱" name="email">
        </div>
        <div class="form-group">
            <label>留言内容:</label>
            <textarea class="form-control" rows="6" name="content"></textarea>
        </div>

        <div class="form-group">
            <button style="float: left;width: 25%;margin-left: 5%;" type="reset" class="btn btn-default">取消</button>
            <button style="float:right;width: 25%;margin-right: 5%;" type="submit" class="btn btn-success">提交</button>
        </div>
    </form>
</div>

<!--一些公开的留言的列表-->
<div>


</div>
</body>

</html>