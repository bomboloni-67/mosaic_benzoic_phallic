package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/customers") 
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // Route: GET /customers (List all members)
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customer-list";
    }

    // Route: GET /customers/new (Show registration form)
    @GetMapping("/new")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    // Route: GET /customers/{id} (View specific profile)
    @GetMapping("/{id}")
    public String showCustomerDetails(@PathVariable("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        model.addAttribute("customer", customer);
        return "customer-details";
    }

    // Route: POST /customers (Process registration)
    @PostMapping
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    // Route: GET /customers/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }

    /**
     * API Endpoint for JavaScript: Fetch only the books borrowed by a specific customer.
     * Returns a List of Maps to avoid Circular Reference errors in JSON.
     */
    @GetMapping("/{id}/borrowed-books")
    @ResponseBody
    public List<Map<String, Object>> getBorrowedBooks(@PathVariable("id") Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        // Convert the Book entities into a simple Map structure
        return customer.getCurrentlyBorrowedBooks().stream()
                .map(book -> {
                    Map<String, Object> bookData = new HashMap<>();
                    bookData.put("id", book.getId());
                    bookData.put("title", book.getTitle());
                    return bookData;
                })
                .toList();
    }
}