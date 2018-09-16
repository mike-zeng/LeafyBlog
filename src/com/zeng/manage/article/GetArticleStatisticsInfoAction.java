package com.zeng.manage.article;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.zeng.manage.statistics.StatisticsDao;
import com.zeng.manage.statistics.StatisticsDaoImp;
import net.sf.json.JSON;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

//获取文章的相关统计数据，如阅读量
public class GetArticleStatisticsInfoAction extends ActionSupport {
    private static StatisticsDao statisticsDao=new StatisticsDaoImp();
    @Override
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int pages=statisticsDao.pageCount();
        int reads=statisticsDao.readCount();
        int likes=statisticsDao.likeCount();
        ArticleInfoBean articleInfoBean=new ArticleInfoBean(pages,reads,likes);

        Gson gson=new Gson();
        String jsonStr=gson.toJson(articleInfoBean,ArticleInfoBean.class);
        response.getWriter().write(jsonStr);

        return NONE;
    }
}

