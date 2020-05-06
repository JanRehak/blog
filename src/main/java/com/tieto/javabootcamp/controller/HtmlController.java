package com.tieto.javabootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

	@RequestMapping("/")
	public String get() {
		return "index.html";
	}
	
    @GetMapping("/users")
    public String users() {
        return "users.html";
    }
    
    @GetMapping("/articles")
    public String articles() {
        return "articles.html";
    }

}
