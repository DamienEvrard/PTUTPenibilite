package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Mesure;
import com.ptut.penibilite.entities.Piece;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author damie
 */
@RestController
@RequestMapping(path = "/api/piece")
public class PieceRestController {

    @Autowired
    private PieceRepository pdao;

    @GetMapping("getDonnees")
    public JSONObject getDonnees(Model model, @RequestParam("id") int id){
        JSONObject json = new JSONObject();
        Piece piece = pdao.getOne(id);
        int cpt =0;
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
            donnees.put("libelle",c.getLibelle());
            donnees.put("type",c.getType().getLibelle());
            donnees.put("dates",date);
            donnees.put("valeurs",valeur);
            donnees.put("unite",unite);
            donnees.put("seuilMax",seuilMax);
            donnees.put("seuilMin",seuilMin);
            json.put(String.valueOf(cpt),donnees);
            cpt++;
        }
        model.addAttribute("donnees", json);
        return json;
    }

    /**
     * Supprime une pièce
     *
     * @param id id de la pièce
     * @return JSONObject  status = 0 || 1
     */
    @DeleteMapping(value = "delete")
    public JSONObject deletePiece(@RequestParam("id")int id){
        JSONObject rep = new JSONObject();
        try{
            pdao.deleteById(id);
            rep.put("status", 0);
        }catch (Exception e){
            rep.put("status", 1);
            rep.put("error",e);
            return rep;
        }
        return rep;
    }

    @GetMapping("getTypeCapteur")
    public JSONObject getTypeCapteur(Model model, @RequestParam("id") int id){
        JSONObject json = new JSONObject();
        Piece piece = pdao.getOne(id);
        List<String> listType = new ArrayList<>();
        for(Capteur c : piece.getCapteurs()){
            listType.add(c.getType().getLibelle());
        }
        json.put("types", listType);
        model.addAttribute("types", json);
        return json;
    }

}
