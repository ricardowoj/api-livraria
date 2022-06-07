package com.rw.apilivraria.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rw.apilivraria.api.dto.BookDTO;
import com.rw.apilivraria.api.exceptions.ApiErrros;
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
	public BookDTO create(@RequestBody @Valid BookDTO bookDTO) {
		Book book = new Book();
		book.setAuthor(bookDTO.getAuthor());
		book.setTitle(bookDTO.getTitle());
		book.setIsbn(bookDTO.getIsbn());
		book = bookService.save(book);
		bookDTO.setId(book.getId());
		return bookDTO;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrros handleValidationExceptions(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		return new ApiErrros(bindingResult);
	}
}
