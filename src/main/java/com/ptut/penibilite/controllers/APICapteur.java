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

import javax.servlet.http.HttpServletResponse;

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
     * @return JSONObject contenant un message en fonction du resultat de loperation
     */
    @GetMapping(value = "/ajout", produces = {"application/json"})
    public JSONObject ajoutMesure(@RequestParam("id") int id, @RequestParam("valeur") float valeur, HttpServletResponse response ) {
        JSONObject json = new JSONObject();
        try {
            TypeCapteur type = tdao.getOne(cdao.getOne(id).getType().getId());
            if(valeur> type.getLimiteMax() || valeur < type.getLimiteMin()){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                json.put("message", "valeur erronée");
                return json;
            }
            LocalDateTime date = LocalDateTime.now();
            Capteur capteur = cdao.getOne(id);
            Mesure mesure = new Mesure(date,valeur,capteur);
            mdao.save(mesure);
            response.setStatus(HttpStatus.OK.value());
            json.put("message", "OK");
            return json;
        }catch (Exception e){
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            json.put("message", "erreur d'enregistrement");
            return json;
        }
    }

    /**
     * donne la frequence de mesure
     *
     * @param id id du capteur
     * @return JSONObject contenant la frequence
     */
    @GetMapping(value = "/frequence", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public JSONObject getFrequenceMesure(@RequestParam("id") int id, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        try {
            Capteur capteur = cdao.getOne(id);
            response.setStatus(HttpStatus.OK.value());
            json.put("frequence", capteur.getFrequenceMesure());
            return json;
        }catch (Exception e){
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            json.put("frequence", 0);
            return json;
        }
    }
}
