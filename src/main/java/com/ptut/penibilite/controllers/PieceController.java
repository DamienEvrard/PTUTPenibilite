package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.daos.TypeCapteurRepository;
import com.ptut.penibilite.entities.Capteur;
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

    @Autowired
    private TypeCapteurRepository typeCapteurRepository;

    @GetMapping("")
    public String getPiece(Model model, @RequestParam("id") Piece piece){
        model.addAttribute("pieces", pieceRepository.findAll());
        model.addAttribute("piece", piece);
        model.addAttribute("capteurs",piece.getCapteurs());
        return "piece";
    }

    @GetMapping("add")
    public String getPiece(@ModelAttribute("piece") Piece piece, Model model){
        model.addAttribute("pieces", pieceRepository.findAll());
        return "formAjoutPiece";
    }

    /**
     * Appelé par 'formulaireAjoutPiece.html', méthode POST
     *
     * @param piece Une piece initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des tableaux
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
     * Affiche le formulaire d'ajout de capteur pour une pièce
     *
     * @param capteur modèle de donnée pour le formulaire
     * @param piece la pièce où on ajoute le capteur
     * @return vue formAjoutCapteur.html
     */
    @GetMapping("capteur/add")
    public String getPiece(@ModelAttribute("capteur") Capteur capteur, Model model, @RequestParam("id") Piece piece){
        model.addAttribute("pieces", pieceRepository.findAll());
        model.addAttribute("capteurs", piece.getCapteurs());
        model.addAttribute("typeCapteur", typeCapteurRepository.findAll());
        model.addAttribute("piece", piece);

        return "formAjoutCapteur";
    }
}