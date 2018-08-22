package com.zeng.manage.catalg;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ResourceBundleTextProvider;
import com.zeng.database.DataBaseManage;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import javax.servlet.http.Cookie;
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
        String pageType=request.getParameter("pageType");
        Cookie[] cookies=request.getCookies();
        int page=1;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("TimeListPage")){
                page=Integer.parseInt(cookie.getValue());
            }
        }


        String ret=null;

        if (pageType!=null){
            if(pageType.equals("last")){
                if (page>1){
                    page-=1;
                }
                ret=queryTimeList(page);

            }else if (pageType.equals("next")){
                page+=1;
                ret=queryTimeList(page);

            }else if (pageType.equals("first")){
                page=1;
                ret=queryTimeList(1);
            }
        }
        if (ret==null){
            ret="{}";
        }else{
            Cookie cookie=new Cookie("TimeListPage",page+"");
            response.addCookie(cookie);
        }
        w.write(ret);
        return NONE;
    }

    String queryTimeList(int page) throws Exception{

        int start=(page-1)*5+1;
        int end=start+4;
        int count=1;
        Map<String,String> map=new HashMap<>();
        //获取数据库连接
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        ResultSet resultSet=null;

        String sql="select * from (select DATE_FORMAT(t_article.time,'%Y-%m') as mytime from t_article) as temp group by temp.mytime;";
        //查询数据库
        Statement statement=conn.createStatement();
        resultSet=statement.executeQuery(sql);

        while (resultSet.next()){
            if(count<start){
                count++;
                continue;
            }
            if(count>end){
                break;
            }
            count++;
            map.put((String)resultSet.getString(1),(String)resultSet.getString(1));
        }

//        DataBaseManage.returnConnection(conn);
        resultSet.close();
        statement.close();

        if (map.size()==0){
            return null;
        }
        JSONObject jsonObject= JSONObject.fromObject(map);
        String json=jsonObject.toString();
        return json;
    }

}
