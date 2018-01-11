package edu.nju.cheess.cloudserver.dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by CLL on 17/12/28.
 */
public class HBaseUtil {

    private static Configuration configuration;
    private static Connection conn;
    private static Admin admin;

    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "40.125.162.122,40.125.214.197,40.125.163.24");
        configuration.set("hbase.master", "40.125.162.122:16000");
        configuration.set("hbase.root.dir", "hdfs://40.125.162.122:9000/opt/hbase/database");
    }

    private static void init() {
        try {
            conn = ConnectionFactory.createConnection(configuration);
            admin = conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    private static void close() {
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
     * 创建表
     *
     * @param tableName 表名
     * @param cols      列族列表
     * @throws IOException
     */
    public static void createTable(String tableName, String[] cols) throws IOException {
        init();
        TableName tName = TableName.valueOf(tableName);
        if (admin.tableExists(tName)) {
            System.out.println(tableName + " exists.");
        } else {
            HTableDescriptor hTableDesc = new HTableDescriptor(tName);
            for (String col : cols) {
                HColumnDescriptor hColumnDesc = new HColumnDescriptor(col);
                hTableDesc.addFamily(hColumnDesc);
            }
            admin.createTable(hTableDesc);
        }

        close();
    }

    /**
     * 删除表
     *
     * @param tableName 表名称
     * @throws IOException
     */
    public static void deleteTable(String tableName) throws IOException {
        init();
        TableName tName = TableName.valueOf(tableName);
        if (admin.tableExists(tName)) {
            admin.disableTable(tName);
            admin.deleteTable(tName);
        } else {
            println(tableName + " not exists.");
        }
        close();
    }

    /**
     * 查看已有表
     *
     * @throws IOException
     */
    public static void listTables() {
        init();
        HTableDescriptor hTableDescriptors[] = null;
        try {
            hTableDescriptors = admin.listTables();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (HTableDescriptor hTableDescriptor : hTableDescriptors) {
            println(hTableDescriptor.getNameAsString());
        }
        close();
    }

    /**
     * 插入单行
     *
     * @param tableName 表名称
     * @param rowKey    RowKey
     * @param colFamily 列族
     * @param col       列
     * @param value     值
     * @throws IOException
     */
    public static void insert(String tableName, String rowKey, String colFamily, String col, String value) throws IOException {
        init();
        Table table = conn.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col), Bytes.toBytes(value));
        table.put(put);

        /*
         * 批量插入 List<Put> putList = new ArrayList<Put>(); puts.add(put); table.put(putList);
         */

        table.close();
        close();
    }

    public static void delete(String tableName, String rowKey, String colFamily, String col) throws IOException {
        init();

        if (!admin.tableExists(TableName.valueOf(tableName))) {
            println(tableName + " not exists.");
        } else {
            Table table = conn.getTable(TableName.valueOf(tableName));
            Delete del = new Delete(Bytes.toBytes(rowKey));
            if (colFamily != null) {
                del.addFamily(Bytes.toBytes(colFamily));
            }
            if (colFamily != null && col != null) {
                del.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col));
            }
            /*
             * 批量删除 List<Delete> deleteList = new ArrayList<Delete>(); deleteList.add(delete); table.delete(deleteList);
             */
            table.delete(del);
            table.close();
        }
        close();
    }

    /**
     * 根据RowKey获取数据
     *
     * @param tableName 表名称
     * @param rowKey    RowKey名称
     * @param colFamily 列族名称
     * @param col       列名称
     * @throws IOException
     */
    public static void getData(String tableName, String rowKey, String colFamily, String col) throws IOException {
        init();
        Table table = conn.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        if (colFamily != null) {
            get.addFamily(Bytes.toBytes(colFamily));
        }
        if (colFamily != null && col != null) {
            get.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col));
        }
        Result result = table.get(get);
        showCell(result);
        table.close();
        close();
    }

    /**
     * 根据RowKey获取信息
     *
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public static void getData(String tableName, String rowKey) throws IOException {
        getData(tableName, rowKey, null, null);
    }

    /**
     * 格式化输出
     *
     * @param result
     */
    public static void showCell(Result result) {
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            println("RowName:   " + new String(CellUtil.cloneRow(cell)) + " ");
            println("Timetamp:  " + cell.getTimestamp() + " ");
            println("Family:    " + new String(CellUtil.cloneFamily(cell)) + " ");
            println("Qualifier: " + new String(CellUtil.cloneQualifier(cell)) + " ");
            println("Value:     " + new String(CellUtil.cloneValue(cell)) + " ");
        }
    }

    /**
     * 打印
     *
     * @param obj 打印对象
     */
    private static void println(Object obj) {
        System.out.println(obj);
    }
}
