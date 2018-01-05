package edu.nju.cheess.cloudserver.dao.impl;

import edu.nju.cheess.cloudserver.bean.UserInfoBean;
import edu.nju.cheess.cloudserver.dao.UserDao;
import edu.nju.cheess.cloudserver.entity.User;
import edu.nju.cheess.cloudserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CLL on 18/1/5.
 */
@Service
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserInfoBean getUserByName(String userName) {
        User user=userRepository.findByUsername(userName);
        //TODO
        return null;
    }

    @Override
    public void updateUserInfo(User user) {
        userRepository.updateUserInfo(user.getUsername(),user.getSex(),user.getCity(),
                user.getAge(),user.getMajor(),user.getDiploma(),user.getSkill(),user.getExperience());
    }

    @Override
    public void updateUserPassword(String username, String password) {
        userRepository.updateUserPassword(username,password);
    }

    @Override
    public Long addUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public void followCompany(String username, String companyID) {
        //TODO
    }
}
