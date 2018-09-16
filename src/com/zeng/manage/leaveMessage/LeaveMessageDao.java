package com.zeng.manage.leaveMessage;

public interface LeaveMessageDao {
    public String getLeaveMessageList(int num)throws Exception;
    public boolean addLeaveMessage(String user,String content)throws Exception;
    public boolean delLeaveMessage(String id)throws Exception;
}
