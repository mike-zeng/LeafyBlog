package com.zeng.database;

import java.io.FileInputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

//用来管理数据库的连接
//实现目标，可以记录获取数据库连接的请求，并据此分析web应用的访问情况，动态改变list中的连接数量
public class DataBaseManage {
    private static LinkedList<Connection> connectionsList=new LinkedList<>();
    volatile private static int size=0;//
    private static FileInputStream fin=null;
    private static String url,username,password,drive;

    static {
        int initSize=0;

        try{//加载驱动
            fin=new FileInputStream("/home/zeng/IdeaProjects/Blog/src/com/zeng/database/DBM.properties");
            Properties prop=new Properties();
            prop.load(fin);
            url=prop.getProperty("url");
            username=prop.getProperty("username");
            password=prop.getProperty("password");
            drive=prop.getProperty("driver");
            initSize=Integer.parseInt(prop.getProperty("initSize"));
            Class.forName(drive);
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<initSize;i++){
            try {
                Connection conn=DriverManager.getConnection(url,username,password);
                connectionsList.add(conn);
                conn=null;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        size=initSize;
    }

    public Connection getConnection(){
        while(size==0){
            //没有空余连接，先休息着
            try {
                Thread.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(size>0){
            size--;
            return connectionsList.removeFirst();
        }
        return null;
    }
    public static void returnConnection(Connection conn){
        connectionsList.add(conn);
        size++;
    }//不直接关闭数据库链接而是返回给类

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
        DataBaseManage.returnConnection(connection);
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
