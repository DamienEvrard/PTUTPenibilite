package entities;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Mesure {
    private int id;
    private Date date;
    private float valeur;
    
    @ManyToOne
    Capteur capteur;

    public Mesure(int id, Date date, float valeur) {
        this.id=id;
        this.date=date;
        this.valeur=valeur;
    }
}
