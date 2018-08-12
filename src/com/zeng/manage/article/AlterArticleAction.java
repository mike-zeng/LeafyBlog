package com.zeng.manage.article;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterArticleAction extends ActionSupport {
    private static ArticleDao articleDao=new ArticleDaoImp();
    @Override
    public String execute() throws Exception {

        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        if(request.getMethod().equals("GET")){
            int id=Integer.parseInt(request.getParameter("articleId"));
            ArticleBean articleBean=articleDao.getArticleById(id,"article");
            System.out.println(articleBean);
            request.setAttribute("articleId",id);
            request.setAttribute("articleBean",articleBean);
            return SUCCESS;
        }else if(request.getMethod().equals("POST")){
            articleDao.alterArticle();//修改
            System.out.println("ok-----------");
        }
        return NONE;
    }
}
