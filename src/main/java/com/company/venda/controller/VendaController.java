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

import com.company.venda.model.Venda;
import com.company.venda.repository.VendaRepository;


@RestController 
@RequestMapping("/vendas")
public class VendaController {
	
	@Autowired
	private VendaRepository vendaR;
	
	@GetMapping
	public List<Venda> listar(){
		return vendaR.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Venda cadastro(@RequestBody Venda venda) {
		return vendaR.save(venda);
	}

}
