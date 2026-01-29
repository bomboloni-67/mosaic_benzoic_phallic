package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends BasicEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;
    
    @ManyToMany
    private List<Book> currentlyBorrowedBooks = new ArrayList<>();

    @OneToMany(mappedBy="customer", cascade= CascadeType.ALL)
    private List<Visit> visitHistory = new ArrayList<>(); 

    @Column(nullable = false)
    private String password;

    public Customer() {}

    public Customer(String name, String email, String phoneNumber, String password, List<Book> currentlyBorrowedBooks, List<Visit> visitHistory) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.currentlyBorrowedBooks = currentlyBorrowedBooks;
        this.visitHistory = visitHistory;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Book> getCurrentlyBorrowedBooks() { return currentlyBorrowedBooks; }
    public void setCurrentlyBorrowedBooks(List<Book> currentlyBorrowedBooks) {this.currentlyBorrowedBooks = currentlyBorrowedBooks;}

    public List<Visit> getVisitHistory() { return visitHistory; }
    public void setVisitHistory(List<Visit> visitHistory) { this.visitHistory = visitHistory; }
}