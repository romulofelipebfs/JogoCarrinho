package com.carrinho.carrinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carrinho.carrinho.model.Jogo;



public interface JogoRepository extends JpaRepository<Jogo, Long> {
      
}