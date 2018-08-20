package com.zeng.manage.article;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowArticleAction extends ActionSupport {
    ArticleDao articleDao=new ArticleDaoImp();
    @Override
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String id=request.getParameter("id");
        ArticleBean articleBean=articleDao.getArticleById(id,"article");
        request.setAttribute("article",articleBean.getArticle());
        request.setAttribute("title",articleBean.getTitle());
        return SUCCESS;
    }
}
