package edu.nju.cheess.cloudserver;

import edu.nju.cheess.cloudserver.dao.HBaseUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by CLL on 17/12/28.
 */
public class HBaseTest {

    @Test
    public void testGetData() throws IOException {
        HBaseUtil.listTables();
    }
    
}
