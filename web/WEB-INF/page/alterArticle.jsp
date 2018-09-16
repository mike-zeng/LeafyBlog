
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
        function alter(id,isDraft,old) {

            function artObj(title,article,tag,kind,classification,isDraft) {
                this.title=title;
                this.article=article;
                this.tag=tag;
                this.kind=kind;
                this.classification=classification;
                this.draft=isDraft;
            }
//            从页面中获取文章的而所有信息，并生成json对象,发送给服务器
            var title=document.getElementById("title").value;//获取文章标题
            var article=document.getElementById("text").innerHTML;//获取文章内容
            var tag=document.getElementById("tag").value;//获取
            var kind= $('#kind input:radio:checked').val();;//类型
            var classification=document.getElementById("classification").value;//获取文章所属分类

            var obj=new artObj(title,article,tag,kind,classification,isDraft);
            var objJson=JSON.stringify(obj);

            var req=new XMLHttpRequest();
            var url="alterArticle?oldType="+old+"&&id="+id;
            req.open("POST",url,true);
            req.setRequestHeader("Content-type","application/json");
            req.send(objJson);//发送json给服务器；

            root=document.getElementById("info");
            root.innerHTML="<p style='color: red'>修改成功!</p>"
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
    put_article.setAttribute("onclick","alter(${requestScope.id},false,\"${requestScope.oldType}\")" );

    var save_draft=document.getElementById("saveDraft");
    save_draft.removeAttribute("onclick");
    save_draft.setAttribute("onclick","alter(${requestScope.id},true,\"${requestScope.oldType}\")")
</script>

<%--为保存信息赋予初始值--%>
</body>
</html>


