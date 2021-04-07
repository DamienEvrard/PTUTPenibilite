
package daos;
import entities.Capteur;
import entities.Mesure;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CapteurRepository extends JpaRepository<Capteur, Integer>{
    
    /**
     * 
     * @return le tableau des mesures relevées
     */
    @Query("SELECT M.id, M.date, M.value from Mesure M, Capteur C where C.id = M.capteur.id")
    Mesure[] getMesure();
    
    /**
     * @param date la date de debut de relevé
     * @return le tableau des mesures relevées à partir de la date 
     */
    @Query("SELECT M.id, M.date, M.value from Mesure M, Capteur C where C.id = M.capteur.id and M.date = :date")
    Mesure[] getMesure(Date date);
    
    
}
