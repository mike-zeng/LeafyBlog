package com.zeng.manage.leaveMessage;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;

public class AddLeaveMessageAction extends ActionSupport {
    private static LeaveMessageDao leaveMessageDao=new LeaveMessageDaoIml();
    @Override
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        request.setCharacterEncoding("utf-8");

        String email=request.getParameter("email");
        String content=request.getParameter("content");
        leaveMessageDao.addLeaveMessage(email,content);
        return SUCCESS;
    }
}
