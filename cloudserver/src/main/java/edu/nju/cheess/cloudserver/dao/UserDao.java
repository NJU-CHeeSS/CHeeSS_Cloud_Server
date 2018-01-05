package edu.nju.cheess.cloudserver.dao;

import edu.nju.cheess.cloudserver.bean.UserInfoBean;
import edu.nju.cheess.cloudserver.entity.User;

public interface UserDao {
    /**
     * 返回用户完整信息
     * @param username
     * @return
     */
    UserInfoBean getUserByName(String username);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUserInfo(User user);

    /**
     * 修改密码
     * @param username
     * @param password
     */
    void updateUserPassword(String username,String password);

    /**
     * 新增用户
     * @param user
     * @return
     */
    Long addUser(User user);

    /**
     * 关注企业
     * @param username
     * @param companyID
     */
    void followCompany(String username,String companyID);

}
