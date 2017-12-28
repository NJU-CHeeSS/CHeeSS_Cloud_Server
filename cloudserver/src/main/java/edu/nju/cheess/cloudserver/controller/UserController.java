package edu.nju.cheess.cloudserver.controller;

import edu.nju.cheess.cloudserver.bean.PasswordBean;
import edu.nju.cheess.cloudserver.bean.ResultMessageBean;
import edu.nju.cheess.cloudserver.entity.User;
import edu.nju.cheess.cloudserver.service.UserService;
import edu.nju.cheess.cloudserver.util.ResultMessage;
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
            @RequestBody User user) {
        ResultMessage resultMessage = userService.signUp(user.getUsername(), user.getPassword());
        ResultMessageBean result = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            result.result = true;
        } else if (resultMessage == ResultMessage.EXIST) {
            result.message = "该用户名已存在";
        }
        return result;
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
            @RequestBody User user) {
        ResultMessage resultMessage = userService.signIn(user.getUsername(), user.getPassword());
        ResultMessageBean result = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            result.result = true;
        } else if (resultMessage == ResultMessage.FAILED) {
            result.message = "密码错误";
        }
        return result;
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
        return new ResultMessageBean(true);
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
    public User getUserInfo() {
        return userService.getUserInfo();
    }

    /**
     * 修改密码
     *
     * @return 是否修改成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/password",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean editPassword(@RequestBody PasswordBean password) {
        ResultMessage resultMessage = userService.resetPassword(password);
        boolean result = resultMessage == ResultMessage.SUCCESS;
        String message = "";
        if (!result) {
            message = "修改失败";
        }
        return new ResultMessageBean(result, message);
    }

    /**
     * 修改个人信息
     *
     * @return 是否修改成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/info",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean editUserInfo(@RequestBody User user) {
        ResultMessage resultMessage = userService.editUserInfo(user);
        boolean result = resultMessage == ResultMessage.SUCCESS;
        String message = "";
        if (!result) {
            message = "修改失败";
        }
        return new ResultMessageBean(result, message);
    }

}
