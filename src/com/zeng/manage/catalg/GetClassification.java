package com.zeng.manage.catalg;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

//响应浏览器使用ajax获得种类的数据
public class GetClassification extends ActionSupport{
    static CatalogDao catalogDao=new CatalogDaoImp();
    @Override
    public String execute() throws Exception {

        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        String pageType=request.getParameter("pageType");
        int page=1;
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("ClassificationPage")){
                page=Integer.parseInt(cookie.getValue());
            }
        }
        System.out.println("pageType===="+pageType);
        System.out.println("page========"+page);
        String ret=null;
        if(pageType==null){
            return NONE;
        }else{
            if (pageType.equals("last")){
                if(page<=1){

                }else{
                    page-=1;
                    ret=catalogDao.queryCatalog(page);
                }


            }else if (pageType.equals("next")){
                page+=1;
                ret=catalogDao.queryCatalog(page);
            }else if(pageType.equals("first")){
                page=1;
                ret=catalogDao.queryCatalog(1);
            }else if (pageType.equals("all")){
                page=1;
                ret=catalogDao.queryCatalog(-1);
            }

        }

        Writer writer=response.getWriter();


        if(ret==null){
            ret="{}";
        }else{
            Cookie cookie=new Cookie("ClassificationPage",page+"");
            response.addCookie(cookie);
        }
        System.out.println(ret);
        writer.write(ret);
        writer.close();
        return NONE;
    }

}
