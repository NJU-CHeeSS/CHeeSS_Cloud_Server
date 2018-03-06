package edu.nju.cheess.cloudserver.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

@Component
public class ApplicationConfiguration implements Serializable {

    private final transient JavaSparkContext JAVA_SPARK_CONTEXT;

    public ApplicationConfiguration() {
        SparkConf SPARK_CONF = new SparkConf()
                .setAppName("cloud")
                .setMaster("spark://chenlili1:7077")
                .setJars(new String[]{"/root/cloudserver-0.0.1-SNAPSHOT.jar"});

        JAVA_SPARK_CONTEXT = new JavaSparkContext(SPARK_CONF);
    }

    public JavaSparkContext javaSparkContext() {
        return JAVA_SPARK_CONTEXT;
    }


    public Configuration hBaseConfiguration(String tableName, Scan scan) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "chenlili1,chenlili2,chenlili4");
        configuration.set("hbase.master", "chenlili1:16000");
        configuration.set("hbase.root.dir", "hdfs://chenlili1:9000/mnt/resource/hbase/data");

        configuration.set(TableInputFormat.INPUT_TABLE, tableName);
        configuration.set(TableInputFormat.SCAN, Base64.encodeBytes(ProtobufUtil.toScan(scan).toByteArray()));
        return configuration;
    }

}
