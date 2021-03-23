
package daos;
import org.springframework.data.jpa.repository.JpaRepository;


public class CapteurRepository extends JpaRepository<Capteur, Integer>{
    
    /**
     * 
     * @return le tableau des valeurs relevées
     */
    @Query("SELECT M.value from Mesure M, Capteur C where C.id = M.capteur.id")
    Float[] getMesure();
    
    /**
     * @param date la date de debut de relevé
     * @return le tableau des valeurs relevées à partir de la date 
     */
    @Query("SELECT M.value from Mesure M, Capteur C where C.id = M.capteur.id and M.date = :date")
    Float[] getMesure(Date date);
}
