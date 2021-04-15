
package com.ptut.penibilite.daos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ptut.penibilite.entities.Piece;
import com.ptut.penibilite.entities.Mesure;
import java.time.LocalDateTime;


public interface PieceRepository extends JpaRepository<Piece, Integer>{
    
    /**
     * 
     * @param id id de la piece
     * @return le tableau des mesures relevées de la piece 
     */
    @Query(value = "SELECT M.id, M.date, M.valeur from Mesure M, Capteur C where C.id = M.capteur_id and C.salle_id = :id", nativeQuery = true)
    Mesure[] getPenibility(int id);
    
    /**
     * 
     * @param id id de la piece
     * @param date la date de debut de relevé 
     * @return le tableau des mesures relevées de la piece 
     */
    @Query(value = "SELECT M.id, M.date, M.valeur from Mesure M, Capteur C where C.id = M.capteur_id and C.salle_id = :id and and M.date = :date", nativeQuery = true)
    Mesure[] getPenibility(int id, LocalDateTime date);
}
