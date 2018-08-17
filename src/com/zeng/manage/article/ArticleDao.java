package com.zeng.manage.article;

public interface ArticleDao{
    //增加文章
    public boolean addArticle(ArticleBean articleBean)throws Exception;
    //删除文章
    public boolean delArticle(String id,String table)throws Exception;
    //查询文章列表
    public String queryArticle(String type,String arg,int page)throws Exception;
    //修改文章
    public boolean alterArticle(ArticleBean articleBean,boolean isDraft) throws Exception;

    public String getArticleCount()throws Exception;

    public ArticleBean getArticleById(String id,String table)throws Exception;

    public String queryDraft(int start)throws Exception;
}
