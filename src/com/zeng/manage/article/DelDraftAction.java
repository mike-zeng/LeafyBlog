package com.zeng.manage.article;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelDraftAction extends ActionSupport {
    private static ArticleDao articleDao=new ArticleDaoImp();
    @Override
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        String id=request.getParameter("del_id");
        articleDao.delArticle(id,"t_draft");
        Cookie[] cookies=request.getCookies();
        int lastDraft=0;
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("last_draft")){
                int v=Integer.parseInt(cookies[i].getValue());//屏幕上有几条
                cookies[i].setValue(v+"");
                response.addCookie(cookies[i]);
            }
        }
        return NONE;
    }
}
