function saveArticle(isDraft) {
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
    req.open("POST","saveArticle",true);
    req.setRequestHeader("Content-type","application/json");

    console.log(objJson);
    req.send(objJson);//发送json给服务器；


    req.onreadystatechange=function () {
        if (req.readyState==4 && req.status==200) {
            var json=JSON.parse(req.responseText);
            console.log(json);//待开发
            var e=document.getElementById("info");
            var info=null;
            if(json.ret=="success"){
                info="<p style='color: green'>文章发布成功!</p>";
            }else if(json.ret=="error"){
                info="<p style='color: red'>不知道发生了什么导致文章发布失败了!</p>";
            }else if(json.ret=="frequently"){
                info="<p style='color: orangered'>你写文章的速度也太快了吧!</p>";
            }
            e.innerHTML=info;
        }
    }
}