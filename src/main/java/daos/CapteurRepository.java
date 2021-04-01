
package daos;
import entities.Capteur;
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
    
    /**
     * 
     * @param id id de la piece
     * @return le tableau des valeurs relevées de la piece 
     */
    @Query("SELECT M.value from Mesure M, Capteur C, Piece P where C.id = M.capteur.id and P:id = : id")
    Float[] getPenibility(int id);
    
    /**
     * 
     * @param id id de la piece
     * @param date id de la piece
     * @return le tableau des valeurs relevées de la piece 
     */
    @Query("SELECT M.value from Mesure M, Capteur C, Piece P where C.id = M.capteur.id and P:id = :id and M.date = :date")
    Float[] getPenibility(int id, Date date);
}
