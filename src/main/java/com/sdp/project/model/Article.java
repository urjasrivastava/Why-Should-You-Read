package com.sdp.project.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(indexName = "blog_article")
@Data
public class Article {

    @Id
    private String id;

    private String title;

    private String link;

    private String summary;

    private String body;

    @Field(type = FieldType.Nested)
    private User author;

    @Field(type = FieldType.Nested)
    private List<Comment> comments;

    @Field(type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
    private Date createdDate;


    public Article(String id, String title, String link, String summary, String body, User author, List<Comment> comments, Date createdDate) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.summary = summary;
        this.body = body;
        this.author = author;
        this.comments = comments;
        this.createdDate = createdDate;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", summary='" + summary + '\'' +
                ", body='" + body + '\'' +
                ", author=" + author +
                ", comments=" + comments +
                ", createdDate=" + createdDate +
                '}';
    }
}
