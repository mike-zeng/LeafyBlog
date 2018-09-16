
//type表示要查询的类型
//arg为参数
//page为页数
function func1(e) {
    e.style="margin-top: 5%;box-shadow:1px -1px 16px #0F3322;";
}
function func2(e) {
    e.style="margin-top: 5%";
}

function getArticleList(type,arg,page) {

    var FLAG=page;
    var e = document.getElementById("articleList");
    if(page=='L'){
        page=Number(getCookie("last_page"))-1;
    }else if (page=="N") {
        page= Number(getCookie("last_page"))+1;
    }else{
        var childs=e.childNodes;
        for(var i=childs.length-1;i>=0;i--){
            e.removeChild(childs[i]);
        }
    }

    var obj={
        "type":type,
        "arg":arg,
        "page":page
    }
    var objJson=JSON.stringify(obj);

    var req=new XMLHttpRequest();
    req.open("POST","getArticleList",true);
    req.setRequestHeader("Content-type","application/json");
    req.send(objJson);//发送json给服务器；

    req.onreadystatechange=function () {
        if (req.readyState == 4 && req.status == 200) {
            var json = JSON.parse(req.responseText);
            var e = document.getElementById("articleList");
            var article;
            var articleList=e.innerHTML;

            for (var a in json) {

                if(a=='count'){
                    break;
                }
                var id=a;
                var articleinfo = json[a]; //文章的信息
                var title =articleinfo[0];//文章标题
                var content = articleinfo[1];//文章概要
                var time = articleinfo[2];//时间
                var reading=articleinfo[3];//访问量
                var comments=articleinfo[4];//评论数
                var like=articleinfo[5];
                article=" <div class=\"article\" style=\"margin-top: 5%\" onmouseover=\"func1(this)\" onmouseout=\"func2(this)\">\n" +
                    "            <div style=\"height: 160px;background-color: white;text-align: center;padding: 1% 1% 1% 1%\">\n" +
                    "                <a href=\"showArticle?id="+id+"\"\"><h3>"+title+"</h3></a>\n" +
                    "                <p>"+content+"\n" +
                    "                </p>\n" +
                    "\n" +
                    "                <div style=\"margin-bottom: 1%;color: cornflowerblue\">\n" +
                    "                    <span>阅读: </span>"+reading+"&nbsp;\n" +
                    "                    <span>评论: </span>"+comments+"&nbsp;\n" +
                    "                    <span>点赞: </span>"+like+"&nbsp;\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>";
                articleList=article+articleList;
            }
            e.innerHTML=articleList;

        }
    }


}

function getCookie(c_name) {
    if(document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");//获取字符串的起点
        if(c_start != -1) {
            c_start = c_start + c_name.length + 1;//获取值的起点
            c_end = document.cookie.indexOf(";", c_start);//获取结尾处
            if(c_end == -1) c_end = document.cookie.length;//如果是最后一个，结尾就是cookie字符串的结尾
            return decodeURI(document.cookie.substring(c_start, c_end));//截取字符串返回
        }
    }
    return "";
}