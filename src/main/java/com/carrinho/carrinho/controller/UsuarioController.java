package com.carrinho.carrinho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.carrinho.carrinho.service.UsuarioService;

import ch.qos.logback.core.model.Model;

@Controller
public class UsuarioController {

    UsuarioService service;


    @GetMapping("/index")
    public String getIndex(Model model){

        return "index.html";
    }

}
