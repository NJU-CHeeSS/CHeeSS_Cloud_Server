package edu.nju.cheess.cloudserver.service;

import edu.nju.cheess.cloudserver.bean.PasswordBean;
import edu.nju.cheess.cloudserver.bean.ResultMessageBean;
import edu.nju.cheess.cloudserver.bean.UserInfoBean;

public interface UserService {

    /**
     * 登录
     *
     * @param username 用户账号
     * @param password 用户密码
     * @return 当前登录状态
     */
    ResultMessageBean signIn(String username, String password);

    /**
     * 登出
     *
     * @return 当前登录状态
     */
    ResultMessageBean signOut();

    /**
     * 注册用户
     *
     * @param username 用户账号
     * @param password 用户密码
     * @return 是否增加成功
     */
    ResultMessageBean signUp(String username, String password);

    /**
     * 重置密码
     *
     * @param passwordBean 用户新旧密码
     * @return 重置密码结果状态
     */
    ResultMessageBean resetPassword(PasswordBean passwordBean);

    /**
     * 根据username查找用户
     *
     * @param userId 用户id
     * @return 查到的用户
     */
    UserInfoBean getUserById(Long userId);

    /**
     * 更新用户信息
     *
     * @param user 新用户
     * @return 是否更新成功
     */
    ResultMessageBean editUserInfo(UserInfoBean user);

    /**
     * 获得当前登录的用户
     *
     * @return 当前登录用户
     */
    UserInfoBean getUserInfo();
}
