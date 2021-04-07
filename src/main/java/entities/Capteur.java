package entities;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Capteur {
    private int id;
    private String libelle;
    private int frequenceMeusure;
    
    @ManyToOne
    private Piece salle;
    
    @ManyToOne
    private TypeCapteur type;
    
    @OneToMany(mappedBy="capteur")
    List<Mesure> mesures;
    
}
