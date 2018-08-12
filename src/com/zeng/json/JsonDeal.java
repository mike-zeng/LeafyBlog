package com.zeng.json;

import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;

//利用合法的json字符串和给定的class字符串，实例化一个Bean对象并且返回
public class JsonDeal {
    public static Object getJsonBean(HttpServletRequest request,String className) throws Exception{
        Class c=Class.forName(className);
        Object retObj=null;
        int length=request.getContentLength();
        if(length<0){
            return null;
        }else{
            byte buffer[]=new byte[length];
            for(int i=0;i<length;i++){
                int len=request.getInputStream().read(buffer,i,length);
                if(len==-1){
                    break;
                }
                i+=len;
            }
            String json=new String(buffer,"utf-8");
            JSONObject jobj=JSONObject.fromObject(json);
            retObj=JSONObject.toBean(jobj,c);
        }
        return retObj;
    }
}
