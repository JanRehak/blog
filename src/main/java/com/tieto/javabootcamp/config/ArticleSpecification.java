package com.tieto.javabootcamp.config;

import com.tieto.javabootcamp.model.Article;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;


public class ArticleSpecification {


    public static Specification<Article> isNotDeleted() {
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get("deletedDateTime"), LocalDateTime.now());
            }
        };
    }

    public static Specification<Article> isDeletedAfterDateTime(LocalDateTime moment) {
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("deletedDateTime"), moment, LocalDateTime.now());
            }
        };
    }

    public static Specification<Article> isModifiedAfterDateTime(LocalDateTime moment) {
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get("modifiedDateTime"), moment);
            }
        };
    }




}
