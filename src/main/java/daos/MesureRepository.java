
package daos;
import org.springframework.data.jpa.repository.JpaRepository;
import entities.Mesure;

public interface MesureRepository extends JpaRepository<Mesure, Integer>{
    
}
