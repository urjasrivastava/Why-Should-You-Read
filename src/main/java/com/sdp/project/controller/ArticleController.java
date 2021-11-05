package com.sdp.project.controller;

import com.sdp.project.exception.UserNameExists;
import com.sdp.project.model.Article;
import com.sdp.project.model.User;
import com.sdp.project.exception.NotFoundException;
import com.sdp.project.service.ArticleService;
import com.sdp.project.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }
    @GetMapping
    public Page<Article> index(
                        @RequestParam(required = false, value = "searchString") String searchString,
                        @RequestParam(required = false, value = "page") Integer page,
                        @RequestParam(required = false, value = "size") Integer size) {
        Page<Article> articles;
        if (searchString == null) {
            articles= articleService.getAll(PageRequest.of(page, size));
        } else {
            articles =articleService.search(searchString,PageRequest.of(page, size));
        }
        return articles;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable String id) {
        articleService.deleteById(id);
        return  ResponseEntity.ok().build();
    }
    @GetMapping("/show/{link}")
    public Article getPost(@PathVariable String link) {
        Optional<Article> article = articleService.getByLink(link);
        if (!article.isPresent()) {
            throw new NotFoundException(link);
        }
        return article.get();
    }
    @PostMapping("/save")
    public ResponseEntity savePost(@AuthenticationPrincipal UserDetails userDetails,@RequestBody Article article) throws URISyntaxException, ParseException {
        //new Article
        if (article.getId() == null || article.getId().length() == 0) {
            User user = userService.getByUsername(userDetails.getUsername());
            user.setPassword(null);
            article.setAuthor(user);
        }
        //editing existing one
        Article savedArticle = articleService.save(article);

        return  ResponseEntity.created(new URI("/show/" + savedArticle.getLink())).body(savedArticle);
    }
    @PostMapping("/newuser")
    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException
    {
        //new Article
        User check = userService.getByUsername(user.getUsername());
        if(check==null){
        userService.save(user);}
        else
        {
           throw new UserNameExists("Username already taken");
        }
        return  ResponseEntity.ok().build();
    }

}
