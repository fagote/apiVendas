package com.company.venda.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private int total_vendas = 0;
	private float media_vendas_diaria = 0;
	//private int diasAtivos = 0;
	
	@ElementCollection
	private List<LocalDate> datas_venda;
	
	
	
	public List<LocalDate> getDatas_venda() {
		return datas_venda;
	}
	
	public int addDataVenda(LocalDate data) {
		
		if (this.datas_venda == null) {
	        this.datas_venda = new ArrayList<>();
	    }

	    if (!this.datas_venda.contains(data)) {
	        this.datas_venda.add(data);
	    }
	    
	    return datas_venda.size();
    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTotal_vendas() {
		return total_vendas;
	}
	public void setTotal_vendas(int total_vendas) {
		this.total_vendas = total_vendas;
	}
	public float getMedia_vendas_diaria() {
		return media_vendas_diaria;
	}
	public void setMedia_vendas_diaria(float media_vendas_diaria) {
		this.media_vendas_diaria = media_vendas_diaria;
	}
	
	
	
	/*
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}*/
	
	
	// serve para o java comparar dois objetos iguais se forem usados com 
	//hashmap e hashset
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
