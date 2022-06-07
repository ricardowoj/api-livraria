package com.rw.apilivraria.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rw.apilivraria.api.dto.BookDTO;
import com.rw.apilivraria.api.model.Book;
import com.rw.apilivraria.api.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookDTO create(@RequestBody BookDTO bookDTO) {
		Book book = new Book(10l, "As aventuras", "Artur", "001");
		book = bookService.save(book);
		bookDTO.setId(book.getId());
		return bookDTO;
	}
}
