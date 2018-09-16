package com.zeng.manage.statistics;

import com.zeng.database.DataBaseManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatisticsDaoImp implements StatisticsDao{
    @Override
    public void addReading(String id) throws Exception{
        String sql="update t_article set reading=reading+1 where id=?";
        Connection conn= DataBaseManage.getConnection();
        PreparedStatement pres=conn.prepareStatement(sql);
        pres.setString(1,id);
        pres.executeUpdate();
        pres.close();
        conn.close();
    }

    @Override
    public void addLikes(String id)throws Exception{
        String sql="update t_article set t_article.like=t_article.like+1 where id=?";
        Connection conn= DataBaseManage.getConnection();
        PreparedStatement pres=conn.prepareStatement(sql);
        pres.setString(1,id);
        pres.executeUpdate();
        pres.close();
        conn.close();
    }

    @Override
    public int pageCount() throws Exception {
        String sql="select count(id) from t_article;";
        Connection conn=DataBaseManage.getConnection();
        PreparedStatement pres=conn.prepareStatement(sql);
        ResultSet resultSet=pres.executeQuery();
        int count=0;
        if (resultSet.next()){
            count=resultSet.getInt(1);
        }
        pres.close();
        conn.close();
        return count;
    }

    @Override
    public int readCount() throws Exception {
        String sql="select sum(reading) from t_article";
        Connection conn=DataBaseManage.getConnection();
        PreparedStatement pres=conn.prepareStatement(sql);
        ResultSet resultSet=pres.executeQuery();
        int count=0;
        if (resultSet.next()){
            count=resultSet.getInt(1);
        }
        pres.close();
        conn.close();
        return count;
    }

    @Override
    public int likeCount()throws Exception {
        String sql="select sum(t_article.like) from t_article";
        Connection conn=DataBaseManage.getConnection();
        PreparedStatement pres=conn.prepareStatement(sql);
        ResultSet resultSet=pres.executeQuery();
        int count=0;
        if(resultSet.next()){
            count=resultSet.getInt(1);
        }
        pres.close();
        conn.close();
        return count;
    }
}
