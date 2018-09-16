package com.zeng.manage.article;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DraftToArticleAction extends ActionSupport {
    static ArticleDao articleDao=new ArticleDaoImp();
    @Override
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String id=request.getParameter("id");

        ArticleBean articleBean=articleDao.getArticleById(id,"draft");

        System.out.println(articleBean+"=========");
        if(articleBean!=null){
            articleBean.setDraft(false);
            articleDao.addArticle(articleBean);//添加文章
            articleDao.delArticle(id,"t_draft");//删除草稿
        }
        return NONE;
    }
}
