package com.zeng.manage.article;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelArticleAction extends ActionSupport {
    private static ArticleDao articleDao=new ArticleDaoImp();
    @Override
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String id=(String) request.getParameter("articleId");
        articleDao.delArticle(id);
        System.out.println("del-------"+id);
        return NONE;
    }
}
