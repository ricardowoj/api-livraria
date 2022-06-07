package com.rw.apilivraria.api.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rw.apilivraria.api.dto.BookDTO;
import com.rw.apilivraria.api.model.Book;
import com.rw.apilivraria.api.service.BookService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	static String BOOK_API = "/api/books";

	@Autowired
	MockMvc mvc;
	
	@MockBean
	BookService bookService;
	
	@Test
	@DisplayName("Deve criar um livro com sucesso.")
	public void createdBookTest() throws Exception {
		
		BookDTO bookDTO = new BookDTO("As aventuras", "Artur", "001");
		Book savedBook = new Book(101l, "As aventuras", "Artur", "001");
		
		BDDMockito.given(bookService.save(Mockito.any(Book.class))).willReturn(savedBook);
		String json = new ObjectMapper().writeValueAsString(bookDTO);
		
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders
					.post(BOOK_API)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(json);
		
		mvc
			.perform(request)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("id").value(101))
			.andExpect(MockMvcResultMatchers.jsonPath("title").value(bookDTO.getTitle()))
			.andExpect(MockMvcResultMatchers.jsonPath("author").value(bookDTO.getAuthor()))
			.andExpect(MockMvcResultMatchers.jsonPath("isbn").value(bookDTO.getIsbn()));
	}
	
	@Test
	@DisplayName("Deve lançar erro de validação quando não houver dados suficiente para criação do livro.")
	public void createInvalidBookTest() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new BookDTO());
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders
					.post(BOOK_API)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(json);
		
		mvc.perform(request)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("errors", Matchers.hasSize(3)));
	}
}
