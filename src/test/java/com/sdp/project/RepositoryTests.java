package com.sdp.project;

import static org.assertj.core.api.Assertions.assertThat;

import com.sdp.project.model.Article;
import com.sdp.project.model.User;
import com.sdp.project.repository.ArticleRepository;
import com.sdp.project.service.ArticleService;
import com.sdp.project.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RepositoryTests {
    @Autowired
    private ArticleService service;
    @Autowired
    private UserService userService;
    @Test
    public void saveArticle() throws ParseException {
        service.clearAll();
        Article article = new Article(null,"Java",null,"java","java",new User(),null,null);
        Article saved = service.save(article);
        System.out.println(saved.getLink());
        assertThat(saved.getId()!=null);
    }
    @Test
    public void saveUser()
    {
        User user = new User(null,"JKro3","password",null,"Student");
        User saved = userService.save(user);
        assertThat(saved.getId()!=null);
    }
    @Test
    public void getAllArticles() throws Exception
    {
        System.out.println(service.getAll(PageRequest.of(0, 1)).toString());
       assertThat(service.getAll(PageRequest.of(0, 1))!=null);

    }
}
