package com.zeng.database;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

//用来管理数据库的连接
//实现目标，可以记录获取数据库连接的请求，并据此分析web应用的访问情况，动态改变list中的连接数量
public class DataBaseManage {
    private static Properties properties=new Properties();
    private static DataSource dataSource;
    static {
        try {
            FileInputStream is=new FileInputStream("/home/zeng/IdeaProjects/Blog/src/com/zeng/database/dbcp.properties");
            properties.load(is);
            System.out.println(properties);
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            dataSource= BasicDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    public static PreparedStatement getPreparedStatement(String sql, String[] args){
        DataBaseManage dbm=new DataBaseManage();
        Connection connection=dbm.getConnection();
        PreparedStatement pres=null;
        try {
            pres=connection.prepareStatement(sql);
            for(int i=1;i<=args.length;i++){
                pres.setString(i,args[i-1]);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return pres;
    }

    public static final String EXECUTEQUERY = "executeQuery";
    public static final String EXECUTEUPDATE = "executeUpdate";
    public static ResultSet execuePres(String type,PreparedStatement pres){
        ResultSet resultSet=null;
        try {
            if(type.equals("executeQuery")){
                resultSet=pres.executeQuery();
            }else if(type.equals("executeUpdate")){
                pres.executeUpdate();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }

}
