package com.ptut.penibilite.entities;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Capteur {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String libelle;
    @NonNull
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
    
}
