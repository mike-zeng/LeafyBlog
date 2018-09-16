package com.zeng.manage.leaveMessage;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

public class getLeaveMessage extends ActionSupport {
    static LeaveMessageDaoIml leaveMessageDaoIml=new LeaveMessageDaoIml();
    @Override
    public String execute() throws Exception {

        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Writer writer=response.getWriter();

        //获取之前读到第几条留言
        Cookie[] cookies=request.getCookies();
        int lastLeaveMessage=0;
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("last_lm")){
                String v=cookies[i].getValue();
                lastLeaveMessage+=Integer.parseInt(v);
            }
        }
        lastLeaveMessage+=1;
        System.out.println(lastLeaveMessage);
        String res=leaveMessageDaoIml.getLeaveMessageList(lastLeaveMessage);

        if(!res.equals("{}")){
            Cookie cookie=new Cookie("last_lm",(lastLeaveMessage+4)+"");
            response.addCookie(cookie);
        }
        //设置当前读取到的最后一条
        writer.write(res);
        writer.close();
        return NONE;
    }
}
