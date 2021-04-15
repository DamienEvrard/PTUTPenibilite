package com.ptut.penibilite;

import com.ptut.penibilite.daos.PieceRepository;
import com.ptut.penibilite.entities.Piece;
import lombok.extern.log4j.Log4j2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.test.context.jdbc.Sql;

@Log4j2 
public class PieceRepositoryTest {
    
    /*private PieceRepository pieceDAO;*/

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    public void lesPiecesSontToutesDifferentes() {
         
        log.info("On compte le nombre de pièce dans la table 'Piece' ");
        /* à implémenter:TODO
        
        assertEquals(nbreDePieceDansJeuDeTest, nbrP, "On doit trouver 3 pieces");*/
}
}
