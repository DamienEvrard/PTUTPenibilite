
package com.ptut.penibilite.daos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ptut.penibilite.entities.TypeCapteur;
import org.springframework.data.jpa.repository.Query;

public interface TypeCapteurRepository extends JpaRepository<TypeCapteur, Integer>{

    /**
     *
     * @param id id de la piece
     * @param type type de capteur
     * @return le nombre de mesures depassant les seuils
     */
    @Query(value = "SELECT count(M.valeur) from Mesure M, Capteur C, Type_Capteur T where C.id = M.capteur_id and C.salle_id = :id and C.type_id = (Select id from Type_Capteur where Libelle = :type) and (M.valeur >= T.seuilMax or M.valeur <= T.seuilMin);", nativeQuery = true)
    int depacementSeuil(int id, String type);
}
