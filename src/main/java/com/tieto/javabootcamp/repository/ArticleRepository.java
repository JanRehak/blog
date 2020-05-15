package com.tieto.javabootcamp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.tieto.javabootcamp.model.Article;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long>, JpaSpecificationExecutor<Article>, JpaRepository<Article, Long> {

    @Override
    Page<Article> findAll(Specification<Article> specification, Pageable pageable);

    @Override
    List<Article> findAll(Specification<Article> specification, Sort sort);
}
