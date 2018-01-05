package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.PasswordBean;
import edu.nju.cheess.cloudserver.bean.ResultMessageBean;
import edu.nju.cheess.cloudserver.bean.UserInfoBean;
import edu.nju.cheess.cloudserver.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResultMessageBean signIn(String username, String password) {
        return null;
    }

    @Override
    public ResultMessageBean signOut() {
        return null;
    }

    @Override
    public ResultMessageBean signUp(String username, String password) {
        return null;
    }

    @Override
    public ResultMessageBean resetPassword(PasswordBean passwordBean) {
        return null;
    }

    @Override
    public UserInfoBean getUserByName(Long username) {
        return null;
    }

    @Override
    public ResultMessageBean editUserInfo(UserInfoBean user) {
        return null;
    }

    @Override
    public UserInfoBean getUserInfo() {
        return null;
    }
}
