package com.zeng.manage.catalg;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ResourceBundleTextProvider;
import com.zeng.database.DataBaseManage;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

//按照时间分类---获取时间
public class GetTimeList extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Writer w=response.getWriter();

        //获取数据库连接
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        ResultSet resultSet=null;

        String sql="select * from (select DATE_FORMAT(t_article.time,'%Y-%m') as mytime from t_article) as temp group by temp.mytime;";
        //查询数据库
        Statement statement=conn.createStatement();
        resultSet=statement.executeQuery(sql);

        //组成json串
        Map<String,String> map=new HashMap<>();
        while (resultSet.next()){
            map.put((String)resultSet.getString(1),(String)resultSet.getString(1));
        }
        JSONObject jsonObject= JSONObject.fromObject(map);
        String json=jsonObject.toString();
        //发送json串
        w.write(json);
        return NONE;
    }

}
