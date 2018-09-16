package com.zeng.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class LoginActionFilter extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //检查用户是否登入
        Object object=ServletActionContext.getRequest().getSession().getAttribute("LoginFlag");
        if(object!=null){
            return actionInvocation.invoke();//已登入，放行
        }else{
            return "login";//没有登入跳到登入页面
        }
    }
}
