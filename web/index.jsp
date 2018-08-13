<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>zeng's Blog</title>
  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
    #classification{
      /*display: none;*/
      width: 200px;
      height: 350px;
      background-color: white;
      position: fixed;
      top: 150px;
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
      function searchShow() {
          var e=document.getElementById("search");
          if(e.style.display=='none'){
              e.style.display="block";
          }else{
              e.style.display="none";
          }
      }
      function listShow() {
          var e=document.getElementById("list");
          if(e.style.display=='none'){
              e.style.display="block";
          }else{
              e.style.display="none";
          }
      }

  </script>
</head>
<body>

<div id="head">
  <!--搜索和更多-->
  <div>

    <a href="#" style="float: left;margin: 2% 2% 2% 2%;color: black" onclick="searchShow()">
      <span class="glyphicon glyphicon-search"></span>
    </a>
    <div>

      <a href="#" style="float: right;margin: 2% 2% 2% 1%;color: black" onclick="listShow()">
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

  <div id="article-list" style="margin-right: 20%;margin-left: 20%;">

    <div id="search" class="input-group col-md-3" style="width:30%;margin:2% 35% 0% 35%;display: none">

            <span class="input-group-btn">
                <input type="text" class="form-control" placeholder="请输入关键字"/>
               <button class="btn btn-info btn-search">查找</button>
            </span>
    </div>

    <div class="article" style="margin-top: 5%" onmouseover="func1(this)" onmouseout="func2(this)">
      <div style="height: 160px;background-color: white;text-align: center;padding: 1% 1% 1% 1%">
        <a href="http://www.baidu.com"><h3>我的标题</h3></a>
        <p>这个简写属性设置元素所有内边距的宽度，或者设置各边上内边距的宽度。行内非替换元素上设置的内边距不会影响行高计算；因此，如果一个元素既有内边距又有背景，从视觉上看可能会延伸到其他行，有可能还会与其他内容重叠。元素的背景会延伸穿过内边距。不允许指定负边距值。
        </p>

        <div style="margin-bottom: 1%;color: cornflowerblue">
          <span>阅读: </span>1280&nbsp;
          <span>评论: </span>12&nbsp;
          <span>点赞: </span>48&nbsp;
        </div>
      </div>
    </div>

    <div class="article" style="margin-top: 5%" onmouseover="func1(this)" onmouseout="func2(this)">
      <div style="height: 160px;background-color: white;text-align: center;padding: 1% 1% 1% 1%">
        <h3>我的标题</h3>
        <p>这个简写属性设置元素所有内边距的宽度，或者设置各边上内边距的宽度。行内非替换元素上设置的内边距不会影响行高计算；因此，如果一个元素既有内边距又有背景，从视觉上看可能会延伸到其他行，有可能还会与其他内容重叠。元素的背景会延伸穿过内边距。不允许指定负边距值。
        </p>

        <div style="margin-bottom: 1%;color: cornflowerblue">
          <span>阅读: </span>1280&nbsp;
          <span>评论: </span>12&nbsp;
          <span>点赞: </span>48&nbsp;
        </div>
      </div>
    </div>

    <div class="article" style="margin-top: 5%" onmouseover="func1(this)" onmouseout="func2(this)">
      <div style="height: 160px;background-color: white;text-align: center;padding: 1% 1% 1% 1%">
        <h3>我的标题</h3>
        <p>这个简写属性设置元素所有内边距的宽度，或者设置各边上内边距的宽度。行内非替换元素上设置的内边距不会影响行高计算；因此，如果一个元素既有内边距又有背景，从视觉上看可能会延伸到其他行，有可能还会与其他内容重叠。元素的背景会延伸穿过内边距。不允许指定负边距值。
        </p>

        <div style="margin-bottom: 1%;color: cornflowerblue">
          <span>阅读: </span>1280&nbsp;
          <span>评论: </span>12&nbsp;
          <span>点赞: </span>48&nbsp;
        </div>
      </div>
    </div>

    <div class="article" style="margin-top: 5%" onmouseover="func1(this)" onmouseout="func2(this)">
      <div style="height: 160px;background-color: white;text-align: center;padding: 1% 1% 1% 1%">
        <h3>我的标题</h3>
        <p>这个简写属性设置元素所有内边距的宽度，或者设置各边上内边距的宽度。行内非替换元素上设置的内边距不会影响行高计算；因此，如果一个元素既有内边距又有背景，从视觉上看可能会延伸到其他行，有可能还会与其他内容重叠。元素的背景会延伸穿过内边距。不允许指定负边距值。
        </p>

        <div style="margin-bottom: 1%;color: cornflowerblue">
          <span>阅读: </span>1280&nbsp;
          <span>评论: </span>12&nbsp;
          <span>点赞: </span>48&nbsp;
        </div>
      </div>
    </div>

    <div class="article" style="margin-top: 5%" onmouseover="func1(this)" onmouseout="func2(this)">
      <div style="height: 160px;background-color: white;text-align: center;padding: 1% 1% 1% 1%">
        <h3>我的标题</h3>
        <p>这个简写属性设置元素所有内边距的宽度，或者设置各边上内边距的宽度。行内非替换元素上设置的内边距不会影响行高计算；因此，如果一个元素既有内边距又有背景，从视觉上看可能会延伸到其他行，有可能还会与其他内容重叠。元素的背景会延伸穿过内边距。不允许指定负边距值。
        </p>

        <div style="margin-bottom: 1%;color: cornflowerblue">
          <span>阅读: </span>1280&nbsp;
          <span>评论: </span>12&nbsp;
          <span>点赞: </span>48&nbsp;
        </div>
      </div>
    </div>

  </div>
</div>

<!--目录和分类-->
<div>
  <div id="classification">
    <div>
      <span><a href="#"><strong>个人分类</strong></a></span>
      <span><a href="#"><strong>时间分类</strong></a></span>
    </div>
    <hr>
    <!--分类-->
    <div>

    </div>
    <div>
      <span style="margin-right: 15px"><a href="#"><strong><<</strong></a></span>
      <span style="margin-left: 15px"><a href="#"><strong>>></strong></a></span>
    </div>

  </div>

</div>


</body>
</html>