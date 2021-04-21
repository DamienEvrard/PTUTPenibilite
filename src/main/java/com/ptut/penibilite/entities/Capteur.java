package com.ptut.penibilite.entities;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Capteur {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull @Column(nullable = false)
    private String libelle;

    @NonNull @Column(nullable = false)
    private int frequenceMesure;

    @ToString.Exclude
    @ManyToOne @NonNull
    private Piece salle;

    @ToString.Exclude
    @ManyToOne @NonNull
    private TypeCapteur type;

    @ToString.Exclude
    @OneToMany(mappedBy="capteur")
    List<Mesure> mesures;

    /**
     *
     * @return la valeur de la derniere mesure
     */
    public Float getLastMesure(){
        if(this.getMesures().size() > 0) {
            return this.getMesures().get(this.getMesures().size() - 1).getValeur();
        }
        return new Float(0);
    }
    
}
