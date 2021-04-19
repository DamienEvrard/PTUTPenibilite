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
    public String addTypeCapteur(TypeCapteur type, RedirectAttributes redirectInfo) {
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

    /**
     * Affiche le formulaire de modification d'un type capteur
     *
     * @param typeCapteur modèle de donnée pour le formulaire
     * @param myType le type à modifier
     * @return vue formModifySensorType.html
     */
    @GetMapping(path = "modify")
    public String viewModifyTypeCapteur(Model model, @ModelAttribute("typeC") TypeCapteur typeCapteur,@RequestParam("id") TypeCapteur myType) {
        model.addAttribute("pieces", pieceRepository.findAll());
        model.addAttribute("types", typeCapteurRepository.findAll());
        model.addAttribute("type", myType);
        return "formModifySensorType";
    }

    /**
     * Appelé par 'formModifySensorType.html', méthode POST
     *
     * @param type Un type de capteur initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @param id id du type de capteur à modifier
     * @return une redirection vers le formulaire si pas de modification
     * @return une redirection vers le formAjoutTypeCapteur si modifier
     */
    @PostMapping(path = "modify/save/{id}")
    public String modifyTypeCapteur(TypeCapteur type, RedirectAttributes redirectInfo,@PathVariable int id) {
        String message;
        int cpt = 0;
        TypeCapteur typeToUpDate = typeCapteurRepository.getOne(id);

        if (!type.getLibelle().isEmpty() && type.getLibelle() != typeToUpDate.getLibelle()){
            typeToUpDate.setLibelle(type.getLibelle());
            cpt++;
        }
        if (type.getLimiteMin() != typeToUpDate.getLimiteMin()){
            typeToUpDate.setLimiteMin(type.getLimiteMin());
            cpt++;
        }
        if (type.getLimiteMax() != typeToUpDate.getLimiteMax()){
            typeToUpDate.setLimiteMax(type.getLimiteMax());
            cpt++;
        }
        if (type.getSeuilMin() != typeToUpDate.getSeuilMin()){
            typeToUpDate.setSeuilMin(type.getSeuilMin());
            cpt++;
        }
        if (type.getSeuilMax() != typeToUpDate.getSeuilMax()){
            typeToUpDate.setSeuilMax(type.getSeuilMax());
            cpt++;
        }
        if (!type.getUnite().isEmpty() && type.getUnite() != typeToUpDate.getUnite()){
            typeToUpDate.setUnite(type.getUnite());
            cpt++;
        }

        if (cpt>0){
            try {
                typeCapteurRepository.save(typeToUpDate);
                message = "Les modification du type '" + typeToUpDate.getLibelle() + "' ont été correctement enregistrées";
            } catch (DataIntegrityViolationException e) {
                message = "Erreur mis-à-jour : du type '" + typeToUpDate.getLibelle();
            }
            redirectInfo.addFlashAttribute("message", message);
            return "redirect:/type/add";
        }else {
            message = "Aucune modification apporté pour le type '" + typeToUpDate.getLibelle();
            redirectInfo.addFlashAttribute("message", message);
            return "redirect:/type/modify?id="+id;
        }
    }
}