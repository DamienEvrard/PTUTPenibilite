package com.ptut.penibilite.entities;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Capteur {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) @NonNull
    private int id;
    @NonNull
    private String libelle;
    @NonNull
    private int frequenceMesure;
    
    @ManyToOne @NonNull
    private Piece salle;
    
    @ManyToOne @NonNull
    private TypeCapteur type;
    
    @OneToMany(mappedBy="capteur")
    List<Mesure> mesures;
    
}
