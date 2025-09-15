package com.company.venda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.venda.model.Vendedor;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {
	
	/*
	@GetMapping
	public List<Vendedor> listar() {
	
	}*/
	
	@GetMapping
	public String teste() {
		return "Hello world";
	}

}
