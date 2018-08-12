
//type表示要查询的类型
//arg为参数
//page为页数
var stcticObj={};
function getArticleList(type,arg,page) {
    var FLAG=page;
    console.log(page);
    if(page=='L'){
        page=Number(getCookie("last_page"))-1;
    }else if (page=="N") {
        page= Number(getCookie("last_page"))+1;
    }

    console.log(page);

    // getClassification();
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
            var articleList="";

            var head = '    <div style="margin-left: 2%">' +
                '        <div id="blog">' +
                '            <ul>';

            for (var a in json) {
                if(a=="count"){
                    if(FLAG=="1"||(FLAG=="N"&&page%5==1)) {
                        setPaging(page,json["count"]);
                    }
                    else if(FLAG=="L"&&page%5==0){
                        setPaging(page-4,json["count"]);
                    }
                    continue;
                }
                var id=a;
                var articleinfo = json[a]; //文章的信息
                var title =articleinfo[0];//文章标题
                var content = articleinfo[1];//文章概要
                var time = articleinfo[2];//时间

                var article="<div id=\""+id+"\" style=\"margin-top: 5%;background-color: white;padding: 1% 1% 1% 1%;text-align: center\" onmouseover=\"show(this)\" onmouseout=\"show(this)\">\n" +
                    "    <h4>"+title+"</h4>\n" +
                    "    <br>\n" +
                    "    <p>"+content+"</p>\n" +
                    "    <br>\n" +
                    "    <div>\n" +
                    "        <span style=\"display: block\"><a>时间: "+time.substring(0,10)+"</a></span>\n" +
                    "        <div style=\"display: none\">\n" +
                    "            <span><a onclick='remove("+id+")' href=\"#\" style=\"color: red\">删除</a></span>\n" +
                    "            <span><a href=\"alterArticle?articleId="+id+"\" style=\"color: red\" target='_blank'>编辑</a></span>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>";
                articleList += article;

            }
            var end = '            </ul>' +
                '        </div>' +
                '    </div>';

            if(articleList.length!=0){
                var e = document.getElementById("articleList");
                e.innerHTML = articleList;
            }

        }
    }


}


function setPaging(start_page,count) {
    var stytle="   height: 30px;width: 30px;background-color: white;border-style: solid;border-width: 1px;color: cornflowerblue;"
    var root=document.getElementById("paging")
    var sum=(count-1)/5+1;
    if(start_page<=0||start_page>sum){
        return;
    }
    var end_page=Number(start_page)+4>sum?sum:Number(start_page)+4;
    var childs=root.childNodes;
    for(var i=childs.length-1;i>=0;i--){
        root.removeChild(childs[i]);
    }
    var temp=document.createElement("button");
    temp.setAttribute("onclick","getArticleList(null,null,'L')");
    var text=document.createTextNode("<<");
    temp.setAttribute("stytle",stytle);
    temp.appendChild(text);
    root.appendChild(temp);

    for(var i=start_page;i<=end_page;i++){
        temp=document.createElement("button");//按钮
        text=document.createTextNode(i);
        temp.setAttribute("stytle",stytle);
        temp.setAttribute("onclick","getArticleList(null,null,"+i+")");
        temp.appendChild(text);
        root.appendChild(temp);
    }

    temp=document.createElement("button");
    text=document.createTextNode(">>");
    temp.setAttribute("stytle",stytle);
    temp.setAttribute("onclick","getArticleList(null,null,'N')");
    temp.appendChild(text);
    root.appendChild(temp);

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

function show(e) {
    var s=e.children[4];
    var s1=s.children[0];
    var s2=s.children[1];
    if(s1.style.display=="block"){
        s1.style.display="none";
        s2.style.display="block";

    }else{
        s1.style.display="block";
        s2.style.display="none";
    }
}

function remove(id) {
    var req=new XMLHttpRequest();
    var url="delArticle?articleId="+id;
    req.open("POST",url,true);
    req.send();
    var p=document.getElementById("articleList");
    var e=document.getElementById(id);
    p.removeChild(e);
    console.log("remove-------")
}
function alter(id) {
    var req=new XMLHttpRequest();
    var url="alterArticle?articleId="+id;
    req.open("GET",url,true);
    req.send();
    console.log("test alter")
}
