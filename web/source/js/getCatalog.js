function getCatalog(pageType,type) {
    var node,url;
    if(type=='Classification'){
        node=document.getElementById("PersonalClassification");
        url="getClassification?pageType="+pageType;

    }else if (type=='Time'){
        node=document.getElementById("TimeClassification");
        url="getTimeList?pageType="+pageType;
    }


    var req=new XMLHttpRequest();

    console.log(url);

    req.open("GET",url,true);
    req.onreadystatechange=function () {
        if (req.readyState==4 && req.status==200) {
            if(req.responseText.length==2){
                return;//不改变
            }else{
                var childs=node.childNodes;
                for(var i=childs.length-1;i>=0;i--){
                    node.removeChild(childs[i]);
                }
            }
            var json=JSON.parse(req.responseText);

            for(var k in json){
                var v=json[k];
                var temp=document.createElement("a");
                var func;
                if(type=="Classification"){
                    func="getArticleList('CLASSIFICATION'"+",'"+k+"',"+"'1')";
                }else{
                    func="getArticleList('TIME'"+",'"+k+"',"+"'1')";
                }
                temp.setAttribute("onclick",func);
                var temp2=document.createElement("br");
                var text=document.createTextNode(v);
                temp.appendChild(text);
                node.appendChild(temp);
                node.appendChild(temp2);
            }

        }
    }
    req.send();
}


