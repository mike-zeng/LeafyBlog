
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8" />
    <title>Simple example - Editor.md examples</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/source/editor.md-master/examples/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/source/editor.md-master/css/editormd.css" />
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />
    <script>
        function alter(id) {
            var req=new XMLHttpRequest();
            var url="alterArticle?articleId="+id;
            req.open("POST",url,true);
            req.send();
            console.log("test alter------")
        }
    </script>
</head>
<body>
<div id="layout">
    <header style="text-align: right">
        <div style="float: left">
            <input type="text" class="form-control" id="title"  value="${requestScope.articleBean['title']}" placeholder="请输入标题">
        </div>
        <div>
            <button class="btn btn-default" style="float:right;background-color: #449D44;color: #0D3349;" data-toggle="modal" data-target="#myModal" onclick="getClassification()">
                保存文章
            </button>
        </div>
    </header>
    <div id="test-editormd">
        <textarea style="display:none;" id="text">${requestScope.articleBean['article']}</textarea>
    </div>
</div>
<script src="${pageContext.request.contextPath}/source/editor.md-master/editormd.min.js"></script>
<%--<script src="js/jquery.min.js"></script>--%>

<script type="text/javascript">
    var testEditor;

    $(function() {
        testEditor = editormd("test-editormd", {
            width   : "90%",
            height  : 640,
            syncScrolling : "single",
            path    : "${pageContext.request.contextPath}/source/editor.md-master/lib/"
        });

    });
</script>

<%@ include file="articleInfo.jsp"%>
<script>
    var e_tag=document.getElementById("tag");
    e_tag.value="${requestScope.articleBean['tag']}";

    var put_article=document.getElementById("putArticle");
    put_article.removeAttribute("onclick");
    put_article.setAttribute("onclick","alter(${requestScope.articleId})");
    console.log(put_article);
</script>

<%--为保存信息赋予初始值--%>
</body>
</html>


