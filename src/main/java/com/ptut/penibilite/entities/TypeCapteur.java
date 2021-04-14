package com.ptut.penibilite.entities;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class TypeCapteur {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    private float limiteMax;
    private float limiteMin;
    
    @OneToMany(mappedBy="type")
    private List<Capteur> capteurs;
}
