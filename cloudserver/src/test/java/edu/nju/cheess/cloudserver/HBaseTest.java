package edu.nju.cheess.cloudserver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by CLL on 17/12/28.
 */
public class HBaseTest {

    private Configuration configuration;
    private Connection conn;
    private Admin admin;

    @Before
    public void init() throws IOException {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "40.125.162.122,40.125.214.197,40.125.163.24");
        configuration.set("hbase.master", "40.125.162.122:16000");
        configuration.set("hbase.root.dir", "hdfs://40.125.162.122:9000/opt/hbase/database");

        conn = ConnectionFactory.createConnection(configuration);
        admin = conn.getAdmin();
    }

    @After
    public void close() throws IOException {
        admin.close();
        conn.close();
    }

    @Test
    public void testGetDataByPage() {
        byte[] POSTFIX = new byte[]{ 0x00 };

        try {
            int totalRows = 0;
            byte[] lastRow = null;
            Filter filter = new PageFilter(10);
            Table table = conn.getTable(TableName.valueOf("cloud:company"));

            while (true) {
                Scan scan = new Scan();
                scan.setFilter(filter);
                if (lastRow != null) {
                    byte[] startRow = Bytes.add(lastRow, POSTFIX);
                    System.out.println("start row: " + Bytes.toStringBinary(startRow));
                    scan.setStartRow(startRow);
                }
                ResultScanner scanner = table.getScanner(scan);
                int localRows = 0;
                for (Result result : scanner) {
                    System.out.println(new String(CellUtil.cloneRow(result.rawCells()[0])));
                    localRows++;
                    totalRows++;
                    lastRow = result.getRow();
                }
                scanner.close();
                if (localRows == 0) {
                    break;
                }
            }
            System.out.println("total row: " + totalRows);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
