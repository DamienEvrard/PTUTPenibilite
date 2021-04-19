package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.entities.Piece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Marie Cagnasso
 */
@Controller
@RequestMapping(path = "/piece")
public class PieceController {

    @Autowired
    private PieceRepository pieceRepository;

    @GetMapping("")
    public String getPiece(Model model, @RequestParam("id") Piece piece,@ModelAttribute("upDatePiece") Piece p){
        model.addAttribute("pieces", pieceRepository.findAll());
        model.addAttribute("piece", piece);
        model.addAttribute("capteurs",piece.getCapteurs());
        model.addAttribute("types",piece.getTypeCapteur());
        return "piece";
    }

    @GetMapping("add")
    public String getAddPiece(@ModelAttribute("piece") Piece piece, Model model){
        model.addAttribute("pieces", pieceRepository.findAll());
        return "formAjoutPiece";
    }

    /**
     * Appelé par 'formulaireAjoutPiece.html', méthode POST
     *
     * @param piece Une piece initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers le formulaire
     */
    @PostMapping(path = "save")
    public String addPiece(Piece piece, RedirectAttributes redirectInfo) {
        String message;
        try {
            pieceRepository.save(piece);
            message = "La pièce '" + piece.getLibelle() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            message = "Erreur : La pièce '" + piece.getLibelle() + "' existe déjà";
        }
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:add";
    }

    /**
     * Appelé par 'piece.html', méthode POST
     *
     * @param piece Une piece initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @param id id de la pièce à modifier
     * @return une redirection vers piece.html
     */
    @PostMapping(path = "modify")
    public String modifyPiece(Piece piece, RedirectAttributes redirectInfo,@RequestParam int id) {
        String message;
        message = "";
        int cpt = 0;
        Piece pieceToUpDate = pieceRepository.getOne(id);

        if (!piece.getLibelle().isEmpty() && piece.getLibelle() != pieceToUpDate.getLibelle()){
            pieceToUpDate.setLibelle(piece.getLibelle());
            cpt++;
        }

        if (cpt>0){
            try {
                pieceRepository.save(pieceToUpDate);
            } catch (DataIntegrityViolationException e) {
                message = "Échec de de la modification.";
            }
        }
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:/piece?id="+id;
    }
}