<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>zeng's Blog</title>
  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

  <script src="${pageContext.request.contextPath}/source/js/getCatalog.js"></script>
  <script src="${pageContext.request.contextPath}/source/js/getArticleForClient.js"></script>

  <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

  <style>
    body{
      background-color: honeydew;
    }
    #head{
      height: 40%;
      background-image: url("${pageContext.request.contextPath}/source/img/index_background.jpg");
      background-size: 100% 100%;
      width: 100%;
    }
    #search{
      border-style: none;
      border-radius: 10%;
      width: 300px;
      height: 40px;
    }
    #list{
      position: absolute;
      float: right;
      top: 50px;
      right: 40px;
      display: none;
    }
    #classificationA{
      display: none;
      width: 200px;
      height: 200px;
      background-color: white;
      position: fixed;
      top: 150px;
      text-align: center;
      padding: 15px 15px 15px 15px;
    }
    #classificationB{
        display: none;
        width: 200px;
        height: 200px;
        background-color: white;
        position: fixed;
        top: 400px;
        text-align: center;
        padding: 15px 15px 15px 15px;
    }
  </style>

  <script>
      function func1(e) {
          e.style="margin-top: 5%;box-shadow:1px -1px 16px #0F3322;";
      }
      function func2(e) {
          e.style="margin-top: 5%";
      }

      function show(elem) {
          var e=document.getElementById(elem);
          if(e.style.display=='none'){
              e.style.display="block";
          }else{
              e.style.display="none";
          }
      }
      document.onmousemove=function () {
          var e = window.event;
          // console.log(e.clientX+'   '+e.clientY);
          if(e.clientY>=120&&e.clientY<=370&&e.clientX>=0&&e.clientX<=50){
              var node=document.getElementById("classificationA");
              node.style.display="block";

          }

          if(e.clientY<120||e.clientY>370||e.clientX>220){
              var node=document.getElementById("classificationA");
              node.style.display="none";
          }
          if(e.clientY>=380&&e.clientY<=620&&e.clientX==0&&e.clientX<=50){
              var node=document.getElementById("classificationB");
              node.style.display="block";
          }

          if(e.clientY<380||e.clientY>620||e.clientX>220){
              var node=document.getElementById("classificationB");
              node.style.display="none";
          }

      }

  </script>
</head>
<body>

<div id="head">
  <!--搜索和更多-->
  <div>

    <a href="#" style="float: left;margin: 2% 2% 2% 2%;color: black" onclick="show('search')">
      <span class="glyphicon glyphicon-search"></span>
    </a>
    <div>

      <a href="#" style="float: right;margin: 2% 2% 2% 1%;color: black" onclick="show('list')">
        <span class="glyphicon glyphicon-list"></span>
      </a>

      <div class="btn-group-vertical" id="list">
        <button type="button" class="btn btn-default">留言</button>
        <button type="button" class="btn btn-default">作者</button>
        <button type="button" class="btn btn-default">关于</button>
      </div>

    </div>

  </div>
  <br>
  <!--设置头像-->
  <div id="photo" style="text-align: center;margin-bottom: 2%;">
    <img src="${pageContext.request.contextPath}/source/img/index_photo.jpg" style="width: 8%;height: 8%;border-radius: 70%;margin-top: 5%;">
    <h4>曾宪辉的博客</h4>
  </div>
  <div style="text-align: center">

  </div>

</div>
<div>

  <div  style="margin-right: 20%;margin-left: 20%;">

    <div id="search" class="input-group col-md-3" style="width:30%;margin:2% 35% 0% 35%;display: none">

            <span class="input-group-btn">
                <input type="text" class="form-control" placeholder="请输入关键字"/>
               <button class="btn btn-info btn-search">查找</button>
            </span>
    </div>
    <div id="articleList">
      <script>
          getArticleList('ALL','','1');
      </script>
    </div>

    <div style="text-align: center">
      <br>
      <span class="glyphicon glyphicon-refresh" onclick="getArticleList(null,null,'N')"></span>
      <br>
    </div>

  </div>
</div>

<!--目录和分类-->
<div>
  <div id="classificationA">
    <div>
      <span><a href="#" onclick="getCatalog('first','Classification');getArticleList('ALL','','1')"><strong>个人分类</strong></a></span>
      <%--<span><a href="#"><strong>时间分类</strong></a></span>--%>
    </div>
    <hr>
    <!--分类-->
    <div id="PersonalClassification">
        <script>
            getCatalog('first','Classification');
        </script>

    </div>
      <br>
    <div style="position: absolute;top: 180px;left: 65px">
        <span style="margin-right: 15px" onclick="getCatalog('last','Classification')" class="glyphicon glyphicon-arrow-left"></span>
        <span style="margin-left: 15px" onclick="getCatalog('next','Classification')" class="glyphicon glyphicon-arrow-right"></span>

    </div>

  </div>


    <%--按照时间分类--%>
    <div id="classificationB">
        <div>
            <span><a href="#" onclick="getCatalog('first','Time');getArticleList('ALL','','1')"><strong>时间分类</strong></a></span>
            <%--<span><a href="#"><strong>时间分类</strong></a></span>--%>
        </div>
        <hr>
        <!--分类-->
        <div id="TimeClassification">
            <script>
                getCatalog('first','Time');
            </script>
        </div>
        <br>
        <div style="position: absolute;top: 180px;left: 65px">
            <span style="margin-right: 15px" onclick="getCatalog('last','Time')" class="glyphicon glyphicon-arrow-left"></span>
            <span style="margin-left: 15px" onclick="getCatalog('next','Time')" class="glyphicon glyphicon-arrow-right"></span>
        </div>

    </div>

</div>


</body>
</html>