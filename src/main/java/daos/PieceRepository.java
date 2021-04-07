
package daos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import entities.Piece;
import entities.Mesure;
import java.util.Date;


public interface PieceRepository extends JpaRepository<Piece, Integer>{
    
    /**
     * 
     * @return le tableau des valeurs relevées de la piece 
     */
    @Query("SELECT M.value from Mesure M, Capteur C where C.id = M.capteur.id")
    Float[] getPenibility();
    
    /**
     * 
     * @param id id de la piece
     * @return le tableau des mesures relevées de la piece 
     */
    @Query("SELECT M.id, M.date, M.value from Mesure M, Capteur C, Piece P where C.id = M.capteur.id and P:id = : id")
    Mesure[] getPenibility(int id);
    
    /**
     * 
     * @param id id de la piece
     * @param date la date de debut de relevé 
     * @return le tableau des mesures relevées de la piece 
     */
    @Query("SELECT M.id, M.date, M.value from Mesure M, Capteur C, Piece P where C.id = M.capteur.id and P:id = :id and M.date = :date")
    Mesure[] getPenibility(int id, Date date);
}
