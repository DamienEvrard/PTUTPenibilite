
package com.ptut.penibilite.daos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ptut.penibilite.entities.Mesure;

public interface MesureRepository extends JpaRepository<Mesure, Integer>{
    
}
