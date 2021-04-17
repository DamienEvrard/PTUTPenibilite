package com.ptut.penibilite.controllers;

import com.ptut.penibilite.daos.TypeCapteurRepository;
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
}
