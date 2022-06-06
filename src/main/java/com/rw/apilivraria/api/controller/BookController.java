package com.rw.apilivraria.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rw.apilivraria.api.dto.BookDTO;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookDTO create() {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(1l);
		bookDTO.setAuthor("Author");
		bookDTO.setTitle("Meu Livro");
		bookDTO.setIsbn("1213212");
		return bookDTO;
	}
}
