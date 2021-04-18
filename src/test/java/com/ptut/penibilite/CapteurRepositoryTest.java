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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        TypeCapteur tCapteur = new TypeCapteur("Type1","unite1",10,0);
        Capteur capteur = new Capteur("capteur1", 120, salle, tCapteur);
        assertEquals(salle.getId(), capteur.getSalle().getId());
        assertEquals(tCapteur.getId(), capteur.getType().getId());
    }

    @Test
    public void capteurEnregistre() {
        Piece salle = new Piece("salle1");
        TypeCapteur tCapteur = new TypeCapteur("type1","unite1",10,0);
        pdao.save(salle);
        tdao.save(tCapteur);

        Capteur capteur = new Capteur("capteur1", 120, salle, tCapteur);
        cdao.save(capteur);
        List<Capteur> c = cdao.findAll();
        assertEquals(10, c.size());
    }

    @Test
    public void getMesures() {
        LocalDateTime d1 = LocalDateTime.now();
        String d = "2022-03-28 12:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime d2 = LocalDateTime.parse(d, formatter);

        Capteur capteur = cdao.getOne(1);

        Mesure m1 = new Mesure(d1,45,capteur);
        Mesure m2 = new Mesure(d2,12,capteur);
        mdao.save(m1);
        mdao.save(m2);

        List<Mesure> lMesure = new ArrayList<Mesure>();
        lMesure.add(m1);
        lMesure.add(m2);
        capteur.setMesures(lMesure);
        cdao.save(capteur);

        List<Capteur> listC = cdao.findAll();

        String date = "2022-03-28 11:30:00";
        LocalDateTime d3 = LocalDateTime.parse(date, formatter);

        assertEquals(12, listC.get(0).getMesures().get(listC.get(0).getMesures().size()-1).getValeur());
        assertEquals(12,cdao.getMesure(capteur.getId(), d3)[0]);
    }


    @Test
    public void lastMesureCapteur() {
        LocalDateTime d1 = LocalDateTime.now();
        String d = "2021-03-28 12:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime d2 = LocalDateTime.parse(d, formatter);
        Piece salle = new Piece("salle1");
        TypeCapteur tCapteur = new TypeCapteur("Type1","unite1",10,0);
        Capteur capteur = new Capteur("capteur1", 120, salle, tCapteur);
        Mesure m1 = new Mesure(d1,45,capteur);
        Mesure m2 = new Mesure(d2,12,capteur);
        pdao.save(salle);
        tdao.save(tCapteur);
        cdao.save(capteur);
        mdao.save(m1);
        mdao.save(m2);
        int lastMesure = cdao.getLastMesure(capteur.getId(),LocalDateTime.now());
        assertEquals(lastMesure,m1.getValeur());
    }
}
