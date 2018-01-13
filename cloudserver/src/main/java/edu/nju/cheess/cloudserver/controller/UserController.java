package edu.nju.cheess.cloudserver.controller;

import edu.nju.cheess.cloudserver.bean.PasswordBean;
import edu.nju.cheess.cloudserver.bean.ResultMessageBean;
import edu.nju.cheess.cloudserver.bean.UserInfoBean;
import edu.nju.cheess.cloudserver.bean.UserPasswordBean;
import edu.nju.cheess.cloudserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param user 用户名和密码
     * @return 是否注册成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/sign-up",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean signUp(
            @RequestBody UserPasswordBean user) {

        return userService.signUp(user.getUsername(), user.getPassword());
    }

    /**
     * 登录
     *
     * @param user 用户名和密码
     * @return 是否登录成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/sign-in",
            method = RequestMethod.POST)
    public ResultMessageBean signIn(
            @RequestBody UserPasswordBean user) {

        return userService.signIn(user.getUsername(), user.getPassword());
    }

    /**
     * 注销
     *
     * @return 是否注销成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/sign-out",
            method = RequestMethod.POST
    )
    public ResultMessageBean signOut() {
        return userService.signOut();
    }

    /**
     * 当前登录用户
     *
     * @return 当前用户信息
     */
    @ResponseBody
    @RequestMapping(
            value = "",
            method = RequestMethod.GET)
    public UserInfoBean getUserInfo() {
        return userService.getUserInfo();
    }


    /**
     * 修改密码
     *
     * @param password 新旧密码
     * @return 是否修改成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/password",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean editPassword(@RequestBody PasswordBean password) {

        return userService.resetPassword(password);
    }

    /**
     * 修改个人信息
     *
     * @param user 用户信息
     * @return 是否修改成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/info",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean editUserInfo(@RequestBody UserInfoBean user) {

        return userService.editUserInfo(user);
    }

    /**
     * 关注企业
     *
     * @param companyId 企业id
     * @return 关注结果
     */
    @ResponseBody
    @RequestMapping(
            value = "/follow",
            method = RequestMethod.GET)
    public ResultMessageBean follow(Long companyId) {

        return userService.follow(getUserInfo().getUserId(), companyId);
    }

    /**
     * 取关企业
     *
     * @param companyId 企业id
     * @return 取关结果
     */
    @ResponseBody
    @RequestMapping(
            value = "/unfollow",
            method = RequestMethod.GET)
    public ResultMessageBean cancelFollow(Long companyId) {

        return userService.cancelFollow(getUserInfo().getUserId(), companyId);
    }

    /**
     * 获得是否关注企业
     *
     * @param companyId 企业id
     * @return 是否关注企业
     */
    @ResponseBody
    @RequestMapping(
            value = "/checkFollow",
            method = RequestMethod.GET)
    public boolean checkFollow(Long companyId) {

        return userService.checkFollow(getUserInfo().getUserId(), companyId);
    }
}
