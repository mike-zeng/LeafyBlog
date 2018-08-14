package com.zeng.manage.leaveMessage;

import com.zeng.database.DataBaseManage;

import javax.naming.ldap.PagedResultsControl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeaveMessageDaoIml implements LeaveMessageDao{
    @Override
    public String getLeaveMessageList(int num) throws Exception {
        String sql="select * from t_leaveMessage order by time desc";
        String[] s={};
        PreparedStatement preparedStatement=DataBaseManage.getPreparedStatement(sql,s);
        ResultSet resultSet=DataBaseManage.execuePres(DataBaseManage.EXECUTEQUERY,preparedStatement);
        String json="{",id,user,content,time;
        int flag=1;
        //id,name.content
        while (resultSet.next()){
            if(flag<num){
                flag++;
                continue;
            }
            if(flag!=num){
                json+=",";
            }
            id=resultSet.getString("id");
            user=resultSet.getString("user");
            content=resultSet.getString("content");
            time=resultSet.getString("time");
            json+="\""+id+"\""+":"+"["+"\""+user+"\""+","+"\""+content+"\""+","+"\""+time+"\""+"]";
            if(flag==num+4){
                break;
            }
            flag++;
        }
        json+="}";

        return json;
    }

    @Override
    public boolean addLeaveMessage() throws Exception {
        return false;
    }

    @Override
    public boolean delLeaveMessage(String id) throws Exception {
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        String sql="delete from t_leaveMessage where id=?;";
        PreparedStatement pres=conn.prepareStatement(sql);
        pres.setString(1,id);
        pres.executeUpdate();//执行
        return true;
    }
}
