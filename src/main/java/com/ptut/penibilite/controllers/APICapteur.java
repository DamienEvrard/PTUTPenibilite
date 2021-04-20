/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.MesureRepository;
import com.ptut.penibilite.daos.TypeCapteurRepository;
import com.ptut.penibilite.entities.Mesure;
import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.ptut.penibilite.entities.TypeCapteur;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author damie
 */
@RestController
@RequestMapping(path = "/APIcapteur")
public class APICapteur {

    @Autowired
    MesureRepository mdao;
    @Autowired
    CapteurRepository cdao;
    @Autowired
    TypeCapteurRepository tdao;
    
    /**
     * Enregistre une mesure
     *
     * @param id id du capteur emetteur
     * @param valeur valeur de la mesure
     * @return JSONObject contenant le status de la requete et un message
     */
    @GetMapping(value = "/ajout", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public JSONObject ajoutMesure(@RequestParam("id") int id, @RequestParam("valeur") float valeur) {
        JSONObject json = new JSONObject();
        try {
            TypeCapteur type = tdao.getOne(cdao.getOne(id).getType().getId());
            if(valeur> type.getLimiteMax() || valeur < type.getLimiteMin()){
                json.put("status", HttpStatus.BAD_REQUEST);
                json.put("message", "valeur erronée");
                return json;
            }
            LocalDateTime date = LocalDateTime.now();
            Capteur capteur = cdao.getOne(id);
            Mesure mesure = new Mesure(date,valeur,capteur);
            mdao.save(mesure);
            json.put("status", HttpStatus.OK);
            return json;
        }catch (Exception e){
            json.put("status", HttpStatus.UNPROCESSABLE_ENTITY);
            json.put("message", "erreur d'enregistrement");
            return json;
        }
    }

    /**
     * donne la frequence de mesure
     *
     * @param id id du capteur
     * @return JSONObject contenant le status de la requete et la frequence
     */
    @GetMapping(value = "/frequence", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public JSONObject getFrequenceMesure(@RequestParam("id") int id) {
        JSONObject json = new JSONObject();
        try {
            Capteur capteur = cdao.getOne(id);
            json.put("status", HttpStatus.OK);
            json.put("frequence", capteur.getFrequenceMesure());
            return json;
        }catch (Exception e){
            json.put("status", HttpStatus.UNPROCESSABLE_ENTITY);
            json.put("frequence", 0);
            return json;
        }
    }
}
