package com.rw.apilivraria.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rw.apilivraria.api.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
