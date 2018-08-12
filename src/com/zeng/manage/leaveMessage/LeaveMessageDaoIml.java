package com.zeng.manage.leaveMessage;

import com.zeng.database.DataBaseManage;

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
            id=resultSet.getString("id");
            user=resultSet.getString("user");
            content=resultSet.getString("content");
            time=resultSet.getString("time");
            json+="\""+id+"\""+":"+"["+"\""+user+"\""+","+"\""+content+"\""+","+"\""+time+"\""+"]";
            if(flag==num+4){
                break;
            }
            json+=",";
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
    public boolean delLeaveMessage() throws Exception {
        return false;
    }
}
