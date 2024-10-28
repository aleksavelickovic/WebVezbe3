package com.ftn.OWPVezba3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("HelloWorld")
public class HelloWorldController {
	
	@GetMapping
	public String helloWorld() {
		return "/helloWorld.html";
	}
	
	@GetMapping("v2")
	@ResponseBody
	public String helloWorld2() {
		return "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	Hello world!!!!!!!!!\r\n"
				+ "</body>\r\n"
				+ "</html>";
	}
	
}
