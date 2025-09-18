package com.company.venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.venda.model.Vendedor;


// a interface repository implementa as funções básicas de crud() para evitar retrabalho
@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

}
