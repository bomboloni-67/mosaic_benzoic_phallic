package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Librarian;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    Librarian findByEmail(String email);
    Librarian findByName(String name);
}