package com.carrinho.carrinho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.carrinho.carrinho.model.Usuario;
import com.carrinho.carrinho.service.UsuarioService;

@Controller
public class UsuarioController {

    UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }


    @GetMapping("/index")
    public String getIndex(Model model){

        return "index.html";
    }


    @GetMapping("/cadastrarUsuario")
    public String cadastrarUsuario(Model model){
        Usuario u = new Usuario();
        model.addAttribute("usuario", u);
        return "cadastrarUsuario";
    }

    @PostMapping("/doSalvarUsuario")
    public String doSalvarUsuario(@ModelAttribute Usuario u){
        service.create(u);

        return "redirect:/";
    }

   
}
