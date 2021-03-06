package edu.nju.cheess.cloudserver.repository;

import edu.nju.cheess.cloudserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by CLL on 18/1/5.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * 查询用户信息
     * @param name
     * @return
     */
    User findByUsername(String name);

    /**
     * 设置密码
     * @param userName
     * @param password
     */
    @Transactional
    @Modifying
    @Query("update User u set u.password=?2 where u.username=?1")
    void updateUserPassword(String userName,String password);

    /**
     * 更新信息
     * @param userName
     * @param sex
     * @param city
     * @param age
     * @param major
     * @param diploma
     * @param skill
     * @param experience
     */
    @Transactional
    @Modifying
    @Query("update User u set u.sex=?2,u.city=?3,u.age=?4,u.major=?5," +
            "u.diploma=?6,u.skill=?7,u.email=?8,u.telephone=?9,u.experience=?10 where u.username=?1")
    void updateUserInfo(String userName, Integer sex, String city, int age, String major, String diploma, String skill, String email,String telephone,double experience);

    //新增用户调用save方法
}
