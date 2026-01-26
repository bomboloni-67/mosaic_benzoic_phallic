package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Book;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Librarian;
import com.example.demo.entities.Visit;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.LibrarianRepository;
import com.example.demo.repositories.BookRepository;

import java.time.LocalDateTime;
import java.util.List; 
import jakarta.transaction.Transactional;


@Service
public class LibraryService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired 
    private BookRepository bookRepository;

    @Transactional
    public void recordCustomerVisit(Long customerId, Long librarianId, List<Long> borrowedBooksIds, List<Long> returnedBooksIds) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Librarian librarian = librarianRepository.findById(librarianId).orElseThrow(() -> new RuntimeException("Librarian not found"));
        Visit newVisit = new Visit();
        newVisit.setCustomer(customer);
        newVisit.setLibrarian(librarian); 
        newVisit.setVisitTime(LocalDateTime.now());

        if (borrowedBooksIds != null) {
            List<Book> borrowed = bookRepository.findAllById(borrowedBooksIds);
            for (Book b : borrowed) {
                b.setAvailable(false); // Mark as out
                customer.getCurrentlyBorrowedBooks().add(b);
            }
            newVisit.setBorrowedBooks(borrowed);
        }   


        if (returnedBooksIds != null) {
            List<Book> returned = bookRepository.findAllById(returnedBooksIds);
            for (Book b : returned) {
                b.setAvailable(true);
                customer.getCurrentlyBorrowedBooks().remove(b);
            }
            newVisit.setReturnedBooks(returned);
        }

        customer.getVisitHistory().add(newVisit);
        customerRepository.save(customer);

    }   
}