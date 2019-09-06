package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	
	
}