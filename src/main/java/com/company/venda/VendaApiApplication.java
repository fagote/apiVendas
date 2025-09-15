package com.company.venda;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.company.venda.model.Vendedor;


@SpringBootApplication
public class VendaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendaApiApplication.class, args);
		
		Scanner ler = new Scanner(System.in);
		
		Vendedor vendedor = new Vendedor();
		
		//System.out.println("Informe o nome do vendedor: ");
		vendedor.setNome("Jurema 10");
	}

}
