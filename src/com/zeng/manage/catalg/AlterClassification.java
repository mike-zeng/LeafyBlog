package com.zeng.manage.catalg;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

//根据id更改目录的信息
public class AlterClassification extends ActionSupport {
    @Override
    public String execute() throws Exception {

        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Writer writer=response.getWriter();

        //获取信息
        //执行sql操作数据库
        //返回值
        return NONE;
    }

}
