package com.zeng.manage.leaveMessage;

public interface LeaveMessageDao {
    public String getLeaveMessageList(int num)throws Exception;
    public boolean addLeaveMessage()throws Exception;
    public boolean delLeaveMessage()throws Exception;
}
