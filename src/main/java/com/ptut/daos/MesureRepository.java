
package com.ptut.daos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ptut.entities.Mesure;

public interface MesureRepository extends JpaRepository<Mesure, Integer>{
    
}
