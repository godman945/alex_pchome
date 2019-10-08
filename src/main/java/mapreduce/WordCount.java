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


45456456564
5454565



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
//    	System.out.println("11111111111111");
//		CompressionCodec codec = compressionCodecs.getCodec(new Path("/druid_source/kdcl_log/2019-08-04/00/20190804_00_4c.log.lzo"));
		
		
		FileSystem fs = FileSystem.get(conf);
		fs.delete(new Path("hdfs://druid1.mypchome.com.tw:9000/durid_source"), true);
		
		System.out.println(fs.exists(new Path("hdfs://druid1.mypchome.com.tw:9000/druid_source/kdcl_log/2019-08-04/00/20190804_00_4c.log.lzo")));
		
		Job job = new Job(conf, "word count");
		job.setJarByClass(WordCount.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setReducerClass(IntSumReducer.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setInputFormatClass(com.hadoop.mapreduce.LzoTextInputFormat.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path("hdfs://druid1.mypchome.com.tw:9000/druid_source/kdcl_log/2019-08-04/00/20190804_00_4c.log.lzo"));
		FileOutputFormat.setOutputPath(job, new Path("/durid_source"));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		String[] jarPaths = {
				"/hadoop_jar/lib/commons-lang-2.6.jar",
				"/hadoop_jar/lib/commons-logging-1.1.1.jar",
				"/hadoop_jar/lib/log4j-1.2.15.jar",
				"/hadoop_jar/lib/mongo-java-driver-2.11.3.jar",
				"/hadoop_jar/lib/softdepot-1.0.9.jar",
				"/hadoop_jar/lib/solr-solrj-4.5.0.jar",
				"/hadoop_jar/lib/noggit-0.5.jar",
				"/hadoop_jar/lib/httpcore-4.2.2.jar",
				"/hadoop_jar/lib/httpclient-4.2.3.jar",
				"/hadoop_jar/lib/httpmime-4.2.3.jar",
				"/hadoop_jar/lib/mysql-connector-java-5.1.12-bin.jar",
				"/hadoop_jar/lib/hadoop-lzo-0.4.20.jar",
				
				// add kafka jar
				"/hadoop_jar/lib/kafka-clients-0.9.0.0.jar",
				"/hadoop_jar/lib/kafka_2.11-0.9.0.0.jar",
				"/hadoop_jar/lib/slf4j-api-1.7.19.jar",
				"/hadoop_jar/lib/slf4j-log4j12-1.7.6.jar",
				"/hadoop_jar/lib/json-smart-2.3.jar",
				"/hadoop_jar/lib/asm-1.0.2.jar" 
		}; 
		for (String jarPath : jarPaths) {
			DistributedCache.addArchiveToClassPath(new Path(jarPath), job.getConfiguration(), fs);
		}
		
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
