package com.ptut.penibilite.entities;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Piece {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull @Column(unique = true, nullable = false)
    private String libelle;
    
    @OneToMany(mappedBy="salle")
    private List<Capteur> capteurs;
}
