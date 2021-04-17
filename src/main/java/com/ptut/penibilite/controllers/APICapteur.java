/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.MesureRepository;
import com.ptut.penibilite.entities.Mesure;
import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
    
    /**
     * Enregistre une mesure
     *
     * @param id id du capteur emetteur
     * @param valeur valeur de la mesure
     */
    @GetMapping(value = "/ajout", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public JSONObject ajoutMesure(@RequestParam("id") int id, @RequestParam("valeur") float valeur) {
        JSONObject json = new JSONObject();
        try {
            LocalDateTime date = LocalDateTime.now();
            Capteur capteur = cdao.getOne(id);
            Mesure mesure = new Mesure(date,valeur,capteur);
            mdao.save(mesure);
            json.put("status", 0);
            return json;
        }catch (Exception e){
            json.put("status", 1);
            return json;
        }
    }
}
