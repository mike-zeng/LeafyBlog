package com.zeng.manage.catalg;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

//响应浏览器使用ajax获得种类的数据
public class GetClassification extends ActionSupport{
    static CatalogDao catalogDao=new CatalogDaoImp();
    @Override
    public String execute() throws Exception {
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        Writer writer=response.getWriter();
        String ret=catalogDao.queryCatalog();

        if(ret==null){
            ret="{}";
        }
        System.out.println(ret);
        writer.write(ret);
        writer.close();
        return NONE;
    }

}
