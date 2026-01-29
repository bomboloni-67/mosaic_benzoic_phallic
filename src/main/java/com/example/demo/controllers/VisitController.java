package com.example.demo.controllers;

import com.example.demo.repositories.*;
import com.example.demo.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/visits")
public class VisitController {
    @Autowired private LibraryService libraryService;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private LibrarianRepository librarianRepository;
    @Autowired private BookRepository bookRepository;

    @GetMapping("/new")
    public String showVisitForm(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("librarians", librarianRepository.findAll());
        model.addAttribute("availableBooks", bookRepository.findByAvailableTrue());
        model.addAttribute("allBooks", bookRepository.findAll());
        return "record-visit";
    }

    @PostMapping
    public String processVisit(@RequestParam Long customerId,
                               @RequestParam Long librarianId,
                               @RequestParam(required = false) List<Long> borrowedBooksIds,
                               @RequestParam(required = false) List<Long> returnedBooksIds) {
        libraryService.recordCustomerVisit(customerId, librarianId, borrowedBooksIds, returnedBooksIds);
        return "redirect:/customers/" + customerId;
    }
    
}