package com.carrinho.carrinho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.carrinho.carrinho.model.Jogo;
import com.carrinho.carrinho.service.JogoService;

@Controller
public class JogoController {

    JogoService service;


    @GetMapping("/cadastrarJogo")
    public String getCadastrarJogo(Model model){
        Jogo j = new Jogo();
        model.addAttribute("jogo", j);
        return "cadastrarJogo";
    }

    
    

}
