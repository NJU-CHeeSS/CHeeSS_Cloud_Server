package edu.nju.cheess.cloudserver.dao.impl;

import edu.nju.cheess.cloudserver.bean.UserInfoBean;
import edu.nju.cheess.cloudserver.bean.UserPasswordBean;
import edu.nju.cheess.cloudserver.dao.CompanyDao;
import edu.nju.cheess.cloudserver.dao.UserDao;
import edu.nju.cheess.cloudserver.entity.Company;
import edu.nju.cheess.cloudserver.entity.FollowCompany;
import edu.nju.cheess.cloudserver.entity.User;
import edu.nju.cheess.cloudserver.repository.FollowCompanyRepository;
import edu.nju.cheess.cloudserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CLL on 18/1/5.
 */
@Service
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowCompanyRepository followCompanyRepository;
    @Autowired
    CompanyDao companyDao;

    @Override
    public UserInfoBean getUserByName(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return null;
        }
        List<Integer> companyIDs = followCompanyRepository.findCompanyIDs(user.getId());
        System.out.println(companyIDs.size());
        List<Company> followCompanies=new ArrayList<>();
        for (long companyId:companyIDs) {
            followCompanies.add(companyDao.getCompanyById(companyId));
        }
        //hbase中取数据，暂时为null
        return new UserInfoBean(user.getId(), user.getUsername(), user.getSex(), user.getCity(),
                user.getAge(), user.getMajor(), user.getDiploma(), user.getSkill(), user.getExperience(), followCompanies);
    }

    @Override
    public UserPasswordBean getUserPasswordByName(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return new UserPasswordBean(username, user.getPassword());
    }

    @Override
    public void updateUserInfo(User user) {
        userRepository.updateUserInfo(user.getUsername(), user.getSex(), user.getCity(),
                user.getAge(), user.getMajor(), user.getDiploma(), user.getSkill(),user.getEmail(), user.getTelephone(),user.getExperience());
    }

    @Override
    public void updateUserPassword(String username, String password) {
        userRepository.updateUserPassword(username, password);
    }

    @Override
    public Long addUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public void followCompany(Long userId, Long companyID) {
        followCompanyRepository.save(new FollowCompany(userId, companyID.intValue()));
    }

    @Override
    public void cancelFollowCompany(Long userId, Long companyID) {
        followCompanyRepository.delete(new FollowCompany(userId, companyID.intValue()));
    }

    @Override
    public boolean isFollowCompany(Long userId, Long companyID) {
        List<Integer> companyIDs = followCompanyRepository.findCompanyIDs(userId);
        return companyIDs.contains(companyID.intValue());
    }
}
