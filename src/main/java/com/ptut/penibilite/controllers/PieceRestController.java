package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.daos.MesureRepository;
import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Mesure;
import com.ptut.penibilite.entities.Piece;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author damie
 */
@RestController
@RequestMapping(path = "/piece")
public class PieceRestController {

    @Autowired
    private PieceRepository pdao;

    @GetMapping("getDonnees")
    public JSONObject getDonnees(Model model, @RequestParam("id") int id){
        JSONObject json = new JSONObject();
        Piece piece = pdao.getOne(id);
        for(Capteur c : piece.getCapteurs()){
            List<Mesure> listMesure;
            listMesure = c.getMesures();
            JSONObject donnees = new JSONObject();
            List<Float> valeur = new ArrayList<>();
            List<LocalDateTime> date = new ArrayList<>();
            for(Mesure m : listMesure){
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
            donnees.put("seuilMin",seuilMin);
            json.put(c.getLibelle(),donnees);
        }
        model.addAttribute("donnees", json);
        return json;
    }
}
