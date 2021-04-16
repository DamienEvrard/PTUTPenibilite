package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.entities.Piece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/piece")
public class PieceController {

    @Autowired
    private PieceRepository pieceRepository;

    @GetMapping("")
    public String getPiece(Model model, @RequestParam("id") Piece piece){
        model.addAttribute("pieces", pieceRepository.findAll());
        model.addAttribute("piece", piece);
        model.addAttribute("capteurs",piece.getCapteurs());
        return "piece";
    }

    @GetMapping("add")
    public String getPiece(Model model){
        model.addAttribute("pieces", pieceRepository.findAll());
        return "formAjoutPiece";
    }


}
