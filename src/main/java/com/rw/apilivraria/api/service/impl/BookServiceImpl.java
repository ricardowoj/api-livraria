package com.rw.apilivraria.api.service.impl;

import org.springframework.stereotype.Service;

import com.rw.apilivraria.api.model.Book;
import com.rw.apilivraria.api.repository.BookRepository;
import com.rw.apilivraria.api.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;
	
	

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}



	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

}
