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

    @Autowired
    private CapteurRepository capteurRepository;

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
     * Affiche le formulaire d'ajout de capteur pour une pièce
     *
     * @param capteur modèle de donnée pour le formulaire
     * @param piece la pièce où on ajoute le capteur
     * @return vue formAjoutCapteur.html
     */
    @GetMapping("capteur/add")
    public String getPiece(@ModelAttribute("newCapteur") Capteur capteur, Model model, @RequestParam("id") Piece piece){
        model.addAttribute("pieces", pieceRepository.findAll());
        model.addAttribute("capteurs", piece.getCapteurs());
        model.addAttribute("typeCapteur", typeCapteurRepository.findAll());
        model.addAttribute("piece", piece);

        return "formAjoutCapteur";
    }

    /**
     * Appelé par 'formulaireAjoutCapteur.html', méthode POST
     *
     * @param capteur Un capteur initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers le formulaire
     */
    @PostMapping(path = "capteur/save")
    public String addCapteur(Capteur capteur, RedirectAttributes redirectInfo, @RequestParam("salle") Piece piece) {
        String message;
        try {
            capteurRepository.save(capteur);
            message = "Le capteur '" + capteur.getLibelle() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            message = "Erreur : Le capteur '" + capteur.getLibelle() + "' existe déjà";
        }
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:/piece/capteur/add?id="+piece.getId();
    }
}