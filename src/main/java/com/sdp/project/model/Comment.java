package com.sdp.project.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Comment {

    private String blogId;
    private String user;
    private String commentText;


    public String getBlogId() {
        return blogId;
    }
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getCommentText() {
        return commentText;
    }
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comment {" +
                "\"user\":" + user+ "\"" +
                "\"commentText\":" + commentText+ "\"" +
                "})";
    }

}