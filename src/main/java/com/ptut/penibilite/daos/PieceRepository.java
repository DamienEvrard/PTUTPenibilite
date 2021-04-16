
package com.ptut.penibilite.daos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ptut.penibilite.entities.Piece;
import com.ptut.penibilite.entities.Mesure;
import java.time.LocalDateTime;
import java.util.List;


public interface PieceRepository extends JpaRepository<Piece, Integer>{
    
    /**
     * 
     * @param id id de la piece
     * @param type type de capteur
     * @return le tableau des mesures relevées de la piece 
     */
    @Query(value = "SELECT M.valeur from Mesure M, Capteur C where C.id = M.capteur_id and C.salle_id = :id and C.type_id = (Select id from Type_Capteur where Libelle = :type);", nativeQuery = true)
    int[] getPenibility(int id, String type);

    /**
     * 
     * @param id id de la piece
     * @param date la date de debut de relevé
     * @param type type de capteur
     * @return le tableau des mesures relevées de la piece 
     */
    @Query(value = "SELECT M.valeur from Mesure M, Capteur C where C.id = M.capteur_id and C.salle_id = :id and M.date >= (Select Max(Me.date) FROM Mesure Me WHERE Me.date <= :date ) and C.type_id = (Select id from Type_Capteur where Libelle = :type)", nativeQuery = true)
    int[] getPenibility(int id, LocalDateTime date, String type);
}
