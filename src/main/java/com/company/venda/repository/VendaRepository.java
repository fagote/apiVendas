package com.company.venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.venda.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{

}
