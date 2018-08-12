var node1=document.getElementById("PersonalClassification");
var req1=new XMLHttpRequest();
req1.open("GET","getClassification",true);
req1.onreadystatechange=function () {
    if (req1.readyState==4 && req1.status==200) {
        var json=JSON.parse(req1.responseText);
        for(var k in json){
            var v=json[k];
            var temp=document.createElement("a");
            var func="getArticleList('CLASSIFICATION'"+",'"+k+"',"+"'1')";
            temp.setAttribute("onclick",func);
            var temp2=document.createElement("br");
            var text=document.createTextNode(v);
            temp.appendChild(text);
            node1.appendChild(temp);
            node1.appendChild(temp2);
        }

    }
}
req1.send();




var node2=document.getElementById("TimeClassification");
var req2=new XMLHttpRequest
req2.open("GET","getTimeList",true);
req2.onreadystatechange=function () {
    if (req2.readyState==4 && req2.status==200) {
        var json=JSON.parse(req2.responseText);
        for(var k in json){
            var v=json[k];
            var temp=document.createElement("a");
            var temp2=document.createElement("br");
            var func="getArticleList('TIME'"+",'"+k+"',"+"'1')";
            temp.setAttribute("onclick",func);
            var text=document.createTextNode(v);
            temp.appendChild(text);
            node2.appendChild(temp);
            node2.appendChild(temp2);
        }

    }
}
req2.send();