package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.daos.TypeCapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Mesure;
import com.ptut.penibilite.entities.Piece;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marie Cagnasso
 */
@RestController
@RequestMapping("/api/type")
public class typeCapteurRestController {


    @Autowired
    private TypeCapteurRepository typeCapteurRepository;

    @Autowired
    private PieceRepository pdao;

    /**
     * Supprime un type de capteur
     *
     * @param id id du type de capteur
     * @return JSONObject  status = 0 || 1
     */
    @DeleteMapping(value = "delete")
    public JSONObject deleteTypeCapteur(@RequestParam("id")int id){
        JSONObject rep = new JSONObject();
        try{
            typeCapteurRepository.deleteById(id);
            rep.put("status", 0);
        }catch (Exception e){
            rep.put("status", 1);
            rep.put("error",e);
            return rep;
        }
        return rep;
    }

    /**
     *
     * @param id id de la piece
     * @return le nombre de mesures depassant les seuils en format Json
     */
    @GetMapping(value = "depassement")
    public JSONObject depassementSeuil(@RequestParam("id")int id){
        JSONObject json = new JSONObject();
        Piece piece = pdao.getOne(id);
        int compteur=0;
        for(Capteur c : piece.getCapteurs()){
            for(Mesure m : c.getMesures()){
                if((m.getValeur()>=c.getType().getSeuilMax())||(m.getValeur()<=c.getType().getSeuilMin())){
                    compteur++;
                }
            }
            json.put("libelle",c.getLibelle());
            json.put("depassement",compteur);
        }
        return json;
    }
}
