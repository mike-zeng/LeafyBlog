package com.zeng.manage.article;

public class ArticleInfoBean {
    int read,like,page;

    public ArticleInfoBean(int read, int like, int page) {
        this.read = read;
        this.like = like;
        this.page = page;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
