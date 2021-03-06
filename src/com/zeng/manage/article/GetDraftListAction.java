package com.zeng.manage.article;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

public class GetDraftListAction extends ActionSupport {
    static ArticleDao articleDao=new ArticleDaoImp();
    @Override
    public String execute() throws Exception {

        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Writer writer=response.getWriter();

        //获取之前读到第几条草稿
        Cookie[] cookies=request.getCookies();
        int lastDraft=0;
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("last_draft")){
                String v=cookies[i].getValue();
                lastDraft+=Integer.parseInt(v);
            }
        }
        lastDraft+=1;
        String res=articleDao.queryDraft(lastDraft);

        if(!res.equals("{}")){
            Cookie cookie=new Cookie("last_draft",(lastDraft+4)+"");
            response.addCookie(cookie);
        }
        //设置当前读取到的最后一条
        System.out.println(res);
        writer.write(res);
        writer.close();
        return NONE;
    }

}
