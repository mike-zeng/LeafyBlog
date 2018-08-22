package com.zeng.Login;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import com.zeng.database.DataBaseManage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginAction extends ActionSupport implements ModelDriven<UserBean> {
    UserBean user=new UserBean();

    //获取用户信息
    public UserBean getModel() {
        return user;
    }
    @Override
    public String execute() throws Exception {
        if(judge()){
            HttpServletRequest request= ServletActionContext.getRequest();//设置session
            request.getSession(true).setAttribute(user.getName(),user.getPassword());
            return SUCCESS;//成功则展示后台页面，并设置登入信息的session信息
        }else{
            return ERROR;//错误则回到登入页面
        }
    }

    //判断登入信息是否正确
    private boolean judge(){
        Connection conn= DataBaseManage.getConnection();
        ResultSet result=null;
        PreparedStatement pres=null;
        try {
            pres=conn.prepareStatement("SELECT * FROM t_root WHERE name=? AND password=?");
            pres.setString(1,user.getName());
            pres.setString(2,user.getPassword());

            result=pres.executeQuery();
            if(result!=null&&result.next()){
              return true;
            }else{
              return false;
            }
        }catch (Exception e){
            e.printStackTrace();;
        }finally {
            try {
                if(result!=null){
                    result.close();
                }
                pres.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
