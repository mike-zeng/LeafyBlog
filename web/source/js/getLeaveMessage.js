function getLeaveMessage(flag) {

    var root=document.getElementById("leaveMessageList");
    if(flag=="true"){
        clearCookie("last_lm");
        var childs=root.childNodes;
        for(var i=childs.length-1;i>=0;i--){
            root.removeChild(childs[i]);
        }
    }

    var root=document.getElementById("leaveMessageList");

    console.log("test------------")
    var req=new XMLHttpRequest();
    req.open("GET","getLeaveMessage",true);
    req.onreadystatechange=function () {
        if (req.status==200&&req.readyState==4){
            var json=JSON.parse(req.responseText);
            if(json=='{}'){
                return;
            }
            var text=root.innerHTML;
            for(var k in json){
                var info=json[k];
                var temp="<div id='lm"+k+"' style=\"margin-left:30%;margin-right:30%;margin-top: 5%;background-color: white;padding: 1% 1% 1% 1%;text-align: center\" onmouseover=\"show(this)\" onmouseout=\"show(this)\">\n" +
                    "    <h4>"+info[0]+"</h4>\n" +
                    "    <br>\n" +
                    "    <p>"+info[1]+"</p>\n" +
                    "    <br>\n" +
                    "    <div>\n" +
                    "        <span style=\"display: block\"><a>时间: "+info[2].substring(0,16)+"</a></span>\n" +
                    "        <div style=\"display: none\">\n" +
                    "            <span><a href=\"#\" style=\"color: red\" onclick='removeLeaveMessage("+k+")'>删除</a></span>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>";

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

function removeLeaveMessage(id) {
    var req=new XMLHttpRequest();
    var url="delLeaveMessage?del_id="+id;
    req.open("GET",url,true);
    req.send();
    var target="lm"+id;
    var root=document.getElementById("leaveMessageList");
    var childs=root.childNodes;
    for(var i=childs.length-1;i>=0;i--){
        if(childs[i].id==target){
            root.removeChild(childs[i]);
            console.log("del lm--------");
        }

    }

}