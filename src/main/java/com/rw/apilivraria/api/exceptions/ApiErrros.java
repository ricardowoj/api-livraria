package com.rw.apilivraria.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

public class ApiErrros {
	private List<String> errors;
	
	public ApiErrros(BindingResult bindingResult) {
		this.errors = new ArrayList<>();
		bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
	}
	
	public List<String> getErrors() {
		return errors;
	}
}
