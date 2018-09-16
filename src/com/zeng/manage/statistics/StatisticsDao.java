package com.zeng.manage.statistics;

public interface StatisticsDao {
    void addReading(String id) throws Exception;
    void addLikes(String id) throws Exception;
    int pageCount()throws Exception;
    int readCount()throws Exception;
    int likeCount()throws Exception;
}
