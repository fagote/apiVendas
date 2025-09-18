package com.company.venda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.company.venda.model.Venda;
import com.company.venda.model.Vendedor;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class VendaApiApplicationTests {
	
	@Autowired
	private TestRestTemplate test;

	@Test
	public Vendedor createVendedor() {
		// Arrange
		Vendedor vendedor = new Vendedor();
		vendedor.setNome("jurema");
		
		// Act
		Vendedor saved = test.postForObject("/vendedores", vendedor, Vendedor.class);
		
		// Assert
		assertNotNull(saved);
		assertNotNull(saved.getId());       
		assertEquals("jurema", saved.getNome()); 
		
		return saved;
	}
	
	@Test
	public void venda() {
        
        Vendedor vendedor = createVendedor();

        
        Venda venda = new Venda();
        venda.setVendedor(vendedor);
        venda.setDataVenda(LocalDate.of(2025,9,15));
        venda.setValor(200.0f);

        
        Venda saved = test.postForObject("/vendas", venda, Venda.class);

        
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertNotNull(saved.getVendedor());
        assertEquals("jurema", saved.getVendedor().getNome());
    
		
		
	}
}












