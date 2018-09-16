package com.zeng.manage.comment;

import com.zeng.database.DataBaseManage;
import net.sf.json.JSONObject;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CommentDaoImp implements CommenDao{
    @Override
    public String getComment(String articleid) throws Exception{
        String sql="select * from t_comment where article=? order by t_comment.time desc";
        String[] args={articleid};
        ResultSet resultSet=new DataBaseManage().execueSql(DataBaseManage.EXECUTEQUERY,sql,args);

        Map<String,List> map=new HashMap<>();
        String res=null;
        while (resultSet!=null&&resultSet.next()){
            List<String> list=new LinkedList<>();
            list.add(resultSet.getString("name"));
            list.add(resultSet.getString("content"));
            list.add(resultSet.getString("time"));
            map.put(resultSet.getString("id"),list);
        }
        JSONObject jsonObject=JSONObject.fromObject(map);
        String json=jsonObject.toString();
        System.out.println(json);
        return json;
    }

    @Override
    public String getCommentList(int page) throws Exception{
        String sql="select * from t_comment order by t_comment.time desc";
        String[] args={};
        ResultSet resultSet=new DataBaseManage().execueSql(DataBaseManage.EXECUTEQUERY,sql,args);

        int start=(page-1)*5+1;
        int end=start+4;
        int count=1;

        Map<String,List> map=new LinkedHashMap<>();
        while (resultSet.next()){
            if (count<start){
                count++;
                continue;
            }
            if(count>end){
                break;
            }
            List<String> list=new LinkedList<>();
            list.add(resultSet.getString("name"));
            list.add(resultSet.getString("content"));
            list.add(resultSet.getString("time"));
            map.put(resultSet.getString("id"),list);
            count++;
        }

        JSONObject jsonObject=JSONObject.fromObject(map);
        String json=jsonObject.toString();
        resultSet.close();
        return json;
    }

    @Override
    public String setComment(CommentBean commentBean)throws Exception {
        String sql="insert into t_comment(article, name, content) VALUES (?,?,?);";
        String[] args={commentBean.getArticle(),commentBean.getName(),commentBean.getContent()};
        DataBaseManage.execueSql(DataBaseManage.EXECUTEUPDATE,sql,args);
        return null;
    }

    @Override
    public String delComment(String id)throws Exception{
        String sql="delete from t_comment where id=?";
        String[] args={id};
        DataBaseManage.execueSql(DataBaseManage.EXECUTEUPDATE,sql,args);
        return null;
    }

}
