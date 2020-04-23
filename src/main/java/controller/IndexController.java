package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.IDruidService;


@Controller
public class IndexController {

	@Autowired
	private IDruidService druidService;

	@RequestMapping("/index")
	public String home(Model model) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "ALEX999999 CCC");
		List<Map> cities = new ArrayList<Map>();
		cities.add(map);
		model.addAttribute("cities", cities);
		System.out.println("DDDDDDDDDDDDDD");
		return "index";
	}

}