package com.zeng.manage.catalg;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

//用来设置目录
public class SetClassification extends ActionSupport {
    CatalogBean catalogBean=null;
    private static CatalogDao catalogDao=new CatalogDaoImp();
    @Override
    public String execute() throws Exception {
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Writer writer=response.getWriter();

        boolean ret=catalogDao.setCatalog(catalogBean);
        //反馈结果---------------------
        writer.write("ok");
        return NONE;
    }

}
