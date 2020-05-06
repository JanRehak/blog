package com.tieto.javabootcamp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("article")
public class Article {

    @SequenceGenerator(name = "article_id_seq", allocationSize = 1, sequenceName = "article_id_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_id_seq")
    private Long id;

    @Column(name="text_content", length = 65536)
    private String content;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

}
