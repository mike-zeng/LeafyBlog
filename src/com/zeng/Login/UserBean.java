package com.zeng.Login;

public class UserBean {
    private String name=new String();
    private String password=new String();

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        //密码使用加密算法先加密--------
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
