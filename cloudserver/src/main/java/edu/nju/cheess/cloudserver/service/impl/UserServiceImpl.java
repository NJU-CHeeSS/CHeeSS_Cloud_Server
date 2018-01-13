package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.PasswordBean;
import edu.nju.cheess.cloudserver.bean.ResultMessageBean;
import edu.nju.cheess.cloudserver.bean.UserInfoBean;
import edu.nju.cheess.cloudserver.bean.UserPasswordBean;
import edu.nju.cheess.cloudserver.dao.UserDao;
import edu.nju.cheess.cloudserver.entity.User;
import edu.nju.cheess.cloudserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResultMessageBean signIn(String username, String password) {

        UserPasswordBean user = userDao.getUserPasswordByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            return new ResultMessageBean(false, "用户名或密码错误");
        }

        return new ResultMessageBean(true);
    }

    @Override
    public ResultMessageBean signOut() {

        return new ResultMessageBean(true);
    }

    @Override
    public ResultMessageBean signUp(String username, String password) {

        UserInfoBean oldUser = userDao.getUserByName(username);
        if (oldUser != null) {
            return new ResultMessageBean(false, "用户已存在");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDao.addUser(user);
        return new ResultMessageBean(true);
    }

    @Override
    public ResultMessageBean resetPassword(PasswordBean passwordBean) {
        String username = getUserInfo().getUsername();
        if (passwordBean.newPassword.equals(passwordBean.oldPassword)) {
            return new ResultMessageBean(false, "新旧密码相同");
        } else if (!userDao.getUserPasswordByName(username).getPassword().equals(passwordBean.oldPassword)) {
            return new ResultMessageBean(false, "原密码错误");
        }

        userDao.updateUserPassword(username, passwordBean.newPassword);
        return new ResultMessageBean(true);
    }

    @Override
    public UserInfoBean getUserByName(String username) {

        return userDao.getUserByName(username);
    }

    @Override
    public ResultMessageBean editUserInfo(UserInfoBean user) {

        User newUser = new User();

        newUser.setId(newUser.getId());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(userDao.getUserPasswordByName(user.getUsername()).getPassword());
        newUser.setAge(user.getAge());
        newUser.setCity(user.getCity());
        newUser.setDiploma(user.getDiploma());
        newUser.setEmail(user.getEmail());
        newUser.setExperience(user.getExperience());
        newUser.setMajor(user.getMajor());
        newUser.setSex(user.getSex());
        newUser.setSkill(user.getSkill());
        newUser.setTelephone(user.getTelephone());

        userDao.updateUserInfo(newUser);
        return new ResultMessageBean(true);
    }

    @Override
    public UserInfoBean getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return userDao.getUserByName(auth.getName());
        }
        return null;
    }

    @Override
    public ResultMessageBean follow(Long userId, Long companyId) {
        userDao.followCompany(userId, companyId);
        return new ResultMessageBean(true);
    }

    @Override
    public ResultMessageBean cancelFollow(Long userId, Long companyId) {
        userDao.cancelFollowCompany(userId, companyId);
        return new ResultMessageBean(true);
    }

    @Override
    public boolean checkFollow(Long userId, Long companyId) {
        return userDao.isFollowCompany(userId, companyId);
    }
}
