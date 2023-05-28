package com.carrinho.carrinho.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carrinho.carrinho.model.Jogo;
import com.carrinho.carrinho.repository.JogoRepository;

@Service
public class JogoService {

    JogoRepository repository;


    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public List<Jogo> findAll(){
        return repository.findAll();
    }

}