package com.zeng.manage.catalg;

import com.zeng.database.DataBaseManage;
import com.zeng.manage.catalg.CatalogBean;
import com.zeng.manage.catalg.CatalogDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CatalogDaoImp implements CatalogDao {

    @Override
    public  Boolean setCatalog(CatalogBean catalogBean) throws Exception{
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        String sql="insert into t_catalog(name) VALUES (?);";
        PreparedStatement pres=conn.prepareStatement(sql);
        pres.setString(1,catalogBean.name);
        boolean ret=pres.execute();
        return !ret;
    }

    @Override
    public String queryCatalog() throws Exception {

        Map<String,String> map=new HashMap<>();
        //连接数据库，获取所有的目录的id和名字,存放在一个map中
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        Statement state=null;
        ResultSet resultSet=null;

        try{
            String sql="select id,name from t_catalog";
            state=conn.createStatement();
            resultSet=state.executeQuery(sql);
            while (resultSet.next()){
                map.put(resultSet.getString(1),resultSet.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            resultSet.close();
            state.close();
            DataBaseManage.returnConnection(conn);
        }

        String jsonStr=null;
        if(map.size()!=0){
            //将这个map转换成json对象
            JSONObject jobj=JSONObject.fromObject(map);
            jsonStr=jobj.toString();
        }

        return jsonStr;
    }

    @Override
    public String alterCatalog(CatalogBean catalogBean) {
        return null;
    }

    @Override
    public String delCatalog(String id) {
        return null;
    }
}
