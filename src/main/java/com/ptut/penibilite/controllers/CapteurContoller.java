package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.daos.TypeCapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Piece;
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
@RequestMapping(path = "/capteur")
public class CapteurContoller {

    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private TypeCapteurRepository typeCapteurRepository;

    @Autowired
    private CapteurRepository capteurRepository;

    /**
     * Affiche le formulaire d'ajout de capteur pour une pièce
     *
     * @param capteur modèle de donnée pour le formulaire
     * @param piece la pièce où on ajoute le capteur
     * @return vue formAjoutCapteur.html
     */
    @GetMapping("add")
    public String getFormCapteur(@ModelAttribute("newCapteur") Capteur capteur, Model model, @RequestParam("id") Piece piece){
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
    @PostMapping(path = "save")
    public String addCapteur(Capteur capteur, RedirectAttributes redirectInfo, @RequestParam("salle") Piece piece) {
        String message;
        try {
            capteurRepository.save(capteur);
            message = "Le capteur '" + capteur.getLibelle() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            message = "Erreur : Le capteur '" + capteur.getLibelle() + "' existe déjà";
        }
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:/capteur/add?id="+piece.getId();
    }

    /**
     * Affiche le formulaire de modification d'un type capteur
     *
     * @param c modèle de donnée pour le formulaire
     * @param myCapteur le type à modifier
     * @return vue formModifySensor.html
     */
    @GetMapping(path = "modify")
    public String viewModifyTypeCapteur(Model model, @ModelAttribute("setCapteur") Capteur c, @RequestParam("id") Capteur myCapteur) {
        model.addAttribute("pieces", pieceRepository.findAll());
        model.addAttribute("types", typeCapteurRepository.findAll());
        model.addAttribute("capteur", myCapteur);
        model.addAttribute("piece",myCapteur.getSalle());
        model.addAttribute("capteurs", myCapteur.getSalle().getCapteurs());
        return "formModifySensor";
    }

    /**
     * Appelé par 'formModifySensorType.html', méthode POST
     *
     * @param capteur Un capteur initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @param id id du capteur à modifier
     * @return une redirection vers le formulaire si pas de modification
     * @return une redirection vers le formAjoutCapteur si modifier
     */
    @PostMapping(path = "modify/save/{id}")
    public String modifyTypeCapteur(Capteur capteur, RedirectAttributes redirectInfo,@PathVariable int id) {
        String message;
        int cpt = 0;
        Capteur capteurToUpDate = capteurRepository.getOne(id);

        if (!capteur.getLibelle().isEmpty() && capteur.getLibelle() != capteurToUpDate.getLibelle()){
            capteurToUpDate.setLibelle(capteur.getLibelle());
            cpt++;
        }
        if (capteur.getFrequenceMesure() != capteurToUpDate.getFrequenceMesure()){
            capteurToUpDate.setFrequenceMesure(capteur.getFrequenceMesure());
            cpt++;
        }
        if (capteur.getType() != capteurToUpDate.getType()){
            capteurToUpDate.setType(capteur.getType());
            cpt++;
        }

        if (cpt>0){
            try {
                capteurRepository.save(capteurToUpDate);
                message = "Le capteur '" + capteurToUpDate.getLibelle() + "' a été correctement modifié";
            } catch (DataIntegrityViolationException e) {
                message = "Erreur mis-à-jour : du capteur '" + capteurToUpDate.getLibelle();
            }
            redirectInfo.addFlashAttribute("message", message);
            return "redirect:/capteur/add?id="+capteurToUpDate.getSalle().getId();
        }else {
            message = "Aucune modification pour le capteur " + capteurToUpDate.getLibelle();
            redirectInfo.addFlashAttribute("message", message);
            return "redirect:/capteur/modify?id="+id;
        }
    }
}