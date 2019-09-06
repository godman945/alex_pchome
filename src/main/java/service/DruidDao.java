package service;



import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;



@Repository
public class DruidDao implements IDruidDao{

	@Autowired
	private Connection druidConnection;


	@Override
	public JSONArray findCategory(String stardDate,String endDate,String layer) throws Exception {
		System.out.println(stardDate);
		System.out.println(endDate);
		
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  ");
		sql.append(" CASE WHEN LOOKUP(mark_value,'cn_name') = ''  ");
		sql.append(" then mark_value  "); 
		sql.append(" ELSE LOOKUP(mark_value,'cn_name') end mark_value,  ");
		sql.append(" count(distinct uuid)count_total,  ");
		sql.append(" sum(pvs)pvs ");
		sql.append(" FROM dmp_db  "); 
		sql.append(" where log_source ='bu_log'  ");
		sql.append(" and mark_value != ''  ");
		sql.append(" and mark_layer = ").append("'").append(layer).append("'");
		sql.append(" and (__time >= ").append("'").append(stardDate).append("'").append(" and __time <=").append("'").append(endDate).append("'").append(" )");
		sql.append(" group by mark_value  ");
		sql.append(" order by count(distinct uuid) desc  ");
		
		System.out.println(sql.toString());
		final PreparedStatement preparedStatement = druidConnection.prepareStatement(sql.toString());
		
		final ResultSet resultSet = preparedStatement.executeQuery();
		List<String> dataList = new ArrayList<String>();
		JSONArray array = new JSONArray();
		while (resultSet.next()) {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			JSONObject json = new JSONObject(new LinkedHashMap<String, String>());
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				json.put(rsmd.getColumnLabel(i), resultSet.getString(rsmd.getColumnName(i)));
			}
			array.add(json);
		}
		return array;
	}


	@Override
	public JSONArray findPageUser(String sql) throws Exception {
		
		
		
		
		return null;
	}
}
