package edu.nju.cheess.cloudserver.dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        configuration.set("hbase.zookeeper.quorum", "40.125.167.161,40.125.212.93,40.125.215.227");
        configuration.set("hbase.master", "40.125.167.161:16000");
        configuration.set("hbase.root.dir", "hdfs://40.125.167.161:9000/mnt/resource/hbase/data");
    }

    public synchronized void init() {
        try {
            conn = ConnectionFactory.createConnection(configuration);
            admin = conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void close() {
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

    private Map<String, String> resultToMap(Result result) {
        Map<String, String> map = new HashMap<>();
        for (Cell cell : result.rawCells()) {
            map.put("rowKey", new String(CellUtil.cloneRow(cell)));
            map.put(new String(CellUtil.cloneQualifier(cell)), new String(CellUtil.cloneValue(cell)));
        }
        return map;
    }

    private List<Map<String, String>> resultScannerToMapList(ResultScanner scanner) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Result result : scanner) {
            mapList.add(resultToMap(result));
        }
        return mapList;
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
        Map<String, String> data = new HashMap<>();
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
            data = resultToMap(result);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
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

    /**
     * 分页查询
     *
     * @param tableName     表名
     * @param pageSize      页大小
     * @param pageNumber    页数
     * @return              Result
     */
    public List<Map<String, String>> getDataByPage(String tableName, int pageSize, int pageNumber) {
        List<Map<String, String>> mapList = new ArrayList<>();
        byte[] POSTFIX = new byte[]{ 0x00 };
        byte[] lastRow = null;

        Filter filter = new PageFilter(pageSize);
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            for (int i = 1; i <= pageNumber; i++) {
                Scan scan = new Scan();
                scan.setFilter(filter);
                if (lastRow != null) {
                    byte[] startRow = Bytes.add(lastRow, POSTFIX);
                    scan.setStartRow(startRow);
                }
                ResultScanner scanner = table.getScanner(scan);
                if (i != pageNumber) {
                    // 未到达最后一页，记录本次Scan的lastRow
                    int localRows = 0;
                    for (Result result : scanner) {
                        localRows++;
                        lastRow = result.getRow();
                    }
                    // pageNumber超出总页数
                    if (localRows < pageSize) {
                        scanner.close();
                        break;
                    }
                } else {
                    // 获取此页信息
                    mapList = resultScannerToMapList(scanner);
                }
                scanner.close();
            }
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapList;
    }

    /**
     * 列值查询（采用子串匹配比较器）
     * @param tableName     表名
     * @param columnFamily  列族
     * @param column        列名
     * @param value         值
     * @return              Result
     */
    public List<Map<String, String>> getDataByColumnValue(String tableName, String columnFamily, String column, String value) {
        List<Map<String, String>> mapList = new ArrayList<>();
        try {
            Table table = conn.getTable(TableName.valueOf(tableName));
            // 设置SingleColumnValueFilter
            Filter filter = new SingleColumnValueFilter(
                    Bytes.toBytes(columnFamily),
                    Bytes.toBytes(column),
                    CompareFilter.CompareOp.EQUAL,
                    new SubstringComparator(value));
            Scan scan = new Scan();
            scan.setFilter(filter);
            ResultScanner scanner = table.getScanner(scan);
            mapList = resultScannerToMapList(scanner);
            scanner.close();
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapList;
    }

}
