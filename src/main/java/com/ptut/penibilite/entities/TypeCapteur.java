package com.ptut.penibilite.entities;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class TypeCapteur {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull @Column(unique = true, nullable = false)
    private String libelle;

    private float limiteMax;

    private float limiteMin;

    @NonNull @Column(nullable = false)
    private String unite;

    @NonNull @Column(nullable = false)
    private float seuilMax;

    @NonNull @Column(nullable = false)
    private float seuilMin;

    @ToString.Exclude
    @OneToMany(mappedBy="type")
    private List<Capteur> capteurs;
}
