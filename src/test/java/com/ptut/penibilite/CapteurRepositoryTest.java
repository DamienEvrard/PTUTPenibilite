package com.ptut.penibilite;

import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Piece;
import com.ptut.penibilite.entities.TypeCapteur;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Log4j2
@DataJpaTest
public class CapteurRepositoryTest {

    @Autowired
    private CapteurRepository cdao;


    @Test
    public void testCapteur(){
        Piece salle = new Piece(1,"salle1");
        TypeCapteur TCapteur= new TypeCapteur(1,"Type1");
        Capteur capteur = new Capteur(1,"capteur1",120,salle,TCapteur);
    }


}
