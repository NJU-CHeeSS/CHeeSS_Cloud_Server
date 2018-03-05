package edu.nju.cheess.cloudserver.bean;

public class UserPasswordBean {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public UserPasswordBean() {}

    public UserPasswordBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
