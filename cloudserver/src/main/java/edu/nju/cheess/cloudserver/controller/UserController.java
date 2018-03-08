package edu.nju.cheess.cloudserver.controller;

import edu.nju.cheess.cloudserver.bean.*;
import edu.nju.cheess.cloudserver.service.CompanyService;
import edu.nju.cheess.cloudserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

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
            params = {"companyId"},
            method = RequestMethod.GET)
    public ResultMessageBean follow(@RequestParam(value = "companyId") Long companyId) {

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
            params = {"companyId"},
            method = RequestMethod.GET)
    public ResultMessageBean cancelFollow(@RequestParam(value = "companyId") Long companyId) {

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
            params = {"companyId"},
            method = RequestMethod.GET)
    public boolean checkFollow(@RequestParam(value = "companyId") Long companyId) {

        return userService.checkFollow(getUserInfo().getUserId(), companyId);
    }

    /**
     * 获取用户关注企业
     *
     * @return 企业列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/companies",
            method = RequestMethod.GET)
    public List<CompanyInfoBean> getFollowCompanies() {

        return companyService.getFollowCompanies(getUserInfo().getUserId());
    }

    /**
     * 申请职位
     *
     * @param jobId 职位id
     * @return 关注结果
     */
    @ResponseBody
    @RequestMapping(
            value = "/apply",
            params = {"jobId"},
            method = RequestMethod.GET)
    public ResultMessageBean apply(@RequestParam(value = "jobId") Long jobId) {

        return userService.apply(getUserInfo().getUserId(), jobId);
    }

    /**
     * 获得是否申请职位
     *
     * @param jobId 职位id
     * @return 是否关注企业
     */
    @ResponseBody
    @RequestMapping(
            value = "/checkApply",
            params = {"companyId"},
            method = RequestMethod.GET)
    public boolean checkApply(@RequestParam(value = "jobId") Long jobId) {

        return userService.checkApply(getUserInfo().getUserId(), jobId);
    }

}
