package service;


import org.springframework.stereotype.Service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public interface IDruidService{
	
	public JSONArray findCategory(String stardDate,String endDate,String layer) throws Exception;
	
	
	public JSONArray findPageUser(JSONObject condition) throws Exception;
}
