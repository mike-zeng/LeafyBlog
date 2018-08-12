package com.zeng.Login;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import com.zeng.database.DataBaseManage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginAction extends ActionSupport implements ModelDriven<UserBean> {
    UserBean user=new UserBean();

    public UserBean getModel() {
        return user;
    }
    @Override
    public String execute() throws Exception {
        if(judge()){
            HttpServletRequest request= ServletActionContext.getRequest();//设置session
            request.getSession(true).setAttribute(user.getName(),user.getPassword());
            return SUCCESS;//成功则暂时后台页面，并设置登入信息的session信息
        }else{
            return ERROR;//错误则回到登入页面
        }
    }

    //判断登入信息是否正确
    private boolean judge(){

        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();

        ResultSet result=null;
        PreparedStatement prep=null;
        try {
            prep=conn.prepareStatement("SELECT * FROM t_root WHERE name=? AND password=?");
          prep.setString(1,user.getName());
          prep.setString(2,user.getPassword());

          result=prep.executeQuery();
          if(result!=null&&result.next()){
              System.out.println(result.getString(1));
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
                prep.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            DataBaseManage.returnConnection(conn);//归还数据库连接
        }
        return true;
    }

}
