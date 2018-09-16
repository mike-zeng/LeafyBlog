<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="utf-8" />
        <title>${requestScope.title}</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/source/editor.md-master/examples/css/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/source/editor.md-master/css/editormd.preview.css" />
        <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />


        <script>
            flag=true;
            function like(e) {
                var id=${requestScope.id};
                if(flag){
                    //点赞
                    var req=new XMLHttpRequest();
                    var url="addLikes?id="+id;
                    req.open("POST",url,true);
                    req.send();
                    e.src="${pageContext.request.contextPath}/source/img/like2.png";
                    flag=false;
                }
            }
        </script>
    </head>

    <body>
        <div id="layout" style="text-align: center;margin-left: 10%;margin-right: 10%">
            <%--标题--%>
            <div style="margin-bottom: 5%;margin-top: 5%">
                <h1>${requestScope.title}</h1>
            </div>
                <div style="margin-bottom: 2%">
                    <span>时间: 2013.1.4</span>
                </div>
            <hr>
            <div id="test-editormd-view" style="margin-bottom: 5%">
                <textarea id="append-test" style="display:none;">${requestScope.article}</textarea>
            </div>

                <%--点赞--%>
                <div style="margin-bottom: 5%">
                    <img style="width: 50px;height: 50px" src="${pageContext.request.contextPath}/source/img/${requestScope.image}" onclick="like(this)">
                </div>
            <hr>
                <%--导航--%>
                <div class="btn-group" style="margin-top: 2%;margin-bottom: 5%">
                    <button type="button" class="btn btn-default">上一篇</button>
                    <button type="button" class="btn btn-default">目录</button>
                    <button type="button" class="btn btn-default">下一篇</button>
                </div>

        </div>

        <script src="${pageContext.request.contextPath}/source/editor.md-master/examples/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/source/editor.md-master/lib/marked.min.js"></script>
        <script src="${pageContext.request.contextPath}/source/editor.md-master/lib/prettify.min.js"></script>
        
        <script src="${pageContext.request.contextPath}/source/editor.md-master/lib/raphael.min.js"></script>
        <script src="${pageContext.request.contextPath}/source/editor.md-master/lib/underscore.min.js"></script>
        <script src="${pageContext.request.contextPath}/source/editor.md-master/lib/sequence-diagram.min.js"></script>
        <script src="${pageContext.request.contextPath}/source/editor.md-master/lib/flowchart.min.js"></script>
        <script src="${pageContext.request.contextPath}/source/editor.md-master/lib/jquery.flowchart.min.js"></script>

        <script src="${pageContext.request.contextPath}/source/editor.md-master/editormd.js"></script>
        <script type="text/javascript">
            $(function() {
                var testEditormdView;
                testEditormdView = editormd.markdownToHTML("test-editormd-view", {
                    htmlDecode      : "style,script,iframe",  // you can filter tags decode
                    emoji           : true,
                    taskList        : true,
                    tex             : true,  // 默认不解析
                    flowChart       : true,  // 默认不解析
                    sequenceDiagram : true,  // 默认不解析
                });
            });
        </script>
    </body>
</html>