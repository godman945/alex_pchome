package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {
	@Bean(name = "druidConnection")
	public Connection getDruidConnection() throws Exception {
		Class.forName("org.apache.calcite.avatica.remote.Driver");    
		String url = "jdbc:avatica:remote:url=http://druid1.mypchome.com.tw:8082/druid/v2/sql/avatica/";
		Connection con = DriverManager.getConnection(url);
		Properties connectionProperties = new Properties();
		con = DriverManager.getConnection(url, connectionProperties);
		return con;
	}
}
