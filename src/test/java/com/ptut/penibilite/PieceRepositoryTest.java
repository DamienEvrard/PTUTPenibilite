package com.ptut.penibilite;

import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.daos.MesureRepository;
import com.ptut.penibilite.daos.PieceRepository;
import lombok.extern.log4j.Log4j2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
@DataJpaTest
public class PieceRepositoryTest {

    @Autowired
    private PieceRepository pdao;

    @Autowired
    private CapteurRepository cdao;

    @Autowired
    private MesureRepository mdao;


    @Test
    public void getPenibilite() {

        String type = "Température";
        int[] listMesures = pdao.getPenibility(1,type);
        assertEquals(18, listMesures[0]);
    }

    @Test
    public void getPenibiliteDate() {

        String type = "Température";
        String d = "2021-03-27 10:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime d1 = LocalDateTime.parse(d, formatter);

        int[] listMesures = pdao.getPenibility(1,d1,type);
        assertEquals(20, listMesures[0]);
    }


}
