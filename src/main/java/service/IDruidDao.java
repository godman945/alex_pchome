package service;


import org.springframework.stereotype.Repository;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Repository
public interface IDruidDao {

	public JSONArray findCategory(String stardDate,String endDate,String layer) throws Exception;
	
	public JSONArray findPageUser(String sql) throws Exception;
}
