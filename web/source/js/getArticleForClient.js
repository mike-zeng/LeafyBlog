
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
    console.log(page);
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
           console.log(json);
            var e = document.getElementById("articleList");

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

                //第一个节点
                var e1=document.createElement("div");
                e1.class="article";
                e1.style.marginTop="5%";
                e1.onmouseover=function (ev) {
                    this.style="margin-top: 5%;box-shadow:1px -1px 16px #0F3322;";
                };
                e1.onmouseout=function (ev) {
                    this.style="margin-top: 5%";
                };

                //第二个节点
                var e2=document.createElement("div");
                e2.style="height: 160px;background-color: white;text-align: center;padding: 1% 1% 1% 1%";

                //文章标题
                var e3=document.createElement("a");
                e3.href="showArticle?id="+id;//文章链接
                var e4=document.createElement("h3");
                e4.appendChild(document.createTextNode(title));
                e3.appendChild(e4);

                //文章段落内容
                var e5=document.createElement("p");
                e5.appendChild(document.createTextNode(content));

                var e6=document.createElement("div");
                e6.style="margin-bottom: 1%;color: cornflowerblue";

                var e7=document.createElement("span");
                e7.appendChild(document.createTextNode("阅读: "+reading+" "));

                var e8=document.createElement("span");
                e8.appendChild(document.createTextNode("评论: "+comments+" "));

                var e9=document.createElement("span");
                e9.appendChild(document.createTextNode("喜欢: "+like+" "))

                //组合节点
                e6.appendChild(e7);
                e6.appendChild(e8);
                e6.appendChild(e9);

                e2.appendChild(e3);
                e2.appendChild(e5);
                e2.appendChild(e6);

                e1.appendChild(e2);

                e.appendChild(e1);
                console.log(e1);
            }

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