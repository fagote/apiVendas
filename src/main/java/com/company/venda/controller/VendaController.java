package com.company.venda.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.venda.DTO.VendedorResumo;
import com.company.venda.model.Venda;
import com.company.venda.model.Vendedor;
import com.company.venda.repository.VendaRepository;
import com.company.venda.repository.VendedorRepository;


@RestController //serve para retorno de dados e os dados são gravados na resposta http em formato json
@RequestMapping("/vendas")
public class VendaController {
	
	//Injeção de dependências, não é necessário instanciar a classe com new
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
		diasAtivos = vendedor.addDataVenda(venda.getDataVenda());
		vendedor.setMedia_vendas_diaria((float)vendedor.getTotal_vendas()/(float)diasAtivos);
		vendedorR.save(vendedor);
		
		venda.setVendedor(vendedor);
		
		return vendaR.save(venda);
	}
	
	@GetMapping("/resumo")
	public List <VendedorResumo> resumoPeriodo(@RequestParam LocalDate inicio, @RequestParam LocalDate fim){
		
		List<Venda> vendas = vendaR.findByDataVendaBetween(inicio, fim);
		
		Map<Vendedor, List<Venda>> vendasPorVendedor = vendas.stream()
	            .collect(Collectors.groupingBy(Venda::getVendedor));
		
		List<VendedorResumo> resumo = new ArrayList<>();
		
		for (Map.Entry<Vendedor, List<Venda>> entry : vendasPorVendedor.entrySet()) {
	        Vendedor vendedor = entry.getKey();
	        List<Venda> vendasDoVendedor = entry.getValue();

	        int totalVendas = vendasDoVendedor.size();

	        // dias ativos distintos no período
	        long diasAtivos = vendasDoVendedor.stream()
	                .map(Venda::getDataVenda) 
	                .distinct()
	                .count();

	        float media = (diasAtivos > 0) ? (float) totalVendas / diasAtivos : 0;

	        resumo.add(new VendedorResumo(vendedor.getNome(), totalVendas, media));
	    }
		
		return resumo;
		
	}

}
