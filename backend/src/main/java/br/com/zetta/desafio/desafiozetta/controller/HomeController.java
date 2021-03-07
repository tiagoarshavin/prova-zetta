package br.com.zetta.desafio.desafiozetta.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "Desafio Zetta!";
    }
}