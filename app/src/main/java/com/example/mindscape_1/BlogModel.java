package com.example.mindscape_1;

public class BlogModel {
    String head;
    String Content;

    String Url;

    public BlogModel() {
    }

    public BlogModel(String head, String content, String url) {
        this.head = head;
        Content = content;
        Url = url;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
