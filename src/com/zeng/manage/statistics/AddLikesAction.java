package com.zeng.manage.statistics;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class AddLikesAction extends ActionSupport {
    private static StatisticsDao statisticsDao=new StatisticsDaoImp();
    @Override
    public String execute() throws Exception {
        //不能重复点赞，进行过滤
        HttpServletRequest request= ServletActionContext.getRequest();

        String id=request.getParameter("id");
        String like=(String) request.getSession().getAttribute("like"+id);
        if (like!=null){
            return NONE;
        }
        statisticsDao.addLikes(id);
        request.getSession().setAttribute("like"+id,"liked");
        return NONE;
    }
}
