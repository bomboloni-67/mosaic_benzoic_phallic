package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Librarian;
import com.example.demo.repositories.LibrarianRepository;

@Controller
@RequestMapping("/staff") // Matches the link in your index.html
public class LibrarianController {

    @Autowired
    private LibrarianRepository librarianRepository;

    // Route: GET /staff
    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("staffMembers", librarianRepository.findAll());
        return "staff-list";
    }

    // Route: GET /staff/new
    @GetMapping("/new")
    public String showStaffForm(Model model) {
        model.addAttribute("librarian", new Librarian());
        return "add-staff";
    }

    @GetMapping("/{id}")
    public String showLibrarianDetails(@PathVariable("id") Long id, Model model) {
        Librarian librarian = librarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found with id: " + id));
        model.addAttribute("librarian", librarian);
        return "librarian-details";
    }

    // Route: POST /staff
    @PostMapping
    public String saveStaff(@ModelAttribute Librarian librarian) {
        librarianRepository.save(librarian);
        return "redirect:/staff";
    }

    // Route: GET /staff/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable("id") Long id) {
        librarianRepository.deleteById(id);
        return "redirect:/staff";
    }
}