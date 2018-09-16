package com.zeng.json;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

//利用合法的json字符串和给定的class字符串，实例化一个Bean对象并且返回
public class JsonDeal {
    public static Object getJsonBean(HttpServletRequest request,String className) throws Exception{
        Class c=Class.forName(className);
        Object retObj=null;
        int length=request.getContentLength();
        InputStream inputStream=request.getInputStream();
        if(length<0){
            return null;
        }else{
            byte buffer[]=new byte[length];
            System.out.println("length"+"     "+length);
            for(int i=0;i<length;i++){
                int len=inputStream.read(buffer,i,length);
                if(len==-1){
                    break;
                }
                i+=len;
            }

            String json=new String(buffer,"utf-8");
            Gson gson=new Gson();
            retObj=gson.fromJson(json,c);
        }
        return retObj;
    }
}
