package com.carrinho.carrinho.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.carrinho.carrinho.model.Jogo;
import com.carrinho.carrinho.service.JogoService;
import com.carrinho.carrinho.util.UploadUtil;
import jakarta.validation.Valid;

@Controller

public class JogoController {


    JogoService service;
    @Autowired

    public JogoController(JogoService service) {
        this.service = service;
    }


    @GetMapping("/cadastrarJogo")
    public String getCadastrarJogo(Model model){
        Jogo j = new Jogo();
        model.addAttribute("jogo", j);
        return "cadastrarJogo";
    }


    @GetMapping("/editarJogo/{id}")
    public String getEditarJogo(@PathVariable(name = "id") long id, Model model){

        Optional<Jogo> j = service.findById(id);
        if (j.isPresent()){
            model.addAttribute("jogo", j.get());
        }else{
            return "redirect:/index";
        }

        return "editarJogo";
    }

    @GetMapping("/delete/{id}")
    public String getEditarPage(@PathVariable(name = "id") long id, Model model){

        Optional<Jogo> jogoOptional = service.findById(id);
        if (jogoOptional.isPresent()){
            Jogo jogo = jogoOptional.get();
            jogo.setDeleted(new Date());
            service.create(jogo);
            return "redirect:/index";
        }else{
            return "redirect:/index";
        }

       
    }


    @PostMapping(value = "/doCadastrarJogo")
    public String cadastrar(@ModelAttribute @Valid Jogo jogo, @RequestParam("file") MultipartFile imagem, Errors errors){
        if (errors.hasErrors()){
            System.out.println("erro: "+errors.getAllErrors());
            return "cadastrarJogo";
        }else{
            try {
                if (imagem != null && !imagem.isEmpty()) {
                    if (UploadUtil.fazerUploadImagem(imagem)) {
                        jogo.setImageUri(imagem.getOriginalFilename());
                    }
                }
                service.create(jogo);
                return "redirect:/index";
            }catch(Exception e){
                return "redirect:/index";
            }
            
        }
    }

    @GetMapping(value = {"/", "/index", "/index.html"})
    public String getIndex(Model model){
        List<Jogo> jogos = service.findAll();
        for (Jogo jogo : jogos) {
            String caminhoImagem = "images/img-Upload/" + jogo.getImageUri();
            jogo.setImageUri(caminhoImagem);
        }
        model.addAttribute("jogos", jogos);
        return "index";
    }
    
}
