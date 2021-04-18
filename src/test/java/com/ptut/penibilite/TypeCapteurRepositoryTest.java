package com.ptut.penibilite;

import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.daos.MesureRepository;
import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.daos.TypeCapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Mesure;
import com.ptut.penibilite.entities.Piece;
import com.ptut.penibilite.entities.TypeCapteur;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Log4j2
@DataJpaTest
public class TypeCapteurRepositoryTest {

    @Autowired
    private CapteurRepository cdao;

    @Autowired
    private PieceRepository pdao;

    @Autowired
    private TypeCapteurRepository tdao;

    @Autowired
    private MesureRepository mdao;

    @Test
    public void depacementSeuil() {
        Piece salle = new Piece("salle1");
        TypeCapteur tCapteur = new TypeCapteur("Type1","unite1",10,0);
        Capteur capteur = new Capteur("capteur1", 120, salle, tCapteur);

        pdao.save(salle);
        tdao.save(tCapteur);
        cdao.save(capteur);

        LocalDateTime date = LocalDateTime.now();
        Mesure m1 = new Mesure(date,45,capteur);
        Mesure m2 = new Mesure(date,12,capteur);
        Mesure m3 = new Mesure(date,5,capteur);
        Mesure m4 = new Mesure(date,4,capteur);
        Mesure m5 = new Mesure(date,-1,capteur);

        mdao.save(m1);
        mdao.save(m2);
        mdao.save(m3);
        mdao.save(m4);
        mdao.save(m5);

        List<Mesure> lMesure = new ArrayList<Mesure>();
        lMesure.add(m1);
        lMesure.add(m2);
        lMesure.add(m3);
        lMesure.add(m4);
        lMesure.add(m5);
        capteur.setMesures(lMesure);
        cdao.save(capteur);

        assertEquals(3, tdao.depacementSeuil(salle.getId(),tCapteur.getLibelle()));
    }
    
}
