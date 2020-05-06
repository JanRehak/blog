package com.tieto.javabootcamp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;

import com.tieto.javabootcamp.model.Article;

public interface ArticleService {
	
	Article saveArticle(Article article, User user);
	Iterable<Article> listArticles();

	Page<Article> listPageable(Pageable p);

}
