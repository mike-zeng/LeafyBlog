package com.zeng.database;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//用来管理数据库的连接
public class DataBaseManage {
    private static Properties properties=new Properties();
    private static DataSource dataSource;
    //加载数据源
    static {
        try {
            FileInputStream is=new FileInputStream("/home/zeng/IdeaProjects/Blog/src/com/zeng/database/dbcp.properties");
            properties.load(is);
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            dataSource= BasicDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //获取数据库连接
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
        Connection connection=getConnection();
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
    public static ResultSet execueSql(String type,String sql,String[] args){

        Connection connection=getConnection();
        PreparedStatement pres=null;
        try {
            pres=connection.prepareStatement(sql);
            for(int i=1;i<=args.length;i++){
                pres.setString(i,args[i-1]);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        ResultSet resultSet=null;
        try {
            if(type.equals("executeQuery")){
                resultSet=pres.executeQuery();
            }else if(type.equals("executeUpdate")){
                pres.executeUpdate();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                pres.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return resultSet;
    }

}
