package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.service.SparkService;
import edu.nju.cheess.cloudserver.util.ApplicationConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Service
public class SparkServiceImpl implements SparkService, Serializable {

    private final ApplicationConfiguration appConf;

    @Autowired
    public SparkServiceImpl(ApplicationConfiguration appConf) {
        this.appConf = appConf;
    }

    public List<String> getCompanyType(String table, String column) {
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes("info"));
        scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes(column));

        try {
            JavaPairRDD<ImmutableBytesWritable, Result> rdd = appConf
                    .javaSparkContext()
                    .newAPIHadoopRDD(appConf.hBaseConfiguration(table, scan),
                    TableInputFormat.class, ImmutableBytesWritable.class, Result.class);
//            JavaRDD<String> types = rdd
//                    .map(tuple -> Bytes.toString(tuple._2.getValue(Bytes.toBytes("info"), Bytes.toBytes(column))));

            JavaRDD<String> types = rdd.map(new Function<Tuple2<ImmutableBytesWritable,Result>, String>() {
                @Override
                public String call(Tuple2<ImmutableBytesWritable, Result> tuple) {
                    return Bytes.toString(tuple._2.getValue(Bytes.toBytes("info"), Bytes.toBytes(column)));
                }
            });

            return types.distinct().collect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
