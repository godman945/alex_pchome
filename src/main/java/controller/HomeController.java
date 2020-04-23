package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.codehaus.plexus.util.IOUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.minidev.json.JSONArray;
import service.IDruidService;

//import net.guides.springboot2.springboot2webappthymeleaf.repositories.UserRepository;

@Controller
public class HomeController {

	
	
	@Autowired
	private IDruidService druidService;

	@RequestMapping("/alex")
	public String  home(Model model) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", "ALEX999999 CCC");
		List<Map> cities = new ArrayList<Map>();
		cities.add(map);
		model.addAttribute("cities", cities);
		System.out.println("DDDDDDDDDDDDDD");
		return "index";

	}
	
	@RequestMapping("/alex2")
	public ModelAndView  home2(Map<String,Object> map) {
		ModelAndView mv = new ModelAndView("ftl/index");
        map.put("name", "道哥");
        return mv;
	}
	
	@RequestMapping("/welcome")
	public String  firstPage() {
        return "tiles/welcome";
	}
	
	@RequestMapping("/query")
	public String  query() {
        return "tiles/query";
	}
	
	
	//報表demo用
	@RequestMapping(value="/data",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String api2(HttpServletRequest request,@RequestBody(required= false) String queryParameterStr) {
		try {
			JSONArray result = null;
			org.json.JSONObject queryParameter = new org.json.JSONObject(queryParameterStr);
			System.out.println(queryParameter);
			if("type_1".equals(queryParameter.getString("query_type"))) {
				result = druidService.findCategory(queryParameter.getString("start"), queryParameter.getString("end"),queryParameter.getString("layer"));
			}
			return result.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			 return "ERROR";
		}		
    }
	
	//查詢條件用
	
	@RequestMapping(value="/querydata",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String queryData(HttpServletRequest request,@RequestBody(required= false) String queryParameterStr) {
		try {
			JSONArray result = null;
			System.out.println(queryParameterStr);
			org.json.JSONObject queryParameter = new org.json.JSONObject(queryParameterStr);
			
			
			
			
			
			
			
			
			
			
			
			
//			if("type_1".equals(queryParameter.getString("query_type"))) {
//				result = druidService.findCategory(queryParameter.getString("start"), queryParameter.getString("end"),queryParameter.getString("layer"));
//			}
			return result.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			 return "ERROR";
		}		
    }
	
	
	
	
	public static void main(String args[]) {
		try {
			
			
			long a = System.currentTimeMillis();
			
			Class.forName("org.apache.calcite.avatica.remote.Driver");    
			String url = "jdbc:avatica:remote:url=http://druidq1.mypchome.com.tw:8082/druid/v2/sql/avatica/";
			Connection druidConnection = DriverManager.getConnection(url);
			Properties connectionProperties = new Properties();
			druidConnection = DriverManager.getConnection(url, connectionProperties);
			
			
			StringBuffer queryStr = new StringBuffer();
			queryStr.append(" select  ");
			queryStr.append(" count(distinct uuid)distinct_uuid_count, ");
			queryStr.append(" mark_value, ");
			queryStr.append(" CASE WHEN LOOKUP(mark_value,'cn_name') = '' then mark_value ELSE LOOKUP(mark_value,'cn_name') end mark_value_name  ");
			queryStr.append(" from dmp_db ");
			queryStr.append(" where 1= 1  ");
			queryStr.append(" and log_source = 'bu_log' ");
			queryStr.append(" and mark_layer = 1 ");
			queryStr.append(" and uuid in(select  distinct uuid		 from dmp_db 		where 1= 1 		and log_source = 'kdcl_log' and pfp_customer_info_id = 'AC2019102900003'		and cks >= 0) ");
			queryStr.append(" group by mark_value ");
			queryStr.append(" order by count(distinct uuid) desc limit 5 ");
			
			
			
			System.out.println(queryStr);
			final PreparedStatement preparedStatement = druidConnection.prepareStatement(queryStr.toString());
			
			final ResultSet resultSet = preparedStatement.executeQuery();
			List<String> dataList = new ArrayList<String>();
			JSONArray array = new JSONArray();
			while (resultSet.next()) {
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
				System.out.println(rsmd.getColumnLabel(1));
				System.out.println(resultSet.getString(rsmd.getColumnName(1)));
//				JSONObject json = new JSONObject(new LinkedHashMap<String, String>());
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					json.put(rsmd.getColumnLabel(i), resultSet.getString(rsmd.getColumnName(i)));
//				}
//				array.add(json);
			}
			
			long b = System.currentTimeMillis();
			System.out.println((b - a) / 1000);
			
			
			
			
			
			
//			StringBuffer queryStr = new StringBuffer();
//			queryStr.append("select 		pfp_customer_info_id,		uuid		 from dmp_db 		where 1= 1 		and log_source = 'kdcl_log' 		and pfp_customer_info_id = 'AC2019042900004'		and cks >= 0		group by pfp_customer_info_id,uuid ");
//			System.out.println(queryStr);
//			
//			
//			
//			final PreparedStatement preparedStatement = druidConnection.prepareStatement(queryStr.toString());
//			
//			final ResultSet resultSet = preparedStatement.executeQuery();
//			List<String> dataList = new ArrayList<String>();
//			JSONArray array = new JSONArray();
//			while (resultSet.next()) {
//				ResultSetMetaData rsmd = resultSet.getMetaData();
//				JSONObject json = new JSONObject(new LinkedHashMap<String, String>());
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					json.put(rsmd.getColumnLabel(i), resultSet.getString(rsmd.getColumnName(i)));
//				}
//				array.add(json);
//			}
//			
//			
//			StringBuffer userStr = new StringBuffer();
//			for (Object object : array) {
//				JSONObject json = (JSONObject) object;
//				if(userStr.length() == 0) {
//					userStr.append("'").append(json.get("uuid")).append("'");
//				}else {
//					userStr.append(",").append("'").append(json.get("uuid")).append("'");
//				}
//			}
//			System.out.println(userStr);
			
			
			
			
			
			
			/* CSV START*/
//			String a = "★強打活動★&&$299↗$899↗88折►營業用品";
//			String a = "$１００１~$２９９９";
//			
//			String price = a.replaceAll("[^~０１２３４５６７８９\\-\\d]", "");
//			
//			
//			System.out.println(price);
			
			
			
//			String org = "★強打活動★&&$299↗$899↗88折►營業用品";
//			char[] chars = org.toCharArray();
//			int tranTemp = 0;
//
//			for (int i = 0; i < chars.length; i++) {
//				tranTemp = (int) chars[i];
//				
//				// ASCII碼: 是number
//				if (tranTemp - 65248 >= 48 && tranTemp - 65248 <= 57) {
//					
//				} 
//				// 此數字是 Unicode編碼轉為十進位 和 ASCII碼的 差
//				tranTemp -= 65248; 
//				org += (char) tranTemp;
//			}
//			System.out.println(org);
			
			
//			for (String string : a.split("")) {
//				System.out.println(string + ">>>>>>>>>>" + string.contains("$"));
//			}
			
			
			
//			String price = "";
//			int start = 0;
//			for (int i = 0; i < a.split("").length; i++) {
//				if((i < a.length() -1)) {
//					if(a.split("")[i].equals("$") && Character.isDigit(a.charAt(i+1))) {
//						start = i;
//						break;
//					}
//				}
//			}
//			price = a.substring(start,a.length());
//			StringBuffer b = new StringBuffer();
//			int strTimes = 0; 
//			for (String string : price.split("")) {
//			if(string.equals("$")) {
//				if(b.length() == 0) {
//					b.append("$");	
//				}else {
//					b.append("~$");
//				}
//				continue;
//			}
//			
//			if(Character.isDigit(price.charAt(price.indexOf(string)))) {
//				if(strTimes == 0) {
//					b.append(string);
//				}
//				if(strTimes == 1) {
//					int strTimes2 = 0;
//					for (String string2 : b.toString().split("")) {
//						if(string2.equals("$")) {
//							strTimes2 = strTimes2 + 1;
//						}
//					}
//					if(strTimes2 !=2) {
//						break;
//					}else {
//						b.append(string);
//					}
//				}
//				continue;
//			}
//			if(!Character.isDigit(price.charAt(price.indexOf(string)))) {
//				strTimes = strTimes + 1;
//			}
//		}
//			
//			
//		System.out.println(b);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			for (String string : a.split("")) {
//				System.out.println(string+">>>>>>>"+a.indexOf(string));
//				
//				if(string.equals("$") && Character.isDigit(a.charAt(a.indexOf(string)+1))) {
//					start = a.indexOf(string);
//				}
//			}
//			System.out.println(a.substring(start, a.length()));
			
			
			
			
			
//			a = a.substring(a.indexOf("$"), a.length());
//			System.out.println(a);
//			StringBuffer b = new StringBuffer();
//			int strTimes = 0; 
//			for (String string : a.split("")) {
//				if(string.equals("$")) {
//					if(b.length() == 0) {
//						b.append("$");	
//					}else {
//						b.append("~$");
//					}
//					continue;
//				}
//				
//				if(Character.isDigit(a.charAt(a.indexOf(string)))) {
//					if(strTimes == 0) {
//						b.append(string);
//					}
//					if(strTimes == 1) {
//						int strTimes2 = 0;
//						for (String string2 : b.toString().split("")) {
//							if(string2.equals("$")) {
//								strTimes2 = strTimes2 + 1;
//							}
//						}
//						if(strTimes2 !=2) {
//							break;
//						}else {
//							b.append(string);
//						}
//					}
//					continue;
//				}
//				if(!Character.isDigit(a.charAt(a.indexOf(string)))) {
//					strTimes = strTimes + 1;
//				}
//			}
//			
//			System.out.println(b);
			
			
			
//			System.out.println(a.replaceAll("[^~\\-件折$\\d]", ""));
//			a = a.substring(a.indexOf("$"), a.length());
//			int strEnd = 0;
//			for (String string : a.split("")) {
//				if(string.equals("$")) {
//					continue;
//				}
//				if(!Character.isDigit(a.charAt(a.indexOf(string)))) {
//					strEnd = a.indexOf(string);
//					break;
//				}
//			}
//			a = a.substring(0,strEnd);
//			System.out.println(a);
//			System.out.println(strEnd+"---");
			
//			System.out.println(a.replaceAll("[^~\\-件折$\\d]", ""));
			
//			
//			String price = a.replaceAll("[^~\\-件折$\\d]", "").substring(a.replaceAll("[^~\\-件折$\\d]", "").indexOf("$"),a.replaceAll("[^~\\-件折$\\d]", "").length()).replace("$", "");
//			
//			System.out.println(price);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			FileInputStream brandCsv = new FileInputStream(new File("D:\\Users\\alexchen\\Desktop\\AAAAAAAAA\\adm_brand_correspond.csv"));
//			Reader reader2 = new InputStreamReader(brandCsv);
//			CSVParser csvParser2 = new CSVParser(reader2, CSVFormat.DEFAULT);
//			org.json.JSONArray brandJsonArray = new org.json.JSONArray();
//			
//			
//			JSONObject brandJson = new JSONObject();
//			for (CSVRecord csvRecord2 : csvParser2) {
//				if(csvRecord2.get(1).equals("NA")) {
//					continue;
//				}
//				
//				brandJson.put(csvRecord2.get(0), csvRecord2.get(1));
//				
////				JSONObject brandJson = new JSONObject();
////				brandJson.put(csvRecord2.get(0), csvRecord2.get(1));
//////				brandJson.put("brand_db_seq", csvRecord2.get(0));
//////				brandJson.put("brand_name", csvRecord2.get(1));
////				brandJsonArray.put(brandJson);
//			}
//			
//			System.out.println(brandJson);
//			
			
			
			
			
//			System.out.println(brandJsonArray);
//			FileInputStream isr = new FileInputStream(
//					new File("D:\\Users\\alexchen\\Desktop\\AAAAAAAAA\\24h_menu_new.csv"));// 檔案讀取路徑
//			
////			Reader reader = new InputStreamReader(isr);
//			Reader reader = new BufferedReader(new InputStreamReader(isr,"UTF-8"));  
//			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
//			int first = 0;
//			org.json.JSONArray menu24hMappingJsonArray = new org.json.JSONArray();
//			for (CSVRecord csvRecord : csvParser) {
//				if(first == 0) {
//					first = 1;
//					continue;
//				}
//				if(!csvRecord.get(5).equals("DSAX0I")) {
//					continue;
//				}
////				System.out.println(csvRecord.get(4));
////				System.out.println(IOUtil.toString(csvRecord.get(4).getBytes(), "UTF-8"));
////				if(csvRecord.get(4).contains("直播")) {
////					System.out.println("AAAAAAAAAAAAAAAAA");
////				}else {
////					System.out.println("BBBBBBBBBBBBBb");
////				}
//				
//				
//				
//				org.json.JSONObject menu24hMappingJson = new org.json.JSONObject();
//				menu24hMappingJson.put("level_1_code", csvRecord.get(1));
//				menu24hMappingJson.put("level_2_code", csvRecord.get(3));
//				menu24hMappingJson.put("level_3_code", csvRecord.get(5));
//				
//				for (Object object : brandJsonArray) {
//					org.json.JSONObject brandJson  = (org.json.JSONObject) object;
//					if(!brandJson.getString("brand_name").contains("華碩")) {
//						continue;
//					}
//					
//					
//					System.out.println(csvRecord.get(4)+"@@@@@"+brandJson.getString("brand_name"));
//					
//					
//					
//					if(csvRecord.get(0).contains(brandJson.getString("brand_name"))) {
//						menu24hMappingJson.put("level_1_brand", brandJson.getString("brand_db_seq"));
//					}
//					if(csvRecord.get(2).contains(brandJson.getString("brand_name"))) {
//						menu24hMappingJson.put("level_2_brand", brandJson.getString("brand_db_seq"));
//					}
//					if(csvRecord.get(4).contains(brandJson.getString("brand_name"))) {
//						menu24hMappingJson.put("level_3_brand", brandJson.getString("brand_db_seq"));
//					}
//				}
//				menu24hMappingJsonArray.put(menu24hMappingJson);
//			}
//			
//			System.out.println(menu24hMappingJsonArray);
			
			
			
			
			
//			while ((line = reader.readLine()) != null) {
//				String item[] = line.split(",");
//				/** 讀取 **/
//				String data0 = item[0].trim();
//				String data1 = item[1].trim();
//				String data2 = item[2].trim();
//				String data3 = item[3].trim();
//				
//				
////				BufferedReader reader2 = new BufferedReader(brandCsv);
////				String line2 = null;
////				while ((line2 = reader2.readLine()) != null) {
////					String item2[] = line2.split(",");
////					String a0 = item2[0].trim();
////					String a1 = item2[1].trim();
////					System.out.println(a0);
////					System.out.println(a1);
////					
////					
////					
////					
////				}
//				
//				break;
//			}
			
			
			
			
			
			/* CSV END */
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		
		
		
		
		
	}
	
	
	
	
}