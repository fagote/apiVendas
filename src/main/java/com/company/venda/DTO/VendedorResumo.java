package com.company.venda.DTO;

public class VendedorResumo {

	private String nome;
	private int totalVendas;
	private float mediaVendasDiaria;
	
	public VendedorResumo(String nome, int totalVendas, float mediaDiaria) {
		
		this.nome = nome;
		this.totalVendas = totalVendas;
		this.mediaVendasDiaria = mediaDiaria;
		
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getTotalVendas() {
		return this.totalVendas;
	}
	
	public float getMediaVendasDiaria() {
		return this.mediaVendasDiaria;
	}
	
	
}
