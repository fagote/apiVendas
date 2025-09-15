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
import com.company.venda.model.Vendedor;
import com.company.venda.repository.VendaRepository;
import com.company.venda.repository.VendedorRepository;


@RestController 
@RequestMapping("/vendas")
public class VendaController {
	
	@Autowired
	private VendaRepository vendaR;
	
	@Autowired
	private VendedorRepository vendedorR;
	
	@GetMapping
	public List<Venda> listar(){
		return vendaR.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Venda cadastro(@RequestBody Venda venda) {
		
		int diasAtivos = 0;
		
		Vendedor vendedor = vendedorR.findById(venda.getVendedor().getId())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
		
		vendedor.setTotal_vendas(vendedor.getTotal_vendas()+1);
		diasAtivos = vendedor.addDataVenda(venda.getData_venda());
		vendedor.setMedia_vendas_diaria((float)vendedor.getTotal_vendas()/(float)diasAtivos);
		vendedorR.save(vendedor);
		
		venda.setVendedor(vendedor);
		
		return vendaR.save(venda);
	}

}
