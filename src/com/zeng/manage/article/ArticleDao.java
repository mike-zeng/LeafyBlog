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
    //获取文章数量
    public int getArticleCount()throws Exception;
    //通过名字查询某一篇文章
    public ArticleBean getArticleById(String id,String table)throws Exception;
    //获取草稿
    public String queryDraft(int start)throws Exception;
}
