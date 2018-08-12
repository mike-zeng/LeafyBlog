<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/source/css/article.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/source/css/paging.css" />
    <!--markdown资源-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/source/editor.md-master/examples/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/source/editor.md-master/css/editormd.css" />
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />
    <script src="${pageContext.request.contextPath}/source/editor.md-master/editormd.min.js"></script>
    <script src="${pageContext.request.contextPath}/source/js/getLeaveMessage.js"></script>
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

    <script src="${pageContext.request.contextPath}/source/js/saveSource.js"></script>
    <script src="${pageContext.request.contextPath}/source/js/getSource.js"></script>

</head>
<body style="background-color: rgba(143,220,252,0.29)">
<ul id="myTab" class="nav nav-tabs">
    <li><a href="#home" data-toggle="tab">个人中心</a></li>
    <li class="active">
        <a href="#write" data-toggle="tab">新的文章</a>
    </li>
    <li><a href="#article" data-toggle="tab" onclick="getArticleList('ALL','','1')" >文章管理</a></li>
    <li><a href="#draft" data-toggle="tab">草稿管理</a></li>
    <li><a href="#statistics" data-toggle="tab">统计分析</a></li>
    <li><a href="#comment" data-toggle="tab">评论管理</a></li>
    <li><a href="#leaveMessage" data-toggle="tab"  onclick="getLeaveMessage('true')">留言管理</a></li>
</ul>

<div id="myTabContent" class="tab-content">
    <!--home 个人中心-->
    <div class="tab-pane fade" id="home">
        <h2>待开发home</h2>
    </div>

    <!--markdown　新的文章-->
    <div class="tab-pane fade in active" id="write">
        <!--markdown工作区-->
        <div id="layout">
            <header style="text-align: right">
                <div style="float: left">
                    <input type="text" class="form-control" id="title" placeholder="请输入文章标题">
                </div>
                <div>
                    <button class="btn btn-default" style="float:right;background-color: #449D44;color: #0D3349;" data-toggle="modal" data-target="#myModal" onclick="getClassification()">
                        保存文章
                    </button>
                </div>
            </header>
            <div id="test-editormd">
                <textarea style="display:none;" id="text">##剑气纵横三万里 一剑光寒十九洲</textarea>
            </div>
        </div>
    </div>

    <!--文章管理-->
    <link>
    <div class="tab-pane fade" id="article">
        <div style="width: 65%;float: left;margin-right: 5%">
            <div id="articleList" ></div>

            <div id="paging" style="margin-top: 2%;margin-bottom: 2%">

            </div>

        </div>

        <div style="width: 27%;margin-top: 3%;margin-right:3%;float: left">

            <div class="panel panel-default">
                <div class="panel-heading">
                    我的文章
                </div>
                <div class="panel-body">
                    <%--<a onclick="myGetClassification()">查看个人分类</a>--%>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    个人分类
                </div>
                <div class="panel-body" id="PersonalClassification">
                    <%--<a onclick="myGetClassification()">查看个人分类</a>--%>
                </div>
            </div>

            <div class="panel panel-default" style="margin-top: 3%">
                <div class="panel-heading">
                    时间分类
                </div>
                <div class="panel-body" id="TimeClassification">
                </div>
            </div>
        </div>
    </div>

    <!---->
    <div class="tab-pane fade" id="draft">
        <h2>待开发draft</h2>
    </div>

    <div class="tab-pane fade" id="statistics">
        <h2>待开发statistics</h2>
    </div>

    <div class="tab-pane fade" id="comment">
        <h2>待开发comment</h2>
    </div>

    <div class="tab-pane fade" id="leaveMessage">
        <div id="leaveMessageList">
            <button id="more"></button>
        </div>
    </div>
</div>

</body>

<script src="${pageContext.request.contextPath}/source/js/getCatalog.js"></script>


</html>
