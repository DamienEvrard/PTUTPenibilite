package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    private PieceRepository pieceRepository;


    @GetMapping(path = "/")
    public String afficheToutesLesPiècesDansLeMenu(Model model) {
        model.addAttribute("pieces", pieceRepository.findAll());
        return "index";
    }
}
