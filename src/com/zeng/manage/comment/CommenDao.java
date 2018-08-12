package com.zeng.manage.comment;

public interface CommenDao {
    public String getComment(String id)throws Exception;
    public String getCommentList(int page)throws Exception;
    public String setComment(CommentBean commentBean)throws Exception;
    public String delComment(String id)throws Exception;
}
