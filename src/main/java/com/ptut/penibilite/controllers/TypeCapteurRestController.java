package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.daos.TypeCapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Mesure;
import com.ptut.penibilite.entities.Piece;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Marie Cagnasso
 */
@RestController
@RequestMapping("/api/type")
public class TypeCapteurRestController {


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
    public JSONObject depassementSeuil(@RequestParam("id")int id,@RequestParam("dateMin") String dateMin,@RequestParam("dateMax") String dateMax){
        JSONObject json = new JSONObject();
        Piece piece = pdao.getOne(id);
        int cpt=0;
        for(Capteur c : piece.getCapteurs()){
            JSONObject donnees = new JSONObject();
            int depassement=0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime dMin =LocalDateTime.parse(dateMin+ " 00:00:00", formatter);;
            LocalDateTime dMax =LocalDateTime.parse(dateMax+ " 00:00:00", formatter);;

            for(Mesure m : c.getMesures()){
                if((m.getValeur()>=c.getType().getSeuilMax())||(m.getValeur()<=c.getType().getSeuilMin())&&(m.getDate().isAfter(dMin))&&(m.getDate().isBefore(dMax))){
                    depassement++;
                }
            }
            donnees.put("libelle",c.getLibelle());
            donnees.put("depassement",depassement);
            json.put(String.valueOf(cpt),donnees);
            cpt++;
        }
        return json;
    }
}
