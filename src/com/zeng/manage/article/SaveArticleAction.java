package com.zeng.manage.article;

import com.opensymphony.xwork2.ActionSupport;
import com.zeng.database.DataBaseManage;
import com.zeng.json.JsonDeal;
//import com.zeng.manage.ArticleBean;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class SaveArticleAction extends ActionSupport{
    ArticleBean articleBean=null;
    private static ArticleDao articleDao=new ArticleDaoImp();

    @Override
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Writer w=response.getWriter();

        //判断一下两次提交文章的时间，三分钟内不能提交多次
        long now=new Date().getTime();
//        if(request.getSession().getAttribute("lastTime")!=null){
//            long lastTime=(long)request.getSession().getAttribute("lastTime");
//            if(now-lastTime<=1000*60*3){
//                w.write("{\"ret\":\"frequently\"}");//告知浏览器操作过于频繁
//                w.close();
//                return NONE;
//            }
//        }
        //将文章保存为javaBean对象
        String className="com.zeng.manage.article.ArticleBean";
        articleBean=(ArticleBean)JsonDeal.getJsonBean(request,className);

        //处理并且返回json串告知处理结果
        String retStr=null;
        if(articleDao.addArticle(articleBean)){
            retStr="{\"ret\":\"success\"}";
            request.getSession().setAttribute("lastTime",new Date().getTime());//设置本次操作的时间
        }else{
            retStr="{\"ret\":\"error\"}";
        }
        w.write(retStr);//回写响应消息，告知浏览器是否操作成功
        w.close();
        return SUCCESS;
    }
}
