package edu.nju.cheess.cloudserver.dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class HBaseHelper {

//    private static final String NAMESPACE = "cloud";

    private Configuration configuration;
    private Connection conn;
    private Admin admin;

    public HBaseHelper() {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "40.125.162.122,40.125.214.197,40.125.163.24");
        configuration.set("hbase.master", "40.125.162.122:16000");
        configuration.set("hbase.root.dir", "hdfs://40.125.162.122:9000/opt/hbase/database");
    }

    public void init() {
        try {
            conn = ConnectionFactory.createConnection(configuration);
            admin = conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (null != admin) {
                admin.close();
            }
            if (null != conn) {
                conn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据RowKey获取数据
     *
     * @param tableName 表名
     * @param rowKey    rowKey名
     * @param colFamily 列族名
     * @param col       列名
     * @return          Result
     */
    public Map<String, String> getData(String tableName, String rowKey, String colFamily, String col) {
        Map<String, String> dataMap = new HashMap<>();
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowKey));
            if (colFamily != null) {
                get.addFamily(Bytes.toBytes(colFamily));
            }
            if (colFamily != null && col != null) {
                get.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col));
            }
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                dataMap.put("rowKey", new String(CellUtil.cloneRow(cell)));
                dataMap.put(new String(CellUtil.cloneQualifier(cell)), new String(CellUtil.cloneValue(cell)));
            }
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    /**
     * 根据RowKey获取信息
     *
     * @param tableName     表名
     * @param rowKey        rowKey
     * @return              Result
     */
    public Map<String, String> getData(String tableName, String rowKey) {
        return getData(tableName, rowKey, null, null);
    }


}
