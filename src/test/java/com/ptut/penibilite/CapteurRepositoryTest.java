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

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@DataJpaTest
public class CapteurRepositoryTest {

    @Autowired
    private CapteurRepository cdao;

    @Autowired
    private PieceRepository pdao;

    @Autowired
    private TypeCapteurRepository tdao;

    @Autowired
    private MesureRepository mdao;


    @Test
    public void capteurDansPieceEtType() {
        Piece salle = new Piece("salle1");
        TypeCapteur tCapteur = new TypeCapteur("Type1");
        Capteur capteur = new Capteur("capteur1", 120, salle, tCapteur);
        assertEquals(salle.getId(), capteur.getSalle().getId());
        assertEquals(tCapteur.getId(), capteur.getType().getId());
    }

    @Test
    public void capteurEnregistre() {
        Piece salle = new Piece("salle1");
        TypeCapteur tCapteur = new TypeCapteur("Type1");
        pdao.save(salle);
        tdao.save(tCapteur);

        Capteur capteur = new Capteur("capteur1", 120, salle, tCapteur);
        cdao.save(capteur);
        List<Capteur> c = cdao.findAll();
        assertEquals(1, c.size());
    }

    @Test
    public void capteurMesure() {
        LocalDateTime d1 = LocalDateTime.now();
        Piece salle = new Piece("salle1");
        TypeCapteur tCapteur = new TypeCapteur("Type1");
        pdao.save(salle);
        tdao.save(tCapteur);
        Capteur capteur = new Capteur("capteur1", 120, salle, tCapteur);
        cdao.save(capteur);

        Mesure m1 = new Mesure(d1,45,capteur);
        Mesure m2 = new Mesure(d1,12,capteur);
        mdao.save(m1);
        mdao.save(m2);

        List<Mesure> lMesure = new ArrayList<Mesure>();
        lMesure.add(m1);
        lMesure.add(m2);
        capteur.setMesures(lMesure);
        cdao.save(capteur);

        List<Capteur> c = cdao.findAll();
        assertEquals(45, c.get(0).getMesures().get(0).getValeur());
    }
}
