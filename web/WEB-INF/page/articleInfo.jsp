<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function getClassification() {
            document.getElementById("info").innerHTML=null;
            var req=new XMLHttpRequest();
            req.open("GET","getClassification?pageType=all",true);
            req.onreadystatechange=function () {
                if (req.readyState==4 && req.status==200) {
                    var json=JSON.parse(req.responseText);
                    var str;
                    for(var a in json){
                        str+='<option class="btn btn-default" value="'+a+'">'+json[a]+'</option>';
                    }
                    var e=document.getElementById("classification");
                    e.innerHTML=str;
                }
            }
            req.send();
        }
    </script>
</head>
<body>
<!--模态框,用来完善文章的信息-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" style="text-align: center" id="myModalLabel">
                    发布文章
                </h4>
                <div id="info"></div>
            </div>
            <div class="modal-body">
                <div>
                    <label>文章标签</label><br>
                    <input type="text" class="form-control" id="tag" placeholder="自定义,使用分号隔开">
                </div>
                <br>
                <div id="kind">
                    <label>文章类型</label><br>
                    <label class="radio-inline">
                        <input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="1" checked> 原创
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="optionsRadiosinline" id="optionsRadios2"  value="2"> 转载
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="optionsRadiosinline" id="optionsRadios3"  value="3"> 翻译
                    </label>
                </div>
                <br>
                <div>
                    <label>文章分类</label><br>
                    <select class="btn btn-default" id="classification">

                    </select>
                </div>
                <br>
            </div>

            <div class="modal-footer" style="text-align: center">
                <button id="saveDraft" type="button" class="btn btn-default" onclick="saveArticle(true)">
                    保存草稿
                </button>
                <button id="putArticle" type="button" class="btn btn-default" onclick="saveArticle(false)">
                    发布文章
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
