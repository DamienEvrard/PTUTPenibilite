package com.ptut.penibilite.entities;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Mesure {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull @Column(nullable = false)
    private LocalDateTime date;
    
    @NonNull @Column(nullable = false)
    private float valeur;
    
    @ManyToOne @NonNull @Column(nullable = false)
    Capteur capteur;
}
