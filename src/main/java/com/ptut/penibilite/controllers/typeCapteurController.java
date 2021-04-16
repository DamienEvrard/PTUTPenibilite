package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.daos.TypeCapteurRepository;
import com.ptut.penibilite.entities.TypeCapteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Marie Cagnasso
 */
@Controller
@RequestMapping(path = "/type")
public class typeCapteurController {

    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private TypeCapteurRepository typeCapteurRepository;

    @GetMapping("/add")
    public String getPiece(Model model, @ModelAttribute("typeC") TypeCapteur typeCapteur){
        model.addAttribute("pieces", pieceRepository.findAll());
        model.addAttribute("types", typeCapteurRepository.findAll());
        return "formAjoutTypeCapteur";
    }
}
