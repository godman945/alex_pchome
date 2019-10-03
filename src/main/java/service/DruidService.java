package service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class DruidService implements IDruidService {

	@Autowired
	private IDruidDao druidDao;

	public JSONArray findCategory(String stardDate, String endDate, String layer) throws Exception {
		return druidDao.findCategory(stardDate, endDate, layer);
	}

	public JSONArray findPageUser(JSONObject condition) throws Exception {

		return druidDao.findPageUser("");
	}

	public static void main(String args[]) {
		org.json.JSONObject json = new org.json.JSONObject("{'group_0':[{'url_tyle':'url','condition_tyle':'contain','condition_value':'34'},{'url_tyle':'url','condition_tyle':'contain','condition_value':'17'},{'url_tyle':'url','condition_tyle':'not_contain','condition_value':'ad_index'}],'group_1':[{'url_tyle':'url','condition_tyle':'equal','condition_value':'http://travel.pchome.com.tw/expert/34/monograph/10372'}],'query_type':'query_any_group'}");
		String queryTyle = json.getString("query_type");
		
		
		System.out.println(queryTyle);
		
		
//		Iterator<String> keysItr = json.keys();
//		while (keysItr.hasNext()) {
//			String key = keysItr.next();
//			org.json.JSONArray jsonArray = (org.json.JSONArray) json.get(key);
//			if(key.equals("query_type")) {
//				continue;
//			}
//
//			for (String string : jsonArray) {
//				
//			}
//			
//			
//			
//			System.out.println(key);
//			System.out.println(value);
//			
//		}

	}

}
