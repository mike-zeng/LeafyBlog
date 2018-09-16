package com.zeng.manage.article;

import com.zeng.database.DataBaseManage;
import org.apache.struts2.ServletActionContext;

import javax.print.attribute.standard.PresentationDirection;
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
            //判断是草稿还是正式文章
            if(articleBean.getDraft()){
                sql="insert into t_draft(title,article,tag,kind,classification) values(?,?,?,?,?)";
            }else{
                sql="insert into t_article(title,article,tag,kind,classification) values(?,?,?,?,?)";
            }
            //万恶的jdbc代码....
            Connection conn= DataBaseManage.getConnection();
            PreparedStatement prep=conn.prepareStatement(sql);
            prep.setString(1,articleBean.getTitle());
            prep.setString(2,articleBean.getArticle());
            prep.setString(3,articleBean.getTag());
            prep.setString(4,articleBean.getKind());
            prep.setString(5,articleBean.getClassification());
            //写入数据库
            int ret=prep.executeUpdate();
            prep.close();
            conn.close();
            if(ret==0){
                return false;
            }
        }else{
            return false;
        }
        return true;
    }

    @Override
    public boolean delArticle(String id,String table) throws Exception {
        String sql=null;
        if (table.equals("t_article")){
            sql="delete from t_article where id=?;";
        }else if (table.equals("t_draft")){
            sql="delete from t_draft where id=?;";
        }else{
            return false;
        }

        Connection conn=DataBaseManage.getConnection();
        PreparedStatement pres=conn.prepareStatement(sql);

        pres.setString(1,id);
        int ret=pres.executeUpdate();
        pres.close();
        conn.close();

        if(ret==0){
            return false;
        }
        return true;
    }

    @Override
    public String queryArticle(String type,String arg,int page) throws Exception {

        String res=null,sql=null;

        Connection conn=DataBaseManage.getConnection();
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
            conn.close();
            pres.close();
        }
        return res;
    }

    @Override
    public boolean alterArticle(ArticleBean articleBean,boolean isDraft) throws Exception {
        //articleBean表示修改过后的文章
        DataBaseManage dataBaseManage=new DataBaseManage();
        Connection conn=dataBaseManage.getConnection();

        String sql=null;
        if(isDraft){
            sql="update t_draft set title=?,article=?,tag=?,kind=?,classification=? where id=?;";
        }else {
            sql="update t_article set title=?,article=?,tag=?,kind=?,classification=? where id=?;";
        }
        PreparedStatement pres=conn.prepareStatement(sql);
        pres.setString(1,articleBean.getTitle());
        pres.setString(2,articleBean.getArticle());
        pres.setString(3,articleBean.getTag());
        pres.setString(4,articleBean.getKind());
        pres.setString(5,articleBean.getClassification());
        pres.setInt(6,articleBean.getId());
        int ret=pres.executeUpdate();
        if(ret==0){
            return false;
        }
        return true;
    }

    @Override
    public int getArticleCount() throws Exception{
        String res=null;
        DataBaseManage dbm=new DataBaseManage();
        Connection conn=dbm.getConnection();
        PreparedStatement pres=null;

        String sql="select count(*) from t_article";
        pres=conn.prepareStatement(sql);
        ResultSet resultSet=pres.executeQuery();
        int ret=0;
        if (resultSet.next()){
            ret=resultSet.getInt(0);
        }
        return ret;
    }

    @Override
    public ArticleBean getArticleById(String id,String table)throws Exception {
        //查询byid
        Connection conn=DataBaseManage.getConnection();
        String sql=null;
        //出现未知bug-------
        if(table.equals("article")){
            sql="select * from t_article where id=?";
        }else if (table.equals("draft")){
            sql="select * from t_draft where id=?";
        }else{
            return null;
        }
        PreparedStatement pres=conn.prepareStatement(sql);
        pres.setString(1,id);

        ResultSet resultSet=pres.executeQuery();
        ArticleBean articleBean=null;
        if (resultSet.next()){
           articleBean=new ArticleBean(resultSet.getString("title"),resultSet.getString("article"),
                    resultSet.getString("tag"),resultSet.getString("kind"),resultSet.getString("classification"),
                    false
            );
        }else{
            return null;
        }
        resultSet.close();
        pres.close();
        conn.close();
        return articleBean;
    }

    @Override
    public String queryDraft(int start) throws Exception {

        Connection conn=DataBaseManage.getConnection();

        String sql="select * from t_draft";
        PreparedStatement pres=conn.prepareStatement(sql);
        ResultSet resultSet=pres.executeQuery();
        int count=1,end=start+4;
        boolean flag=false;
        String res="{",id,title,time,article;

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
        resultSet.close();
        pres.close();
        conn.close();
        res+="}";
        return res;
    }

    @Override
    public void addRead(String id) throws Exception {
        String sql="update t_article set reading=reading+1 where id=?";
        Connection conn=DataBaseManage.getConnection();
        PreparedStatement prep=conn.prepareStatement(sql);
        prep.setString(1,id);
        prep.executeUpdate();
    }

    @Override
    public void addLike(String id)throws Exception{
        String sql="update t_article set like=like+1 where id=?";
        Connection conn=DataBaseManage.getConnection();
        PreparedStatement prep=conn.prepareStatement(sql);
        prep.setString(1,id);
        prep.executeUpdate();
    }

    private String returnList(PreparedStatement pres, int page) throws Exception{
        ResultSet resultSet=null;
        int start=(page-1)*5+1,end=start+4;
        String res=null;
        try {
            resultSet=pres.executeQuery();//执行代码,拿到数据

            String title,article,time,id;
            int like=0,comments=0,reading=0;
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
                comments=resultSet.getInt("comments");
                reading=resultSet.getInt("reading");
                like=resultSet.getInt("like");
                res+=("\""+id+"\""+":"+"["+ "\""+title+"\""+ "," +"\""+article+"\""+","+"\""+time+"\""+ "," +"\""+reading+"\""+ "," +"\""+comments+"\""+ "," +"\""+like+"\""+"]");
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
