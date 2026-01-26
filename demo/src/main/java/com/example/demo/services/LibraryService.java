package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Book;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Librarian;
import com.example.demo.entities.Visit;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.LibrarianRepository;


@Service
public class LibraryService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    public void recordCustomerVisit(Long customerId, Long librarianId, List<Book> borrowedBooks, List<Book> returnedBooks) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Librarian librarian = librarianRepository.findById(librarianId).orElseThrow(() -> new RuntimeException("Librarian not found"));
        Visit newVisit = new Visit();
        newVisit.setCustomer(customer);
        newVisit.setLibrarian(librarian); 
        newVisit.setBorrowedBooks(borrowedBooks);
        newVisit.setReturnedBooks(returnedBooks);

        customer.getVisitHistory().add(newVisit);
        customerRepository.save(customer);
    }   
}