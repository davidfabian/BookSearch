package com.example.d.booksearch;

/**
 * Created by d on 9/17/2017.
 */

public class Volume {
    private String author;
    private String title;

    public void Volume(String auth, String tit){
        author = auth;
        title = tit;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
