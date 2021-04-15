package Repository_Tests;

import com.ptut.penibilite.daos.CapteurRepository;
import com.ptut.penibilite.entities.Capteur;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
@DataJpaTest
public class CapteurRepositoryTest {

    @Autowired
    private CapteurRepository cdao;

    @Test
    public void unCapteur() {
        Capteur capteur =new Capteur();

    }

    @Test
    public void trouverUnCapteur() {
        Optional<Capteur> capteur = cdao.findById(1);
        assertEquals(1, capteur.get().getId());
    }

}
