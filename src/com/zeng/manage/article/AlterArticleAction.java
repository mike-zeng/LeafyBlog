package com.zeng.manage.article;

import com.opensymphony.xwork2.ActionSupport;
import com.zeng.json.JsonDeal;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//修改文章或者草稿
public class AlterArticleAction extends ActionSupport {
    private static ArticleDao articleDao=new ArticleDaoImp();
    private ArticleBean articleBean=null;
    @Override
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        if(request.getMethod().equals("GET")){
            String type=request.getParameter("type");
            String id=request.getParameter("id");
            if(type==null){
                return NONE;
            }
            ArticleBean articleBean=articleDao.getArticleById(id,type);
            request.setAttribute("id",id);
            request.setAttribute("articleBean",articleBean);
            request.setAttribute("oldType",type);
            return SUCCESS;
        }else if(request.getMethod().equals("POST")){
            String type=request.getParameter("oldType");
            String className="com.zeng.manage.article.ArticleBean";
            articleBean=(ArticleBean) JsonDeal.getJsonBean(request,className);
            articleBean.setId(Integer.parseInt(request.getParameter("id")));

            //如果本身是一篇已经发布了的文章，却要存为草稿，则暂时删除文章，并储存草稿
            if(type.equals("article")&&articleBean.isDraft()){
                articleDao.addArticle(articleBean);//增加
                articleDao.delArticle( ""+articleBean.getId(),"t_article");
            }else if (type.equals("draft")&&!articleBean.isDraft()){
                articleDao.addArticle(articleBean);
                articleDao.delArticle(""+articleBean.getId(),"t_draft");
            }else{
                articleDao.alterArticle(articleBean,articleBean.isDraft());//修改
            }
        }
        return NONE;
    }
}
