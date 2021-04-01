
package daos;
import org.springframework.data.jpa.repository.JpaRepository;


public class PieceRepository extends JpaRepository<Piece, Integer>{
    
    /**
     * 
     * @return le tableau des valeurs relevées de la piece 
     */
    @Query("SELECT M.value from Mesure M, Capteur C where C.id = M.capteur.id")
    Float[] getPenibility();
}
