package cion.springCloudService.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
	@Value("${spring.application.name}")
	private String name;
	
	@GetMapping("/service")
	@ResponseBody
	public String test(HttpServletResponse response, String para) {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		System.out.println(name);
		return para + "_service";
	}
	
	@GetMapping("/feignservice/{para}")
	@ResponseBody
	public String feignservice(HttpServletResponse response, @PathVariable String para) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println(name+para);
		return para + "_service";
	}
}
