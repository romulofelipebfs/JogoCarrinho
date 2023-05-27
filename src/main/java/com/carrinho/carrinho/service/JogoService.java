package com.carrinho.carrinho.service;

import com.carrinho.carrinho.repository.JogoRepository;

public class JogoService {

    JogoRepository repository;


    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    

}