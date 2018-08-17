function getDraftList(flag) {

    var root=document.getElementById("draftList");
    if(flag=="true"){
        clearCookie("last_draft");
        var childs=root.childNodes;
        for(var i=childs.length-1;i>=0;i--){
            root.removeChild(childs[i]);
        }
        console.log("clear--------")
    }


    console.log("test------------")
    var req=new XMLHttpRequest();
    req.open("GET","getDraftList",true);
    req.onreadystatechange=function () {
        if (req.status==200&&req.readyState==4){
            var json=JSON.parse(req.responseText);
            if(json=='{}'){
                return;
            }
            var text=root.innerHTML;
            for(var id in json){
                var info=json[id];

                var title=info[0];
                var article=info[1];
                var time=info[2].substring(0,9);
                var temp="<div id=draft"+id+" style=\"margin-top: 5%;background-color: white;padding: 1% 1% 1% 1%;text-align: center\" onmouseover=\"show(this)\" onmouseout=\"show(this)\">\n" +
                    "    <h4>"+title+"</h4>\n" +
                    "    <br>\n" +
                    "    <p>"+article+"</p>\n" +
                    "    <br>\n" +
                    "    <div>\n" +
                    "        <span style=\"display: block\"><a>时间: "+time+"</a></span>\n" +
                    "        <div style=\"display: none\">\n" +
                    "            <span><a href=\"#\" onclick='removeDraft("+id+")' style=\"color: red\">删除</a></span>\n" +
                    "            <span><a target='_blank' href=\"alterArticle?type=draft&&id="+id+"\" style=\"color: red\">编辑</a></span>\n" +
                    "            <span><a href=\"#\" onclick='draftToArticle("+id+")' style=\"color: red\">发布</a></span>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>\n";

                if(text=='undefined'){
                    text=temp;
                }else{
                    text+=temp;
                }

            }
            root.innerHTML=text;
        }

    }

    req.send();

}

function clearCookie(name) {
    setCookie(name, "", -1);
}

function setCookie(c_name,value,expiredays)
{
    var exdate=new Date()
    exdate.setDate(exdate.getDate()+expiredays)
    document.cookie=c_name+ "=" +escape(value)+
        ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}

function removeDraft(id) {
    var req=new XMLHttpRequest();
    var url="delDraft?del_id="+id;
    req.open("GET",url,true);
    req.send();
    var target="draft"+id;
    var root=document.getElementById("draftList");
    var childs=root.childNodes;
    for(var i=childs.length-1;i>=0;i--){
        if(childs[i].id==target){
            root.removeChild(childs[i]);
            console.log("del draft--------");
        }
    }
}

function draftToArticle(id) {
    var req=new XMLHttpRequest();
    var url="draftToArticle?id="+id;
    req.open("GET",url,true);
    req.send();
    var target="draft"+id;
    var root=document.getElementById("draftList");
    var childs=root.childNodes;
    for(var i=childs.length-1;i>=0;i--){
        if(childs[i].id==target){
            root.removeChild(childs[i]);
            console.log("del draft--------");
        }
    }
}