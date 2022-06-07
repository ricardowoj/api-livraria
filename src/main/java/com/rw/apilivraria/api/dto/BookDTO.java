package com.rw.apilivraria.api.dto;

import javax.validation.constraints.NotEmpty;

public class BookDTO {
	private Long id;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String author;
	
	@NotEmpty
	private String isbn;

	public BookDTO() {
	}

	public BookDTO(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
