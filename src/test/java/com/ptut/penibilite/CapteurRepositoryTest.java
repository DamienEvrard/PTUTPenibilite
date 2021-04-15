package com.ptut.penibilite;

import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Piece;
import com.ptut.penibilite.entities.TypeCapteur;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@DataJpaTest
public class CapteurRepositoryTest {

    @Autowired
    private CapteurRepository cdao;


    @Test
    public void testCapteurDansPieceEtType(){
        Piece salle = new Piece("salle1");
        TypeCapteur tCapteur= new TypeCapteur("Type1");
        Capteur capteur = new Capteur("capteur1",120,salle,tCapteur);
        assertEquals(salle.getId(),capteur.getSalle().getId());
        assertEquals(tCapteur.getId(),capteur.getType().getId());
    }


}
