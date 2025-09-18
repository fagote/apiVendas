package com.company.venda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.venda.model.Vendedor;
import com.company.venda.repository.VendedorRepository;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {
	
	@Autowired
	private VendedorRepository vendedorRepo;
	
	@GetMapping
	public List<Vendedor> listar() {
		return vendedorRepo.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Vendedor cadastro(@RequestBody Vendedor vendedor) {
		return vendedorRepo.save(vendedor);
	}
	

}
