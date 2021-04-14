
package com.ptut.penibilite.daos;
import com.ptut.penibilite.entities.Capteur;
import com.ptut.penibilite.entities.Mesure;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CapteurRepository extends JpaRepository<Capteur, Integer>{
    
    /**
     * @param id l'id du capteur
     * @return le tableau des mesures relevées
     */
//    @Query("SELECT M.id, M.date, M.value from Mesure M, Capteur C where M.capteur.id = :id")
//    Mesure[] getMesure(int id);
    
    /**
     * @param id l'id du capteur
     * @param date la date de debut de relevé
     * @return le tableau des mesures relevées à partir de la date 
     */
//    @Query("SELECT M.id, M.date, M.value from Mesure M, Capteur C where M.capteur.id = :id and M.date = :date")
//    Mesure[] getMesure(int id, Date date);
    
    
}
