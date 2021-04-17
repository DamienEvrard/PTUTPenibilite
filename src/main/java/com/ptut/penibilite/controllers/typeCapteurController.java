package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.daos.TypeCapteurRepository;
import com.ptut.penibilite.entities.TypeCapteur;
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
@RequestMapping(path = "/type")
public class typeCapteurController {

    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private TypeCapteurRepository typeCapteurRepository;

    /**
     * Affiche le formulaire d'ajout de type capteur
     *
     * @param typeCapteur modèle de donnée pour le formulaire
     * @return vue formAjoutTypeCapteur.html
     */
    @GetMapping("add")
    public String getPiece(Model model, @ModelAttribute("typeC") TypeCapteur typeCapteur){
        model.addAttribute("pieces", pieceRepository.findAll());
        model.addAttribute("types", typeCapteurRepository.findAll());
        return "formAjoutTypeCapteur";
    }

    /**
     * Appelé par 'formulaireAjoutTypeCapteur.html', méthode POST
     *
     * @param type Un type de capteur initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers le formulaire
     */
    @PostMapping(path = "save")
    public String addCapteur(TypeCapteur type, RedirectAttributes redirectInfo) {
        String message;
        try {
            typeCapteurRepository.save(type);
            message = "Le type capteur '" + type.getLibelle() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            message = "Erreur : Le type capteur '" + type.getLibelle() + "' existe déjà";
        }
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:add";
    }
}
