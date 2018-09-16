package com.zeng.manage.article;

import com.opensymphony.xwork2.ActionSupport;
import com.zeng.json.JsonDeal;
import com.zeng.manage.statistics.StatisticsDao;
import com.zeng.manage.statistics.StatisticsDaoImp;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
//用来查询所有文章并处理分页逻辑
public class GetArticleListAction extends ActionSupport {
    private TypeBean typeBean=null;
   private static ArticleDao articleDao=new ArticleDaoImp();

    public String execute() throws Exception{
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        String className="com.zeng.manage.article.TypeBean";
        typeBean=(TypeBean) JsonDeal.getJsonBean(request,className);


        Writer writer=response.getWriter();
        String json=null;


        if(typeBean.getType()==null){
            typeBean.setType((String)request.getSession().getAttribute("type"));
        }else{
            request.getSession().setAttribute("type",typeBean.getType());
        }
        if(typeBean.getArg()==null){
            typeBean.setArg((String) request.getSession().getAttribute("arg"));
        }else{
            request.getSession().setAttribute("arg",typeBean.getArg());
        }

        json=articleDao.queryArticle(typeBean.getType(),typeBean.getArg(),typeBean.getPage());
        System.out.println(json);
        writer.write(json);
        writer.close();
        return NONE;
    }

}
