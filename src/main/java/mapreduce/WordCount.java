package mapreduce;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

import com.hadoop.mapreduce.LzoTextInputFormat;


ystem.setProperty("hadoop.home.dir", "D:\\hadoop_2.8.5");
System.getProperties().put("HADOOP_USER_NAME", "webuser");
Configuration conf = new Configuration();
conf.addResource("classpath:/hadoop/core-site.xml");
conf.addResource("classpath:/had


public class WordCount {
	public static void main(String[] args) throws Exception {
		
		System.setProperty("hadoop.home.dir", "D:\\hadoop_2.8.5");
		System.getProperties().put("HADOOP_USER_NAME", "webuser");
		Configuration conf = new Configuration();
		conf.addResource("classpath:/hadoop/core-site.xml");
	    conf.addResource("classpath:/hadoop/hdfs-site.xml");
	    conf.addResource("classpath:/hadoop/mapred-site.xml");
		
		
		
		conf.set("mapreduce.map.output.compress.codec", "com.hadoop.mapreduce.LzoTextInputFormat");
		conf.set("mapred.map.output.compression.codec", "com.hadoop.compression.lzo.LzoCodec");
		conf.set("io.compression.codecs", "org.apache.hadoop.io.compress.GzipCodec,org.apache.hadoop.io.compress.DefaultCodec,com.hadoop.compression.lzo.LzoCodec,com.hadoop.compression.lzo.LzopCodec,org.apache.hadoop.io.compress.BZip2Codec");
		conf.set("io.compression.codec.lzo.class", "com.hadoop.compression.lzo.LzoCodec");
		conf.set("mapred.compress.map.output", "true");
		conf.set("mapred.map.output.compression.codec", "com.hadoop.compression.lzo.LzoCodec");
		
//		CompressionCodecFactory compressionCodecs = new CompressionCodecFactory(conf);
//    	System.out.println(
	}
}
