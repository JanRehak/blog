package com.tieto.javabootcamp.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.tieto.javabootcamp.exception.BadRequestException;
import com.tieto.javabootcamp.exception.NotFoundException;
import com.tieto.javabootcamp.model.Article;
import com.tieto.javabootcamp.repository.ArticleRepository;
import com.tieto.javabootcamp.repository.UserRepository;
import com.tieto.javabootcamp.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Article saveArticle(Article article, User user) {
		if (user == null) {
			throw new BadRequestException("No author specified");
		}
		article.setAuthor(
			userRepository.findByName(user.getUsername())
				.orElseThrow(() -> new NotFoundException("Author with supplied id not found"))
		);
		return articleRepository.save(article);
	}

	@Override
	public Iterable<Article> listArticles() {
		return articleRepository.findAll();
	}

	@Override
	public Page<Article> listPageable(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}



	// TODO - dodelat check jestli je existujici autor
	@Override
	public void deleteArticle(Long id, User user) {
		//TODO chybi metoda na
		System.out.println("Dostal jsem se zde");
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			articleRepository.deleteById(id);
		} else if (articleRepository.findById(id).get().getAuthor().getName().equals(user.getUsername())){
			articleRepository.deleteById(id);
		}
	}

	@Override
	public Article updateArticle(Long id, Article article, User user) {
		Article articleToUpdate = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			articleToUpdate.setContent(article.getContent());
		} else if (articleToUpdate.getAuthor().getName().equals(user.getUsername())) {
			articleToUpdate.setContent(article.getContent());
		}
		return articleRepository.save(articleToUpdate);
	}
}
