<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/showdown/1.3.0/showdown.min.js"></script>
    <script>
        flag=true;
        function like(e) {
            if(flag){
                e.src="${pageContext.request.contextPath}/source/img/like2.png";
                flag=false;
            }else {
                e.src="${pageContext.request.contextPath}/source/img/like.png";

                flag=true;
            }
        }
    </script>

</head>
<body>

<div style="text-align: center;margin-left: 25%;margin-right: 25%">
    <!--标题-->
    <div style="margin-bottom: 5%">
        <h3>${requestScope.title}</h3>
    </div>
    <br>
    <!--文章简要信息-->
    <div style="margin-bottom: 5%">
        <span>时间: 2018.8.20  字数:20000  阅读:500</span>
    </div>
    <hr>
    <!--正文-->
    <div id="article" style="margin-bottom: 5%">

    </div>

    <!--导航-->
    <div class="btn-group" style="margin-bottom: 5%">
        <button type="button" class="btn btn-default">上一篇</button>
        <button type="button" class="btn btn-default">目录</button>
        <button type="button" class="btn btn-default">下一篇</button>
    </div>


    <br>
    <div style="margin-bottom: 5%">
        <img style="width: 80px;height: 80px" src="${pageContext.request.contextPath}/source/img/like.png" onclick="like(this)">
    </div>
</div>



<!--评论区 暂时不实现-->
<div>
</div>

<script type="text/javascript">
    var converter = new showdown.Converter(); //初始化转换器
    var htmlcontent  = converter.makeHtml(${requestScope.article}); //将MarkDown转为html格式的内容
    var article=document.getElementById("article");
    console.log(htmlcontent);
    article.innerHTML=htmlcontent;
    console.log("ok");
</script>

</body>



</html>