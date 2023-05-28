package com.carrinho.carrinho.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;

import com.carrinho.carrinho.model.Jogo;
import com.carrinho.carrinho.repository.JogoRepository;
import com.carrinho.carrinho.service.JogoService;
import com.carrinho.carrinho.util.UploadUtil;

@Controller
//@RequestMapping("/Jogo")
public class JogoController {


    JogoService service;
    @Autowired
    private JogoRepository jogoRepo;

    public JogoController(JogoService service) {
        this.service = service;
    }


    @GetMapping("/cadastrarJogo")
    public String getCadastrarJogo(Model model){
        Jogo j = new Jogo();
        model.addAttribute("jogo", j);
        return "cadastrarJogo";
    }

   
    @PostMapping("/doCadastrarJogo")
    public String cadastro(@ModelAttribute Jogo jogo, @RequestParam("file") MultipartFile imagem){

        ModelAndView mv = new ModelAndView("/doCadastrarJogo");
        mv.addObject("usuario", jogo);

        try {
            
            if(UploadUtil.fazerUploadImagem(imagem)){
                jogo.setImageUri(imagem.getOriginalFilename());;
            }
            jogoRepo.save(jogo);
            System.out.println("Salvo com sucesso: " + jogo.getNome() + " " + jogo.getImageUri());
            return "redirect:/";

        } catch (Exception e) {
            mv.addObject("msErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return "redirect:/";
        }
        
    }
    
}
