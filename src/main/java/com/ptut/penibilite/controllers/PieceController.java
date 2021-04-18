package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.daos.MesureRepository;
import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.daos.TypeCapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Mesure;
import com.ptut.penibilite.entities.Piece;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marie Cagnasso
 */
@Controller
@RequestMapping(path = "/piece")
public class PieceController {

    @Autowired
    private PieceRepository pieceRepository;
    @Autowired
    private CapteurRepository capteurRepository;
    @Autowired
    private MesureRepository mesureRepository;

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

    @GetMapping("getDonnees")
    public String getDonnees(Model model, @RequestParam("id") int id){
        JSONObject json = new JSONObject();
        Piece piece = pieceRepository.getOne(id);
        for(Capteur c : piece.getCapteurs()){
            List<Mesure> listMesure;
            listMesure = c.getMesures();
            JSONObject donnees = new JSONObject();
            List<Float> valeur = new ArrayList<>();
            List<LocalDateTime> date = new ArrayList<>();
            for(Mesure m : listMesure){
                date = new ArrayList<>();
                valeur = new ArrayList<>();
                valeur.add(m.getValeur());
                date.add(m.getDate());
            }
            String unite = c.getType().getUnite();
            Float seuilMax = c.getType().getSeuilMax();
            Float seuilMin = c.getType().getSeuilMin();
            donnees.put("dates",date);
            donnees.put("valeurs",valeur);
            donnees.put("unite",unite);
            donnees.put("seuilMax",seuilMax);
            donnees.put("seuilMain",seuilMin);
            json.put(c.getLibelle(),donnees);
        }
        model.addAttribute("donnees", json);
        return "donnees";
    }

}