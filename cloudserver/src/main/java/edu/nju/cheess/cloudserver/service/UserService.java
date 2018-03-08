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
     * @param username 用户账号
     * @return 查到的用户
     */
    UserInfoBean getUserByName(String username);

    /**
     * 更新用户信息
     *
     * @param user 新用户
     * @return 是否更新成功
     */
    ResultMessageBean editUserInfo(UserInfoBean user);

    /**
     * 获得当前用户
     *
     * @return 当前用户信息
     */
    UserInfoBean getUserInfo();

    /**
     * 关注企业
     *
     * @param userId    用户
     * @param companyId 企业id
     * @return 关注结果
     */
    ResultMessageBean follow(Long userId, Long companyId);

    /**
     * 取关企业
     *
     * @param userId    用户
     * @param companyId 企业id
     * @return 取关结果
     */
    ResultMessageBean cancelFollow(Long userId, Long companyId);

    /**
     * 获得是否关注企业
     *
     * @param userId    用户
     * @param companyId 企业id
     * @return 是否关注企业
     */
    boolean checkFollow(Long userId, Long companyId);

    /**
     * 申请岗位
     *
     * @param userId 用户id
     * @param jobId  职位id
     * @return 申请结果
     */
    ResultMessageBean apply(Long userId, Long jobId);

    /**
     * 获得是否申请岗位
     *
     * @param userId 用户
     * @param jobId  职位id
     * @return 是否申请岗位
     */
    boolean checkApply(Long userId, Long jobId);

}
