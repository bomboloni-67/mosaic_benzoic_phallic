package com.example.demo.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Visit extends BasicEntity {

    @ManyToOne()
    @JoinColumn(name ="customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    private Librarian librarian;

    private LocalDateTime visitTime;

    @ManyToMany
    private List<Book> returnedBooks;

    @ManyToMany
    private List<Book> borrowedBooks;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

    public List<Book> getReturnedBooks() {
        return returnedBooks;
    }

    public void setReturnedBooks(List<Book> returnedBooks) {
        this.returnedBooks = returnedBooks;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}