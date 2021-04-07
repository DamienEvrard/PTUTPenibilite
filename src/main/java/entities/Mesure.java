package entities;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Mesure {
    private int id;
    private Date date;
    private float valeur;
    
    @ManyToOne
    Capteur capteur;
}
