
package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;

@Controller

public class WebController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-list";
    }

    @GetMapping("/books/new")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @GetMapping("/books/{id}")
    public String showBookDetails(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: "+id));
        model.addAttribute("book", book);
        return "book-details";
    }

    @PostMapping("/books")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/"; 
    }
}