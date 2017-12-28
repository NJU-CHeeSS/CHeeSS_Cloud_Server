package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.PasswordBean;
import edu.nju.cheess.cloudserver.entity.User;
import edu.nju.cheess.cloudserver.service.UserService;
import edu.nju.cheess.cloudserver.util.ResultMessage;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResultMessage signIn(String username, String password) {
        return null;
    }

    @Override
    public ResultMessage signOut() {
        return null;
    }

    @Override
    public ResultMessage signUp(String username, String password) {
        return null;
    }

    @Override
    public ResultMessage resetPassword(PasswordBean passwordBean) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public ResultMessage editUserInfo(User user) {
        return null;
    }

    @Override
    public User getUserInfo() {
        return null;
    }
}
