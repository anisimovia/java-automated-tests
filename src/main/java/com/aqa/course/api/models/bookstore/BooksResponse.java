package com.aqa.course.api.models.bookstore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksResponse {
    private List<Book> books;

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}