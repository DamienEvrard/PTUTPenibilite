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
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author damie
 */
@Controller
@RequestMapping(path = "/APICapteur")
public class CapteurControlleur {
    
    MesureRepository mdao;
    CapteurRepository cdao;
    
    /**
     * Enregistre une mesure
     *
     * @param id id du capteur emetteur
     * @param date date de l'envoi
     * @param valeur valeur de la mesure
     */
    @GetMapping(path = "ajout")
    public void ajoutMesure(@RequestParam("id") int id, @RequestParam("date") LocalDateTime date, @RequestParam("valeur") float valeur) {
        Capteur capteur= new Capteur();
        Optional<Capteur> c = cdao.findById(id);
        capteur.setFrequenceMesure(c.get().getFrequenceMesure());
        capteur.setId(c.get().getId());
        capteur.setLibelle(c.get().getLibelle());
        capteur.setSalle(c.get().getSalle());

        Mesure mesure = new Mesure(date, valeur, capteur);
        try {
            mdao.save(mesure);
        } catch (DataIntegrityViolationException e) {
            System.out.println("erreur enregistrement Mesure");
        }
    }
}
