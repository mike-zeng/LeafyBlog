package com.zeng.manage.comment;

public class CommentBean {
    private String name,content,time,article;

    //评论的文章
    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    //评论者的名称，可以为空
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //评论的内容
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //评论的时间
    public String getTime() {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }


}
