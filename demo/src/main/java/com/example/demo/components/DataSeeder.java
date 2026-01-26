package com.example.demo.components;

import com.example.demo.entities.Book;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Librarian;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.ArrayList;


@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired 
    private LibrarianRepository librarianRepository;

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "A classic American novel", true);
            Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", "A gripping tale of racial injustice and childhood innocence", true);
            Book book3 = new Book("1984", "George Orwell", "9780451524935", "A dystopian social science fiction novel", true);
            Book book4 = new Book("Pride and Prejudice", "Jane Austen", "9781503290563", "A romantic novel of manners", true);
            Book book5 = new Book("Majestic Fantastic Four","Stan Lee", "481203423-4821", "The rise of the mighty Fantastic Four",false);
            Book book6 = new Book("Kronos vs Thanos", "Stephen King", "102934812348", "An epic tale of two gods", false);
            bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5, book6));
        }

        // Seed Customers
        if (customerRepository.count() == 0) {
            Customer customer1 = new Customer("Alice", "alice@test.com", "123456", "pass123", new ArrayList<>(), new ArrayList<>());
            Customer customer2 = new Customer("Bob", "bob@test.com", "789012", "pass456", new ArrayList<>(), new ArrayList<>());
            Customer customer3 = new Customer("Charlie", "charlie@test.com", "345678", "pass789", new ArrayList<>(), new ArrayList<>());
            customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
        }

        if (librarianRepository.count() == 0) {
            Librarian librarian1 = new Librarian("Admin", "admin@test.com", "000000", "adminpass");
            Librarian librarian2 = new Librarian("Polly", "polly@test.com", "111111", "pollypass");
            librarianRepository.saveAll(Arrays.asList(librarian1, librarian2));
        }
    }
}