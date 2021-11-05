package com.sdp.project.service;
import com.sdp.project.model.Article;
import com.sdp.project.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article) throws ParseException {
        if (article.getId() == null || article.getId().length()==0) {
            article.setId(UUID.randomUUID().toString());
            article.setLink(UUID.randomUUID().toString());
            DateFormat df = new SimpleDateFormat("dd-MM-yy");
            String dateToStr =df.format(new Date());
            Date jsondate = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH).parse(dateToStr);
            article.setCreatedDate(jsondate);
        }
        if(article.getAuthor()==null)
            throw new RuntimeException("User not found");
        return articleRepository.save(article);
    }

    public Page<Article> getAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Optional<Article> getByLink(String link) {
        return articleRepository.findByLink(link);
    }

    public Optional<Article> getById(String id) {
        return articleRepository.findById(id);
    }

    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }

    public Page<Article> search(String q, Pageable pageable) {
        return articleRepository.findByTitleContainingAndBodyContaining(q, q, pageable);
    }
    public void clearAll()
    {
        articleRepository.deleteAll();
    }
}