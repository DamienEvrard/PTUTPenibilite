
package com.ptut.penibilite.daos;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Mesure;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CapteurRepository extends JpaRepository<Capteur, Integer>{
    

    /**
     * @param id l'id du capteur
     * @param date la date de debut de relevé
     * @return le tableau des mesures relevées à partir de la date 
     */
    @Query("SELECT M.id, M.date, M.valeur from Mesure M where M.capteur.id = :id and M.date = :date")
    Mesure[] getMesure(int id, LocalDateTime date);

    /**
     * @param id l'id du capteur
     * @param date la date d'aujourd'hui
     * @return la derniere mesure relevée
     */
    @Query(value = "SELECT M.valeur from Mesure M where M.capteur_id = :id and M.date = (Select Max(Me.date) FROM Mesure Me WHERE Me.date< :date )", nativeQuery = true)
    int getLastMesure(int id, LocalDateTime date);
}
