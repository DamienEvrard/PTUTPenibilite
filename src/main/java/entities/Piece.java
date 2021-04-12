package entities;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Piece {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    
    @OneToMany(mappedBy="salle")
    private List<Capteur> Capteurs;
}
