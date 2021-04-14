/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.MesureRepository;
import entities.Mesure;
import java.util.Date;
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
public class API {
    
    MesureRepository mdao;
    
    /**
     * Enregistre une mesure
     *
     * @param id id du capteur emetteur
     * @param date date de l'envoi
     * @param valeur valeur de la mesure
     */
    @GetMapping(path = "ajout")
    public void ajoutMesure(@RequestParam("id") int id, @RequestParam("date") Date date, @RequestParam("valeur") float valeur) {
        Mesure mesure = new Mesure(id, date, valeur);
        try {
            mdao.save(mesure);
            
        } catch (DataIntegrityViolationException e) {
            System.out.println("erreur enregistrement Mesure");
        }
    }
}
