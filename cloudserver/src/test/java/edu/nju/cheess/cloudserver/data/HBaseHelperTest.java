package edu.nju.cheess.cloudserver.data;

import edu.nju.cheess.cloudserver.dao.HBaseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HBaseHelperTest {

    @Autowired
    public HBaseHelper hBaseHelper;

    @Test
    public void testPage() {
        hBaseHelper.getDataByPage("cloud:company", 10, 2);
    }

}
