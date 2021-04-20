package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.CapteurRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marie Cagnasso
 */
@RestController
@RequestMapping("/api/capteur")
public class capteurRestController {

    @Autowired
    private CapteurRepository capteurRepository;

    /**
     * Supprime un capteur
     *
     * @param id id du capteur
     * @return JSONObject  status = 0 || 1
     */
    @DeleteMapping(value = "delete")
    public JSONObject deleteCapteur(@RequestParam("id")int id){
        JSONObject rep = new JSONObject();
        try{
            capteurRepository.deleteById(id);
            rep.put("status", 0);
        }catch (Exception e){
            rep.put("status", 1);
            rep.put("error",e);
            return rep;
        }
        return rep;
    }
}
