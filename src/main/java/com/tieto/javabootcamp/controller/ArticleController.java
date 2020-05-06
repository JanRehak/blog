package com.tieto.javabootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tieto.javabootcamp.model.Article;
import com.tieto.javabootcamp.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	@GetMapping public Iterable<Article> get() { 
		return articleService.listArticles();
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping public Article post(@RequestBody Article article, @AuthenticationPrincipal User user) {
		return articleService.saveArticle(article, user);
	}

	@GetMapping("/pageable")
	public Page<Article> getPageArticles(Pageable pageable){
		return articleService.listPageable(pageable);
	}
}
