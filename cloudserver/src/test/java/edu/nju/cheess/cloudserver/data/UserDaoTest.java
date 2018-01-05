package edu.nju.cheess.cloudserver.data;

import edu.nju.cheess.cloudserver.dao.UserDao;
import edu.nju.cheess.cloudserver.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by CLL on 18/1/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void findUserByNameTest(){
        User user=userDao.findByUsername("chen");
        System.out.println(user.getAge());
    }

    @Test
    public void registerTest(){
        userDao.save(new User("wang","123456"));
    }

    @Test
    public void updatePasswordTest(){
        userDao.updateUserPassword("wang","12345678");
    }

    @Test
    public void updateUserInfoTest(){
        userDao.updateUserInfo("wang",1,"南京",21,"软件工程","本科","java",1);
    }


}
