package com.sdp.project;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static
        org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sdp.project.model.Article;
import com.sdp.project.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import
        org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class WhyShouldYouReadApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testAuthentication() throws Exception {
// Testing authentication with correct credentials
        mockMvc.perform(post("/login")
                .content("{\"username\":\"admin\",\"password\":\"password\"}")).
                andDo(print()).andExpect(status().isOk());
// Testing authentication with wrong credentials
        mockMvc.perform(post("/login")
                .content("{\"username\":\"admin\",\"password\":\"wrongpassword\"}")).
                andDo(print()).andExpect(status().is4xxClientError());
        //sending article without jwt
        Article article= new Article(null,"Elasticsearch",null,"data search and save","elasticsearch with spring data",null,null,null);
        mockMvc.perform(post("/article/save").contentType(
                MediaType.APPLICATION_JSON).content(String.valueOf(article))).andDo(print()).andExpect(status().is4xxClientError());

    }
    @Test
    public void testArticleEndpoint() throws Exception
    {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "0");
        requestParams.add("size", "1");
        mockMvc.perform(get("/article").params(requestParams)).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void testShowEndpoint() throws Exception
    { //first run repository test to get the exact link of the saved article
        mockMvc.perform(get("/article/show/5b274e3d-8139-45c2-b19b-7b3c42bbd802")).andDo(print()).andExpect(status().isOk());
    }
}
