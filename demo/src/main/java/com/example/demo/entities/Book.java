package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "books")
public class Book extends BasicEntity {
    private String title;
    private String author;
    private String isbn;
    private String description;
    private boolean available = true;

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }   
    public String getIsbn() {
        return isbn;
    }
    public String getDescription() {
        return description;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    public void setDescription(String description) {this.description = description;}
    public void setAvailable(boolean available) {this.available = available;}

}