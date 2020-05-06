package com.tieto.javabootcamp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.tieto.javabootcamp.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {


    Page<Article> findAll(Pageable pageable);

}
