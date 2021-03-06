package com.zeng.manage.article;



public class ArticleBean {

    String title,article,tag,kind,classification;
    boolean draft;
    int id;

    int reading,comments,like;
    public ArticleBean(String title, String article, String tag, String kind, String classification, boolean draft) {
        this.title = title;
        this.article = article;
        this.tag = tag;
        this.kind = kind;
        this.classification = classification;
        this.draft = draft;
    }

    public ArticleBean(){

    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setDraft(boolean draft) {
      this.draft=draft;
    }

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public String getTag() {
        return tag;
    }

    public String getKind() {
        return kind;
    }

    public String getClassification() {
        return classification;
    }

    public boolean getDraft() {
        return draft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean isDraft() {
        return draft;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "["+title+","+article+","+tag+","+kind+","+classification+"]";
    }
//   判断数据是否完整，展示没考虑是否合法，不要相信任何来自客户端的消息
    public boolean isComplete(){
        return (title!=null&&title.length()!=0)&&(article!=null&&article.length()!=0)
                &&(tag!=null&&tag.length()!=0)&&(kind!=null&& kind.length()!=0)
                &&(classification!=null&&classification.length()!=0);
    }

}
