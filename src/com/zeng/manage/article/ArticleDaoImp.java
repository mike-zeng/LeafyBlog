package com.zeng.manage.article;

import com.zeng.database.DataBaseManage;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArticleDaoImp implements ArticleDao{

    private static final String  TIME= "TIME";
    private static final String CLASSIFICATION ="CLASSIFICATION";
    private static final String ALL="ALL";
    @Override
    public boolean addArticle(ArticleBean articleBean) throws Exception {
        if(articleBean.isComplete()){
            String sql=null;
//            判断是草稿还是正式文章
            if(articleBean.getDraft()){
                sql="insert into t_draft(title,article,tag,kind,classification) values(?,?,?,?,?)";
            }else{
                sql="insert into t_article(title,article,tag,kind,classification) values(?,?,?,?,?)";
            }
            DataBaseManage dbm=new DataBaseManage();
            Connection conn= dbm.getConnection();
            PreparedStatement prep=conn.prepareStatement(sql);
            prep.setString(1,articleBean.getTitle());
            prep.setString(2,articleBean.getArticle());
            prep.setString(3,articleBean.getTag());
            prep.setString(4,articleBean.getKind());
            prep.setString(5,articleBean.getClassification());

            int ret=prep.executeUpdate();
            if(ret==0){
                return false;
            }
//
            DataBaseManage.returnConnection(conn);
            prep.close();
        }else{
            return false;
        }
        return true;
    }

    @Override
    public boolean delArticle(String id) throws Exception {
        String sql="delete from t_article where id=?;";
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        PreparedStatement pres=conn.prepareStatement(sql);

        pres.setString(1,id);
        int ret=pres.executeUpdate();

        pres.close();
        DataBaseManage.returnConnection(conn);
        if(ret==0){
            return false;
        }
        return true;
    }

    @Override
    public String queryArticle(String type,String arg,int page) throws Exception {

        String res=null,sql=null;
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        PreparedStatement pres=null;
        try {

            if(type.equals(this.TIME)){
                sql="select * from t_article where t_article.time like ? order by t_article.time desc";
                pres=conn.prepareStatement(sql);
                pres.setString(1,arg+'%');
            }else if (type.equals(this.CLASSIFICATION)){
                sql="select * from t_article where t_article.classification =? order by t_article.time desc";
                pres=conn.prepareStatement(sql);
                pres.setString(1,arg);
            }else if (type.equals(this.ALL)){
                sql="select * from t_article order by t_article.time desc";
                pres=conn.prepareStatement(sql);
            }
            res=returnList(pres,page);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DataBaseManage.returnConnection(conn);
        }
        System.out.println(res);
        return res;
    }

    @Override
    public boolean alterArticle() throws Exception {
        return false;
    }

    @Override
    public String getArticleCount(){
        String res=null;
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        PreparedStatement pres=null;

        String sql="";
        String ret=null;
        return ret;
    }

    @Override
    public ArticleBean getArticleById(int id,String table)throws Exception {
        //查询byid
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        String sql=null;

        if(table.equals("article")){
            sql="select * from t_article where id=?";
        }

        PreparedStatement pres=pres=conn.prepareStatement(sql);
        pres.setInt(1,id);

        ResultSet resultSet=pres.executeQuery();
        ArticleBean articleBean=null;
        if (resultSet.next()){
           articleBean=new ArticleBean(resultSet.getString("title"),resultSet.getString("article"),
                    resultSet.getString("tag"),resultSet.getString("kind"),resultSet.getString("classification"),
                    false
            );
        }

        DataBaseManage.returnConnection(conn);
        return articleBean;
    }

    private String returnList(PreparedStatement pres, int page) throws Exception{
        ResultSet resultSet=null;
        int start=(page-1)*5+1,end=start+4;
        String res=null;
        try {
            resultSet=pres.executeQuery();//执行代码,拿到数据

            String title,article,time,id;
            res="{";
            boolean flag=false;
            int count=1;

            while (resultSet.next()){

                if(count<start){
                    count++;continue;
                }

                if(count>end){
                    break;
                }

                if(flag){
                    res+=',';
                }
                flag=true;
                id=resultSet.getString("id");
                title=resultSet.getString("title");//获取标题
                article=resultSet.getString("article");//获取文章概要
                time=resultSet.getString("time");//获取文章时间
                res+=("\""+id+"\""+":"+"["+ "\""+title+"\""+ "," +"\""+article+"\""+","+"\""+time+"\""+"]");
                count++;
            }
            if(flag){
                HttpServletResponse response= ServletActionContext.getResponse();
                Cookie cookie=new Cookie("last_page",page+"");
                response.addCookie(cookie);
            }
            if(res.length()!=1){
                res+=",";
            }
            if(resultSet!=null){
                resultSet.last();
            }
            res+="\"count\":"+"\""+resultSet.getRow()+"\"";
            res+="}";

        }catch (Exception e){
            resultSet.close();
            pres.close();
        }
        return res;
    }


}
