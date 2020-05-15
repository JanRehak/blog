package com.tieto.javabootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping public Article post(@RequestBody Article article, @AuthenticationPrincipal User user) {
		return articleService.saveArticle(article, user);
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@DeleteMapping("/{articleId}") public void deleteArticle(@PathVariable("articleId") Long id, @AuthenticationPrincipal User user) {
		articleService.deleteArticle(id, user);
	}

	@PutMapping("/{articleId}")
	public Article updateArticle(@PathVariable("articleId") Long id, @RequestBody Article article, @AuthenticationPrincipal User user) {
		return articleService.updateArticle(id, article, user);
	}

	@GetMapping("/pageable")
	public Page<Article> getPageArticles(Pageable pageable){
		return articleService.listPageable(pageable);
	}
}
