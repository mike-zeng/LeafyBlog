function getLeaveMessage(flag) {

    var root=document.getElementById("leaveMessageList");
    if(flag=="true"){
        clearCookie("last_lm");
        var childs=root.childNodes;
        for(var i=childs.length-1;i>=0;i--){
            root.removeChild(childs[i]);
        }
    }

    var more=document.getElementById("more");
    if(more!=null){
        root.removeChild(more);
    }


    more=document.createElement("button");
    more.appendChild(document.createTextNode("查看更多"));
    more.setAttribute("onclick","getLeaveMessage()");
    more.setAttribute("id","more");

    var myStyle="margin:2% 30% 2% 30%;padding:1% 2% 1% 2%;background-color: white;text-align: center;";

    console.log("test------------")
    var req=new XMLHttpRequest();
    req.open("GET","getLeaveMessage",true);
    req.onreadystatechange=function () {
        if (req.status==200&&req.readyState==4){
            var json=JSON.parse(req.responseText);
            console.log(json);

            for(var k in json){
                var info=json[k];
                //div
                var e1=document.createElement("div");
                e1.setAttribute("style",myStyle);
                //h
                var e2=document.createElement("h3");
                e2.appendChild(document.createTextNode(info[0]));

                e1.appendChild(e2);

                var e3=document.createElement("p");
                e3.appendChild(document.createTextNode(info[1]));

                e1.appendChild(e3);

                var e4=document.createElement("p");
                e4.appendChild(document.createTextNode(info[2]));
                e1.appendChild(e4);
                root.appendChild(e1);
            }
        }
        root.appendChild(more);
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