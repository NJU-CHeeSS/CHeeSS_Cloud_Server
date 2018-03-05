package edu.nju.cheess.cloudserver.bean;

public class PasswordBean {

    /**
     * 旧密码
     */
    public String oldPassword;

    /**
     * 新密码
     */
    public String newPassword;

    public PasswordBean() {
    }

    public PasswordBean(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
